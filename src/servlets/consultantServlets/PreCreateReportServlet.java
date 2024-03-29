package servlets.consultantServlets;

import databaseOperations.CustomerOperations;
import model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PreCreateReportServlet", urlPatterns = {"/consultant/logged/create_report"})
public class PreCreateReportServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Customer> customers = CustomerOperations.getAllCustomers();
        request.setAttribute("customers", customers);
        getServletContext().getRequestDispatcher("/consultant/logged/create_report.jsp").forward(request, response);
    }
}
