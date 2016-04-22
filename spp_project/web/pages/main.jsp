<%@ page import="classes.web.entity.Load" %>
<%@ page import="java.util.List" %>
<%@ page import="classes.web.view.View" %>
<%@ page import="classes.web.entity.Transport" %>
<%@ page import="static classes.web.entity.user.User.*" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
  <head>
      <title>Main</title>
      <meta charset="utf-8">
      <link rel="stylesheet" href="css/main.css">
      <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
  </head>
  <body>
        <!--Top-->
        <div id="top-panel">
            <a id="main" href="main.jsp">Main</a><br>
            <!--Login form-->
            <div id="login">
                <%  UserType userType;
                    if (request.getSession().getAttribute("user_type") != null)
                        userType = (UserType) request.getSession().getAttribute("user_type");
                    else
                        userType = null;
                    if (userType == null) { %>
                        <form class="form-inline" action="main.jsp" method="post">
                            <input class="form-control" type="text" name="login" placeholder="login">
                            <input class="form-control" type="password" name="password" placeholder="password">
                            <button class="btn" type="submit" name="enter" value="enter">Sign in</button>
                            <button class="btn" type="button" onclick="location.href='registration.jsp'">Registration</button><br>
                        </form>
                        <div id="result">
                            <%=View.loginUserView(request)%> <!--Result of login-->
                        </div>
                    <% } else if (userType != null) {
                        switch (userType) {
                            case ADMIN: { %> <button class="btn" type="button" onclick="location.href='admin_account.jsp'"><%= request.getSession().getAttribute("login")  %></button> <% } break;
                            case DISPATCHER: { %> <button class="btn" type="button" onclick="location.href='main.jsp'"><%= request.getSession().getAttribute("login")  %></button> <% } break;
                            case CLIENT: { %> <button class="btn" type="button" onclick="location.href='client_account.jsp'"><%= request.getSession().getAttribute("login")  %></button> <% }
                        } %>

                        <!--Exit button-->
                        <form class="form-inline" action="main.jsp" method="post">
                            <input class="btn" type="submit" name="exit" value="exit">
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
            <div id="loads">
                <!--Load table-->
                <h1>Loads</h1>
                <table class="table">
                    <tr>
                        <td><b>Load ID</b></td>
                        <td><b>Load weight</b></td>
                        <td><b>Cost of delivery</b></td>
                        <td><b>Load type</b></td>
                        <td><b>Description</b></td>
                    </tr>
                    <%  List<Load> loadList = View.viewFreeLoadList();
                        for (int i=0;i<loadList.size();i++) { %>
                    <tr>
                        <td><%=loadList.get(i).getLoadID()%></td>
                        <td><%=loadList.get(i).getWeight()%></td>
                        <td><%=loadList.get(i).getCostOfDelivery()%></td>
                        <td><%=loadList.get(i).getLoadType().getLoadTypeName()%></td>
                        <td><%=loadList.get(i).getLoadDescription()%></td>
                    </tr>
                    <% } %>
                </table>

                <!--Load add/remove-->
                <%
                    if (request.getSession().getAttribute("user_type") != null) {
                        userType = (UserType) request.getSession().getAttribute("user_type");
                        switch (userType) {
                            case DISPATCHER: { %>
                                <!--Add load-->
                                Add new load
                                <form action="main.jsp" method="get" class="form-group">
                                    <input type="text" name="weight" placeholder="weight"><br>
                                    <input type="text" name="cost_of_delivery" placeholder="cost of delivery"><br>
                                    <input type="radio" name="load_type" value="dangerous">Dangerous<br>
                                    <input type="radio" name="load_type" value="perishable">Perishable<br>
                                    <input type="radio" name="load_type" value="superheavy">Superheavy<br>
                                    <input type="radio" name="load_type" value="alive">Alive<br>
                                    <input type="radio" name="load_type" value="bulky">Bulky<br>
                                    <textarea name="load_description" placeholder="description"></textarea><br>
                                    <input type="submit" name="add_free_load" value="Add load"><br>
                                </form>
                                <%=View.addFreeLoadView(request)%><br><br>

                                <!--Remove load-->
                                Delete load
                                <form action="main.jsp" method="get" class="form-group">
                                    <input type="text" name="load_id" placeholder="load_id"><br>
                                    <input type="submit" name="delete_load" value="delete"><br>
                                </form>
                                <%=View.removeLoadView(request)%>

                                <% } break;
                                    case CLIENT: { %>
                                <!--Add load to client order-->
                                <form action="main.jsp" method="get" class="form-group">
                                    <input type="text" name="load_id" placeholder="load_id"><br>
                                    <input type="submit" name="add_load" value="Add load"><br>
                                </form>
                                <%=View.addClientLoadView(request)%>
                                <% }
                            }
                        } %>
            </div>


            <div id="transports">
                <!--Transports table-->
                <h1>Transports</h1>
                <table class="table">
                    <tr>
                        <td><b>State number</b></td>
                        <td><b>Model</b></td>
                        <td><b>Tonnage</b></td>
                        <td><b>Trailer type</b></td>
                        <td><b>Payment for kilometer</b></td>
                    </tr>
                    <%
                        List<Transport> transportList = View.viewFreeTransportList();
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
                </table>

                <!--Transport add/remove-->
                <%
                    if (request.getSession().getAttribute("user_type") != null) {
                        userType = (UserType) request.getSession().getAttribute("user_type");
                        switch (userType) {
                            case DISPATCHER: { %>
                                <!--Add transport by dispatcher form-->
                                Add new transport
                                <form action="main.jsp" method="get" class="form-group">
                                    <input type="text" name="state_number" placeholder="state number"><br>
                                    <input type="text" name="model" placeholder="model"><br>
                                    <input type="text" name="tonnage" placeholder="tonnage"><br>
                                    <textarea name="payment_for_kilometer" placeholder="payment for kilometer"></textarea><br>
                                    <input type="radio" name="trailer_type" value="semi-trailer">Semi-trailer<br>
                                    <input type="radio" name="trailer_type" value="refrigerator">Refrigerator<br>
                                    <input type="radio" name="trailer_type" value="open-platform">Open-platform<br>
                                    <input type="radio" name="trailer_type" value="road-train">Road train<br>
                                    <input type="radio" name="trailer_type" value="thermos">Thermos<br>
                                    <input type="radio" name="trailer_type" value="jumbo">G-type<br>
                                    <input type="submit" name="add_free_transport" value="Add transport"><br>
                                </form>
                                <%=View.addFreeTransportView(request)%><br><br>

                                <!--Remove load form for dispatcher-->
                                Delete load
                                <form action="main.jsp" method="get" class="form-group">
                                    <input type="text" name="state_number" placeholder="state number"><br>
                                    <input type="submit" name="delete_transport" value="delete"><br>
                                </form>
                                <%=View.removeTransportView(request)%>
                                <% } break;
                                    case CLIENT: { %>
                                <!--Add transport to client's order form-->
                                <form action="main.jsp" method="get" class="form-group">
                                    <input type="text" name="state_number" placeholder="state number"><br>
                                    <input type="submit" name="add_transport" value="Add transport"><br>
                                </form>
                                <%=View.addClientTransportView(request)%>
                                <% }
                                }
                            } %>
            </div>
        </div>


        <!--Bottom-->
        <div id="bottom-panel">
            <p align="center">&copy 2016 гр.351004</p>
        </div>
  </body>
</html>
