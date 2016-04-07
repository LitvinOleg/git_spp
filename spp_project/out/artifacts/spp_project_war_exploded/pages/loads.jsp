<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="classes.web.view.View" %>
<%@ page import="classes.web.view.exception.ViewException" %>
<%@ page import="classes.web.entity.Load" %><%--
  Created by IntelliJ IDEA.
  User: Олег
  Date: 26.03.2016
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Загрузки</title>

    <h1>Loads</h1>
    <table>
        <tr>
            <td>Load ID</td>
            <td>Load weight</td>
            <td>Cost of delivery</td>
            <td>Load type</td>
            <td>Description</td>
        </tr>
        <%
            List<Load> loadList = View.viewFreeLoadList();
            for (int i=0;i<loadList.size();i++) {
        %>
        <tr>
            <td><%=loadList.get(i).getLoadID()%></td>
            <td><%=loadList.get(i).getWeight()%></td>
            <td><%=loadList.get(i).getCostOfDelivery()%></td>
            <td><%=loadList.get(i).getLoadType().getLoadTypeName()%></td>
            <td><%=loadList.get(i).getLoadDescription()%></td>
        </tr>
        <%}%>
    </table>
</head>
<body>

</body>
</html>
