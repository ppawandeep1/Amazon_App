package com.example.amazonapp.Models;

import java.util.ArrayList;

public class CategoryResponseModel {
    public String success;
    public ArrayList<CategoryModel> data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<CategoryModel> getData() {
        return data;
    }

    public void setData(ArrayList<CategoryModel> data) {
        this.data = data;
    }
}
