<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ConsultantReport" %>
<%
    HttpSession httpSession = request.getSession();
    ArrayList<ConsultantReport> consultantReports = (ArrayList<ConsultantReport>)
            request.getAttribute("consultantReports");
    String title = "Welcome to the Customers' Page, " + httpSession.getAttribute("customerUsername");
%>
<jsp:include page="/headerAndFooter/header.jsp">
    <jsp:param name="title" value="<%=title%>"/>
</jsp:include>
<div class="content">
    <div class="logoutButton">
        <a href="<%=request.getContextPath()%>/customer/logout">Logout</a>
    </div>
    Welcome,
    <br>
    <%= httpSession.getAttribute("firstName") + " " + httpSession.getAttribute("lastName")%> !
    <br>
    <br>
    These are your current and pending reports from the consultants of "Consultant Agency Neon" Ltd.
    <br>
    <br>
    <a href="<%=request.getContextPath()%>/customer/logged/main_page">
        <img src="<%=request.getContextPath()%>/images/refresh-button.png" style="height: 30px;
         width: auto; margin-bottom: 1%; margin-top: -2%" title="Refresh">
    </a>
    <table class="reportsTable">
        <tr>
            <th>Report's Id</th>
            <th>Consultant's name</th>
            <th>Total Sum</th>
            <th>See the report</th>
        </tr>
        <%
            if(consultantReports.size() != 0) {
                for (ConsultantReport consultantReport : consultantReports) {
                    out.print("<tr><td>");
                    long startDate = consultantReport.getStartedWorkingDate().getTime();
                    long endDate = consultantReport.getEndedWorkingDate().getTime();
                    long difference = endDate - startDate;
                    long numberOfDays = difference / 86400000 + 1;
                    double totalSum = numberOfDays * consultantReport.getConsultantHourlyWage()
                            * consultantReport.getWorkingHoursPerDay() * consultantReport.getConsultantCoefficient();
                    String totalSumString = String.format("%.2f", totalSum);
                    out.print(consultantReport.getId() + "</td><td>" + consultantReport.getConsultantFirstName()
                            + " " + consultantReport.getConsultantLastName()
                            + "</td><td>" + totalSumString + " lv.</td><td>");
                    out.print("<a href=\"" + request.getContextPath() + "/customer/logged/check_report?reportId=" +
                            consultantReport.getId() + "\">See the report</a></td></tr>");
                }
            } else {
                out.print("<tr><td colspan=\"4\">" +
                        "There are not any currently pending reports for you. :)</td></tr>");
            }
        %>
    </table>
</div>
<jsp:include page="/headerAndFooter/footer.jsp"/>