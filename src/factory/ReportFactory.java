package factory;

import model.Report;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportFactory {
    public static Report createReportFromForm(HttpServletRequest request) {
        Report report = new Report();
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        int consultantId = (int) request.getAttribute("consultantId");
        Date startingDate = Date.valueOf(request.getParameter("startingDate"));
        Date endingDate = Date.valueOf(request.getParameter("endingDate"));
        int numberOfHoursPerDay = Integer.parseInt(request.getParameter("hoursPerDay"));
        String comment;
        if(request.getParameter("comment") == null) {
            comment = "-";
        }
        else {
            comment = request.getParameter("comment");
        }
        String status = "CustomerVisit";
        report.setComment(comment);
        report.setConsultantId(consultantId);
        report.setCustomerId(customerId);
        report.setStartedWorkingDate(startingDate);
        report.setEndedWorkingDate(endingDate);
        report.setStatus(status);
        report.setWorkingHoursPerDay(numberOfHoursPerDay);
        return report;
    }

    public static Report createReportFromResultSet(ResultSet resultSet) throws SQLException {
        Report report = new Report();
        int reportId = resultSet.getInt("id");
        int customerId = resultSet.getInt("customer_id");
        int consultantId = resultSet.getInt("consultant_id");
        Date startedWorkingDate = resultSet.getDate("started_working_date");
        Date endedWorkingDate = resultSet.getDate("ended_working_date");
        int workingHoursPerDay = resultSet.getInt("working_hours_per_day");
        String status = resultSet.getString("status");
        report.setId(reportId);
        report.setCustomerId(customerId);
        report.setConsultantId(consultantId);
        report.setStartedWorkingDate(startedWorkingDate);
        report.setEndedWorkingDate(endedWorkingDate);
        report.setWorkingHoursPerDay(workingHoursPerDay);
        report.setStatus(status);
        return report;
    }
}
