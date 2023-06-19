<%-- 
    Document   : CheckoutSuccessfully
    Created on : Mar 11, 2023, 2:39:33 AM
    Author     : CONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success</title>
    </head>
    <body>
        <a href="loginPage">Home</a> <br>
        <a href="storeView">Shopping again</a>
        <c:set var="bill" value="${sessionScope.BILL}"/>
        <c:if test="${not empty bill}">
            <h1>You are checkout successfully</h1>
            <p>Here your bill</p>
            <c:set var="dtoOrder" value="${bill.dtoOrder}"/>
            <c:if test="${not empty dtoOrder}">
                <div>
                    <p style="font-weight: bold;">Order ID:
                        <span style="font-weight: normal;">:${dtoOrder.orderID}</span>
                    </p>
                    
                    <p style="font-weight: bold;">Username
                        <span style="font-weight: normal;">:${dtoOrder.name}</span>
                    </p>
                    
                    <p style="font-weight: bold;">Address:
                        <span style="font-weight: normal;">${dtoOrder.address}</span>
                    </p>
                    
                    <p style="font-weight: bold;">Time order: 
                        <span style="font-weight: normal;">
                            <fmt:formatDate value="${dtoOrder.date}" 
                                            pattern="yyyy-MM-dd HH:mm:ss"/></span>
                    </p>
                </div>
            </c:if>

            <c:set var="cart" value="${bill.cart}"/>
            <c:if test="${not empty cart}">
                <c:set var="cartItem" value="${cart.items}"/>
                <c:if test="${not empty cartItem}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Product Name</th>
                                <th>Quantity</th>
                                <th>Product Price</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${cartItem.entrySet()}" 
                                       varStatus="counter">
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${item.value.productName}
                                    </td>
                                    <td>
                                        ${item.value.productPrice}$
                                    </td>
                                    <td>
                                        ${item.value.productQuantity}
                                    </td>
                                    <td style="color:red;">
                                        <fmt:formatNumber value="${item.value.totalPrice}" 
                                                      maxFractionDigits="1" />$
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <h2 style="color:red;">
                        Total price: <fmt:formatNumber value="${cart.totalPrice}" 
                                                      maxFractionDigits="1" />$
                    </h2>
                </c:if>
            </c:if>
        </c:if>
            <c:if test="${empty bill}">
                <h2 style="color:red;">No Bill existed</h2>
            </c:if>

</body>
</html>
