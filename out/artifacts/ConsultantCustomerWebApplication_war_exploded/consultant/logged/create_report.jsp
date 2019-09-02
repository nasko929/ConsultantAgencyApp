<%@ page import="model.Customer" %>
<%@ page import="java.util.ArrayList" %>
<%
    String title = "Create New Report";
    ArrayList<Customer> customers= (ArrayList<Customer>) request.getAttribute("customers");
%>
<jsp:include page="/headerAndFooter/header.jsp">
    <jsp:param name="title" value="<%=title%>"/>
</jsp:include>

<div class="content">
    <form action="<%=request.getContextPath()%>/consultant/logged/adding_report" method="post">
        <fieldset>
            <legend>Create new Report</legend>
            <label for="customerId">Choose customer:</label>
            <br>
            <select name="customerId" id="customerId" required>
                <option disabled selected value> -- Select a customer -- </option>
                <%
                    for(Customer customer: customers) {
                        out.print("<option value=\"" + customer.getId() + "\">"
                        + customer.getFirstName() + " " + customer.getLastName() + " from company "
                        + customer.getCompanyName() + "</option>");
                    }
                %>
            </select>
            <br>
            <%
                if(request.getAttribute("customerIdError") != null)
                    out.print("<p class=\"errorText\">" + request.getAttribute("customerIdError") + "</p>");
            %>
            <br>
            <label for="startingDate">Choose starting date of consulting service:</label>
            <br>
            <input type="date" name="startingDate" id="startingDate" required>
            <br>
            <%
                if(request.getAttribute("startingDateError") != null)
                    out.print("<p class=\"errorText\">" + request.getAttribute("startingDateError") + "</p>");
            %>
            <br>
            <label for="endingDate">Choose ending date of consulting service:</label>
            <br>
            <input type="date" name="endingDate" id="endingDate" required>
            <br>
            <%
                if(request.getAttribute("endingDateError") != null)
                    out.print("<p class=\"errorText\">" + request.getAttribute("endingDateError") + "</p>");
            %>
            <br>
            <label for="hoursPerDay">Choose the number of daily spent working hours:</label>
            <br>
            <input type="text" name="hoursPerDay" id="hoursPerDay" pattern="^[0-9]+$" required>
            <br>
            <%
                if(request.getAttribute("hoursPerDayError") != null)
                    out.print("<p class=\"errorText\">" + request.getAttribute("hoursPerDayError") + "</p>");
            %>
            <br>
            <input type="submit" value="Create new report" class="btn btn-primary">
        </fieldset>
    </form>
    <a href="<%=request.getContextPath()%>/consultant/logged/main_page">Return to main page</a>
</div>

<jsp:include page="/headerAndFooter/footer.jsp"/>