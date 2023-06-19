/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congcv.orders;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author CONG
 */
public class OrdersDTO implements Serializable  {
    
    private String orderID;
    private String name;
    private String address;
    private Timestamp date;
    private double total;

    public OrdersDTO() {
    }

    public OrdersDTO(String orderID, String name, String address, Timestamp date, double total) {
        this.orderID = orderID;
        this.name = name;
        this.address = address;
        this.date = date;
        this.total = total;
    }
    
    public OrdersDTO(String orderID) {
        this.orderID = orderID;
    }
    

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
