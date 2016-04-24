package database;

import java.sql.SQLException;

/**
 * Created by PolinaDomnina on 24.04.2016.
 */
public class main {
    public static void main(String []args) throws SQLException, ClassNotFoundException {
        DatabaseHelper db=new DatabaseHelper();
        db.ConnectDatabase();
        db.makeAllRequestAuthor();
        db.makeAllRequestBook();
        db.CloseDatabase();

    }
}
