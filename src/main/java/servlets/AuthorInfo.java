package servlets;

import database.DatabaseManagement;
import database.entities.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/authorinfo")
public class AuthorInfo extends HttpServlet {
    private static final Logger log= LoggerFactory.getLogger(DatabaseManagement.class);
    private DatabaseManagement db;

    @Override
    public void init() {
        log.info("AuthorInfo servlet initialization...");
        db = new DatabaseManagement();
        try {
            db.connectDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        log.info("AuthorInfo servlet was initialized!");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("GET method");
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            if (request.getParameter("id")!=null) {
                log.info("author id: " + Integer.parseInt(request.getParameter("id")));
                request.setAttribute("books", db.getAllBook(null, null, Integer.parseInt(request.getParameter("id"))));
                request.setAttribute("author", db.getOneAuthor(Integer.parseInt(request.getParameter("id"))));
            }
            if (request.getParameter("name")!=null){
                log.info("author name: " + request.getParameter("name"));
                List<Book> list = db.getAllBook(null, request.getParameter("name"), 0);
                request.setAttribute("books", list);
                request.setAttribute("author", list.get(0).getAuthor());
            }
            getServletContext().getRequestDispatcher("/authorinfo.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy(){
        log.info("AuthorInfo servlet destroying...");
        try {
            db.closeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("AuthorInfo servlet was destroyed!");
    }
}