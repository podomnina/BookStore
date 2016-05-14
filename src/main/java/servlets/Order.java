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

@WebServlet("/order")
public class Order extends HttpServlet {
    private static final Logger log= LoggerFactory.getLogger(DatabaseManagement.class);
    private DatabaseManagement db;
    int id;

    @Override
    public void init() {
        log.info("Order servlet initialization...");
        db = new DatabaseManagement();
        try {
            db.connectDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        log.info("Order servlet was initialized!");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("GET method");
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            id=Integer.parseInt(request.getParameter("id"));  //book id
            getServletContext().getRequestDispatcher("/order.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("POST method");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            log.info("Name:"+request.getParameter("name")+" Email:" + request.getParameter("email"));
            db.insertBooking(request.getParameter("name"), request.getParameter("email"), id);
            PrintWriter pw = response.getWriter();
            int booking_id = db.getBookingId(request.getParameter("name"),request.getParameter("email"),id);
            if (booking_id>0)
                pw.println(booking_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy(){
        log.info("Order servlet destroying...");
        try {
            db.closeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("Order servlet was destroyed!");
    }
}
