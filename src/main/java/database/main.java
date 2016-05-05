package database;

import database.entities.Author;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PolinaDomnina on 24.04.2016.
 */
public class main {


   public static void main(String []args) throws SQLException, ClassNotFoundException, IOException {
        DatabaseManagement db=new DatabaseManagement();
        db.connectDatabase();
        List<Author> list = db.getAllAuthor();
        for (Author str:list)
            System.out.println(str);

        db.getAllBook();
        db.closeDatabase();
    }
}
