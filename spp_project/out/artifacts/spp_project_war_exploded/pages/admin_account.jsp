<%@ page import="classes.web.view.View" %>
<%@ page import="classes.web.entity.user.User" %>
<%@ page import="classes.web.entity.user.User.UserType" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <title>Admin</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/admin.css">
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
        <% if (request.getSession().getAttribute("user_type") != null &&
                (UserType)request.getSession().getAttribute("user_type") == UserType.ADMIN) { %>
            <!--Table of users-->
            <div id="view_users">
                <table class="table">
                    <tr>
                        <th>User login</th>
                        <th>User name</th>
                        <th>User surname</th>
                        <th>User type</th>
                        <th></th>
                    </tr>
                    <%
                        List<User> userList = View.viewAllUsersView(request);
                        for (int i=0; i<userList.size(); i++) { %>
                    <tr>
                        <td><%=userList.get(i).getLogin()%></td>
                        <td><%=userList.get(i).getName()%></td>
                        <td><%=userList.get(i).getSurname()%></td>
                        <td><%=userList.get(i).getUserType().getUserType()%></td>
                        <td>
                            <form action="admin_account.jsp" method="get">
                                <button class="delete-button" type="submit" name="delete_user" value="<%=userList.get(i).getLogin()%>">Delete</button>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </table>
            </div>

            <!--Registration form for admins and dispatchers-->
            <div id="registration">
                <div class="big-name">Register user</div>
                <form action="admin_account.jsp" method="post">
                    <input class="text-input" type="text" name="login" placeholder="login"><br>
                    <input class="text-input" type="text" name="name" placeholder="user name"><br>
                    <input class="text-input" type="text" name="surname" placeholder="user surname"><br>
                    <input class="text-input" type="password" name="password" placeholder="password"><br>
                    <input type="radio" name="user_type" value="admin" title="Admin">Admin<br>
                    <input type="radio" name="user_type" value="dispatcher" title="Dispatcher">Dispatcher<br>
                    <input class="button" type="submit" name="registration" value="registration"><br>
                </form>
                <%
                    if (request.getParameter("registration") != null ) {
                        if (request.getParameter("user_type") != null &&
                                request.getParameter("user_type").equals("admin"))
                            userType = UserType.ADMIN;
                        else if (request.getParameter("user_type") != null &&
                                request.getParameter("user_type").equals("dispatcher"))
                            userType = UserType.DISPATCHER;
                        else { %>
                <%="Not all fields are field!"%>
                <% } %>
                <%=View.registerUserView(request, userType)%>
                <% } %>
            </div><br><br><br>
            <div class="result"><%=View.removeUserView(request)%></div>
        <% } %>
    </div>


    <!--Bottom-->
    <div id="bottom-panel">
    </div>
</body>
</html>
