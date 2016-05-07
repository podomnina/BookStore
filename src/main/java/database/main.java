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
        MakeFileURL m=new MakeFileURL();
       m.getURL("C:\\JetBrainsUltimateProj\\Server\\images\\1.jpg");
    }
}
