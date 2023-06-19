<%-- 
    Document   : search.jsp
    Created on : Feb 17, 2023, 8:13:16 AM
    Author     : CONG
--%>

<%@page import="congcv.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <h2 style="color:red;">
            Welcome ${USER.fullname},
        </h2>
        <h1>Search Page</h1>
        <form action="searchAction">
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="btAction" />
        </form>
            
        <p>Type 'all' for show all users</p>
        
        <form action="logoutAction">
            <input type="submit" value="LogOut" name="btAction" />
        </form> </br>
        
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}" />
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="updateAccountAction">
                             <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUserName" 
                                           value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" 
                                           value="${dto.password}" />
                                </td>
                                <td>
                                    <input type="text" name="txtFullname" 
                                           value="${dto.fullname}" />
                                </td>
                                <td style="text-align: center;">
                                    <input type="checkbox" name="chkAdmin" 
                                           value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>/>
                                </td>         
                                <td>
                                    <c:url var="deleteLink" value="deleteAccountAction">
                                        <c:param name="pk" value="${dto.username}"></c:param>
                                        <c:param name="lastSearchValue" value="${searchValue}"></c:param>
                                        <c:param name="chkAdmin" value="${dto.role}"></c:param>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                </td>
                            </tr>               
                        </form>          
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
            <c:if test="${empty result}">
                <h2>No record is matched</h2>
            </c:if>
            <c:set var="msg" value="${param.message}"/>
            <c:if test="${not empty msg}">
                <p style="color:red;">${msg}</p>
            </c:if>
        </c:if>
        <%--
       <%! private final String LOGIN_PAGE = "login.html"; %>
        <%
            
                String searchValue = request.getParameter("txtSearchValue");
                if (searchValue == null) {
                    searchValue = "";
                }
                
                RegistrationDTO user = (RegistrationDTO) session.getAttribute("USER");
                if (user == null) {
                    response.sendRedirect(LOGIN_PAGE);
                    return;
                }
                
        %>
            <font color="red">
            Welcome , ${USER.fullname}<br>
            </font> 
        <h1>Search Page</h1>
        <form action="DispatchController">
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="btAction" />
            <input type="submit" value="LogOut" name="btAction" />
        </form> <br/>
        <%
            
            
            if (searchValue != null && !searchValue.isEmpty()) {
                //get attribute phai ep kieu tuong minh
                List<RegistrationDTO> result = (List<RegistrationDTO>) 
                                         request.getAttribute("SEARCH_RESULT");
                if (result != null) {
                    //scriptlet co ten goi la fragment code
                    %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Full name</th>
                                <th>Role</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%  
                                int count = 0;
                                for (RegistrationDTO dto : result) {
                                        String urlReWritting = "DispatchController"
                                                + "?btAction=Delete"
                                                + "&pk=" + dto.getUsername()
                                                + "&lastSearchValue=" + searchValue
                                                + "&chkAdmin=" + dto.isRole();
                                        %>
                        <form action="DispatchController" method="POST">
                                        <tr>
                                        <td>
                                            <%= ++count %>
                                        </td>
                                        <td>
                                            <%= dto.getUsername() %>
                                            <input type="hidden" name="txtUserName" 
                                                   value="<%= dto.getUsername() %>" />
                                        </td>
                                        <td>
                                            <input type="text" name="txtPassword" value="<%= dto.getPassword()%>" />
                                        </td>
                                        <td>
                                            <%= dto.getFullname() %>
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkAdmin" value="ON" 
                                                   <%
                                                   if (dto.isRole()) {
                                                   %>
                                                        checked="checked"
                                                   <%
                                                   }//role is admin
                                                   %>
                                                   />
                                        </td>
                                        <td>
                                            <a href="<%= urlReWritting %>">Delete</a>
                                        </td>
                                        <td>
                                            <input type="submit" value="Update" name="btAction" />
                                            <input type="hidden" name="lastSearchValue" value="<%= searchValue %>" />
                                        </td>
                                    </tr>
                        </form>
                                    
                            <%
                                }//end traverser DTO to show data
                            %>
                        </tbody>
                    </table>

        <%
                } else {
                    %>
                    <h2>
                        No record is matched!!!
                    </h2>
       <%
                }//end no record is matched
            }//end search Value has been loaded
            String message = request.getParameter("message");
            if (message != null && !message.isEmpty()) {
        %>
                <font color="red">
                <%= request.getParameter("message") %>
                </font>   
        <%
            } 
        %>
        --%>
    </body>
</html>
