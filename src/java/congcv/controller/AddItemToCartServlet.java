/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congcv.controller;

import congcv.cart.CartObj;
import congcv.product.ProductDAO;
import congcv.product.ProductDTO;
import congcv.util.MyAppConstants;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
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
@WebServlet(name = "AddItemToCartServlet", urlPatterns = {"/AddItemToCartServlet"})
public class AddItemToCartServlet extends HttpServlet {
    //private final String BOOK_STORE_PAGE = "BookStore.jsp";
    //private final String VIEW_CART_PAGE = "viewCart.jsp";
    //private final String ERROR_PAGE = "error.html";
    
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
        String urlRewritting = MyAppConstants.ERROR_PAGE;
        String message = "";
        try  {
            //1. Cust goes to cart place
            //chac chan phai co cho lay gio? de minh de do vao gio?
            HttpSession session = request.getSession(true);
            //2. Cust takes his cart
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObj();
            }//end cart has NOT existed
            //3. Cust drops item into CART
            Integer productKey = 
                    Integer.parseInt(request.getParameter("txtProductKey"));
            
            String productName = 
                    request.getParameter("txtProductName");
            message = "Exceed number quantity of \""+  productName +"\" in stock";
            String productDescription = 
                    request.getParameter("txtProductDescription");
            
            double productPrice = 
                    Double.parseDouble(request.getParameter("txtProductPrice"));
            
            int quantity = 1;
            
            ProductDTO product = new ProductDTO(productKey, productName, 
                    productDescription, productPrice, quantity, true);

            Map<Integer, ProductDTO> items = cart.getItems();
            ProductDAO dao = new ProductDAO();
            
            //get quantity of product in Stock
            int currentQuantity = dao.getProductQuantity(productKey);
            
            //no items in cart
            if (items == null) {
                cart.addItemToCart(productKey, product);
                message = "Add to cart success!";
            } else {
                //have item contain give SKU
                if (items.containsKey(productKey)) {
                    //quantity of product in cart less than current
                    if (items.get(productKey).getProductQuantity() < currentQuantity) {
                        cart.addItemToCart(productKey, product);
                        message = "Add to cart success!";
                    }
                } else {
                    cart.addItemToCart(productKey, product);
                    message = "Add to cart success!";
                }
            }//items existed
            session.setAttribute("CART", cart);
            
            //4. Customer continously goes shopping
            urlRewritting = MyAppConstants.BookStoreFeatures.BOOK_STORE_CONTROLLER
                    + "?message=" + message;
        } catch (SQLException ex) {
            log("SQLException:ERROR at AddItemToCartServlet: " + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException:ERROR at AddItemToCartServlet: " + ex.getMessage());
        } catch (Exception ex) {
            log("Other:ERROR at AddItemToCartServlet: " + ex.getMessage());
        } finally {
            //RequestDispatcher rd = request.getRequestDispatcher(urlRewritting);
            //rd.forward(request, response);
            response.sendRedirect(urlRewritting);
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
