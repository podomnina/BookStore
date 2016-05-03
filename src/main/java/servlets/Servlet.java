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
            RequestDispatcher dispatcher = request.getRequestDispatcher("/mainpage.jsp");
            dispatcher.forward(request, response);

            System.out.println(str);

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
        str="author: " + request.getParameter("author_name");
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

