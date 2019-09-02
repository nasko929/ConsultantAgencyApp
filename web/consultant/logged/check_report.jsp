<%@ page import="model.ConsultantCustomerReport" %>
<%
    ConsultantCustomerReport data = (ConsultantCustomerReport)
            request.getAttribute("infoAboutReport");
    String title = "Checking Report #" + data.getReportId();
%>

<jsp:include page="/headerAndFooter/header.jsp">
    <jsp:param name="title" value="<%=title%>"/>
</jsp:include>
<%
    long startDate = data.getStartedWorkingDate().getTime();
    long endDate = data.getEndedWorkingDate().getTime();
    long difference = endDate - startDate;
    long numberOfDays = difference / 86400000 + 1;
    double totalSum = numberOfDays * data.getConsultantHourlyWage()
            * data.getWorkingHoursPerDay() * data.getConsultantCoefficient();
    String totalSumString = String.format("%.2f", totalSum);
%>
<div class="content">
    Report #<%= data.getReportId()%>
    <br>
    Status: <p style="font-weight: bold; <%=(data.getReportStatus().equals("Accepted")
                                ? ("color: green;")
                                : ("color: red;"))%>"><%=data.getReportStatus()%></p>
    <%
        if(data.getReportStatus().equals("Rejected")) {
    %>
    <form action="<%=request.getContextPath()%>/consultant/logged/edit_report" method="post">
        <input type="hidden" name="reportId" value="<%=data.getReportId()%>">
        <input type="submit" value="Edit Report And Send Back To Customer" class="btn btn-warning">
    </form>
    <%
        }
    %>
    <div class="info">
        <div class="firstRow">
            <div class="consultantInfo">
                <b><i>Consultant's Info</i></b>
                <hr>
                <u><b>Consultant's name:</b></u>
                <br>
                <%= data.getConsultantFirstName() + " " + data.getConsultantLastName()%>
                <br>
                <u><b>Consultant's email:</b></u>
                <br>
                <%= data.getConsultantEmail()%>
                <br>
                <u><b>Consultant's hourly wage:</b></u>
                <br>
                <%= data.getConsultantHourlyWage()%>
                <br>
                <u><b>Consultant's rank:</b></u>
                <br>
                <%= data.getConsultantRank()%>
                <br>
            </div>
            <div class="customerInfo">
                <b><i>Customer's Info</i></b>
                <hr>
                <u><b>Customer's name:</b></u>
                <br>
                <%= data.getCustomerFirstName() + " " + data.getCustomerLastName()%>
                <br>
                <u><b>Customer's email:</b></u>
                <br>
                <%= data.getCustomerEmail()%>
                <br>
                <u><b>Customer's company name:</b></u>
                <br>
                <%= data.getCustomerCompanyName()%>
            </div>
        </div>
        <div class="secondRow">
            <div class="reportInfo">
                <b><i>Report's data</i></b>
                <hr>
                <u><b>Started working date:</b></u>
                <br>
                <%= data.getStartedWorkingDate()%>
                <br>
                <u><b>Ended working date:</b></u>
                <br>
                <%= data.getEndedWorkingDate()%>
                <br>
                <u><b>Working hours per day by the consultant:</b></u>
                <br>
                <%= data.getWorkingHoursPerDay()%>
                <br>
                <u><b>Total sum:</b></u>
                <br>
                <%= totalSumString%> lv.
                <br>
                <u><b>Comment from the consultant:</b></u>
                <br>
                <%= data.getReportComment()%>
            </div>
        </div>
    </div>
    <br>

    <a href="<%=request.getContextPath()%>/consultant/logged/main_page">Return to main page</a>
</div>

<jsp:include page="/headerAndFooter/footer.jsp"/>