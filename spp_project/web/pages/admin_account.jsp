<%@ page import="classes.web.view.View" %>
<%@ page import="classes.web.entity.user.User" %>
<%@ page import="classes.web.entity.user.User.UserType" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <title>Admin</title>
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
                    <form action="admin_account.jsp" method="post">
                        <input type="text" name="login" placeholder="login"><br>
                        <input type="password" name="password" placeholder="password"><br>
                        <input type="submit" name="enter" value="enter">
                    </form>
                    <div id="result">
                        <%=View.loginUserView(request)%> <!--Result of login-->
                    </div>
                <a href="admin_account.jsp">Registration</a><br>
                <% } else if (userType != null) {
                    switch (userType) {
                        case ADMIN: { %> <a href="admin_account.jsp"><%= request.getSession().getAttribute("login")  %></a> <% } break;
                        case DISPATCHER: { %> <a href="main.jsp"><%= request.getSession().getAttribute("login")  %></a> <% } break;
                        case CLIENT: { %> <a href="client_account.jsp"><%= request.getSession().getAttribute("login")  %></a> <% }
                } %>

                <!--Exit button-->
                <form action="admin_account.jsp" method="post">
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
        <% if (request.getSession().getAttribute("user_type") != null &&
                (UserType)request.getSession().getAttribute("user_type") == UserType.ADMIN) { %>
            <!--Table of users-->
            <div id="view_users">
                <table>
                    <tr>
                        <td>User login</td>
                        <td>User name</td>
                        <td>User type</td>
                    </tr>
                    <%
                        List<User> userList = View.viewAllUsersView(request);
                        for (int i=0; i<userList.size(); i++) { %>
                    <tr>
                        <td><%=userList.get(i).getLogin()%></td>
                        <td><%=userList.get(i).getName()%></td>
                        <td><%=userList.get(i).getUserType().getUserType()%></td>
                    </tr>
                    <% } %>
                </table>
            </div>

            <!--Registration form for admins and dispatchers-->
            <div id="registration">
                Register user
                <form action="admin_account.jsp" method="post">
                    <input type="text" name="login" placeholder="login"><br>
                    <input type="text" name="name" placeholder="user name"><br>
                    <input type="text" name="surname" placeholder="user surname"><br>
                    <input type="password" name="password" placeholder="password"><br>
                    <input type="radio" name="user_type" value="admin">Admin<br>
                    <input type="radio" name="user_type" value="dispatcher">Dispatcher<br>
                    <input type="submit" name="registration" value="registration"><br>
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

            <!--Delete user form-->
            <div id="delete">
                Delete user
                <form action="admin_account.jsp" method="get">
                    <input type="text" name="login" placeholder="login"><br>
                    <input type="submit" name="delete" value="delete"><br>
                </form>
                <%=View.removeUserView(request)%>
            </div>
        <% } %>
    </div>


    <!--Bottom-->
    <div id="bottom-panel">
    </div>
</body>
</html>
