/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congcv.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CONG
 */
@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
public class DispatchController extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String LOGOUT_CONTROLLER = "LogoutServlet";
    private final String SEARCH_LASTNAME_PAGE = "SearchServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteServlet";
    private final String TRIGGER_APP_CONTROLLER = "TriggerAppServlet";
    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateServlet";
    private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartServlet";
    private final String REMOVE_ITEM_CONTROLLER = "RemoveItemFromCartServlet";
    private final String VIEW_ITEM_FORM_CART = "viewCart.jsp";
    private final String BOOK_STORE_CONTROLLER = "BookStoreServlet";
    private final String CHECKOUT_BOOK_STORE_CONTROLLER = "CheckoutServlet";
    private final String SIGNUP_CONTROLLER = "CreateNewAccountServlet";
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
        String url = LOGIN_PAGE;
        //which button did user click?
        String btn = request.getParameter("btAction");
        
        
        try {
            /* TODO output your page here. You may use following sample code. */
            if (btn == null) {
                //tranfer to Login page
                url = TRIGGER_APP_CONTROLLER;
            } else if (btn.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (btn.equals("Search")) {
                url = SEARCH_LASTNAME_PAGE;
            } else if (btn.equals("Delete")) {
                url = DELETE_ACCOUNT_CONTROLLER;
            } else if (btn.equals("Update")) {
                url = UPDATE_ACCOUNT_CONTROLLER;
            } else if (btn.equals("LogOut")) {
                url = LOGOUT_CONTROLLER;
            } else if (btn.equals("Add Book to Your Cart")) {
                url = ADD_ITEM_TO_CART_CONTROLLER;
            } else if (btn.equals("View Your Cart")) {
                url = VIEW_ITEM_FORM_CART;
            } else if (btn.equals("Shopping Now")) {
                url = BOOK_STORE_CONTROLLER;
            } else if (btn.equals("Remove")) {
                url = REMOVE_ITEM_CONTROLLER;
            } else if (btn.equals("Create New Account")) {
                url = SIGNUP_CONTROLLER;
            } else if (btn.equals("CheckOut")) {
                url = CHECKOUT_BOOK_STORE_CONTROLLER;
            }
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
