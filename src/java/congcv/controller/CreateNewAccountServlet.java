/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congcv.controller;

import congcv.registration.RegistrationCreateError;
import congcv.registration.RegistrationDAO;
import congcv.registration.RegistrationDTO;
import congcv.util.MyAppConstants;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CONG
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {
    //private final String ERROR_PAGE = "createNewAccount.jsp";
    //private final String LOGIN_PAGE = "login.html";
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
        
        ServletContext sc = getServletContext();
        
        Properties siteMap = (Properties) sc.getAttribute("SITE_MAP");
        String url = siteMap.getProperty(MyAppConstants.CreateNewAccountFeatures.CREATE_NEW_ACCOUNTS_PAGEJ);
        
        String userName = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String fullName = request.getParameter("txtFullname");
        String confirm = request.getParameter("txtConfirm");
        
        boolean foundError = false;
        RegistrationCreateError errors = new RegistrationCreateError();
        try {
            /* TODO output your page here. You may use following sample code. */
            //1. Check user's validation
            if (userName.trim().length() < 6 ||
                userName.trim().length() > 20) {
                foundError = true;
                errors.setUsernameLengthError("Username is required input form 6 to 20 characters");         
            }
            
            //chi bat confirm khi password dung
            if (password.trim().length() < 6 ||
                password.trim().length() > 30) {
                foundError = true;
                errors.setPasswordLengthError("Password is required input form 6 to 30 characters");         
            } else if (!confirm.trim().equals(password.trim())) {
                foundError = true;
                errors.setConfirmIsMatch("Confirm must match password");
            }
            
            if (fullName.trim().length() < 2 ||
                fullName.trim().length() > 50) {
                foundError = true;
                errors.setFullnameLengthError("Full name is required input form 2 to 50 characters");         
            }
            
            //2. process
            //2.1 If error occur, display to user && log
            //2.2 Otherwise, call Model
            if (foundError) {
                request.setAttribute("CREATE_ERRORS", errors);
            } else {//no errors
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(userName, password, fullName, false);
                boolean result = dao.createAccount(dto);
                if (result) {
                    url = siteMap.getProperty(MyAppConstants.LoginFeatures.LOGIN_PAGE);
                }//create action is successfully
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateNewAccountServlet _ SQL " + ex.getMessage());
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(userName + " is exixted");
                request.setAttribute("CREATE_ERRORS", errors);
            }
        } catch (NamingException ex) {
            log("CreateNewAccountServlet _ Naming " + ex.getMessage());
            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
