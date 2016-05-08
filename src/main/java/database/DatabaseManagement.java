package database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.entities.Author;
import database.entities.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oracle.jdbc.pool.OracleDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManagement {

    private static final Logger log=LoggerFactory.getLogger(DatabaseManagement.class);
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

    public void insertCustomer(String name, String email) throws SQLException{
        String sql="insert into customer (id, name, email) values (customer_seq.nextval, '"+name+"','"+email+"')";
        statement.execute(sql);
        log.info("New customer record was inserted");
    }

    public void insertBooking(String name, String email, int book_id) throws SQLException{
        String sql="";
        log.info("New booking record was inserted");
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

    public List<Book> getAllBook(String bookReq, String authorReq, int value) throws SQLException {
        String sql;
        if (value!=0)
            sql="select b.id,b.name,b.pages,b.price,b.language,a.id as author_id,a.name as author_name " +
                    "from book b, author a where a.id=b.id_author and a.id="+value+" order by b.id";
        else
        if (bookReq!=null)
            sql="select b.id,b.NAME,b.PAGES,b.PRICE,b.LANGUAGE,a.id as author_id,a.name as author_name from book b, author a " +
                    "where b.ID_AUTHOR = a.ID and LOWER(b.NAME) LIKE LOWER('%"+bookReq+"%') order by b.id";
        else
        if (authorReq!=null)
            sql="select b.id,b.NAME,b.PAGES,b.PRICE,b.LANGUAGE,a.id as author_id,a.name as author_name from book b, author a " +
                    "where b.ID_AUTHOR = a.ID and b.id_author in " +
                    "(select id from author where LOWER(NAME) LIKE LOWER('%"+authorReq+"%')) order by b.id";
        else
            sql="select b.id,b.name,b.pages,b.price,b.language,a.id as author_id,a.name as author_name " +
                    "from book b, author a where a.id=b.id_author order by b.id";
        resultSet=statement.executeQuery(sql);
        log.info("List of books");
        ArrayList<Book> bookList = new ArrayList<Book>();
        while(resultSet.next()){
            int id=resultSet.getInt("id");
            String name=resultSet.getString("name");
            int pages=resultSet.getInt("pages");
            int price=resultSet.getInt("price");
            String language=resultSet.getString("language");
            int authorId=resultSet.getInt("author_id");
            String authorName=resultSet.getString("author_name");
            Author author=new Author(authorId,authorName);
            Book book = new Book(id,name,pages,price,language,author);
            bookList.add(book);
        }
        return bookList;
    }

    public List<Author> getAllAuthor() throws SQLException, IOException {
        String sql="select * from author order by id";
        resultSet=statement.executeQuery(sql);
        log.info("List of authors");
        ArrayList<Author> authorList = new ArrayList<Author>();
        while(resultSet.next()){
            int id=resultSet.getInt("id");
            String name=resultSet.getString("name");
            Author author = new Author(id,name);
            authorList.add(author);
        }
        return authorList;
    }

    public Book getOneBook(int value, String bookName) throws SQLException, IOException {
        String sql=null;
        if (value!=0)
            sql="select b.id,b.name,b.pages,b.price,b.language,a.id as author_id,a.name as author_name " +
                "from book b, author a where a.id=b.id_author and b.id="+value;
        else if (bookName!=null)
            sql="select b.id,b.NAME,b.PAGES,b.PRICE,b.LANGUAGE,a.id as author_id,a.name as author_name from book b, author a " +
                    "where b.ID_AUTHOR = a.ID and LOWER(b.NAME) LIKE LOWER('%"+bookName+"%')";
        resultSet=statement.executeQuery(sql);
        log.info("One book record");
        Book book=null;
        while(resultSet.next()){
            int id=resultSet.getInt("id");
            String name=resultSet.getString("name");
            int pages=resultSet.getInt("pages");
            int price=resultSet.getInt("price");
            String language=resultSet.getString("language");
            int authorId=resultSet.getInt("author_id");
            String authorName=resultSet.getString("author_name");
            Author author=new Author(authorId,authorName);
            book = new Book(id,name,pages,price,language,author);
        }
        return book;
    }

    public Author getOneAuthor(int value) throws SQLException, IOException {
        String sql="select * from author where id="+value;
        resultSet=statement.executeQuery(sql);
        log.info("One author record");
        Author author=null;
        while(resultSet.next()){
            int id=resultSet.getInt("id");
            String name=resultSet.getString("name");
            author = new Author(id,name);
        }
        return author;
    }

}
