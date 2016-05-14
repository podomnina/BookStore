package servlets;


import database.DatabaseManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/admin")
public class Administration extends HttpServlet{
    DatabaseManagement db;
    private static final Logger log= LoggerFactory.getLogger(DatabaseManagement.class);

    @Override
    public void init() {
        log.info("Administration servlet initialization...");
        db = new DatabaseManagement();
        try {
            db.connectDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        log.info("Administration servlet was initialized!");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("GET method");
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            request.setAttribute("authors", db.getAllAuthor());
            request.setAttribute("books", db.getAllBook(null,null,0));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/administration.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("POST method");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        log.info("REQUEST: Button=" + request.getParameter("button") + " book_id=" + request.getParameter("book_id") + " book_name=" +
        request.getParameter("book_name") + " pages=" + request.getParameter("pages") + " price=" + request.getParameter("price") +
        " language=" + request.getParameter("language") + " author_id=" + request.getParameter("author_id") +
        " author_name=" + request.getParameter("author_name"));
        PrintWriter pw = response.getWriter();
        try {
            if (request.getParameter("button").equals("createAuthor")){
                db.insertAuthor(request.getParameter("author_name"));
                pw.println("Добавлен новый автор:" + request.getParameter("author_name"));
            }else
            if (request.getParameter("button").equals("removeAuthor")){
                boolean delete=db.deleteAuthor(Integer.parseInt(request.getParameter("author_id")));
                if (delete)
                    pw.println("Автор с id:" + request.getParameter("author_id") + "удален");
                else pw.println("Автора невозможно удалить, так как существует книга, связанная с ним");
            }else
            if (request.getParameter("button").equals("updateAuthor")){
                db.updateAuthor(Integer.parseInt(request.getParameter("author_id")),request.getParameter("author_name"));
                pw.println("Автор с id:" + request.getParameter("author_id") + "обновлен");
            }else
            if (request.getParameter("button").equals("createBook")){
                db.insertBook(request.getParameter("book_name"),Integer.parseInt(request.getParameter("pages")),
                        Integer.parseInt(request.getParameter("price")),request.getParameter("language"),
                        Integer.parseInt(request.getParameter("author_id")));
                pw.println("Добавлена новая книга:"+request.getParameter("book_name"));
            }else
            if (request.getParameter("button").equals("removeBook")){
                db.deleteBook(Integer.parseInt(request.getParameter("book_id")));
                pw.println("Книга с id:"+request.getParameter("book_id")+" удалена");
            }else
            if (request.getParameter("button").equals("updateBook")){
                int id=Integer.parseInt(request.getParameter("book_id"));
                int pages;
                try{
                    pages = Integer.parseInt(request.getParameter("pages"));
                }catch (NumberFormatException e){
                    pages=0;
                }
                int price;
                try {
                    price=Integer.parseInt(request.getParameter("price"));
                }catch (NumberFormatException e){
                    price=0;
                }
                int author_id;
                try{
                    author_id=Integer.parseInt(request.getParameter("author_id"));
                }catch (NumberFormatException e){
                    author_id=0;
                }
                db.updateBook(id,request.getParameter("book_name"),pages,price,request.getParameter("language"),author_id);
                pw.println("Книга с id:"+request.getParameter("book_id")+" обновлена");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e){
            pw.println("Ошибка ввода. Должно быть введено целое число");
        }
    }

    @Override
    public void destroy(){
        log.info("Administration servlet destroying...");
        try {
            db.closeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("Administration servlet was destroyed!");
    }
}

