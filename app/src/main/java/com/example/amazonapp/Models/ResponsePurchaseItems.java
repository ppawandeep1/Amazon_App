package com.example.amazonapp.Models;

import java.util.ArrayList;

public class ResponsePurchaseItems {
    public String customerid;
    public String total;
    public ArrayList<PurchaseItem> product;

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<PurchaseItem> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<PurchaseItem> product) {
        this.product = product;
    }
}
