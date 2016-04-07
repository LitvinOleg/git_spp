<%@ page import="classes.web.view.View" %>
<%@ page import="classes.web.entity.user.User" %><%--
  Created by IntelliJ IDEA.
  User: Олег
  Date: 26.03.2016
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
    <!--head-->
    <div id="head">

    </div>

    <!--content-->
    <div id="content">
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

        <div id="reg-result">
            <%=View.registerUserView(request, User.UserType.CLIENT)%>
        </div>
    </div>
</body>
</html>
