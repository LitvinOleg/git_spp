<%@ page import="classes.web.view.View" %>
<%@ page import="static classes.web.entity.user.User.*" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <!--Top-->
    <div id="top-panel">
        <a href="main.jsp" id="main">Main</a><br>
        <!--Login form-->
        <div id="login">
            <%  UserType userType;
                if (request.getSession().getAttribute("user_type") != null)
                    userType = (UserType) request.getSession().getAttribute("user_type");
                else
                    userType = null;
                if (userType == null) { %>
                    <form action="registration.jsp" method="post">
                        <input type="text" name="login" placeholder="login"><br>
                        <input type="password" name="password" placeholder="password"><br>
                        <input type="submit" name="enter" value="enter">
                    </form>
                    <div id="result">
                        <%=View.loginUserView(request)%> <!--Result of login-->
                    </div>
                    <a href="registration.jsp">Registration</a><br>
                <% } else if (userType != null) {
                    switch (userType) {
                        case ADMIN: { %> <a href="admin_account.jsp"><%= request.getSession().getAttribute("login")  %></a> <% } break;
                        case DISPATCHER: { %> <a href="main.jsp"><%= request.getSession().getAttribute("login")  %></a> <% } break;
                        case CLIENT: { %> <a href="client_account.jsp"><%= request.getSession().getAttribute("login")  %></a> <% }
                    } %>

                    <!--Exit button-->
                    <form action="registration.jsp" method="post">
                        <input type="submit" name="exit" value="exit">
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
                    Login<br>
                    <input type="text" name="login"><br>
                    Name<br>
                    <input type="text" name="name"><br>
                    Surname<br>
                    <input type="text" name="surname"><br>
                    Password<br>
                    <input type="password" name="password"><br>
                    <input type="submit" value="registration" name="registration">
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
