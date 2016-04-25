package database;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;

public class DatabaseHelper {

    private static Connection connection = null;
    private static ResultSet resultSet = null;
    private static Statement statement = null;

    public void ConnectDatabase() throws SQLException, ClassNotFoundException {
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

    public void CloseDatabase() throws SQLException {
        connection.close();
    }

    public void InsertBook(int id,String name,int pages,int price, String language, int id_author) throws SQLException {
        String sql="insert into book (id_book, name,pages,price,language,id_author) " +
                "values (" + id + ", '" + name + "', " + pages + ", " + price + ", '" + language + "', " + id_author + ") ";

        statement.execute(sql);
    }
    public void InsertAuthor(int id,String name) throws SQLException {
        String sql="insert into author (id_author, name_author) " +
                "values (" + id + ", '" + name + "') ";
        statement.execute(sql);
    }
    public void DeleteBook() {}
    public void DeleteAuthor(){}
    public void UpdateBook(){}
    public void UpdateAuthor(){}

    public void makeAllRequestBook() throws SQLException {
        String sql="select b.id_book,b.name,b.pages,b.price,b.language,a.name_author from book b, author a where a.id_author=b.id_author";
        resultSet=statement.executeQuery(sql);
        //String str=resultSetToJSON(resultSet);
        System.out.println("Список книг:");
        while(resultSet.next()){
            int id=resultSet.getInt("id_book");
            String name=resultSet.getString("name");
            int pages=resultSet.getInt("pages");
            int price=resultSet.getInt("price");
            String language=resultSet.getString("language");
            String author=resultSet.getString("name_author");
            System.out.println(id+" "+name+" "+pages+" "+price+" "+language+" "+author);
        }



    }
    public void makeAllRequestAuthor() throws SQLException {
        String sql="select * from author";
        resultSet=statement.executeQuery(sql);
        System.out.println("Список авторов:");
        while(resultSet.next()){
            int id=resultSet.getInt("id_author");
            String name=resultSet.getString("name_author");
            System.out.println(id+" "+name);
        }
    }



    public String resultSetToJSON(ResultSet rs) throws java.sql.SQLException {
        String response = "{ \"colNames\":[";
        response = response + '"' + rs.getMetaData().getColumnLabel(1) + '"';
        for (int i=2; i<=rs.getMetaData().getColumnCount(); i++){
            response = response + ',' + '"' + rs.getMetaData().getColumnLabel(i) + '"';
        }

        if (rs.next()) {
            response += "], \"dataArray\":[";


            response += "[" + '"' + rs.getString(1) + '"';
            for (int i = 2; i <= rs.getMetaData().getColumnCount(); i++) {
                response += ", " + '"' + rs.getString(i) + '"';
            }
            response += "]";
            while (rs.next()) {
                response += ",[" + '"' + rs.getString(1) + '"';
                for (int i = 2; i <= rs.getMetaData().getColumnCount(); i++) {
                    response += ", " + '"' + rs.getString(i) + '"';
                }
                response += "]";
            }
        }
        response += "]}";

        return response;
    }


}
