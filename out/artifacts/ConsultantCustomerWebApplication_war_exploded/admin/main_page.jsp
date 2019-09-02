<%
    HttpSession httpSession = request.getSession();
    String title = "Welcome to the Consultant's Page, Consultant " + httpSession.getAttribute("adminUsername");
%>
<jsp:include page="/headerAndFooter/header.jsp">
    <jsp:param name="title" value="<%=title%>"/>
</jsp:include>

<div class="content">
    <div class="logoutButton">
        <a href="<%=request.getContextPath()%>/consultant/logout">Logout</a>
    </div>
    Hello, Admin <%=httpSession.getAttribute("adminUsername")%>!
    <br>
    <a href="<%=request.getContextPath()%>/admin/create_customer.jsp">Create New Customer Profile</a>
    <br>
    <a href="<%=request.getContextPath()%>/admin/create_consultant.jsp">Create New Consultant Profile</a>
</div>

<jsp:include page="/headerAndFooter/footer.jsp"/>