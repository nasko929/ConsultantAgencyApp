<%@ page import="model.Customer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Report" %>
<%
    Report report = (Report) request.getAttribute("report");
    String title = "Edit Report # " + report.getId();
    ArrayList<Customer> customers = (ArrayList<Customer>) request.getAttribute("customers");
%>
<jsp:include page="/headerAndFooter/header.jsp">
    <jsp:param name="title" value="<%=title%>"/>
</jsp:include>

<div class="content">
    <form action="<%=request.getContextPath()%>/consultant/logged/editing_report" method="post">
        <fieldset>
            <legend>Edit Report</legend>
            <input type="hidden" name="reportId" value="<%=report.getId()%>">
            <label for="customerId">Choose customer:</label>
            <br>
            <select name="customerId" id="customerId" required>
                <option> -- Select a customer --</option>
                <%
                    for (Customer customer : customers) {
                        String selected = (report.getCustomerId() == customer.getId()) ? ("selected") : ("");
                        out.print("<option value=\"" + customer.getId() + "\" " + selected + ">"
                                + customer.getFirstName() + " " + customer.getLastName() + " from company "
                                + customer.getCompanyName() + "</option>");
                    }
                %>
            </select>
            <br>
            <%
                if (request.getAttribute("customerIdError") != null)
                    out.print("<p class=\"errorText\">" + request.getAttribute("customerIdError") + "</p>");
            %>
            <br>
            <label for="startingDate">Choose starting date of consulting service:</label>
            <br>
            <input type="date" name="startingDate" id="startingDate" value="<%=report.getStartedWorkingDate()%>"
                   required>
            <br>
            <%
                if (request.getAttribute("startingDateError") != null)
                    out.print("<p class=\"errorText\">" + request.getAttribute("startingDateError") + "</p>");
            %>
            <br>
            <label for="endingDate">Choose ending date of consulting service:</label>
            <br>
            <input type="date" name="endingDate" id="endingDate" value="<%=report.getEndedWorkingDate()%>" required>
            <br>
            <%
                if (request.getAttribute("endingDateError") != null)
                    out.print("<p class=\"errorText\">" + request.getAttribute("endingDateError") + "</p>");
            %>
            <br>
            <label for="hoursPerDay">Choose the number of daily spent working hours:</label>
            <br>
            <input type="text" name="hoursPerDay" id="hoursPerDay" pattern="^[0-9]+$"
                   value="<%=report.getWorkingHoursPerDay()%>" required>
            <br>
            <%
                if (request.getAttribute("hoursPerDayError") != null)
                    out.print("<p class=\"errorText\">" + request.getAttribute("hoursPerDayError") + "</p>");
            %>
            <br>
            <label for="comment">Enter comment:</label>
            <br>
            <textarea name="comment" id="comment" required>Enter a comment describing the changes for the customer..
            </textarea>
            <br>
            <%
                if (request.getAttribute("commentError") != null)
                    out.print("<p class=\"errorText\">" + request.getAttribute("hoursPerDayError") + "</p>");
            %>
            <br>
            <input type="submit" value="Edit report and send back to customer" class="btn btn-primary">
        </fieldset>
    </form>
    <a href="<%=request.getContextPath()%>/consultant/logged/main_page">Return to main page</a>
</div>

<jsp:include page="/headerAndFooter/footer.jsp"/>