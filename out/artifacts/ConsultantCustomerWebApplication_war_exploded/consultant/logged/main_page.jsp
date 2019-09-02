<%@ page import="model.CustomerReport" %>
<%@ page import="java.util.ArrayList" %>
<%
    HttpSession httpSession = request.getSession();
    ArrayList<CustomerReport> data = (ArrayList<CustomerReport>) request.getAttribute("customerReports");
    String title = "Welcome to the Consultant's Page, Consultant " + httpSession.getAttribute("consultantUsername");
%>
<jsp:include page="/headerAndFooter/header.jsp">
    <jsp:param name="title" value="<%=title%>"/>
</jsp:include>
<div class="content">
    <div class="logoutButton">
        <a href="<%=request.getContextPath()%>/consultant/logout">Logout</a>
    </div>
    Welcome, Consultant <%= httpSession.getAttribute("firstName") + " " + httpSession.getAttribute("lastName")%> !
    <br>
    <br>
    These are your newcome reports from the customers.
    <br>
    <br>
    <a href="<%=request.getContextPath()%>/consultant/logged/main_page">
        <img src="<%=request.getContextPath()%>/images/refresh-button.png" style="height: 30px;
         width: auto; margin-bottom: 1%; margin-top: -2%" title="Refresh">
    </a>
    <div class="filterWrapper">
        <div class="filterAccepted">
            <form action = "<%=request.getContextPath()%>/consultant/logged/main_page" method = "post">
                <input type="hidden" name="change" value="true">
                <input type="submit" value="<%=(httpSession.getAttribute("filterAccepted") == "true")
                                            ? ("Show All Reports")
                                            : ("Hide Accepted Reports")%>" class="btn btn-primary">
            </form>
        </div>
    </div>
    <table class="reportsTable">
        <tr>
            <th>Report's Id</th>
            <th>Customer's name</th>
            <th>Customer's company name</th>
            <th>Report's status</th>
            <th>Customer's comment</th>
            <th>See the report</th>
        </tr>
        <%
            if(data.size() != 0) {
                for (CustomerReport customerReport : data) {
                    out.print("<tr><td>");
                    out.print(customerReport.getReportId() + "</td><td>" + customerReport.getCustomerFirstName()
                            + " " + customerReport.getCustomerLastName()
                            + "</td><td>" + customerReport.getCustomerCompanyName() + "</td><td>"
                            + customerReport.getReportStatus() + "</td><td>"
                            + customerReport.getLatestComment() + "</td><td>");
                    out.print("<a href=\"" + request.getContextPath() + "/consultant/logged/check_report?reportId=" +
                            customerReport.getReportId() + "\">See the report</a></td></tr>");
                }
            } else {
                out.print("<tr><td colspan=\"6\">" +
                        "There are not any new incoming reports for you. :)</td></tr>");
            }
        %>
    </table>
    <a href="<%=request.getContextPath()%>/consultant/logged/create_report"
       class="btn btn-primary" style="margin: 3% 0 0 0">Create Report</a>
</div>
<jsp:include page="/headerAndFooter/footer.jsp"/>