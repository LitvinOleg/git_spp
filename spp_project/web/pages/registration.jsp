<%@ page import="classes.web.view.View" %>
<%@ page import="static classes.web.entity.user.User.*" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <link rel="stylesheet" href="css/regStyle.css">
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
                <form class="regForm" action="registration.jsp" method="post">
                    <div class="form_left-block">
                        <p><label for="forName">Имя</label></p>
                        <p><label for="forSurname">Фамилия</label></p>
                        <p><label for="forLogin">Логин</label></p>	<br>
                        <p><label for="forPassword">Пароль</label></p>
                        <p><label for="forRepeat">Повторите</label></p>
                    </div>
                    <div class="form_right-block">
                        <input type="text" id="forName" name="name" required> <span></span><br>
                        <input type="text" id="forSurname" name="surname" required> <span></span><br>
                        <input type="text" id="forLogin" name="login" required> <span></span><br>
                        <input type="password" id="forPassword" name="password" required> <span></span><br>
                        <input type="password" id="forRepeat" name="password" required> <span></span><br>
                    </div>
                    <div class="regButt">
                        <button type="submit" value="registration" name="registration">Registration</button>
                    </div>
                </form>

            <!--Result of registration-->
            <div id="reg-result">
                <%=View.registerUserView(request, UserType.CLIENT)%>
            </div>
    </div>


    <!--Bottom-->
    <div id="bottom-panel">
        <p align="center">&copy 2016 гр.351004</p>
    </div>
</body>
</html>
