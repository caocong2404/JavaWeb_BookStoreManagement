/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congcv.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author CONG
 */
public class DBHelper implements Serializable {
    
    public static Connection makeConnection()
    throws /*ClassNotFoundException,*/ SQLException, NamingException {
        //1. get current context
        Context context = new InitialContext();
        //2. get tomcat(container) context
        Context tomcat = (Context) context.lookup("java:comp/env");//Catalina la nickname thui nha
                                                 //nay moi la alias name
        //3. get datasource
        DataSource ds = (DataSource) tomcat.lookup("CongEnglishDS");
        //4. open connection
        Connection conn = ds.getConnection();
        return conn;
//        //1. Load driver
//        //neu add driver roi ma com. khong bung ra thi remove va add lai
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Create Connection String
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=SinhVienManagement;intanceName=LAPTOP-8MNOC1RP\\CONGSQL2";//TestPRJ301
//        //3. Open Connection
//        Connection conn = DriverManager.getConnection(url, "sa", "123");
//        
//        return conn;
    }
}
