package database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by PolinaDomnina on 24.04.2016.
 */
public class main {


   public static void main(String []args) throws SQLException, ClassNotFoundException, IOException {
        DatabaseHelper db=new DatabaseHelper();
        db.connectDatabase();
        ArrayList<String> list = db.getAllAuthor();
        for (String str:list)
            System.out.println(str);

        db.getAllBook();
        db.closeDatabase();
    }
}
