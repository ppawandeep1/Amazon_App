package com.example.amazonapp.Models;

import java.util.ArrayList;

public class ProfileResponseModel {

    public String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<ProfilePageModel> getData() {
        return data;
    }

    public void setData(ArrayList<ProfilePageModel> data) {
        this.data = data;
    }


    public ArrayList<ProfilePageModel> data;
}
