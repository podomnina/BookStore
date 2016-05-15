BookStore
===================
This is a web application online book store.

Run
-------------

You should configure the Oracle database at first. Open DatabaseManagement.java and find function connectDatabase(). Input your login and password here:

>  ds.setUser("your login");
>  ds.setPassword("your password");

Then run script Tables.sql (way to this file: BookStore/tree/master/src/main). All tables (Author, Book, Customer and Booking) and some records will be created in your database. Configurate your Apache Tomcat server, build project and start server. Now you can input http://localhost:8080/main in your browser and see working program.

Description
-------------------
There are four classes for comfortable work with database records, there are nine servlets and jsp-pages for data processing and user iteraction. 

Capabilities
------------------
All servlets have the same structure:
1. init()
2. doGet()
3. doPost()
4. destroy()
Database is initialized in init() function and is closed in destroy() function.
1. Administation
Administrator watch content of Author and Book tables. He can insert, delete or update records and watch booking list.
2. AuthorInfo
User watch information about selected author (there is list of author`s books in table)
3. BookInfo
User watch information about selected book (name,author,pages,price,language)
4. MainPage
The main page, which user sees at first
5. Order
User inputs his name and email, when he buying book
6. OrderInfo
User watch information about his order
7. OrderList
All information about orders outputs here
8. Search
User input name of author or book, which he wants to find
9. SearchResult
The result of searching some book or author
