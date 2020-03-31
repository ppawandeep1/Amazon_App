package com.example.amazonapp.Models;

import java.util.ArrayList;

public class CountryResponseModel {
    public String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<CountryModel> getData() {
        return data;
    }

    public void setData(ArrayList<CountryModel> data) {
        this.data = data;
    }

    public ArrayList<CountryModel> data;
}
