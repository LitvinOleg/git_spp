<%@ page import="java.web.view.View" %><%--
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
    <h1>Регистрация</h1>
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

    <div><%System.getProperty("java.classpath");%></div>
</body>
</html>
