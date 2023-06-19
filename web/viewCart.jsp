<%-- 
    Document   : viewCart
    Created on : Feb 28, 2023, 8:39:42 AM
    Author     : CONG
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="congcv.product.ProductDTO"%>
<%@page import="java.util.Map"%>
<%@page import="congcv.cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
    </head>
    <body>
        <!--can su dung trang dong boi vi moi nguoi dung 1 cai cart khac nhau-->
        <h1 color="red">CART</h1>
        <!-- 1. Customer goes to his/her cart place -->
        <%--<c:if test="${session ne null}">--%>
        <!-- 2. Customer takes his CART -->
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <!-- 3. Customer get items -->
            <c:set var="cartItem" value="${cart.items}"/>
            <c:if test="${not empty cartItem}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${cartItem.entrySet()}" 
                                   varStatus="counter">
                        <form action="cartDeleteItemAction">
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
                                <td>
                                    <input type="submit" value="Remove" name="btAction"/>
                                    <input type="hidden" name="txtProductKey" 
                                           value="${item.value.productKey}" />
                                </td>
                            </tr>   
                        </form>
                    </c:forEach>
                </tbody>
            </table>
            <h2 style="color:red;">Total price: <fmt:formatNumber value="${cart.totalPrice}" 
                                                       maxFractionDigits="1" />$</h2>
            <form action="cartCheckOutAction" method="POST">
            <c:set var="errors" value="${requestScope.CHECKOUT_ERRORS}"/>
                Name <input type="text" name="txtUsername" value="${param.txtUsername}" /> </br>
                <c:if test="${not empty errors.usernameLengthError}">
                    <font color="red">
                    ${errors.usernameLengthError} 
                    </font></br>
                </c:if>
                Address <textarea name="txtAddress" rows="5" cols="19" 
                                  value="">${param.txtAddress}</textarea> </br>
                <c:if test="${not empty errors.addressLengthError}">
                    <font color="red">
                    ${errors.addressLengthError} 
                    </font></br>
                </c:if>
                <input type="submit" value="CheckOut" name="btAction" />
                <input type="reset" value="Reset" />
            </form>
        </c:if>
            
        <c:if test="${empty cartItem}">
            <h2>
                No item in cart!!!
            </h2>
        </c:if>   
    </c:if>
    <c:if test="${empty cart}">
        <h2>
            No cart is existed!!!
        </h2> 
    </c:if>
    <a href="storeView">Back to shopping</a>
    <%--</c:if>--%>
    <%--
    <!--can su dung trang dong boi vi moi nguoi dung 1 cai cart khac nhau-->
    <h1 color="red">CART</h1>
    <%
        //1. Customer goes to his/her cart place
        if (session != null) {
            //2. Customer takes his CART
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart != null) {
                //3. Customer gets items
                Map<Integer, ProductDTO> items = cart.getItems();
                //cho nay ne`
                if (items != null) {
                    %>
                    <div style="margin-bottom: 30px">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Total Price</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%  
                                    int count = 0;
                                    double totalPrice = 0;
                                    DecimalFormat decimalFormat 
                                                = new DecimalFormat("#.#");
                                    for (Integer key : items.keySet()) {
                                        double totalItemPrice = items.get(key).getProductQuantity()
                                                           * items.get(key).getProductPrice();
                                        totalPrice += totalItemPrice;
                                    %>
                            <form action="DispatchController">
                                <tr>
                                    <td>
                                        <%= ++count %>
                                    </td>
                                    <td>
                                        <%= items.get(key).getProductName() %>
                                        <input type="hidden" name="txtProductKey" value="<%= key %>" />
                                    </td>
                                    <td>
                                        <%= items.get(key).getProductQuantity() %>
                                    </td>
                                    <td>
                                        <%= decimalFormat.format(items.get(key).getProductPrice()) %>$
                                    </td>
                                    <td>
                                        <%= decimalFormat.format(totalItemPrice) %>$
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove" name="btAction" />
                                    </td>
                                </tr>
                            </form>
                                <%
                                    }//traverse Map
                                %>
                            </tbody>
                        </table>
                        <p style="color:red;
                                  font-size: 32px;">Total price: <%= decimalFormat.format(totalPrice) %>$</p>
                    </div>
                        
                   <form action="DispatchController" method="POST">
                       Name <input type="text" name="txtUsename" value="" /> </br>
                       Address <textarea name="txtAddress" rows="5" cols="19" value=""></textarea> </br>
                       <input type="submit" value="Checkout" name="btAction" />
                   </form>
    <%          
                
                }//items had existed
            } else {
                %>
               <h2>
                    No cart is existed!!!
               </h2> 
    <%
            }//cart had not existed
        }//cart place had not existed
    %>
    
    <a href="BookStoreServlet">Back</a>
    --%>
</body>
</html>
