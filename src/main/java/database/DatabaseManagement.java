package database;

import database.entities.*;
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
        insertCustomer(name,email);
        int customer_id=getCustomerId(name,email);
        String sql="insert into booking (id,id_customer,id_book) values (booking_seq.nextval, "+customer_id+","+book_id+")";
        statement.execute(sql);
        log.info("New booking record was inserted");
    }

    public void deleteBook(int id) throws SQLException {
        String sql="delete from book where id="+id;
        statement.execute(sql);
        log.info("Record number " + id + " was deleted");
    }

    public boolean deleteAuthor(int id) throws SQLException {
        if (!checkAuthorDependencies(id)) {
            String sql = "delete from author where id=" + id;
            try {
                statement.execute(sql);
                log.info("Record number " + id + " was deleted");
                return true;
            } catch (SQLException e) {
                log.info("There are books, which connect to this author. Please, delete book at first.");
            }
        }
        return false;
    }

    public void updateBook(int id, String name,int pages,int price,String language, int id_author) throws SQLException {
        StringBuilder sql=new StringBuilder("update book set ");
        if (!name.equals(""))
            sql.append("name='"+name+"',");
        if (pages!=0)
            sql.append("pages="+pages+",");
        if (price!=0)
            sql.append("price="+price+",");
        if (!language.equals(""))
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
        if (!name.equals("")){
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

    public int getCustomerId(String name,String email) throws SQLException {
        String sql="select id from customer where name='"+name+"' and email='"+email+"'";
        resultSet=statement.executeQuery(sql);
        int id=0;
        while(resultSet.next()){
            id=resultSet.getInt("id");
        }
        return id;
    }

    public int getBookingId(String name,String email,int book_id) throws SQLException {
        int customer_id=getCustomerId(name,email);
        String sql="select id from booking where id_customer="+customer_id+" and id_book="+book_id;
        resultSet=statement.executeQuery(sql);
        int id=0;
        while (resultSet.next()){
            id=resultSet.getInt("id");
        }
        return id;
    }

    public List<Booking> getAllBooking(int val) throws SQLException {
        StringBuilder sql= new StringBuilder("select o.ID,o.ID_CUSTOMER,c.name as customer,c.email, o.id_book,b.name, b.pages,b.price,b.language,b.id_author,a.name as author " +
                "from booking o,book b, customer c, author a where o.id_customer=c.id and b.id=o.id_book and b.id_author=a.id");
        if (val!=0) {
            sql.append(" and o.id=" + val);
        }
        resultSet=statement.executeQuery(sql.toString());
        ArrayList<Booking> list = new ArrayList<>();
        while (resultSet.next()){
            int id=resultSet.getInt("id");
            int idCustomer=resultSet.getInt("id_customer");
            String customerName=resultSet.getString("customer");
            String email=resultSet.getString("email");
            int idBook=resultSet.getInt("id_book");
            String bookName=resultSet.getString("name");
            int pages=resultSet.getInt("pages");
            int price=resultSet.getInt("price");
            String language=resultSet.getString("language");
            int idAuthor=resultSet.getInt("id_author");
            String authorName=resultSet.getString("author");
            Customer customer=new Customer(idCustomer,customerName,email);
            Author author=new Author(idAuthor,authorName);
            Book book=new Book(idBook,bookName,pages,price,language,author);
            Booking booking=new Booking(id,book,customer);
            list.add(booking);
        }
        return list;
    }

    public boolean checkAuthorDependencies(int id) throws SQLException {
        String sql="select a.id as id from book b,author a where a.id=b.id_author";
        resultSet=statement.executeQuery(sql);
        List<Integer> list = new ArrayList<Integer>();
        while(resultSet.next()){
            int value=resultSet.getInt("id");
            list.add(value);
        }
        for (Integer val:list){
            if (val.equals(id))
                return true;
        }
        return false;
    }
}
