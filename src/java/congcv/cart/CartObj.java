/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congcv.cart;

import congcv.product.ProductDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author CONG
 */
public class CartObj implements Serializable {

    public CartObj() {
    }
    
    private Map<Integer, ProductDTO> items;

    //lay item
    public Map<Integer, ProductDTO> getItems() {
        return items;
    }
    
    //bo do vao gio
    public boolean addItemToCart(Integer id, ProductDTO product) {
        boolean result = false;
        int quantity = product.getProductQuantity();
        //1. Check data validation
        if (id == null) {
            return result;
        }
//        if (id.trim().isEmpty()) {
//            return result;
//        }
        if (quantity <= 0) {
            return result;
        }
        //2. Check items(ngan chua do) co ton tai hay chua
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //3. Check item has existed
        if (this.items.containsKey(id)) {
            int currentQuantity = this.items.get(id).getProductQuantity();
            product.setProductQuantity(quantity + currentQuantity);
        }//end items has existed 
        //4. update cart items
        this.items.put(id, product);
        
        //this.items.put(id, quantity);
        result = true;

        return result;
    }

    public boolean removeItemFromCart(Integer id, ProductDTO product) {
        boolean result = false;
        int quantity = product.getProductQuantity();
        //1. Check data validation
        if (id == null) {
            return result;
        }
//        if (id.trim().isEmpty()) {
//            return result;
//        }
        if (quantity <= 0) {
            return result;
        }
        //2. Check items not existed
        if (this.items == null) {
            return result;
        }
        //3. Check item not existed
        if (!this.items.containsKey(id)) {
            return result;
        }//item has not existed

        //4. decrease quantity
        int currentQuantity = this.items.get(id).getProductQuantity();
        if (currentQuantity >= quantity) {
            quantity = currentQuantity - quantity;
        }//current larger than quantity
        //5. update cart items
        if (quantity == 0) {
            this.items.remove(id);
            //object trong java la thuong. mac du la rong nhung size van co the > 0
            //sau nay chi can check != null thoi khong can check size > 0
            if (this.items.isEmpty()) {
                this.items = null;
            }
        } else {
            this.items.get(id).setProductQuantity(quantity);
        }
        result = true;

        return result;
    }

    public boolean removeItemFromCart(Integer id) {
        boolean result = false;
        //1. Check data validation
        if (id == null) {
            return result;
        }
//        if (id.trim().isEmpty()) {
//            return result;
//        }

        //2. Check items not existed
        if (this.items == null) {
            return result;
        }
        //3. Check item not existed
        if (!this.items.containsKey(id)) {
            return result;
        }//item has not existed

        //4. remove item
        this.items.remove(id);
        //object trong java la thuong. mac du la rong nhung size van co the > 0
        //sau nay chi can check != null thoi khong can check size > 0
        if (this.items.isEmpty()) {
            this.items = null;
        }
        result = true;

        return result;
    }
    
     public double getTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<Integer, ProductDTO> entry : items.entrySet()) {
            ProductDTO dto = entry.getValue();
            totalPrice += dto.getProductPrice()* dto.getProductQuantity();
        }
        return totalPrice;
    }
}
