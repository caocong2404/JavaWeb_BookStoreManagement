/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congcv.product;

import java.io.Serializable;

/**
 *
 * @author CONG
 */
public class ProductDTO implements Serializable {
    private int productKey;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productQuantity;
    private boolean productStatus;
    private double totalPrice;

    public ProductDTO() {
    }

    public ProductDTO(int productKey, String productName, String productDescription,
            double productPrice, int productQuantity, boolean productStatus) {
        this.productKey = productKey;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productStatus = productStatus;
    }
    
    public ProductDTO(int productKey, int productQuantity) {
        this.productKey = productKey;
        this.productQuantity = productQuantity;  
    }
    
    
    
    
    /**
     * @return the productKey
     */
    public int getProductKey() {
        return productKey;
    }

    /**
     * @param productKey the productKey to set
     */
    public void setProductKey(int productKey) {
        this.productKey = productKey;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productDescription
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * @param productDescription the productDescription to set
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * @return the productPrice
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     * @param productPrice the productPrice to set
     */
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * @return the productQuantity
     */
    public int getProductQuantity() {
        return productQuantity;
    }

    /**
     * @param productQuantity the productQuantity to set
     */
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    /**
     * @return the productStatus
     */
    public boolean isProductStatus() {
        return productStatus;
    }

    /**
     * @param productStatus the productStatus to set
     */
    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public double getTotalPrice() {
        totalPrice = productPrice * productQuantity;
        return totalPrice;
    }

    
}
