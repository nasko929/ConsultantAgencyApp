package servlets.consultantServlets;

import databaseOperations.CustomerOperations;
import databaseOperations.ReportOperations;
import model.Customer;
import model.Report;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "PreEditReportConsultantServlet", urlPatterns = {"/consultant/logged/edit_report"})
public class PreEditReportConsultantServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        int reportId = Integer.parseInt(request.getParameter("reportId"));
        int consultantId = (int) httpSession.getAttribute("consultantId");
        Report report = ReportOperations.getReportWithReportIdAndConsultantId(reportId, consultantId);
        request.setAttribute("report", report);
        ArrayList<Customer> customers = CustomerOperations.getAllCustomers();
        request.setAttribute("customers", customers);
        getServletContext().getRequestDispatcher("/consultant/logged/edit_report.jsp").forward(request, response);
    }
}
