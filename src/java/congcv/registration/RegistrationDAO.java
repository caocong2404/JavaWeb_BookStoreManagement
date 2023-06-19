/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congcv.registration;

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
public class RegistrationDAO implements Serializable {

    private RegistrationDTO user;

    public RegistrationDTO getUser() {
        return user;
    }

    public boolean checkLogin(String userName, String password)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {

            //1. Connect DB
            //line: 30 -> 41 : DAO connect DB
            conn = DBHelper.makeConnection();
            if (conn != null) {
                //2. Write SQL
                String sql = "SELECT username, lastname, isAdmin "
                        + "FROM Registration "
                        + "WHERE username = ? AND password = ?";
                //3. Create Statement Object
                stm = conn.prepareStatement(sql);
                stm.setString(1, userName);
                stm.setString(2, password);

                //4. Execute Statement to get result
                rs = stm.executeQuery();
                //5. Process result
                //lin: 43 -> 60: set value DB to DAO
                if (rs.next()) {
                    String fullname = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    user = new RegistrationDTO(userName, fullname, isAdmin);
                    result = true;
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
        return result;
    }

    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }

    public void searchLastname(String keyword)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Connect DB
            //line: 30 -> 41 : DAO connect DB
            conn = DBHelper.makeConnection();
            if (conn != null) {
                //2. Write SQL
                String sql = "SELECT username, password, lastname, isAdmin "
                        + "FROM Registration "
                        + "WHERE lastname LIKE ?";
                //3. Create Statement Object
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + keyword + "%");

                //4. Execute Statement to get result
                rs = stm.executeQuery();
                //5. Process result
                //lin: 43 -> 60: set value DB to DAO
                while (rs.next()) {
                    //luon luon get bang ten cot -> vi sau nay khong biet 
                    //thu tu cot co thay doi hay khong
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");

                    RegistrationDTO dto = new RegistrationDTO(
                            username, password, lastname, isAdmin);

                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }//end account List had NOT existed
                    this.accountList.add(dto);
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
    
    public void searchAll()
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Connect DB
            //line: 30 -> 41 : DAO connect DB
            conn = DBHelper.makeConnection();
            if (conn != null) {
                //2. Write SQL
                String sql = "SELECT username, password, lastname, isAdmin "
                        + "FROM Registration ";
                //3. Create Statement Object
                stm = conn.prepareStatement(sql);

                //4. Execute Statement to get result
                rs = stm.executeQuery();
                //5. Process result
                //lin: 43 -> 60: set value DB to DAO
                while (rs.next()) {
                    //luon luon get bang ten cot -> vi sau nay khong biet 
                    //thu tu cot co thay doi hay khong
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");

                    RegistrationDTO dto = new RegistrationDTO(
                            username, password, lastname, isAdmin);

                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }//end account List had NOT existed
                    this.accountList.add(dto);
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

    public boolean deleteAccount(String pk)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {

            //1. Connect DB
            //line: 30 -> 41 : DAO connect DB
            conn = DBHelper.makeConnection();
            if (conn != null) {
                //2. Write SQL
                String sql = "DELETE "
                        + "FROM Registration "
                        + "WHERE username = ?";
                //3. Create Statement Object
                stm = conn.prepareStatement(sql);
                stm.setString(1, pk);
                //4. Execute Statement to get result
                int effectRow = stm.executeUpdate();
                //5. Process result
                if (effectRow > 0) {
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

    public boolean updateAccount(String userName, String passWord, String fullname, boolean isAdmin)
            throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        try {
            //1. connect DB
            conn = DBHelper.makeConnection();
            if (conn != null) {
                //2 write SQL query
                String sql = "UPDATE Registration "
                        + "SET password = ?, lastname = ?, isAdmin = ? "
                        + "WHERE username = ?";
                //3. create statement Object
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, passWord);
                ptm.setString(2, fullname);
                ptm.setBoolean(3, isAdmin);
                ptm.setString(4, userName);
                //4. Execute to get result
                int effectRow = ptm.executeUpdate();
                //5 process result
                if (effectRow > 0) {
                    result = true;
                }
            }

        } finally {

            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public String getFullnameAccount(String userName)
            throws SQLException, NamingException {

        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String fullName = null;
        try {
            //1. open connection
            conn = DBHelper.makeConnection();
            if (conn != null) {
                //2 write sql query
                String sql = "SELECT lastname "
                        + "FROM Registration "
                        + "WHERE username = ?";
                //3. create statement object
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, userName);
                //4. Execute to get result
                rs = ptm.executeQuery();
                while (rs.next()) {
                    fullName = rs.getString("lastname");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return fullName;
    }

    public boolean insertAccount(String username, String password, String lastname)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean result = false;
        boolean isAdmin = false;
        try {

            //1. Connect DB
            //line: 30 -> 41 : DAO connect DB
            conn = DBHelper.makeConnection();
            if (conn != null) {
                //2. Write SQL
                String sql = "INSERT INTO "
                        + "Registration(username, password, lastname) "
                        + "VALUES (?, ?, ?, ?)";
                //3. Create Statement Object
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, lastname);
                stm.setBoolean(4, isAdmin);
                //4. Execute Statement to get result
                int effectRow = stm.executeUpdate();
                //5. Process result
                if (effectRow > 0) {
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

    public boolean createAccount(RegistrationDTO dto)
            throws SQLException, NamingException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String fullName = null;
        try {
            //1. open connection
            conn = DBHelper.makeConnection();
            if (conn != null) {
                //2 write sql query
                String sql = "INSERT INTO Registration("
                        + "username, password, lastname, isAdmin"
                        + ") VALUES (?, ?, ?, ?)";
                //3. create statement object
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullname());
                stm.setBoolean(4, dto.isRole());
                //4. Execute to get result
                int row = stm.executeUpdate();
                if (row > 0) {
                    result = true;
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
        return result;
    }

}
