<%
    String title = "Login into the Customer System";
%>
<jsp:include page="/headerAndFooter/header.jsp">
    <jsp:param name="title" value="<%=title%>"/>
</jsp:include>

<div class="content">
    <form action="<%=request.getContextPath()%>/customer/login" method="post">
        <fieldset>
            <legend>Customer Login</legend>
            <label for="username">Username:</label>
            <br>
            <input type="text" name="username" id="username" placeholder="Username"
                   pattern="^[a-zA-Z0-9_]{6,64}$" required>
            <br>
            <%
                if(request.getAttribute("usernameError") != null)
                    out.print("<p class=\"errorText\">" + request.getAttribute("usernameError") + "</p><br>");
            %>
            <label for="password">Password:</label>
            <br>
            <input type="password" name="password" id="password" placeholder="Password"
                   pattern="^[a-zA-Z0-9_]{8,64}$" required>
            <br>
            <%
                if(request.getAttribute("passwordError") != null)
                    out.print("<p class=\"errorText\">" + request.getAttribute("passwordError") + "</p><br>");
            %>
            <input type="submit" value="Login" class="btn btn-primary">
        </fieldset>
    </form>
    You still do not have a customer profile?
    <br>
    <a href="register.jsp">Go and register</a>
    <br>
    <a href="<%=request.getContextPath()%>/">Go back to main page</a>
</div>

<jsp:include page="/headerAndFooter/footer.jsp"/>