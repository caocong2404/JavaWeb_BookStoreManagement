/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congcv.bill;

import congcv.cart.CartObj;
import congcv.orders.OrdersDTO;
import java.io.Serializable;

/**
 *
 * @author CONG
 */
public class Bill implements Serializable  {
    
    private OrdersDTO dtoOrder;
    private CartObj cart;

    public Bill() {
    }

    public Bill(OrdersDTO dtoOrder, CartObj cart) {
        this.dtoOrder = dtoOrder;
        this.cart = cart;
    }
    
    public OrdersDTO getDtoOrder() {
        return dtoOrder;
    }

    public void setDtoOrder(OrdersDTO dtoOrder) {
        this.dtoOrder = dtoOrder;
    }

    public CartObj getCart() {
        return cart;
    }

    public void setCart(CartObj cart) {
        this.cart = cart;
    }

//    public boolean getBill() throws SQLException, NamingException {
//        boolean result = false;
//        
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            //1. get connection
//            conn = DBHelper.makeConnection();
//            if (conn != null) {
//                //2. write query 
//                 
//                String sql = "SELECT TOP 1 OrderID, Name, Address, Date, Total "
//                        + "FROM Orders "
//                        + "ORDER BY OrderID DESC";
//                //3. create statement object
//                stm = conn.prepareStatement(sql);
//
//                //4. execute statement to get result
//                rs = stm.executeQuery();
//                
//                //5. process result 
//                if (rs.next()) {
//                    String orderID = rs.getString("OrderID");
//                    String name = rs.getString("Name");
//                    String address = rs.getString("Address");
//                    Timestamp date = rs.getTimestamp("Date");
//                    double total = rs.getDouble("Total");
//                    
//                   
//                } 
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return result;
//    }

    
}
