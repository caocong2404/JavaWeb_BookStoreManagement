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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CONG
 */
public class DeleteServlet extends HttpServlet {

    //private final String ERRORS_PAGE = "error.html";

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

        String urlReWritting = MyAppConstants.ERROR_PAGE;
        String message = "";
        String username = request.getParameter("pk");
        String lastSearchValue = request.getParameter("lastSearchValue");
        String chkAdmin = request.getParameter("chkAdmin");
        boolean result = false;
        boolean isAdmin = false;
        if (chkAdmin.equalsIgnoreCase("true")) {
            isAdmin = true;
        }
        try {
            HttpSession session = request.getSession(false);
            //authentication
            if (session != null) {
                RegistrationDTO user = (RegistrationDTO) session.getAttribute("USER");
                if (user != null) {
                    boolean checkRole = user.isRole();
                    if (checkRole) {
                        //4. call model
                        //4.1 new DAO object
                        //admin cannot delete another admin and yourself
                        if (!isAdmin) {
                            RegistrationDAO dao = new RegistrationDAO();
                            result = dao.deleteAccount(username);
                        }//delete member
                        if (result) {
                            //call Search function again by using url rewriting
                            //goi chuc nang hay refresh thi la url rewrting
                            message = "Delete successfully"; 
                        }//end delete action is successfully
                        else {
                            message = "You are not authorized to Delete";
                        }//end delete action not successfully
                    }//end if is admin
                    else {
                        //user cannot delete anyone
                        message = "You are not authorized to Delete";
                    }//end if not admin
                }
                urlReWritting = MyAppConstants.SearchAccountFeatures.SEARCH_CONTROLLER
                            + "?txtSearchValue=" + lastSearchValue
                            + "&message=" + message;
            }//end if session not existed
        } catch (SQLException ex) {
            log("SQLException:ERROR at DeleteServlet: " + ex.getMessage());
            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
        } catch (NamingException ex) {
            log("NamingException:ERROR at DeleteServlet: " + ex.getMessage());
            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
        } finally {
            response.sendRedirect(urlReWritting);
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
