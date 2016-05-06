package servlets;

import database.DatabaseManagement;
import database.entities.Author;
import database.entities.Book;
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

@WebServlet("/search")
public class Search extends HttpServlet {
    DatabaseManagement db;
    private static final Logger log = LoggerFactory.getLogger(DatabaseManagement.class);

    @Override
    public void init() {
        log.info("Search servlet initialization...");
        db = new DatabaseManagement();
        try {
            db.connectDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        log.info("Search servlet was initialized!");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("GET method");
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
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
        log.info("REQUEST: " + request.getParameter("text") +
                " " + request.getParameter("bookRB") + " " + request.getParameter("authorRB"));
        try {
            String valBook=null,valAuthor=null;
            if (request.getParameter("bookRB").equals("book"))
                valBook=request.getParameter("text");
            else if (request.getParameter("authorRB").equals("author"))
                valAuthor=request.getParameter("text");

            List<Book> list = db.getAllBook(valBook,valAuthor);
            request.setAttribute("books", list);
            PrintWriter pw = response.getWriter();
            if (list.size()==0)
                pw.print("По запросу '"+request.getParameter("text")+"' ничего не найдено");
            else {
                StringBuilder string = new StringBuilder();
                for (Book book : list)
                    string.append(book.getName());
                pw.println(string.toString());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        log.info("Search servlet destroying...");
        try {
            db.closeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("Search servlet was destroyed!");
    }
}