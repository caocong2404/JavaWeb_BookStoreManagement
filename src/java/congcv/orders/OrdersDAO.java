/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congcv.orders;

import congcv.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.naming.NamingException;

/**
 *
 * @author CONG
 */
public class OrdersDAO implements Serializable  {
    
    public String insertOrder(String name, String address, double total) 
            throws SQLException, NamingException{
        String result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            //1. get connection
            conn = DBHelper.makeConnection();
            if (conn != null) {
                //OrderID: ~~~~ ORDxx
                //query the database to get the latest order ID
                String lastOrderIdIndex = getLastOrder().getOrderID().substring(3);
                int latestOrderId = Integer.parseInt(lastOrderIdIndex);
                // generate the next order ID
                String nextOrderId = "ORD" + String.format("%02d", latestOrderId + 1);
                //generate date
                //java.util.Date date = new java.util.Date();
                Timestamp sqlDate = new Timestamp(System.currentTimeMillis());
                //2. write query 
                String sql = "INSERT INTO Orders "
                        + "(OrderID, Name, Address, Date, Total) "
                        + "VALUES (?, ?, ?, ?, ?)";
                //3. create statement object
                stm = conn.prepareStatement(sql);
                stm.setString(1, nextOrderId);
                stm.setString(2, name);
                stm.setString(3, address);
                stm.setTimestamp(4, sqlDate);
                stm.setDouble(5, total);
                
                //4. execute statement to get result
                int row = stm.executeUpdate();
                
                //5. process result 
                if (row > 0) {
                    result = nextOrderId;
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
    
    public OrdersDTO getLastOrder() throws SQLException, NamingException {
        OrdersDTO dto = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. get connection
            conn = DBHelper.makeConnection();
            if (conn != null) {
                //2. write query 
                 
                String sql = "SELECT TOP 1 OrderID, Name, Address, Date, Total "
                        + "FROM Orders "
                        + "ORDER BY OrderID DESC";
                //3. create statement object
                stm = conn.prepareStatement(sql);

                //4. execute statement to get result
                rs = stm.executeQuery();
                
                //5. process result 
                if (rs.next()) {
                    String orderID = rs.getString("OrderID");
                    String name = rs.getString("Name");
                    String address = rs.getString("Address");
                    Timestamp date = rs.getTimestamp("Date");
                    double total = rs.getDouble("Total");
                    
                    dto = new OrdersDTO(orderID, name, address, date, total);
                } else {
                    dto = new OrdersDTO("ORD00");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return dto;
    }
    
    public OrdersDTO getLastOrder(String username, String address) 
            throws SQLException, NamingException {
        OrdersDTO dto = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. get connection
            conn = DBHelper.makeConnection();
            if (conn != null) {
                //2. write query 
                 
                String sql = "SELECT TOP 1 OrderID, Name, Address, Date, Total "
                        + "FROM Orders "
                        + "WHERE Name = ? AND Address = ? "
                        + "ORDER BY OrderID DESC";
                //3. create statement object
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, address);

                //4. execute statement to get result
                rs = stm.executeQuery();
                
                //5. process result 
                if (rs.next()) {
                    String orderID = rs.getString("OrderID");
                    Timestamp date = rs.getTimestamp("Date");
                    double total = rs.getDouble("Total");
                    
                    dto = new OrdersDTO(orderID, username, address, date, total);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return dto;
    }
}
