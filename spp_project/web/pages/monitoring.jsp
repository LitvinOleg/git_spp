<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="classes.web.view.View" %>
<%@ page import="classes.web.view.exception.ViewException" %>
<%@ page import="classes.web.entity.Load" %>
<%@ page import="classes.web.entity.Transport" %>
<%@ page import="classes.web.entity.user.User" %>
<%@ page import="static classes.web.entity.user.User.*" %><%--
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
</head>
<body>

    <div id="loads">
        <!--Load table-->
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
            <% } %>
        </table>

        <!--Load add/remove-->
        <%
            UserType userType;
            if (request.getSession().getAttribute("user_type") != null) {
                userType = (UserType) request.getSession().getAttribute("user_type");
                switch (userType) {
                    case DISPATCHER: { %>
                        <!--Add load-->
                        Add new load
                        <form action="monitoring.jsp" method="get">
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
                        <form action="monitoring.jsp" method="get">
                            <input type="text" name="load_id" placeholder="load_id"><br>
                            <input type="submit" name="delete_load" value="delete"><br>
                        </form>
                        <%=View.removeLoadView(request)%>

                    <% } break;
                    case CLIENT: { %>
                        <!--Add load to client order-->
                        <form action="monitoring.jsp" method="get">
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
        <table>
            <tr>
                <td>State number</td>
                <td>Model</td>
                <td>Tonnage</td>
                <td>Trailer type</td>
                <td>Payment for kilometer</td>
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
                            <form action="monitoring.jsp" method="get">
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
                            <form action="monitoring.jsp" method="get">
                                <input type="text" name="state_number" placeholder="state number"><br>
                                <input type="submit" name="delete_transport" value="delete"><br>
                            </form>
                            <%=View.removeTransportView(request)%>
                        <% } break;
                        case CLIENT: { %>
                            <!--Add transport to client's order form-->
                            <form action="monitoring.jsp" method="get">
                                <input type="text" name="state_number" placeholder="state number"><br>
                                <input type="submit" name="add_transport" value="Add transport"><br>
                            </form>
                            <%=View.addClientTransportView(request)%>
                        <% }
                    }
                }
            %>
    </div>
</body>
</html>
