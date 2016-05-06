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
      <link rel="stylesheet" href="css/elements.css">
      <script type="text/javascript" src="js/main.js"></script>
  </head>
  <body>
        <%
            View.loginUserView(request);
            View.addFreeLoadView(request);
            View.updateLoadView(request);
            View.removeLoadView(request);
            View.addClientLoadView(request);
            View.addFreeTransportView(request);
            View.updateTransportView(request);
            View.removeTransportView(request);
            View.addClientTransportView(request);
        %>
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
                        <form class="form-inline" action="main.jsp" method="post">
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
            <div id="loads">
                <!--Load table-->
                <div class="big-name">Loads</div>
                <table class="table">
                    <tr>
                        <th>Stock number</th>
                        <th>Load weight</th>
                        <th>Cost of delivery</th>
                        <th>Load type</th>
                        <th>Description</th>
                        <% if (userType == UserType.DISPATCHER || userType == UserType.CLIENT) { %><th></th><% } %>
                    </tr>
                    <%  List<Load> loadList = View.viewFreeLoadList();
                        for (int i=0;i<loadList.size();i++) { %>
                    <tr>
                        <td><%=loadList.get(i).getLoadID()%></td>
                        <td><%=loadList.get(i).getWeight()%>t</td>
                        <td><%=loadList.get(i).getCostOfDelivery()%>$</td>
                        <td><%=loadList.get(i).getLoadType().getLoadTypeName()%></td>
                        <td><%=loadList.get(i).getLoadDescription()%></td>
                        <% if (userType == UserType.DISPATCHER) { %>
                            <td>
                                <form action="main.jsp" method="post">
                                    <button class="delete-button" type="submit" name="delete_load" value="<%=loadList.get(i).getLoadID()%>">Delete</button>
                                </form>
                            </td>
                        <% } else if (userType == UserType.CLIENT) { %>
                            <td>
                                <form action="main.jsp" method="post">
                                    <button class="add-button" type="submit" name="add_load" value="<%=loadList.get(i).getLoadID()%>">Add</button>
                                </form>
                            </td>
                        <% } %>
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
                                <div class="add-element">
                                    <div class="big-name"><b>Add load</b></div>
                                    <form class="form-inline" action="main.jsp" method="get">
                                        <div class="first-column">
                                            <input class="text-input" type="text" name="weight" placeholder="weight"><br>
                                            <input class="text-input" type="text" name="cost_of_delivery" placeholder="cost of delivery"><br>
                                            <textarea class="textarea-input" name="load_description" placeholder="description"></textarea>
                                        </div>
                                        <div class="second-column">
                                            <input type="radio" name="load_type" value="dangerous" title="Dangerous">Dangerous<br>
                                            <input type="radio" name="load_type" value="perishable" title="Perishable">Perishable<br>
                                            <input type="radio" name="load_type" value="superheavy" title="Superheavy">Superheavy<br>
                                            <input type="radio" name="load_type" value="alive" title="Alive">Alive<br>
                                            <input type="radio" name="load_type" value="bulky" title="Bulky">Bulky<br>
                                            <input class="button" type="submit" name="add_free_load" value="Add load">
                                        </div>
                                    </form>
                                </div>
                
                                <!--Update load-->
                                <div class="add-element">
                                    <div class="big-name"><b>Update load</b></div>
                                    <form class="form-inline" action="main.jsp" method="get">
                                        <div class="first-column">
                                            <select class="select" name="load_id" title="Stock number">
                                                <% for (int i=0; i<loadList.size(); i++) { %>
                                                    <option class="select" value="<%=loadList.get(i).getLoadID()%>"><%=loadList.get(i).getLoadID()%></option>
                                                <% } %>
                                            </select>
                                            <input class="text-input" type="text" name="weight" placeholder="weight"><br>
                                            <input class="text-input" type="text" name="cost_of_delivery" placeholder="cost of delivery"><br>
                                            <textarea class="textarea-input" name="load_description" placeholder="description"></textarea>
                                        </div>
                                        <div class="second-column">
                                            <input type="radio" name="load_type" value="dangerous" title="Dangerous">Dangerous<br>
                                            <input type="radio" name="load_type" value="perishable" title="Perishable">Perishable<br>
                                            <input type="radio" name="load_type" value="superheavy" title="Superheavy">Superheavy<br>
                                            <input type="radio" name="load_type" value="alive" title="Alive">Alive<br>
                                            <input type="radio" name="load_type" value="bulky" title="Bulky">Bulky<br>
                                            <input class="button" type="submit" name="update_load" value="Update load">
                                        </div>
                                    </form>
                                </div>

                                <div class="result"><%=View.addFreeLoadView(request)%></div><!--Add load result-->
                                <div class="result"><%=View.updateLoadView(request)%></div><!--Update load result-->
                                <div class="result"><%=View.removeLoadView(request)%></div><!--Remove load result-->

                                <% } break;
                                    case CLIENT: { %>
                                    <!--Add load to client order-->
                                    <div class="result"><%=View.addClientLoadView(request)%></div><!--Add load to client result-->
                                <% }
                            }
                        } %>
            </div>


            <div id="transports">
                <!--Transports table-->
                <div class="big-name">Transports</div>
                <table class="table">
                    <tr>
                        <th>State number</th>
                        <th>Model</th>
                        <th>Tonnage</th>
                        <th>Trailer type</th>
                        <th>Payment for kilometer</th>
                        <% if (userType == UserType.DISPATCHER || userType == UserType.CLIENT) { %><th></th><% } %>
                    </tr>
                    <%
                        List<Transport> transportList = View.viewFreeTransportList();
                        for (int i = 0; i< transportList.size(); i++) {
                    %>
                    <tr>
                        <td><%=transportList.get(i).getStateNumber()%></td>
                        <td><%=transportList.get(i).getModel()%></td>
                        <td><%=transportList.get(i).getTonnage()%>t</td>
                        <td><%=transportList.get(i).getTrailerType().getTrailerTypeName()%></td>
                        <td><%=transportList.get(i).getPaymentForKilometer()%>$</td>
                        <% if (userType == UserType.DISPATCHER) { %>
                        <td>
                            <form action="main.jsp" method="post">
                                <button class="delete-button" type="submit" name="delete_transport" value="<%=transportList.get(i).getStateNumber()%>">Delete</button>
                            </form>
                        </td>
                        <% } else if (userType == UserType.CLIENT) { %>
                            <td>
                                <form action="main.jsp" method="post">
                                    <button class="add-button" type="submit" name="add_transport" value="<%=transportList.get(i).getStateNumber()%>">Add</button>
                                </form>
                            </td>
                        <% } %>
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
                                <div class="add-element">
                                    <form class="form-inline" action="main.jsp" method="get">
                                        <div class="first-column">
                                            <input class="text-input" type="text" name="state_number" placeholder="state number"><br>
                                            <input class="text-input" type="text" name="model" placeholder="model"><br>
                                            <input class="text-input" type="text" name="tonnage" placeholder="tonnage"><br>
                                            <input class="text-input" name="payment_for_kilometer" placeholder="payment for kilometer">
                                        </div>
                                        <div class="second-column">
                                            <input type="radio" name="trailer_type" value="semi-trailer">Semi-trailer<br>
                                            <input type="radio" name="trailer_type" value="refrigerator" title="Refrigerator">Refrigerator<br>
                                            <input type="radio" name="trailer_type" value="open-platform">Open-platform<br>
                                            <input type="radio" name="trailer_type" value="road-train">Road train<br>
                                            <input type="radio" name="trailer_type" value="thermos">Thermos<br>
                                            <input type="radio" name="trailer_type" value="jumbo">G-type<br>
                                            <input class="button" type="submit" name="add_free_transport" value="Add transport"><br>
                                        </div>
                                    </form>
                                </div>

                                <!--Update transport-->
                                <div class="add-element">
                                    <form class="form-inline" action="main.jsp" method="get">
                                        <div class="first-column">
                                            <select class="select" name="state_number" title="Sate number">
                                                <% for (int i=0; i<transportList.size(); i++) { %>
                                                    <option class="select" value="<%=transportList.get(i).getStateNumber()%>"><%=transportList.get(i).getStateNumber()%></option>
                                                <% } %>
                                            </select>
                                            <input class="text-input" type="text" name="model" placeholder="model"><br>
                                            <input class="text-input" type="text" name="tonnage" placeholder="tonnage"><br>
                                            <input class="text-input" name="payment_for_kilometer" placeholder="payment for kilometer">
                                        </div>
                                        <div class="second-column">
                                            <input type="radio" name="trailer_type" value="semi-trailer">Semi-trailer<br>
                                            <input type="radio" name="trailer_type" value="refrigerator">Refrigerator<br>
                                            <input type="radio" name="trailer_type" value="open-platform">Open-platform<br>
                                            <input type="radio" name="trailer_type" value="road-train">Road train<br>
                                            <input type="radio" name="trailer_type" value="thermos">Thermos<br>
                                            <input type="radio" name="trailer_type" value="jumbo">G-type<br>
                                            <input class="button" type="submit" name="update_transport" value="Update transport"><br>
                                        </div>
                                    </form>
                                </div>

                                <div class="result"><%=View.addFreeTransportView(request)%></div><!--Add transport result-->
                                <div class="result"><%=View.updateTransportView(request)%></div><!--Update load result-->
                                <div class="result"><%=View.removeTransportView(request)%></div><!--Remove transport result-->

                                <% } break;
                                    case CLIENT: { %>
                                <!--Add transport to client's order form-->
                                <div class="result"><%=View.addClientTransportView(request)%></div><!--Add transport to client result-->
                                <% }
                                }
                            } %>
            </div>
        </div>

        <!--Bottom-->
        <div id="bottom-panel">
        </div>
  </body>
</html>
