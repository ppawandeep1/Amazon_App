package com.example.amazonapp.Models;

import java.util.ArrayList;

public class ProfileResponseModel {

    public String success;
    public String message;


    public ArrayList<ProfilePageModel> data;



    public ArrayList<ProfilePageModel> getData() {
        return data;
    }

    public void setData(ArrayList<ProfilePageModel> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(String success) {
        this.success = success;
    }



    public String getSuccess() {
        return success;
    }






}
