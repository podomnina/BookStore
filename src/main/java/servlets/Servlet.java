package servlets;


import database.DatabaseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/")
public class Servlet extends HttpServlet{
    DatabaseHelper db;
    private static final Logger log= LoggerFactory.getLogger(DatabaseHelper.class);
    String str=null;

    @Override
    public void init() {
        log.info("Servlet initialization...");
        db = new DatabaseHelper();
        try {
            db.connectDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        log.info("Servlet was initialized!");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("GET method");
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
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
        try {
            if (request.getParameter("author_name")!=null) {
                db.insertAuthor(request.getParameter("author_name"));
                db.getAllAuthor();
            } else if(!request.getParameter("book_name").equals(null)){
                db.insertBook(request.getParameter("book_name"),Integer.parseInt(request.getParameter("pages")),
                        Integer.parseInt(request.getParameter("price")),request.getParameter("language"),
                        Integer.parseInt(request.getParameter("id_author")));
                db.getAllBook();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        str="Added author: " + request.getParameter("author_name")+"\nAdded book:"+request.getParameter("book_name");
        response.getWriter().println("<!DOCTYPE HTML>");
        response.getWriter().println("<html><body><p>" + str + "</p></body></html>");
    }

    @Override
    public void destroy(){
        log.info("Servlet destroying...");
        try {
            db.closeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("Servlet was destroyed!");
    }

}

