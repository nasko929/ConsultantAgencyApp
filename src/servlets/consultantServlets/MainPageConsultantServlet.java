package servlets.consultantServlets;

import databaseOperations.ReportOperations;
import model.CustomerReport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MainPageConsultantServlet", urlPatterns = {"/consultant/logged/main_page"})
public class MainPageConsultantServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        int consultantId = (int) httpSession.getAttribute("consultantId");
        if (request.getParameter("change") != null && request.getParameter("change").equals("true")) {
            if(httpSession.getAttribute("filterAccepted").equals("true")) {
                httpSession.setAttribute("filterAccepted","false");
            } else {
                httpSession.setAttribute("filterAccepted","true");
            }
        }
        ArrayList<CustomerReport> customerReports;
        if(httpSession.getAttribute("filterAccepted").equals("false")) {
            customerReports = ReportOperations.getAllReportsForConsultant(consultantId, false);
        } else {
            customerReports = ReportOperations.getAllReportsForConsultant(consultantId,true);
        }
        request.setAttribute("customerReports", customerReports);
        getServletContext().getRequestDispatcher("/consultant/logged/main_page.jsp").forward(request, response);
    }
}
