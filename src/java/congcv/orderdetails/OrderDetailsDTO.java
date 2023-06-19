/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congcv.orderdetails;

import java.io.Serializable;

/**
 *
 * @author CONG
 */
public class OrderDetailsDTO implements Serializable  {

    private int orderDetailsID;
    private String orderID;
    private int productKey;
    private int quantity;
    private double price;
    private double total;

    public OrderDetailsDTO() {
    }
    
    public OrderDetailsDTO(String orderID, int productKey, 
            int quantity, double price, double total) {
        this.orderID = orderID;
        this.productKey = productKey;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }
    
    public OrderDetailsDTO(int orderDetailsID, String orderID, 
            int productKey, int quantity, double price, double total) {
        this.orderDetailsID = orderDetailsID;
        this.orderID = orderID;
        this.productKey = productKey;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public int getOrderDetailsID() {
        return orderDetailsID;
    }

    public void setOrderDetailsID(int orderDetailsID) {
        this.orderDetailsID = orderDetailsID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getProductKey() {
        return productKey;
    }

    public void setProductKey(int productKey) {
        this.productKey = productKey;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
}
