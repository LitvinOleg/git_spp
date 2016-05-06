<%@ page import="classes.web.entity.Load" %>
<%@ page import="java.util.List" %>
<%@ page import="classes.web.view.View" %>
<%@ page import="classes.web.entity.Transport" %>
<%@ page import="classes.web.entity.user.User.UserType" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <title>Client</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/client.css">
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
        <%  if (request.getSession().getAttribute("user_type") != null &&
                (UserType)request.getSession().getAttribute("user_type") == UserType.CLIENT) { %>
            <!--User's load table-->
            <div id="loads">
                <div class="big-name">Loads</div>
                <table class="table">
                    <tr>
                        <th>Load ID</th>
                        <th>Load weight</th>
                        <th>Cost of delivery</th>
                        <th>Load type</th>
                        <th>Description</th>
                        <th></th>
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
                        <td>
                            <form action="client_account.jsp" method="get">
                                <button class="delete-button" type="submit" name="delete_load" value="<%=loadList.get(i).getLoadID()%>">Delete</button>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </table><br><br>
            </div>

            <div class="result"><%=View.removeLoadView(request)%></div><!--Remove load result-->

            <!--User's transport table-->
            <div id="transports">
                <div class="big-name">Transports</div>
                <table class="table">
                    <tr>
                        <th>State number</th>
                        <th>Model</th>
                        <th>Tonnage</th>
                        <th>Trailer type</th>
                        <th>Payment for kilometer</th>
                        <th></th>
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
                        <td>
                            <form action="client_account.jsp" method="get">
                                <button class="delete-button" type="submit" name="delete_transport" value="<%=transportList.get(i).getStateNumber()%>">Delete</button>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </table><br><br>
            </div>

            <div class="result"><%=View.removeTransportView(request)%></div><!--Remove transport result-->
        <% } %>
    </div>

    <!--Bottom-->
    <div id="bottom-panel">
    </div>
</body>
</html>
