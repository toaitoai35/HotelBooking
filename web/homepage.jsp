

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <style>
            #container{
                float: left;
                width: 80%;
                margin: 1% 10%;
                border: 1px solid rgb(199, 197, 197);
                border-radius: 5px;
                height: 170px;
            }
            #img{
                float: left;
                width: 30%;
                margin: 0.1cm;
            }

            #text{
                float: left;
                width: 50%;
                margin: 0.1cm;
            }

            #action{
                float: left;
                width: 10%;
                border-left: 1px solid rgb(199, 197, 197);
                height: 170px;
            }
            #btn {
                margin: 50% 50%;
            }

            #room_img{
                float: left;
                width: 20%;
                margin: 0.1cm;
            }

            #room_des{
                float: left;
                width: 40%;
                margin: 0.1cm;
                border-left: 1px solid rgb(199, 197, 197);
                height: 170px;
            }

            #room_type{
                float: left;
                width: 10%;
                margin: 0.1cm;
                border-left: 1px solid rgb(199, 197, 197);
                height: 170px;
            }

            #room_price{
                float: left;
                width: 10%;
                margin: 0.1cm;
                border-left: 1px solid rgb(199, 197, 197);
                height: 170px;
            }
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                var dtToday = new Date();

                var tomorrow = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
                var tomday = tomorrow.getDate();
                var tommonth = tomorrow.getMonth() + 1;
                var tomyear = tomorrow.getFullYear();

                var month = dtToday.getMonth() + 1;
                var day = dtToday.getDate();
                var year = dtToday.getFullYear();
                if (month < 10)
                    month = '0' + month.toString();
                if (day < 10)
                    day = '0' + day.toString();

                if (tomday < 10) {
                    tomday = '0' + tomday
                }
                if (tommonth < 10) {
                    tommonth = '0' + tommonth
                }

                var tomorrowDate = tomyear + '-' + tommonth + '-' + tomday;

                var maxDate = year + '-' + month + '-' + day;

                // or instead:
                // var maxDate = dtToday.toISOString().substr(0, 10);

                $('#txtDate').attr('min', maxDate);
                $('#txtDateOut').attr('min', tomorrowDate);

//                if ($('#txtDate').val() === "" && $('#txtDateOut').val() === "") {
//                    $('#txtDate').val(maxDate);
//                    $('#txtDateOut').val(tomorrowDate);
//                }

                var amout = $('#txtAmountRoom').val();
                if (amout === "") {
                    $('#txtAmountRoom').val(1)
                }
            })
        </script>
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
                <c:url var="history" value="historyPage.jsp">
                    <c:param name="txtSearch" value="${param.txtSearch}"></c:param>
                    <c:param name="txtCheckInDate" value="${param.txtCheckInDate}"></c:param>
                    <c:param name="txtCheckOutDate" value="${param.txtCheckOutDate}"></c:param>
                    <c:param name="txtAmountRoom" value="${param.txtAmountRoom}"></c:param>
                </c:url>
            </c:if> 
            <a href="${logout}">Logout</a>
            <a href="${history}">History</a>   
        </c:if>

        <c:if test="${sessionScope.CART != null}">
            <c:url var="view" value="MainController">
                <c:param name="btnAction" value="View"></c:param>
                <c:param name="txtSearch" value="${param.txtSearch}"></c:param>
                <c:param name="txtCheckInDate" value="${param.txtCheckInDate}"></c:param>
                <c:param name="txtCheckOutDate" value="${param.txtCheckOutDate}"></c:param>
                <c:param name="txtAmountRoom" value="${param.txtAmountRoom}"></c:param>
            </c:url>
            <a href="${view}">Cart</a>
        </c:if>     

        <form action="MainController">
            ${requestScope.SEARCH_ERROR}<br/>
            Search: <input type="text" name="txtSearch" value="${param.txtSearch}" /><br/>
            CheckIN <input id="txtDate" type="date" name="txtCheckInDate" value="${param.txtCheckInDate}">
            &nbsp;CheckOUT <input id="txtDateOut" type="date" name="txtCheckOutDate" value="${param.txtCheckOutDate}">
            Amount Room: <input id="txtAmountRoom" min="1" type="number" onkeyup="if (this.value < 0) {
                                this.value = this.value * -1
                            }" name="txtAmountRoom" value="${param.txtAmountRoom}"/><br/>
            <input type="submit" value="Search" name="btnAction"/>
        </form>    

        <c:if test="${not empty requestScope.HOTEL_LIST}">
            <c:forEach var="hotelDTO" items="${requestScope.HOTEL_LIST}">
                <div id="container">
                    <div id="img">
                        <img src="<c:url value="/images/${hotelDTO.hotelImg}" />" width="300" height="150">
                    </div>
                    <div id="text">
                        <span>${hotelDTO.hotelName}</span><br/>
                        Address: <span>${hotelDTO.hotelAddress}</span><br/>
                    </div>
                    <div id="action">
                        <form action="MainController">
                            <input type="hidden" name="txtHotelID" value="${hotelDTO.hotelID}"/>
                            <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                            <input type="hidden" name="txtCheckInDate" value="${param.txtCheckInDate}"/>
                            <input type="hidden" name="txtCheckOutDate" value="${param.txtCheckOutDate}"/>
                            <input type="hidden" name="txtAmountRoom" value="${param.txtAmountRoom}"/>
                            <input id="btn" type="submit" name="btnAction" value="Select room"/>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </c:if>

        <c:if test="${not empty requestScope.ROOM_LIST}">
            <c:set var="checkOutDate" value="${param.txtCheckOutDate}"></c:set>
            <c:set var="checkInDate" value="${param.txtCheckInDate}"></c:set>
            <c:forEach var="roomDTO" items="${requestScope.ROOM_LIST}">
                <div id="container">
                    <div id="room_img">
                        <img src="<c:url value="/images/${roomDTO.roomImg}" />" width="200" height="150">
                    </div>
                    <div id="room_des">
                        <span>Description</span><br/>
                        <span>${roomDTO.roomDescription}</span><br/>
                    </div>
                    <div id="room_type">
                        <span>Type:</span><br/>
                        <span>${roomDTO.roomType}</span><br/>
                    </div>
                    <div id="room_price">
                        <span>Price for one night</span><br/>
                        <span>${roomDTO.roomPrice}</span><br/>
                    </div>
                    <div id="action">
                        <c:if test="${sessionScope.LOGIN_INFO != null}">
                            <form action="MainController">
                                <input type="hidden" name="txtRoomID" value="${roomDTO.roomID}"/>
                                <input type="hidden" name="txtRoomDescription" value="${roomDTO.roomDescription}"/>
                                <input type="hidden" name="txtRoomImg" value="${roomDTO.roomImg}"/>
                                <input type="hidden" name="txtRoomType" value="${roomDTO.roomType}"/>
                                <input type="hidden" name="txtRoomPrice" value="${roomDTO.roomPrice}"/>
                                <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                                <input type="hidden" name="txtHotelID" value="${roomDTO.hotelID}"/>
                                <input type="hidden" name="txtCheckInDate" value="${param.txtCheckInDate}"/>
                                <input type="hidden" name="txtCheckOutDate" value="${param.txtCheckOutDate}"/>
                                <input type="hidden" name="txtAmountRoom" value="${param.txtAmountRoom}"/>
                                <input id="btn" type="submit" name="btnAction" value="Add To Cart"/>
                            </form>
                        </c:if>
                        <c:if test="${sessionScope.LOGIN_INFO == null}">
                            <input id="btn" type="submit" name="btnAction" value="Add To Cart" disabled/>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </body>
</html>
