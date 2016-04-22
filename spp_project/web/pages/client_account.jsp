<%@ page import="classes.web.entity.Load" %>
<%@ page import="java.util.List" %>
<%@ page import="classes.web.view.View" %>
<%@ page import="classes.web.entity.Transport" %>
<%@ page import="classes.web.entity.user.User.UserType" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <title>Account</title>
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
                    <form action="client_account.jsp" method="post">
                        <input type="text" name="login" placeholder="login"><br>
                        <input type="password" name="password" placeholder="password"><br>
                        <input type="submit" name="enter" value="enter">
                    </form>
                    <div id="result">
                        <%=View.loginUserView(request)%> <!--Result of login-->
                    </div>
                    <a href="client_account.jsp">Registration</a><br>
                <% } else if (userType != null) {
                    switch (userType) {
                        case ADMIN: { %> <a href="admin_account.jsp"><%= request.getSession().getAttribute("login")  %></a> <% } break;
                        case DISPATCHER: { %> <a href="main.jsp"><%= request.getSession().getAttribute("login")  %></a> <% } break;
                        case CLIENT: { %> <a href="client_account.jsp"><%= request.getSession().getAttribute("login")  %></a> <% }
                    } %>

                    <!--Exit button-->
                    <form action="client_account.jsp" method="post">
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
        <%  if (request.getSession().getAttribute("user_type") != null &&
                (UserType)request.getSession().getAttribute("user_type") == UserType.CLIENT) { %>

            <!--User name-->
            <div id="user_name">
            <% if (request.getSession().getAttribute("login") != null) { %>
            <%= request.getSession().getAttribute("login")  %>
            <% } %>
            </div>

            <!--User's load table-->
            <div id="loads">
                <h1>Loads</h1>
                <table>
                    <tr>
                        <td>Load ID</td>
                        <td>Load weight</td>
                        <td>Cost of delivery</td>
                        <td>Load type</td>
                        <td>Description</td>
                        <td></td>
                    </tr>
                    <%
                        List<Load> loadList = View.viewClientLoadList((String)request.getSession().getAttribute("login"));
                        for (int i=0;i<loadList.size();i++) {
                    %>
                    <tr>
                        <td><%=loadList.get(i).getLoadID()%></td>
                        <td><%=loadList.get(i).getWeight()%></td>
                        <td><%=loadList.get(i).getCostOfDelivery()%></td>
                        <td><%=loadList.get(i).getLoadType().getLoadTypeName()%></td>
                        <td><%=loadList.get(i).getLoadDescription()%></td>
                    </tr>
                    <% } %>
                </table><br><br>
            </div>

            <!--Remove load form-->
            <div id="remove_load">
                <form action="client_account.jsp" method="get">
                    <input type="text" name="load_id" placeholder="load id"><br>
                    <input type="submit" name="delete_load" value="delete">
                </form>
                <%=View.removeLoadView(request)%>
            </div><br>

            <!--User's transport table-->
            <div id="transports">
                <h1>Transports</h1>
                <table>
                    <tr>
                        <td>State number</td>
                        <td>Model</td>
                        <td>Tonnage</td>
                        <td>Trailer type</td>
                        <td>Payment for kilometer</td>
                    </tr>
                    <%
                        List<Transport> transportList = View.viewClientTransportList((String)request.getSession().getAttribute("login"));
                        for (int i = 0; i< transportList.size(); i++) {
                    %>
                    <tr>
                        <td><%=transportList.get(i).getStateNumber()%></td>
                        <td><%=transportList.get(i).getModel()%></td>
                        <td><%=transportList.get(i).getTonnage()%></td>
                        <td><%=transportList.get(i).getTrailerType().getTrailerTypeName()%></td>
                        <td><%=transportList.get(i).getPaymentForKilometer()%></td>
                    </tr>
                    <% } %>
                </table><br><br>
            </div>

            <!--Remove transport form-->
            <div id="remove_transport">
                <form action="client_account.jsp" method="post">
                    <input type="text" name="state_number" placeholder="state_number"><br>
                    <input type="submit" name="delete_transport" value="delete">
                </form>
                <%=View.removeTransportView(request)%>
            </div><br>
        <% } %>
    </div>


    <!--Bottom-->
    <div id="bottom-panel">
    </div>
</body>
</html>
