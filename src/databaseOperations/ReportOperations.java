package databaseOperations;

import com.mysql.jdbc.Statement;
import databaseAccess.DatabaseAccess;
import factory.ReportFactory;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportOperations {

    public static int insertReport(Report report) {
        String prepStatementInsert = "INSERT INTO reports (customer_id, consultant_id, started_working_date, "
                + "ended_working_date, working_hours_per_day, status) VALUES(?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int candidateId = -1;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementInsert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, report.getCustomerId());
            preparedStatement.setInt(2, report.getConsultantId());
            preparedStatement.setDate(3, report.getStartedWorkingDate());
            preparedStatement.setDate(4, report.getEndedWorkingDate());
            preparedStatement.setInt(5, report.getWorkingHoursPerDay());
            preparedStatement.setString(6, report.getStatus());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            candidateId = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return candidateId;
    }

    public static void insertCommentForReport(int reportId, String comment) {
        String prepStatementForInsertingComment = "INSERT INTO report_comments (report_id,comment," +
                "time_of_comment) VALUES(?,?,current_timestamp())";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementForInsertingComment);
            preparedStatement.setInt(1, reportId);
            preparedStatement.setString(2, comment);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getLatestCommentOfReport(int reportId) {
        String prepStatementForGettingLatestComment = "SELECT comment FROM report_comments WHERE report_id = ?" +
                "ORDER BY time_of_comment DESC LIMIT 1";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String comment = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementForGettingLatestComment);
            preparedStatement.setInt(1,reportId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            comment = resultSet.getString("comment");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return comment;
    }

    public static void consultantUpdateReport(Report report) {
        String prepStatementForUpdatingReport = "UPDATE reports SET customer_id = ?, consultant_id = ?, " +
                "started_working_date = ?, ended_working_date = ?, working_hours_per_day = ?, status = ? " +
                "WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementForUpdatingReport);
            preparedStatement.setInt(1, report.getCustomerId());
            preparedStatement.setInt(2, report.getConsultantId());
            preparedStatement.setDate(3,report.getStartedWorkingDate());
            preparedStatement.setDate(4,report.getEndedWorkingDate());
            preparedStatement.setInt(5,report.getWorkingHoursPerDay());
            preparedStatement.setString(6,report.getStatus());
            preparedStatement.setInt(7,report.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void customerUpdateReport(Report report) {
        String prepStatementForUpdatingReport = "UPDATE reports SET status = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementForUpdatingReport);
            preparedStatement.setString(1, report.getStatus());
            preparedStatement.setInt(2, report.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Report getReportWithReportIdAndCustomerId(int reportId, int customerId) {
        String prepStatementForCheckingForAReport = "SELECT * FROM reports WHERE id = ? AND customer_id = ? " +
                "AND reports.status = 'CustomerVisit'";
        Report report = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementForCheckingForAReport);
            preparedStatement.setInt(1, reportId);
            preparedStatement.setInt(2, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                report = ReportFactory.createReportFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return report;
    }

    public static Report getReportWithReportIdAndConsultantId(int reportId, int consultantId) {
        String prepStatementForCheckingForAReport = "SELECT * FROM reports WHERE id = ? AND consultant_id = ? " +
                "AND reports.status IN ('Rejected','Accepted')";
        Report report = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementForCheckingForAReport);
            preparedStatement.setInt(1, reportId);
            preparedStatement.setInt(2, consultantId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                report = ReportFactory.createReportFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return report;
    }

    public static ArrayList<CustomerReport> getAllReportsForConsultant(int consultantId, boolean filterAccepted) {
        String prepStatementForGettingAllReports = "SELECT reports.id, customers.first_name, "
                + "customers.last_name, customers.company_name, reports.status, "
                + "(SELECT comment "
                + "FROM report_comments "
                + "WHERE report_id = reports.id "
                + "ORDER BY time_of_comment DESC LIMIT 1) AS latest_comment "
                + "FROM reports "
                + "JOIN customers ON customers.id = reports.customer_id "
                + "WHERE reports.consultant_id = ? ";
        if (filterAccepted) {
            prepStatementForGettingAllReports += "AND reports.status = 'Rejected' ORDER BY reports.id";
        } else {
            prepStatementForGettingAllReports += "AND reports.status IN ('Rejected','Accepted') ORDER BY reports.id";
        }
        ArrayList<CustomerReport> customerReports = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementForGettingAllReports);
            preparedStatement.setInt(1, consultantId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CustomerReport customerReport = new CustomerReport();
                customerReport.setCustomerFirstName(resultSet.getString("first_name"));
                customerReport.setCustomerLastName(resultSet.getString("last_name"));
                customerReport.setCustomerCompanyName(resultSet.getString("company_name"));
                customerReport.setReportId(resultSet.getInt("id"));
                customerReport.setReportStatus(resultSet.getString("status"));
                customerReport.setLatestComment(resultSet.getString("latest_comment"));
                customerReports.add(customerReport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customerReports;
    }

    public static ArrayList<ConsultantReport> getAllReportsForCustomer(int customerId) {
        String prepStatementForGettingAllReports = "SELECT consultants_ranks.coefficient, consultants.hourly_wage, "
                + "consultants.first_name, consultants.last_name, reports.id, reports.started_working_date,"
                + " reports.ended_working_date, reports.working_hours_per_day FROM reports "
                + "JOIN consultants ON reports.consultant_id = consultants.id "
                + "JOIN consultants_ranks ON consultants.rank = consultants_ranks.id WHERE reports.customer_id = ? "
                + "AND reports.status = 'CustomerVisit' ORDER BY reports.id";
        ArrayList<ConsultantReport> consultantReports = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementForGettingAllReports);
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ConsultantReport consultantReport = new ConsultantReport();
                consultantReport.setConsultantCoefficient(resultSet.getDouble("coefficient"));
                consultantReport.setConsultantFirstName(resultSet.getString("first_name"));
                consultantReport.setConsultantLastName(resultSet.getString("last_name"));
                consultantReport.setConsultantHourlyWage(resultSet.getInt("hourly_wage"));
                consultantReport.setStartedWorkingDate(resultSet.getDate("started_working_date"));
                consultantReport.setEndedWorkingDate(resultSet.getDate("ended_working_date"));
                consultantReport.setId(resultSet.getInt("id"));
                consultantReport.setWorkingHoursPerDay(resultSet.getInt("working_hours_per_day"));
                consultantReports.add(consultantReport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return consultantReports;
    }

    public static ConsultantCustomerReport getAllInformationAboutReport(int reportId) {
        ConsultantCustomerReport consultantCustomerReport = new ConsultantCustomerReport();
        String prepStatementForGettingAllInformationAboutReport =
                "SELECT customers.first_name as customer_fname, customers.last_name as customer_lname,\n" +
                        "customers.email as customer_email, customers.company_name as customer_cname,\n" +
                        "consultants.first_name as consultant_fname, consultants.last_name as consultant_lname,\n" +
                        "consultants.email as consultant_email, consultants_ranks.name_of_rank as consultant_rank,\n" +
                        "consultants_ranks.coefficient as consultant_coefficient,\n" +
                        "consultants.hourly_wage as consultant_hwage, reports.id as report_id,\n" +
                        "reports.started_working_date as report_sdate, reports.ended_working_date as report_edate,\n" +
                        "reports.working_hours_per_day as reports_whpday, (SELECT comment FROM report_comments WHERE" +
                        " report_id = reports.id ORDER BY time_of_comment DESC LIMIT 1) as report_comment,\n" +
                        " reports.status as report_status FROM reports\n" +
                        "JOIN consultants ON reports.consultant_id = consultants.id\n" +
                        "JOIN customers ON reports.customer_id = customers.id\n" +
                        "JOIN consultants_ranks ON consultants.`rank` = consultants_ranks.id\n" +
                        "JOIN report_comments ON reports.id = report_comments.report_id\n" +
                        "WHERE reports.id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementForGettingAllInformationAboutReport);
            preparedStatement.setInt(1, reportId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                consultantCustomerReport.setCustomerFirstName(resultSet.getString("customer_fname"));
                consultantCustomerReport.setCustomerLastName(resultSet.getString("customer_lname"));
                consultantCustomerReport.setCustomerEmail(resultSet.getString("customer_email"));
                consultantCustomerReport.setCustomerCompanyName(resultSet.getString("customer_cname"));
                consultantCustomerReport.setConsultantFirstName(resultSet.getString("consultant_fname"));
                consultantCustomerReport.setConsultantLastName(resultSet.getString("consultant_lname"));
                consultantCustomerReport.setConsultantEmail(resultSet.getString("consultant_email"));
                consultantCustomerReport.setConsultantRank(resultSet.getString("consultant_rank"));
                consultantCustomerReport.setConsultantCoefficient(resultSet.getDouble(
                        "consultant_coefficient"));
                consultantCustomerReport.setConsultantHourlyWage(resultSet.getInt("consultant_hwage"));
                consultantCustomerReport.setReportId(resultSet.getInt("report_id"));
                consultantCustomerReport.setStartedWorkingDate(resultSet.getDate("report_sdate"));
                consultantCustomerReport.setEndedWorkingDate(resultSet.getDate("report_edate"));
                consultantCustomerReport.setWorkingHoursPerDay(resultSet.getInt("reports_whpday"));
                consultantCustomerReport.setReportComment(resultSet.getString("report_comment"));
                consultantCustomerReport.setReportStatus(resultSet.getString("report_status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return consultantCustomerReport;
    }
}
