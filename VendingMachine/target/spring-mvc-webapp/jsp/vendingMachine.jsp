<%-- 
    Document   : vendingMachine
    Created on : May 26, 2019, 10:18:43 AM
    Author     : keb03_000
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <%-- 
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        --%>
        <title>Vending Machine</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body style="background-color: gray">
        <!-- Vending Machine Container -->
        <div clas="container">
            <h1 style="color: white" align="center">Vending Machine</h1>
            <hr/>
            
            <!-- Items -->
            <div class="col-md-8" align="center">
                <%-- 
                    JSTL forEach tag to process the Items in the List provided 
                    by the Controller 
                --%>
                <c:forEach var="itemList" items="${itemList}">
                    <%-- 
                        ${[pageContext.request.contextPath} is an EL expression
                        equivalent to the JSP expression <%= request.getContextPath() 
                            - Endpoint URL: /itemSelection/${itemList.itemNumber}
                    --%>
                    <a href="${pageContext.request.contextPath}/itemSelection/${itemList.itemNumber}" class="btn btn-info" style="height: 120px; width: 120px; margin-bottom: 40px; margin-left: 120px; color: white; background-color: salmon">
                        <p class="text-left">${itemList.itemNumber}</p>
                        <p class="text-center">${itemList.name}</p>
                        <p class="text-center">$${itemList.price}</p>
                        <p class="text-center">Quantity Left: ${itemList.quantity}</p>
                    </a> 
                </c:forEach>
            </div>
            
            <!-- Add Money -->
            <div id="addMoney" class="col-md-4">
                
                <!-- Total $ In Heading -->
                <div class="row">
                    <div class="col-md-12 text-center">
                        <h3 style="color: white">Total $ In</h3>
                        
                    </div>
                </div>
                
                <!-- Total $ In Input -->
                <div class="row">
                    <div class="col-md-12" align="center">
                        <%--
                            JSP EL
                                value="${deposit}" 
                            ${}
                                When the JSP compiler sees the ${} form in an 
                                attribute, it generates code to evaluate the
                                expression and substitutes the value of expression
                        --%>
                        <input class="text-center" type="text" id="moneyDeposited" value="$${deposit}" name="totalDollarsIn" placeholder="$0.00" align="center" readonly>
                    </div>
                </div>
                <br/>
                
                <!-- Add Dollar, Quarter, Dime, Nickel Buttons -->
                <div class="row">
                    <div class="col-md-6" align="center">
                        <%-- 
                            ${pageContext.request.contextPath) EL Expression 
                                - Endpoint URL: /depositMoney/addDollar
                        --%>
                        <a href="${pageContext.request.contextPath}/depositMoney/addDollar" class="btn btn-primary" style="height: 40px; width: 150px; margin-bottom: 20px; color: white; background-color: salmon">Add Dollar</a>
                        <%-- 
                            ${pageContext.request.contextPath) EL Expression 
                                - Endpoint URL: /depositMoney/addDime
                        --%>
                        <a href="${pageContext.request.contextPath}/depositMoney/addDime" class="btn btn-primary" style="height: 40px; width: 150px; color: white; background-color: salmon">Add Dime</a>
                    </div>
                    <div class="col-md-6" align="center">
                        <%-- 
                            ${pageContext.request.contextPath) EL Expression 
                                - Endpoint URL: /depositMoney/addQuarter
                        --%>
                        <a href="${pageContext.request.contextPath}/depositMoney/addQuarter" class="btn btn-primary" style="height: 40px; width: 150px; margin-bottom: 20px; color: white; background-color: salmon">Add Quarter</a>
                        <%-- 
                            ${pageContext.request.contextPath) EL Expression 
                                - Endpoint URL: /depositMoney/addNickel
                        --%>
                        <a href="${pageContext.request.contextPath}/depositMoney/addNickel" class="btn btn-primary" style="height: 40px; width: 150px; color: white; background-color: salmon">Add Nickel</a>
                    </div>
                </div>
                <hr/>
            </div>
                    
            <!-- Messages -->
            <div id="messages" class="col-md-4">
                
                <!-- Messages Heading -->
                <div class="row">
                    <div class="col-md-12 text-center">
                        <h3 style="color: white">Messages</h3>
                    </div>
                </div>
                
                <!-- Messages Input -->
                <div class="row">
                    <div class="col-md-12" align="center">
                        <%--
                            JSP EL
                                value="${messages}" 
                            ${}
                                When the JSP compiler sees the ${} form in an 
                                attribute, it generates code to evaluate the
                                expression and substitutes the value of expression
                        --%>
                        <input class="text-center" style="width: 250px;" type="text" value="${messages}" id="messageBox" name="messages" placeholder="Messages" align="center" readonly>
                    </div>
                </div>
                <br/>
                
                <!-- Item Label and Input -->
                <div class="row">
                    <div class="col-md-12" align="center">
                        <label for="itemSelectedBox" style="color: white">
                            Item:
                        </label>
                        <%--
                            JSP EL
                                value="${itemNumber}" 
                            ${}
                                When the JSP compiler sees the ${} form in an 
                                attribute, it generates code to evaluate the
                                expression and substitutes the value of expression
                        --%>
                        <input type="text" style="width: 50px;" name="itemSelectedBox" id="itemSelectedBox" value="${itemNumber}" placeholder="Item #" align="center" readonly>
                    </div>
                </div>
                <br/>
                
                <!-- Make Purchase Button -->
                <div class="row">
                    <div class="col-md-12" align="center">
                        <%-- 
                            ${pageContext.request.contextPath) EL Expression 
                                - Endpoint URL: /makePurchase
                        --%>
                        <a href="${pageContext.request.contextPath}/makePurchase" class="btn btn-success" style="height: 40px; width:120px; color: white; background-color: salmon">Make Purchase</a>
                    </div>
                </div>
                <hr/>
            </div>
            
            <div class="col-md-8">
            </div>
            
            <!-- Change Return -->
            <div id="changeReturn" class="col-md-4">
                
                <!-- Change Heading -->
                <div class="row">
                    <div class="col-md-12 text-center">
                        <h3 style="color: white">Change</h3>
                    </div>
                </div>
                
                <!-- Change Input -->
                <div class="row">
                    <div class="col-md-12" align="center">
                        <%--
                            JSP EL
                                value="${change}" 
                            ${}
                                When the JSP compiler sees the ${} form in an 
                                attribute, it generates code to evaluate the
                                expression and substitutes the value of expression
                        --%>
                        <input class="text-center" style="width: 300px;" id="changeBox" value="${change}" type="text" name="change" placeholder="$0.00" align="center" readonly>                        
                    </div>
                </div>
                <br/>
                
                <!-- Return Change Button -->
                <div class="row">
                    <div class="col-md-12" align="center">
                        <%-- 
                            ${pageContext.request.contextPath) EL Expression 
                                - Endpoint URL: /returnChange
                        --%>
                        <a href="${pageContext.request.contextPath}/returnChange" class="btn btn-danger" style="height: 40px; width: 120px; color: white; background-color: salmon">Return Change</a>
                    </div>
                </div>
            </div>
                    
        </div>
        <!-- Placed at the end of document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
