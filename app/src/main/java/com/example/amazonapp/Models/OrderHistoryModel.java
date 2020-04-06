package com.example.amazonapp.Models;

public class OrderHistoryModel {
    public String CustomerId;
    public String Fname;
    public String CustomerOrderId;
    public String date;
    public String Product_name;

    public String getProduct_name() {
        return Product_name;
    }

    public void setProduct_name(String product_name) {
        Product_name = product_name;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getCustomerOrderId() {
        return CustomerOrderId;
    }

    public void setCustomerOrderId(String customerOrderId) {
        CustomerOrderId = customerOrderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String Quantity;
    public String Total;
    public String Image;
}
