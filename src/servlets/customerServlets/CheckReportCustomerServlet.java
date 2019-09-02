package servlets.customerServlets;

import databaseOperations.ReportOperations;
import model.ConsultantCustomerReport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CheckReportCustomerServlet", urlPatterns = {"/customer/logged/check_report"})
public class CheckReportCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reportId = Integer.parseInt(request.getParameter("reportId"));
        ConsultantCustomerReport consultantCustomerReport = ReportOperations.getAllInformationAboutReport(reportId);
        request.setAttribute("infoAboutReport", consultantCustomerReport);
        getServletContext().getRequestDispatcher("/customer/logged/check_report.jsp").forward(request, response);
    }
}
