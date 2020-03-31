package com.example.amazonapp.Models;

public class CartModel {

    public static final int CART_ITEM = 0;
    public static final int TOTAL_AMOUNT = 1;

    private int type;

    //cart item
    private int productImage;
    private String productTitle;
    private String productPrice;
    private int productQuantity;

    //constructor

    public CartModel(int type, int productImage, String productTitle, String productPrice, int productQuantity) {
        this.type = type;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }


    //getter and setter

    public static int getCartItem() {
        return CART_ITEM;
    }

    public static int getTotalAmount() {
        return TOTAL_AMOUNT;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    //cart total

    private String totalItems;
    private String totalItemsAmount;
    private String totalShippingCharges;

    public CartModel(int type, String totalItems, String totalAmount, String totalShippingCharges) {
        this.type = type;
        this.totalItems = totalItems;
        this.totalItemsAmount = totalAmount;
        this.totalShippingCharges = totalShippingCharges;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }
    public String gettotalItemsAmount() {
        return totalItemsAmount;
    }

    public void settotalItemsAmount(String totalAmount) {
        this.totalItemsAmount = totalItemsAmount;
    }

    public String getTotalShippingCharges() {
        return totalShippingCharges;
    }

    public void setTotalShippingCharges(String totalShippingCharges) {
        this.totalShippingCharges = totalShippingCharges;
    }
}
