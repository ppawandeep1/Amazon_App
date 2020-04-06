package com.example.amazonapp.Models;

import java.util.ArrayList;

public class ResponseGetProductById {
    public String success;
    public ArrayList<GetProductByCategory> data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<GetProductByCategory> getData() {
        return data;
    }

    public void setData(ArrayList<GetProductByCategory> data) {
        this.data = data;
    }
}
