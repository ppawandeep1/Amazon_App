package com.example.amazonapp.Models;

import java.util.ArrayList;

public class ProvinceResponseModel {
    public String success;
    public ArrayList<ProvinceModel> data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<ProvinceModel> getData() {
        return data;
    }

    public void setData(ArrayList<ProvinceModel> data) {
        this.data = data;
    }
}
