package model;

import java.sql.Date;

/**
 * A class to represent the records we get from the database when we use join-type of query.
 */
public class ConsultantReport {
    private int id; // The id of the report.
    private Date startedWorkingDate; // The first day of the work.
    private Date endedWorkingDate; // The last day of the work.
    private int workingHoursPerDay; // The number of hours that consultant worked.
    private String consultantFirstName; // The first name of the consultant.
    private String consultantLastName; // The last name of the consultant.
    private double consultantCoefficient; // The consultant's coefficient of extra wage.
    private int consultantHourlyWage; // The consultant's hourly wage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
