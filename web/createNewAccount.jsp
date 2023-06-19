<%-- 
    Document   : createNewAccount
    Created on : Mar 10, 2023, 9:00:31 AM
    Author     : CONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="registerAction" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
            Username* <input type="text" name="txtUsername" 
                             value="${param.txtUsername}" size="20" /> (6 - 20 chars)<br/>
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red">
                    ${errors.usernameLengthError} 
                </font></br>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                    ${errors.usernameIsExisted} 
                </font></br>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" size="30" /> (6 - 30 chars)<br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                    ${errors.passwordLengthError} 
                </font></br>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" size="30" /> <br/>
            <c:if test="${not empty errors.confirmIsMatch}">
                <font color="red">
                    ${errors.confirmIsMatch} 
                </font> </br>
            </c:if>
            Full name* <input type="text" name="txtFullname" 
                              value="${param.txtFullname}" size="50" /> (2 - 50 chars)<br/>
            <c:if test="${not empty errors.fullnameLengthError}">
                <font color="red">
                    ${errors.fullnameLengthError} 
                </font> </br>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
        <a href="loginPage">Back to Login</a>  </br>
    </body>
</html>
