/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congcv.controller;

import congcv.registration.RegistrationDAO;
import congcv.registration.RegistrationDTO;
import congcv.util.MyAppConstants;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
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
public class TriggerAppServlet extends HttpServlet {
    //private final String LOGIN_PAGE = "login.html";
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
        
        String url = MyAppConstants.LoginFeatures.LOGIN_PAGE;
        
        try {
            /* TODO output your page here. You may use following sample code. */
            //1. get Cookies check ton tai hay khong
            Cookie[] cookies = request.getCookies();
            
            if (cookies != null) {
                //2. get last cookies 
                Cookie lastCookie = cookies[cookies.length - 1];
                //3. get username & password
                String userName = lastCookie.getName();
                String passWord = lastCookie.getValue();
                //4. call DAO
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.checkLogin(userName, passWord);
                RegistrationDTO user = dao.getUser();
                if (result) {
                    HttpSession session = request.getSession();
                    //String fullName = dao.getFullnameAccount(userName);
                    //String fulName = user.getFullname();
                    session.setAttribute("USER", user);
                    url = MyAppConstants.SearchAccountFeatures.SEARCH_PAGE;
                }//end username and password are correctly
            }//end cookies had existed
        } catch (SQLException ex) {
            log("SQLException:ERROR at TriggerAppServlet " + ex.getMessage());
            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
        } catch (NamingException ex) {
            log("NamingException:ERROR at TriggerAppServlet " + ex.getMessage());
            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
        } finally {
            //dung foward hay sendRedirect cai nao cung dc
            //cookies luu tren client va server nen ko can lo bi mat
            response.sendRedirect(url);
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
