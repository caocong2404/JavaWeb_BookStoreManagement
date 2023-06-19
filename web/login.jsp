<%-- 
    Document   : login
    Created on : Mar 18, 2023, 4:36:16 PM
    Author     : CONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <c:set var="errors" value="${requestScope.LOGIN_ERROR}"/>
        <div>
            <form action="loginAction" method="POST">
                Username <input type="text" name="txtUserName" value="" /> </br>
                <c:if test="${not empty errors.usernameLengthError}">
                <font color="red">
                    ${errors.usernameLengthError} 
                </font></br>
                </c:if>
                Password <input type="password" name="txtPassword" value="" /> </br>
                <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                    ${errors.passwordLengthError} 
                </font></br>
                </c:if>
                <input type="submit" value="Login" />
                <input type="reset" value="Reset" /> <br>
                <input type="checkbox" name="rememberMe" value="ON" />Remember me <br>
                <!--<a href="shopping.html">Shopping Now</a>--> 
            </form>
        </div>
        
        <div style="margin-top: 8px;">
            <form action="storeView">
                <input type="submit" value="Shopping Now" name="btAction" />        
            </form>
        </div>
        
        <div>
            <p>Does not have account? <a href="registerPageh">Sign Up</a></p>
        </div>
    </body>
</html>
