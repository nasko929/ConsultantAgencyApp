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
    <div class="topSection">
        <div class="partOfTopSection">
            <img src="<%=request.getContextPath()%>/images/accept-button.png" id="acceptImage"
                 onclick = "alterReportCommentSending('Accept','<%=request.getContextPath()%>')"
                 style="width: auto; height: 60%;" title = "Accept the report">
            <br>
            Accept the Report
        </div>
        <div class="partOfTopSection" style="padding: 1% 0px;">
            Report #<%= data.getReportId()%>
        </div>
        <div class="partOfTopSection">
            <img src="<%=request.getContextPath()%>/images/reject-button.jpg" id="rejectImage"
                 onclick = "alterReportCommentSending('Reject','<%=request.getContextPath()%>')"
                 style="width: auto; height: 60%;" title = "Reject the report">
            <br>
            Reject the Report
        </div>
    </div>
    <div class="formAction" style="display: none" id = "formAction">
        <form action = "#" method = "post" name = "commentForm">
            <fieldset>
                <input type="hidden" name="reportId" value="<%=data.getReportId()%>">
                <legend id="legendTitle">Accepting Report</legend>
                <label for="comment">Add comment for the consultant:</label>
                <br>
                <textarea id="comment" name="comment" style="font-size: 15px;">Enter your comment here...</textarea>
                <br>
                <input type="submit" id = "submitButton"
                       value="Accept the Report and send comment to consultant" class="btn btn-primary">
            </fieldset>
        </form>
    </div>
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
                <b><i>Report's Data</i></b>
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
    <a href="<%=request.getContextPath()%>/customer/logged/main_page">Return to main page</a>
</div>

<jsp:include page="/headerAndFooter/footer.jsp"/>