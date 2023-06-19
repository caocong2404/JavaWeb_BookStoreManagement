<%-- 
    Document   : shopping
    Created on : Feb 28, 2023, 10:41:44 PM
    Author     : CONG
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="congcv.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1 style="color:red;">Book Store</h1>
        <c:set var="bookList" value="${requestScope.BOOK_STORE}"/>
        <c:if test="${not empty bookList}">
            <h2>Choose your favorite book</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>SKU</th>
                        <th>Product Name</th>
                        <th>Description</th>
                        <th>Price ($)</th>
                        <th>Current Quantity</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${bookList}">
                        <c:if test="${dto.productStatus and dto.productQuantity > 0}">
                        <form action="cartAddItem">
                            <tr>
                                <td>
                                    ${dto.productKey}
                                    <input type="hidden" name="txtProductKey" 
                                           value="${dto.productKey}" />
                                </td>
                                <td>
                                    ${dto.productName}
                                    <input type="hidden" name="txtProductName" 
                                           value="${dto.productName}" />
                                </td>
                                <td>
                                    ${dto.productDescription}
                                    <input type="hidden" name="txtProductDescription" 
                                           value="${dto.productDescription}" />
                                </td>
                                <td>
                                    ${dto.productPrice}
                                    <input type="hidden" name="txtProductPrice" 
                                           value="${dto.productPrice}" />
                                </td>
                                <td>
                                    ${dto.productQuantity}
                                </td>
                                <td>
                                    <input type="submit" value="Add Book to Your Cart" 
                                           name="btAction" />
                                </td>
                            </tr>
                        </form>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty bookList}">
            <h2>The shop is now out of products!!</h2>
        </c:if>
        <c:set var="message" value="${param.message}"/>
        <c:if test="${not empty message}">
            <h2 style="color:red;">${message}</h2>
        </c:if>
        </br>
        <form action="cartPage" style="display:inline-block;">
            <input type="submit" value="View Your Cart" name="btAction" />
        </form>
        <form action="cartCheckOutPage" style="display:inline-block;">
            <input type="submit" value="View Your Bill" name="btAction" />
        </form> <br>
        <a href="loginPage">Back</a>
        <%--<h1 color="red">Book Store</h1>
        <%
            List<ProductDTO> dto = (List<ProductDTO>) 
                                    request.getAttribute("BOOK_STORE");

            if (dto != null) {
        %>
                    Choose your favorite book
                    <table border="1">
                        <thead>
                            <tr>
                                <th>SKU</th>
                                <th>Product Name</th>
                                <th>Description</th>
                                <th>Price</th>
                                <th>Current Quantity</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                            for (ProductDTO item : dto) {
                                if (item.isProductStatus() && item.getProductQuantity() > 0) {
                                %>
                                <form action="DispatchController">
                                    <tr>
                                        <td>
                                            <%= item.getProductKey() %>
                                            <input type="hidden" name="txtProductKey" 
                                                   value="<%= item.getProductKey() %>" />
                                        </td>
                                        <td>
                                            <%= item.getProductName() %>
                                            <input type="hidden" name="txtProductName" 
                                                   value="<%= item.getProductName() %>" />
                                        </td>
                                        <td>
                                            <%= item.getProductDescription()%>
                                            <input type="hidden" name="txtProductDescription" 
                                                   value="<%= item.getProductDescription()%>" />
                                        </td>
                                        <td>
                                            <%= item.getProductPrice()%>$
                                            <input type="hidden" name="txtProductPrice" 
                                                   value="<%= item.getProductPrice()%>" />
                                        </td>
                                        <td>
                                            <%= item.getProductQuantity()%>
                                        </td>
                                        <td>
                                            <input type="submit" value="Add Book to Your Cart" 
                                                   name="btAction" />
                                        </td>
                                    </tr>   
                                </form>
                                <%
                                        }
                            }
                        %>
                        </tbody>
                    </table>      
                    <br>
                    <form action="DispatchController">
                        <input type="submit" value="View Your Cart" name="btAction" />
                    </form>
                    <a href="login.html">Back</a>
                </form>

        <%
            }
        %>
        --%>
    </body>
</html>
