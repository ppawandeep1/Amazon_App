package com.example.amazonapp.Models;

import java.util.ArrayList;

public class PopularProductResponseModel {
    public String success;
    public ArrayList<PopularProductModel> data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<PopularProductModel> getData() {
        return data;
    }

    public void setData(ArrayList<PopularProductModel> data) {
        this.data = data;
    }
}
