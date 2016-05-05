package servlets;


import database.DatabaseManagement;
import database.entities.Author;
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
import java.util.List;

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
        log.info("REQUEST " + request.getParameter("author_name"));
        try {
            //db.insertAuthor(request.getParameter("author_name"));
            List<Author> list = db.getAllAuthor();
            PrintWriter pw = response.getWriter();
            StringBuilder string = new StringBuilder();
            for (Author author:list)
                string.append(author);
            pw.println(string.toString());
        } catch (SQLException e) {
            e.printStackTrace();
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

