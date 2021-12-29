

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
        <script>
            function deleteCake() {
                var result = confirm("Remove this cake from cart?");

                if (result) {
                    document.getElementById("myForm").submit();
                } else {

                }
            }
        </script>
    </head>
    <body>
        <h1>Your Order Details: </h1>
        <c:url var="back" value="MainController">
            <c:param name="btnAction" value="Search"></c:param>
            <c:param name="txtSearch" value="${param.txtSearch}"></c:param>
            <c:param name="txtCheckInDate" value="${param.txtCheckInDate}"></c:param>
            <c:param name="txtCheckOutDate" value="${param.txtCheckOutDate}"></c:param>
            <c:param name="txtAmountRoom" value="${param.txtAmountRoom}"></c:param>
        </c:url>
        <a href="${back}">Back to shopping</a><br/>
        ${requestScope.MESSAGE}
        <c:if test="${sessionScope.CART != null}">
            <c:set var="total" value="0"></c:set>
            <c:set var="cart" value="${sessionScope.CART}"></c:set>
            <c:forEach var="cartDTO" items="${cart.cart.values()}">
                <input type="hidden" name="" value="${total = total + cartDTO.total}" />
                <div id="container">
                    <div id="room_img">
                        <img src="<c:url value="/images/${cartDTO.roomImg}" />" width="180" height="150">
                    </div>
                    <div id="room_des">
                        <span>Hotel name: ${cartDTO.hotelName}</span>
                        <span>Description</span><br/>
                        <span>${cartDTO.roomDescripton}</span><br/>
                    </div>
                    <form action="MainController">
                        <div id="room_quantity">
                            <span>Quantity</span><br/>
                            <span><input type="number" onkeyup="if (this.value < 0) {
                                        this.value = this.value * -1
                                    }" min="1" max="30" name="txtQuantity" value="${cartDTO.quantity}" /></span><br/>
                        </div>
                        <div id="room_price">
                            <span>Price for one night</span><br/>
                            <span>${cartDTO.roomPrice}</span><br/>
                        </div>
                        <div id="room_total_price">
                            <span>Total</span><br/>
                            <span>${cartDTO.total}</span><br/>
                        </div>

                        <div id="update">
                            <input type="hidden" name="txtRoomID" value="${cartDTO.roomID}"/>
                            <input type="hidden" name="txtCheckInDate" value="${param.txtCheckInDate}"/>
                            <input type="hidden" name="txtCheckOutDate" value="${param.txtCheckOutDate}"/>
                            <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                            <input type="hidden" name="txtAmountRoom" value="${param.txtAmountRoom}"/>
                            <input id="btn" type="submit" name="btnAction" value="Update"/>
                        </div>
                    </form>
                    <!--                    </form>
                                        <form action="MainController" id="myForm">-->
                    <form action="MainController" onsubmit="return confirm('Do you want to remove this from cart')">
                        <div id="delete">

                            <input type="hidden" name="txtDeleteRoomID" value="${cartDTO.roomID}"/>
                            <input type="hidden" name="txtCheckInDate" value="${param.txtCheckInDate}"/>
                            <input type="hidden" name="txtCheckOutDate" value="${param.txtCheckOutDate}"/>
                            <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                            <input type="hidden" name="txtAmountRoom" value="${param.txtAmountRoom}"/>
                            <input type="submit" name="btnAction" value="Delete" />
                            <!--<input id="btn" type="button" value="Delete" onclick="deleteCake()"/>-->
                        </div>
                    </form>
                </div>                   
            </c:forEach>
            <c:if test="${sessionScope.DISCOUNT == null}">
                <form action="MainController">
                    Promotion Code: <input type="text" name="txtPromotion" value="${param.txtPromotion}"/>
                    <input type="hidden" name="txtSearch" value="${param.txtSearch}" />
                    <input type="hidden" name="txtCheckInDate" value="${param.txtCheckInDate}"/>
                    <input type="hidden" name="txtCheckOutDate" value="${param.txtCheckOutDate}"/>
                    <input type="hidden" name="txtAmountRoom" value="${param.txtAmountRoom}"/>
                    <input type="submit" name="btnAction" value="Use code"/>
                </form>
            </c:if>
            <c:if test="${sessionScope.DISCOUNT != null}">
                Promotion Code: <input type="text" name="txtPromotion" value="${param.txtPromotion}" disabled/>
                <input type="submit" name="btnAction" value="Use code" disabled/>
            </c:if>
        </form>
        <c:if test="${sessionScope.DISCOUNT == null}">
            <h1>Total: ${total}</h1>
        </c:if>
        <c:if test="${sessionScope.DISCOUNT != null}">
            <h1>Total: ${total - total * sessionScope.DISCOUNT}</h1>
        </c:if>
        <form action="MainController">
            <input type="submit" name="btnAction" value="Get code" />
        </form>
        <form action="MainController">
            <input type="text" name="txtCodeConfirm" value="${param.txtCodeConfirm}"/>
            <input type="hidden" name="txtTotal" value="${total}" />
            <input type="submit" name="btnAction" value="Checkout" />
        </form>
    </c:if>
</body>
</html>
