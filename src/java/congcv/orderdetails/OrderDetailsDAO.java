/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congcv.orderdetails;

import congcv.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author CONG
 */
public class OrderDetailsDAO implements Serializable {
    
    public boolean insertOrderDetails(String orderID, int productKey, 
            int quantity, double price, double total) throws SQLException, NamingException{
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            //1. get connection
            conn = DBHelper.makeConnection();
            if (conn != null) {    
                //2. write query 
                String sql = "INSERT INTO OrderDetails "
                        + "(OrderID, SKU, Quantity, Price, Total) "
                        + "VALUES (?, ?, ?, ?, ?)";
                //3. create statement object
                stm = conn.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setInt(2, productKey);
                stm.setInt(3, quantity);
                stm.setDouble(4, price);
                stm.setDouble(5, total);
                
                //4. execute statement to get result
                int row = stm.executeUpdate();
                
                //5. process result 
                if (row > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
}
