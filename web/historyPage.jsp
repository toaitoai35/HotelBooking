

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            #container {
                float: left;
                width: 80%;
                margin: 1% 10%;
                border: 1px solid rgb(199, 197, 197);
                border-radius: 5px;
                height: 170px;
            }

            #room_img {
                float: left;
                width: 15%;
                margin: 0.1cm;
            }

            #room_des {
                float: left;
                width: 20%;
                margin: 0.1cm;
                border-left: 1px solid rgb(199, 197, 197);
                height: 170px;
            }

            #room_quantity {
                float: left;
                width: 10%;
                margin: 0.1cm;
                border-left: 1px solid rgb(199, 197, 197);
                height: 170px;
            }

            #room_price {
                float: left;
                width: 10%;
                margin: 0.1cm;
                border-left: 1px solid rgb(199, 197, 197);
                height: 170px;
            }

            #room_total_price {
                float: left;
                width: 10%;
                margin: 0.1cm;
                border-left: 1px solid rgb(199, 197, 197);
                height: 170px;
            }

            #update {
                float: left;
                width: 10%;
                border-left: 1px solid rgb(199, 197, 197);
                height: 170px;
            }

            #delete {
                float: left;
                width: 10%;
                border-left: 1px solid rgb(199, 197, 197);
                height: 170px;
            }

            #btn {
                margin: 50% 50%;
            }
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_INFO == null}">
            <a href="login.jsp">Login</a>
        </c:if>

        <c:if test="${sessionScope.LOGIN_INFO != null}">
            <c:if test="${sessionScope.LOGIN_INFO.role != 'User'}">
                <c:redirect url="login.jsp"></c:redirect>
            </c:if>

            <c:if test="${sessionScope.LOGIN_INFO.role == 'User'}">
                <h1>Welcome, ${sessionScope.LOGIN_INFO.username}</h1>
                <c:url var="logout" value="MainController">
                    <c:param name="btnAction" value="Logout"></c:param>
                </c:url>
            </c:if>

            &nbsp;<a href="${logout}">Logout</a> <br/>

            <c:url var="back" value="MainController">
                <c:param name="btnAction" value="Search"></c:param>
                <c:param name="txtSearch" value="${param.txtSearch}"></c:param>
                <c:param name="txtCheckInDate" value="${param.txtCheckInDate}"></c:param>
                <c:param name="txtCheckOutDate" value="${param.txtCheckOutDate}"></c:param>
                <c:param name="txtAmountRoom" value="${param.txtAmountRoom}"></c:param>
            </c:url>
            <a href="${back}">Back to shopping</a>

            <form action="MainController">
                Search: <input type="text" name="txtSearchOrder" value="${param.txtSearchOrder}" /><br/>
                CheckIN <input id="txtDate" type="date" name="txtHistoryCheckInDate" value="${param.txtCheckInDate}">
                <input type="hidden" name="txtAmountRoom" value="${param.txtAmountRoom}"/>
                <input type="hidden" name="txtCheckInDate" value="${param.txtCheckInDate}"/>
                <input type="hidden" name="txtCheckOutDate" value="${param.txtCheckOutDate}"/>
                <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                <input type="submit" value="SearchOrder" name="btnAction"/>
            </form>

            ${requestScope.MESSAGE}
            <c:set var="orderList" value="${requestScope.ORDER_LIST}"/>
            <c:forEach var="items" items="${orderList}">
                <div id="container">
                    <div id="room_img">
                        <img src="<c:url value="/images/${items.hotelImg}" />" width="180" height="150">
                    </div>
                    <div id="room_des">
                        <span>Hotel name: ${items.hotelName}</span>
                    </div>
                    <div id="room_quantity">
                        <span>Check In Date</span><br/>
                        <span>${items.checkInDate}</span><br/>
                    </div>
                    <div id="room_price">
                        <span>Check Out Date</span><br/>
                        <span>${items.checkOutDate}</span><br/>
                    </div>
                    <div id="room_total_price">
                        <span>Total</span><br/>
                        <span>${items.total}</span><br/>
                    </div>
                    <div id="update">
                        <span>Total Room</span>
                        <span>${items.quantity}</span>
                    </div>
                    <form action="MainController">
                        <div id="delete">
                            <input type="hidden" name="txtOrderID" value="${items.orderID}"/>
                            <input type="hidden" name="txtCheckInDate" value="${param.txtCheckInDate}"/>
                            <input type="hidden" name="txtCheckOutDate" value="${param.txtCheckOutDate}"/>
                            <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                            <input type="hidden" name="txtSearchOrder" value="${param.txtSearchOrder}"/>
                            <input type="hidden" name="txtAmountRoom" value="${param.txtAmountRoom}"/>
                            <input type="submit" name="btnAction" value="Remove" />
                        </div>
                    </form>
                </div>
            </c:forEach>

        </c:if>
    </body>
</html>
