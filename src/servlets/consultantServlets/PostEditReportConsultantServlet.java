package servlets.consultantServlets;

import databaseOperations.ReportOperations;
import factory.ReportFactory;
import model.Report;
import validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "PostEditReportConsultantServlet", urlPatterns = {"/consultant/logged/editing_report"})
public class PostEditReportConsultantServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isFormProperlyFilled = true;
        if (request.getParameter("customerId") == null
                || !Validator.isNumberValid(request.getParameter("customerId"))) {
            isFormProperlyFilled = false;
            request.setAttribute("customerIdError", "You should choose an option from the list.");
        }
        if (request.getParameter("startingDate") == null) {
            isFormProperlyFilled = false;
            request.setAttribute("startingDateError", "You should choose a valid starting date.");
        }
        if (request.getParameter("endingDate") == null) {
            isFormProperlyFilled = false;
            request.setAttribute("endingDateError", "You should choose a valid ending date.");
        }
        if (isFormProperlyFilled) {
            Date start = Date.valueOf(request.getParameter("startingDate"));
            Date end = Date.valueOf(request.getParameter("endingDate"));
            if (start.getTime() > end.getTime()) {
                isFormProperlyFilled = false;
                request.setAttribute("startingDateError", "Starting date should be either the ending date or any" +
                        " date before that.");
            }
        }
        if (!Validator.isNumberValid(request.getParameter("hoursPerDay"))
                || Integer.parseInt(request.getParameter("hoursPerDay")) > 24) {
            isFormProperlyFilled = false;
            request.setAttribute("hoursPerDayError", "You can't work for more than 24 hours in a single day.");
        }
        if(request.getParameter("comment") == null || !Validator.isTextValid(request.getParameter("comment"))) {
            isFormProperlyFilled = false;
            request.setAttribute("commentError", "You should enter at least one symbol as comment.");
        }
        if (!isFormProperlyFilled) {
            getServletContext().getRequestDispatcher("/consultant/logged/edit_report").forward(request, response);
        } else {
            HttpSession httpSession = request.getSession();
            request.setAttribute("consultantId", httpSession.getAttribute("consultantId"));
            Report report = ReportFactory.createReportFromForm(request);
            int reportId = Integer.parseInt(request.getParameter("reportId"));
            report.setId(reportId);
            ReportOperations.consultantUpdateReport(report);
            ReportOperations.insertCommentForReport(report.getId(),report.getComment());
            response.sendRedirect(request.getContextPath() + "/consultant/logged/main_page");
        }
    }
}
