package servlets.customerServlets;

import databaseOperations.ReportOperations;
import model.ConsultantReport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MainPageCustomerServlet", urlPatterns = {"/customer/logged/main_page"})
public class MainPageCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        int customerId = (int)httpSession.getAttribute("customerId");
        ArrayList<ConsultantReport> consultantReports = ReportOperations.getAllReportsForCustomer(customerId);
        request.setAttribute("consultantReports",consultantReports);
        getServletContext().getRequestDispatcher("/customer/logged/main_page.jsp").forward(request,response);
    }
}
