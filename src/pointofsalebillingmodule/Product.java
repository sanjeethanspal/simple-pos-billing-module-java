/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pointofsalebillingmodule;

/**
 *
 * @author sanjeet
 */
public class Product {
    
    private int productId;
    private String productName;
    private float price;
    
      public Product(int productId, String productName, float price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }
      
      public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    
}
