package com.example.amazonapp.Models;

public class PurchaseCartModel {
    String totalPrice;
    String TotalItem;
    String ShippingCharges;
    public static final int TYPE=1;


    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalItem() {
        return TotalItem;
    }

    public void setTotalItem(String totalItem) {
        TotalItem = totalItem;
    }

    public String getShippingCharges() {
        return ShippingCharges;
    }

    public void setShippingCharges(String shippingCharges) {
        ShippingCharges = shippingCharges;
    }
}
