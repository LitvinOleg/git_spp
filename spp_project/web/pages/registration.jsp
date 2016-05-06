<%@ page import="classes.web.view.View" %>
<%@ page import="static classes.web.entity.user.User.*" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <title>Registration</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/registration.css">
    <link rel="stylesheet" href="css/elements.css">
</head>
<body>
    <!--Top-->
    <div id="top-panel">
        <a id="main" href="main.jsp">
            <img id="main-truck-image" src="images/truck.png" alt="Main" title="Main">
        </a>
        <!--Login form-->
        <div id="login">
            <%  UserType userType;
                if (request.getSession().getAttribute("user_type") != null)
                    userType = (UserType) request.getSession().getAttribute("user_type");
                else
                    userType = UserType.VISITOR;
                if (userType == UserType.VISITOR) { %>
                <form class="form-inline" action="registration.jsp" method="post">
                    <input class="big-text-input" type="text" name="login" placeholder="login">
                    <input class="big-text-input" type="password" name="password" placeholder="password">
                    <button class="big-button" type="submit" name="enter" value="enter">Sign in</button>
                    <button class="big-button" type="button" onclick="location.href='registration.jsp'">Registration</button>
                </form>
                <div class="result"><%=View.loginUserView(request)%></div> <!--Result of login-->
            <% } else if (userType != UserType.VISITOR) {
                switch (userType) {
                    case ADMIN: { %> <button class="big-button" type="button" onclick="location.href='admin_account.jsp'"><%= request.getSession().getAttribute("login")  %></button> <% } break;
                    case DISPATCHER: { %> <button class="big-button" type="button" onclick="location.href='main.jsp'"><%= request.getSession().getAttribute("login")  %></button> <% } break;
                    case CLIENT: { %> <button class="big-button" type="button" onclick="location.href='client_account.jsp'"><%= request.getSession().getAttribute("login")  %></button> <% }
                } %>

            <!--Exit button-->
            <form class="form-inline" action="main.jsp" method="post">
                <input class="big-button" type="submit" name="exit" value="exit">
                <% if (request.getParameter("exit") != null) {
                    request.getSession().setAttribute("login", null);
                    request.getSession().setAttribute("user_type", null);
                } %>
            </form>
            <% } %>
        </div>
    </div>


    <!--Content-->
    <div id="content-panel">
        <!--Registration form-->
        <div id="registration">
            <h1>Registration</h1>
            <div id="form">
                <form action="registration.jsp" method="post">
                    <input class="big-text-input" type="text" name="login" placeholder="Login"><br>
                    <input class="big-text-input" type="text" name="name" placeholder="Name"><br>
                    <input class="big-text-input" type="text" name="surname" placeholder="Surname"><br>
                    <input class="big-text-input" type="password" name="password" placeholder="Password"><br>
                    <input class="big-text-input" type="password" name="confirm" placeholder="Confirm password"><br>
                    <input class="big-button border_radius" type="submit" value="Registration" name="registration">
                </form>
            </div>

            <!--Result of registration-->
            <div id="reg-result">
                <%=View.registerUserView(request, UserType.CLIENT)%>
            </div>
        </div>
    </div>


    <!--Bottom-->
    <div id="bottom-panel">
    </div>
</body>
</html>
