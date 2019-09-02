package servlets.customerServlets;

import databaseOperations.ReportOperations;
import model.Report;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AcceptReportCustomerServlet", urlPatterns = {"/customer/logged/accept_report"})
public class AcceptReportCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    /*
    TODO:
    - Add filtering for stopping anyone from directly writing the url and rejecting stuff.
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reportId = Integer.parseInt(request.getParameter("reportId"));
        HttpSession httpSession = request.getSession();
        int customerId = (int)httpSession.getAttribute("customerId");
        String comment = request.getParameter("comment");
        Report report = ReportOperations.getReportWithReportIdAndCustomerId(reportId,customerId);
        report.setStatus("Accepted");
        ReportOperations.customerUpdateReport(report);
        ReportOperations.insertCommentForReport(reportId,comment);
        response.sendRedirect(request.getContextPath() + "/customer/logged/main_page");
    }
}
