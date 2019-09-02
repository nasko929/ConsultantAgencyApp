<%
    String title = "Welcome to Main Page";
%>
<jsp:include page="headerAndFooter/header.jsp">
    <jsp:param name="title" value="<%=title%>"/>
</jsp:include>
<div class="content">
    Welcome to "Consultant Agency Neon" Ltd.
    <br>
    If you are a customer representing an agency,
    <br>
    <a href="customer/login.jsp" class="links"> Log in from the Customers' Entrance Gate </a>,
    <br>
    If you are a consultant at this agency,
    <br>
    <a href="consultant/login.jsp" class="links"> Log in from the Consultants' Entrance Gate </a>.
</div>

<jsp:include page="headerAndFooter/footer.jsp"/>
