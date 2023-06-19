/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congcv.controller;

import congcv.bill.Bill;
import congcv.cart.CartObj;
import congcv.orderdetails.OrderDetailsDAO;
import congcv.orders.OrderCreateError;
import congcv.orders.OrdersDAO;
import congcv.orders.OrdersDTO;
import congcv.product.ProductDAO;
import congcv.product.ProductDTO;
import congcv.util.MyAppConstants;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CONG
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

    //private static final String ERROR_PAGE = "error.html";
    //private static final String CHECKOUT_SUCCESSFUL_PAGE = "CheckoutSuccessfully.jsp";
    
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

        String username = request.getParameter("txtUsername");
        String address = request.getParameter("txtAddress");
        String resultOrder = null;
        
        
        //1 get servlet context
        ServletContext sc = getServletContext();
        //2 get properties file
        Properties siteMap = (Properties) sc.getAttribute("SITE_MAP");
        //3 mapping file
        String url = siteMap.getProperty(MyAppConstants.ERROR_PAGE);
        
        boolean foundError = false;
        OrderCreateError errors = new OrderCreateError();

        try {
            if (username.length() <= 1 || username.length() > 50) {
                errors.setUsernameLengthError("Username must not empty and "
                        + "not exceeds 50 characters");
                foundError = true;
            }

            if (address.length() < 5 || address.length() > 250) {
                errors.setAddressLengthError("Address must have 5-250 characters");
                foundError = true;
            }
            if (foundError) {
                url = siteMap.getProperty(MyAppConstants.BookStoreFeatures.CART_STORE_PAGE);
                request.setAttribute("CHECKOUT_ERRORS", errors);
            } else {
                //1. go to cart place
                HttpSession session = request.getSession(false);
                if (session != null) {
                    //2. take cart
                    CartObj cart = (CartObj) session.getAttribute("CART");
                    if (cart != null) {
                        //3. call DAO
                        OrdersDAO daoOrder = new OrdersDAO();
                        double totalPrice = cart.getTotalPrice();
                        resultOrder = daoOrder.insertOrder(username, address, totalPrice);
                        if (resultOrder != null) {
                            //
                            OrderDetailsDAO daoOrdDetails = new OrderDetailsDAO();
                            ProductDAO daoProduct = new ProductDAO();
                            Map<Integer, ProductDTO> items = cart.getItems();
                            //insert each item -> orderDetails
                            for (Map.Entry<Integer, ProductDTO> item : items.entrySet()) {
                                int productQuantity = item.getValue().getProductQuantity();
                                double productPrice = item.getValue().getProductPrice();
                                //double productTotalPrice = item.getValue().getTotalPrice();
                                double productTotalPrice = productQuantity * productPrice;
                                daoOrdDetails.insertOrderDetails(resultOrder,
                                        item.getKey(), productQuantity, productPrice, productTotalPrice);

                                //update product quantity 
                                daoProduct.updateProductQuantity(item.getKey(), productQuantity);
                            }//insert orderDetail sucess

                            //update product status
                            daoProduct.updateProductStatus();

                            //create bill
                            OrdersDTO dtoOrder = daoOrder.getLastOrder(username, address);
                            Bill bill = new Bill(dtoOrder, cart);
                            session.setAttribute("BILL", bill);

                            session.removeAttribute("CART");
                            url = siteMap.getProperty(MyAppConstants.BookStoreFeatures.CHECKOUT_BOOK_STORE_PAGE);
                        }//insert order success
                    }//cart existed
                }//session existed
            }//not error input
        } catch (SQLException ex) {
            log("SQLException:ERROR at CheckoutServelet " + ex.getMessage());
            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
        } catch (NamingException ex) {
            log("NamingException:ERROR at CheckoutServelet " + ex.getMessage());
            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
        } 
//        catch (Exception ex ){
//            log("Other:ERROR at CheckoutServelet: " + ex.getMessage());
//            response.sendError(response.SC_INTERNAL_SERVER_ERROR);
//        } 
        finally {

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

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
