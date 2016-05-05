package database;

import database.entities.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oracle.jdbc.pool.OracleDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseHelper {

    private static final Logger log=LoggerFactory.getLogger(DatabaseHelper.class);
    private static Connection connection = null;
    private static ResultSet resultSet = null;
    private static Statement statement = null;

    public void connectDatabase() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        OracleDataSource ds = new OracleDataSource ( );
        ds.setDriverType("thin");
        ds.setServerName("localhost");
        ds.setPortNumber(1521);
        ds.setDatabaseName("XE");
        ds.setUser("polina");
        ds.setPassword("polina");
        connection = ds.getConnection( );
        statement = connection.createStatement();
        log.info("Database connected");
    }

    public void closeDatabase() throws SQLException {
        connection.close();
        log.info("Database closed");
    }

    public void insertBook(String name,int pages,int price, String language, int id_author) throws SQLException {
        String sql="insert into book (id, name,pages,price,language,id_author) " +
                "values (book_seq.nextval, '" + name + "', " + pages + ", " + price + ", '" + language + "', " + id_author + ") ";
        statement.execute(sql);
        log.info("New book record was inserted");
    }

    public void insertAuthor(String name) throws SQLException {
        String sql="insert into author (id, name) " +
                "values (author_seq.nextval, '" + name + "') ";
        statement.execute(sql);
        log.info("New author record was inserted");
    }

    public void deleteBook(int id) throws SQLException {
        String sql="delete from book where id="+id;
        statement.execute(sql);
        log.info("Record number " + id + " was deleted");
    }

    public void deleteAuthor(int id) throws SQLException {
        String sql="delete from author where id="+id;
        try{
            statement.execute(sql);
            log.info("Record number " + id + " was deleted");
        }catch (SQLException e){
            log.info("There are books, which connect to this author. Please, delete book at first.");
        }
    }

    public void updateBook(int id, String name,int pages,int price,String language, int id_author) throws SQLException {
        StringBuilder sql=new StringBuilder("update book set ");
        if (name!=null)
            sql.append("name='"+name+"',");
        if (pages!=0)
            sql.append("pages="+pages+",");
        if (price!=0)
            sql.append("price="+price+",");
        if (language!=null)
            sql.append("language='"+language+"',");
        if (id_author!=0)
            sql.append("id_author="+id_author);
        if (sql.charAt(sql.length()-1)==',')     //delete last ','
            sql.deleteCharAt(sql.length()-1);
        sql.append(" where id="+id);
        statement.execute(sql.toString());
        log.info("Record number " + id + " was updated");
    }

    public void updateAuthor(int id,String name) throws SQLException {
        if (name!=null){
            String sql="update author set name='"+name+"' where id="+id;
            statement.execute(sql);
            log.info("Record number "+ id +" was updated");
        }
    }

    public void getAllBook() throws SQLException {
        String sql="select b.id,b.name,b.pages,b.price,b.language,a.name as author_name from book b, author a where a.id=b.id_author order by b.id";
        resultSet=statement.executeQuery(sql);
        System.out.println("Список книг:");
        while(resultSet.next()){
            int id=resultSet.getInt("id");
            String name=resultSet.getString("name");
            int pages=resultSet.getInt("pages");
            int price=resultSet.getInt("price");
            String language=resultSet.getString("language");
            String author=resultSet.getString("author_name");
            System.out.println(id+" "+name+" "+pages+" "+price+" "+language+" "+author);
        }
    }

    public ArrayList<String> getAllAuthor() throws SQLException, IOException {
        String sql="select * from author order by id";
        resultSet=statement.executeQuery(sql);
        System.out.println("Список авторов:");
        ArrayList<String> authorList = new ArrayList<String>();
        while(resultSet.next()){
            int id=resultSet.getInt("id");
            String name=resultSet.getString("name");
            Author author = new Author(id,name);
            Converter converter=new Converter();
            String str=converter.authorToJSON(author);
            authorList.add(str);
            System.out.println(id+" "+name);
        }
        return authorList;
    }





}
