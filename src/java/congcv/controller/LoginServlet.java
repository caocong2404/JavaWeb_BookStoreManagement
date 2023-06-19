/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congcv.controller;

import congcv.registration.RegistrationCreateError;
import congcv.registration.RegistrationDAO;
import congcv.util.MyAppConstants;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CONG
 */
public class LoginServlet extends HttpServlet {

    //private final String INVALID_PAGE = "invalid.html";
    //private final String SEARCH_PAGE = "search.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //get servlet context
        ServletContext context = request.getServletContext();
        //get properties file
        Properties siteMap = (Properties) context.getAttribute("SITE_MAP");
        
        String url = MyAppConstants.LoginFeatures.INVALID_PAGE;
        String username = request.getParameter("txtUserName");
        String password = request.getParameter("txtPassword");
        String rememberMe = request.getParameter("rememberMe");
        
        boolean foundError = false;
        RegistrationCreateError errors = new RegistrationCreateError();
        
        //44-56
        try {
//            System.out.println("username: " + request.getParameter("txtUserName"));
//            System.out.println("password: " + request.getParameter("txtPassword"));
            if (username.trim().isEmpty()) {
                foundError = true;
                errors.setUsernameLengthError("Username can not blank");         
            }
            
            //chi bat confirm khi password dung
            if (password.trim().isEmpty()) {
                foundError = true;
                errors.setPasswordLengthError("Password can not blank");         
            }
            
            if (foundError) {
                request.setAttribute("LOGIN_ERROR", errors);
                url = siteMap.getProperty(MyAppConstants.LoginFeatures.LOGIN_FAIL_PAGE);
            } else {
                //3. call DAO
                //3.1 New Object
                RegistrationDAO dao = new RegistrationDAO();
                //3.2 Call method of that object
                boolean result = dao.checkLogin(username, password);

                //4. send view
                //login duoc -> search page
                //else       -> invalid page
                if (result) {
                    url = MyAppConstants.SearchAccountFeatures.SEARCH_PAGE;
                    if (rememberMe != null) {
                        //store cookies
                        Cookie cookies = new Cookie(username, password);
                        cookies.setMaxAge(60 * 60 * 24);
                        response.addCookie(cookies);
                    }
                    HttpSession session = request.getSession(true);
    //                String fullName = dao.getUser().getFullname();
    //                boolean isAdmin = dao.getUser().isRole();
    //                session.setAttribute("FULL_NAME", fullName);
    //                session.setAttribute("IS_ADMIN", isAdmin);
                    session.setAttribute("USER", dao.getUser());
                } //end user clicked Login
            }//end not found error
        } catch (/*ClassNotFoundException*/NamingException ex) {
            log("NamingException:ERROR at LoginServlet: " + ex.getMessage());
            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
        } catch (SQLException ex) {
            log("SQLException:ERROR at LoginServlet: " + ex.getMessage());
            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
        } finally {
            if (foundError) {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                //5. set value to res obj
                response.sendRedirect(url);
                //RequestDispatcher rd = request.getRequestDispatcher(url);
                //rd.forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
