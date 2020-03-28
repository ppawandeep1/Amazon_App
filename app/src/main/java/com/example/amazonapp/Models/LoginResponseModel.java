package com.example.amazonapp.Models;

import java.util.ArrayList;

public class LoginResponseModel {
    public String success;
    public String message;
    public String token;
    public ArrayList<LoginModel> data;

    public ArrayList<LoginModel> getData() {
        return data;
    }

    public void setData(ArrayList<LoginModel> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }





}
