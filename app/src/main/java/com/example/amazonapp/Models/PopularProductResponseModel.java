package com.example.amazonapp.Models;

import java.util.ArrayList;

public class PopularProductResponseModel {
    public String success;
    public ArrayList<LoginModel> data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<LoginModel> getData() {
        return data;
    }

    public void setData(ArrayList<LoginModel> data) {
        this.data = data;
    }
}
