package model;

import java.sql.Date;

/**
 * A class to represent all models together for the report information we have to display to the user.
 */
public class ConsultantCustomerReport {
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private String customerCompanyName;
    private String consultantFirstName;
    private String consultantLastName;
    private String consultantEmail;
    private String consultantRank;
    private double consultantCoefficient;
    private int consultantHourlyWage;
    private int reportId;
    private Date startedWorkingDate;
    private Date endedWorkingDate;
    private int workingHoursPerDay;
    private String reportComment;
    private String reportStatus;

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerCompanyName() {
        return customerCompanyName;
    }

    public void setCustomerCompanyName(String customerCompanyName) {
        this.customerCompanyName = customerCompanyName;
    }

    public String getConsultantFirstName() {
        return consultantFirstName;
    }

    public void setConsultantFirstName(String consultantFirstName) {
        this.consultantFirstName = consultantFirstName;
    }

    public String getConsultantLastName() {
        return consultantLastName;
    }

    public void setConsultantLastName(String consultantLastName) {
        this.consultantLastName = consultantLastName;
    }

    public String getConsultantEmail() {
        return consultantEmail;
    }

    public void setConsultantEmail(String consultantEmail) {
        this.consultantEmail = consultantEmail;
    }

    public String getConsultantRank() {
        return consultantRank;
    }

    public void setConsultantRank(String consultantRank) {
        this.consultantRank = consultantRank;
    }

    public double getConsultantCoefficient() {
        return consultantCoefficient;
    }

    public void setConsultantCoefficient(double consultantCoefficient) {
        this.consultantCoefficient = consultantCoefficient;
    }

    public int getConsultantHourlyWage() {
        return consultantHourlyWage;
    }

    public void setConsultantHourlyWage(int consultantHourlyWage) {
        this.consultantHourlyWage = consultantHourlyWage;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public Date getStartedWorkingDate() {
        return startedWorkingDate;
    }

    public void setStartedWorkingDate(Date startedWorkingDate) {
        this.startedWorkingDate = startedWorkingDate;
    }

    public Date getEndedWorkingDate() {
        return endedWorkingDate;
    }

    public void setEndedWorkingDate(Date endedWorkingDate) {
        this.endedWorkingDate = endedWorkingDate;
    }

    public int getWorkingHoursPerDay() {
        return workingHoursPerDay;
    }

    public void setWorkingHoursPerDay(int workingHoursPerDay) {
        this.workingHoursPerDay = workingHoursPerDay;
    }

    public String getReportComment() {
        return reportComment;
    }

    public void setReportComment(String reportComment) {
        this.reportComment = reportComment;
    }
}
