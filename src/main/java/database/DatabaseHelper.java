package database;

import java.sql.*;

public class DatabaseHelper {

    private static Connection connection = null;
    private static ResultSet resultSet = null;
    private static Statement statement = null;

    public void ConnectDatabase() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:dssora01","unc16_dopo", "unc16_dopo");
    }

    public void CloseDatabase() throws SQLException {
        connection.close();
    }

    public void InsertBook(int id,String name,int pages,int price, String language, int id_author) throws SQLException {
        String sql="insert into 'book' ('id_book', 'name','pages','price','language','id_author') " +
                "values ('" + id + "', '" + name + "', '" + pages + "', '" + price + "', '" + language + "', '" + id_author + "'); ";

        statement.execute(sql);
    }
    public void InsertAuthor(int id,String name) throws SQLException {
        String sql="insert into 'author' ('id_author', 'name') " +
                "values ('" + id + "', '" + name + "'); ";
        statement.execute(sql);
    }
    public void DeleteBook() {}
    public void DeleteAuthor(){}
    public void UpdateBook(){}
    public void UpdateAuthor(){}

    public void makeAllRequestBook() throws SQLException {
        String sql="select * from book;";
        resultSet=statement.executeQuery(sql);
    }
    public void makeAllRequestAuthor() throws SQLException {
        String sql="select * from author;";
        resultSet=statement.executeQuery(sql);
    }

}
