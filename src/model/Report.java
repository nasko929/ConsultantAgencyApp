package model;

import java.sql.Date;

/*
To convert back to SQL format:
FROM_UNIXTIME(seconds)
 */

/**
 * A class to represent the report that is made after the consultant finishes his work with the customer.
 */
public class Report {
    private int id; // The id of the report.
    private int consultantId; // The id of the consultant that created this report.
    private int customerId; // The customer for whom this report is.
    private Date startedWorkingDate; // The first day of the work.
    private Date endedWorkingDate; // The last day of the work.
    private int workingHoursPerDay; // The number of hours that consultant worked.
    private String status; // The status of the current report.
    private String comment; // The latest comment of the current report.

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", consultantId=" + consultantId +
                ", customerId=" + customerId +
                ", startedWorkingDate=" + startedWorkingDate +
                ", endedWorkingDate=" + endedWorkingDate +
                ", workingHoursPerDay=" + workingHoursPerDay +
                ", status='" + status + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    /**
     * All the getters and setters.
     */

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(int consultantId) {
        this.consultantId = consultantId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
}