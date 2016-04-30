package database;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;

public class DatabaseHelper {

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
    }

    public void closeDatabase() throws SQLException {
        connection.close();
    }

    public void insertBook(String name,int pages,int price, String language, int id_author) throws SQLException {
        String sql="insert into book (id, name,pages,price,language,id_author) " +
                "values (book_seq.nextval, '" + name + "', " + pages + ", " + price + ", '" + language + "', " + id_author + ") ";

        statement.execute(sql);
    }
    public void insertAuthor(String name) throws SQLException {
        String sql="insert into author (id, name) " +
                "values (author_seq.nextval, '" + name + "') ";
        statement.execute(sql);
    }
    public void deleteBook() {}
    public void deleteAuthor(){}
    public void UpdateBook(){}
    public void UpdateAuthor(){}

    public void getAllBook() throws SQLException {
        String sql="select b.id,b.name,b.pages,b.price,b.language,a.name as author_name from book b, author a where a.id=b.id_author";
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
    public void getAllAuthor() throws SQLException {
        String sql="select * from author";
        resultSet=statement.executeQuery(sql);
        System.out.println("Список авторов:");
        while(resultSet.next()){
            int id=resultSet.getInt("id");
            String name=resultSet.getString("name");
            System.out.println(id+" "+name);
        }
    }





}
