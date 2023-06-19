/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congcv.product;

import congcv.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author CONG
 */
public class ProductDAO implements Serializable {

    private List<ProductDTO> productList;

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void setProductList()
            throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. create connection
            conn = DBHelper.makeConnection();
            if (conn != null) {
                //2 Write sql query
                String sql = "SELECT SKU, Name, Description, Price, Quantity, Status "
                        + "FROM Product";
                //3 Create statement object
                stm = conn.prepareStatement(sql);

                //4 Execute Statement to get result
                rs = stm.executeQuery();

                //5 Process result
                while (rs.next()) {
                    int SKU = rs.getInt("SKU");
                    String productName = rs.getString("Name");
                    String productDescription = rs.getString("Description");
                    double productPrice = rs.getDouble("Price");
                    int productQuantity = rs.getInt("Quantity");
                    boolean Status = rs.getBoolean("Status");

                    ProductDTO dto = new ProductDTO(SKU, productName,
                            productDescription, productPrice, productQuantity, Status);
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }
                    this.productList.add(dto);
                }//end rs has not reach EOF
            }//end connection has existed
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
    }

    public boolean updateProductQuantity(int SKU, int quantity)
            throws SQLException, NamingException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. create connection
            conn = DBHelper.makeConnection();
            if (conn != null) {
                //2 Write sql query
                String sql = "UPDATE Product "
                        + "SET quantity = quantity - ? "
                        + "WHERE SKU = ?";
                //3 Create statement object
                stm = conn.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setInt(2, SKU);

                //4 Execute Statement to get result
                int row = stm.executeUpdate();

                //5 Process result
                if (row > 0) {
                    result = true;
                }
            }//connection success       
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

    public boolean updateProductStatus()
            throws SQLException, NamingException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. create connection
            conn = DBHelper.makeConnection();
            if (conn != null) {
                //2 Write sql query
                String sql = "Update Product "
                        + "SET Status = 0 "
                        + "WHERE Quantity = 0 AND Status != 0";
                //3 Create statement object
                stm = conn.prepareStatement(sql);

                //4 Execute Statement to get result
                int row = stm.executeUpdate();

                //5 Process result
                if (row > 0) {
                    result = true;
                }
            }//connection success       
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

    public int getProductQuantity(int SKU)
            throws SQLException, NamingException {
        int result = -1;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. create connection
            conn = DBHelper.makeConnection();
            if (conn != null) {
                //2 Write sql query
                String sql = "SELECT quantity "
                        + "FROM Product "
                        + "WHERE SKU = ?";
                //3 Create statement object
                stm = conn.prepareStatement(sql);
                stm.setInt(1, SKU);

                //4 Execute Statement to get result
                rs = stm.executeQuery();

                //5 Process result
                if (rs.next()) {
                    result = rs.getInt("quantity");
                }
            }//connection success       
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

    public boolean deleteProduct(int SKU)
            throws SQLException, NamingException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            //1. create connection
            conn = DBHelper.makeConnection();
            if (conn != null) {
                //2 Write sql query
                String sql = "DELETE FROM Product"
                        + "WHERE SKU = ?";
                //3 Create statement object
                stm = conn.prepareStatement(sql);
                stm.setInt(1, SKU);

                //4 Execute Statement to get result
                int row = stm.executeUpdate();

                //5 Process result
                if (row > 0) {
                    result = true;
                }
            }//connection success       
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

    public boolean insertProduct(ProductDTO product)
            throws SQLException, NamingException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;

        String productName = product.getProductName();
        String productDescription = product.getProductDescription();
        double productPrice = product.getProductPrice();
        int productQuantity = product.getProductQuantity();
        boolean productStatus = product.isProductStatus();

        try {
            //1. create connection
            conn = DBHelper.makeConnection();
            if (conn != null) {
                //2 Write sql query
                String sql = "INSERT INTO Product"
                        + "(Name, Description, Price, Quantity, Status) "
                        + "VALUES (?, ?, ?, ?, ?)";
                //3 Create statement object
                stm = conn.prepareStatement(sql);
                stm.setString(1, productName);
                stm.setString(2, productDescription);
                stm.setDouble(3, productPrice);
                stm.setInt(4, productQuantity);
                stm.setBoolean(5, productStatus);

                //4 Execute Statement to get result
                int row = stm.executeUpdate();

                //5 Process result
                if (row > 0) {
                    result = true;
                }
            }//connection success       
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

    public boolean updateProduct(int SKU, int quantity)
            throws SQLException, NamingException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            //1. create connection
            conn = DBHelper.makeConnection();
            if (conn != null) {
                //2 Write sql query
                String sql = "UPDATE Product "
                        + "SET quantity = ? "
                        + "WHERE SKU = ?";
                //3 Create statement object
                stm = conn.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setInt(2, SKU);

                //4 Execute Statement to get result
                int row = stm.executeUpdate();

                //5 Process result
                if (row > 0) {
                    result = true;
                }
            }//connection success       
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
