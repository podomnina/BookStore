BookStore
===================
This is a web application online book store.

Run
-------------

You should connect to Oracle database at first. Input your login and password in function connectDatabase():


>  ds.setUser("your login");
>  ds.setPassword("your password");

Then run script Tables.sql (way to this file: BookStore/tree/master/src/main). All tables (Author and Book) and some records will be created in your database.

Capabilities
-------------------
You can insert, delete, update and output records. You can find all these functions in DatabaseHelper class.

Use function insertBook(...) or insertAuthor(...) to insert record and list parameters, that you want to put.
>db.insertAuthor("Agatha Christie");
>db.insertBook("The Clocks", 288, 137 ,"english" ,6 );

Use function deleteBook() or deleteAuthor() to delete record  and write in parameter id of record, that you want to delete.

>db.deleteAuthor(6);
>db.deleteBook(4);

Use function updateBook(...) or updateAuthor(...) to update record and list parameters, that you want to update. If you don`t want to update some arguments, write null (String) or 0 (int).

>db.updateAuthor("Agatha Christie");
>db.updateBook(5, "The Clocks", 250 ,0 ,null, 0);

Use fuction getAllBook() or getAllAuthor() to output records .

>db.getAllBook();
>db.getAllAuthor();