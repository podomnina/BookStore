package servlets;

import database.DatabaseManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/bookinfo")
public class BookInfo extends HttpServlet {
    private static final Logger log= LoggerFactory.getLogger(DatabaseManagement.class);
    private DatabaseManagement db;

    @Override
    public void init() {
        log.info("BookInfo servlet initialization...");
        db = new DatabaseManagement();
        try {
            db.connectDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        log.info("BookInfo servlet was initialized!");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("GET method");
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            if (request.getParameter("id")!=null) {
                log.info("book id: " + Integer.parseInt(request.getParameter("id")));
                request.setAttribute("book", db.getOneBook(Integer.parseInt(request.getParameter("id")),null));
            }
            if (request.getParameter("name")!=null){
                log.info("book name: " + request.getParameter("name"));
                request.setAttribute("book", db.getOneBook(0,(request.getParameter("name"))));
            }
            getServletContext().getRequestDispatcher("/bookinfo.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("POST method");

    }

    @Override
    public void destroy(){
        log.info("BookInfo servlet destroying...");
        try {
            db.closeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("BookInfo servlet was destroyed!");
    }
}