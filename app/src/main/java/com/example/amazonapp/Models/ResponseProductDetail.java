package com.example.amazonapp.Models;

import java.util.ArrayList;

public class ResponseProductDetail {
    public String getSuccess() {
        return success;
    }

    public String success;

   public ArrayList<ProductDetail> data;

    public ArrayList<ProductDetail> getData() {
        return data;
    }

    public void setData(ArrayList<ProductDetail> data) {
        this.data = data;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}
