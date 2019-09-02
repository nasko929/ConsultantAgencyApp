<%
    String title = "Register a new Customer";
%>
<jsp:include page="/headerAndFooter/header.jsp">
    <jsp:param name="title" value="<%=title%>"/>
</jsp:include>

<div class="content">
    <form action="<%=request.getContextPath()%>/customer/register" method="post">
        <fieldset>
            <legend>Customer Registration</legend>
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
                    out.print("<p class=\"errorText\">" + request.getAttribute("usernameError") + "</p><br>");
            %>
            <label for="confPassword">Confirm Password:</label>
            <br>
            <input type="password" name="confPassword" id="confPassword" placeholder="Confirm Password"
                   pattern="^[a-zA-Z0-9_]{8,64}$" required>
            <br>
            <p id="matchingPasswords"></p>
            <label for="email">Email:</label>
            <br>
            <input type="email" name="email" id="email" placeholder="Email" pattern="^.+@.+\..+$" required>
            <br>
            <%
                if(request.getAttribute("emailError") != null)
                    out.print("<p class=\"errorText\">" + request.getAttribute("emailError") + "</p><br>");
            %>
            <label for="firstName">First Name:</label>
            <br>
            <input type="text" name="firstName" id="firstName" placeholder="First Name" required>
            <br>
            <%
                if(request.getAttribute("firstNameError") != null)
                    out.print("<p class=\"errorText\">" + request.getAttribute("firstNameError") + "</p><br>");
            %>
            <label for="lastName">Last Name:</label>
            <br>
            <input type="text" name="lastName" id="lastName" placeholder="Last Name" required>
            <br>
            <%
                if(request.getAttribute("lastNameError") != null)
                    out.print("<p class=\"errorText\">" + request.getAttribute("lastNameError") + "</p><br>");
            %>
            <label for="companyName">Company Name:</label>
            <br>
            <input type="text" name="companyName" id="companyName" placeholder="Company Name" required>
            <br>
            <%
                if(request.getAttribute("companyNameError") != null)
                    out.print("<p class=\"errorText\">" + request.getAttribute("companyNameError") + "</p><br>");
            %>
            <input type="submit" value="Register" class="btn btn-primary">
        </fieldset>
    </form>
    You already have a customer profile?
    <br>
    <a href="login.jsp">Go to login</a>
    <br>
    <a href="<%=request.getContextPath()%>/">Go back to main page</a>
</div>

<jsp:include page="/headerAndFooter/footer.jsp"/>