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
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CONG
 */
public class SearchServlet extends HttpServlet {

    //private final String LOGIN_SERVLET = "login.html";
    //private final String ERROR_SERVLET = "error.html";
    //private final String SHOW_RESULT_PAGE = "search.jsp";
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
        //assign mapping url = search.jsp
        String url = MyAppConstants.LoginFeatures.LOGIN_PAGE;

        String searchValue = request.getParameter("txtSearchValue");
        boolean check = false;

        RegistrationDAO dao = new RegistrationDAO();
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                RegistrationDTO user = (RegistrationDTO) session.getAttribute("USER");
                if (user != null) {
                    if (!searchValue.trim().isEmpty()) {
                        //4 call model/DAO
                        //4.1 new object DAO
                        //RegistrationDAO dao = new RegistrationDAO();
                        //4.2 call method of DAO
                        if (searchValue.equalsIgnoreCase("all")) {
                            dao.searchAll();
                        } else {
                            dao.searchLastname(searchValue);
                        }
                        //4.3 store result to Scope (if any)
                        List<RegistrationDTO> result = dao.getAccountList();
                        request.setAttribute("SEARCH_RESULT", result);
                        check = true;
                    }//process search result 
                    url = siteMap.getProperty(MyAppConstants.SearchAccountFeatures.SEARCH_PAGE);
                }//user existed 
            }//session existed 
            else {
                //url = siteMap.getProperty(MyAppConstants.SearchAccountFeatures.LOGIN_PAGE);
                url = MyAppConstants.LoginFeatures.LOGIN_PAGE;
            }
        } catch (NamingException ex) {
            log("NamingException: ERROR at SearchServlet " + ex.getMessage());
            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
        } catch (SQLException ex) {
            log("SQLException: ERROR at SearchServlet " + ex.getMessage());
            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
        } /*catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }*/ finally {
            if (check) {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                response.sendRedirect(url);
            }
            //response.sendRedirect(url);
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
