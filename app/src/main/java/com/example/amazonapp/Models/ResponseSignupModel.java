package com.example.amazonapp.Models;

import java.util.ArrayList;

public class ResponseSignupModel {
    public String success;
    public String message;
    /*public ArrayList<SignupModel> data;
*/
    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

   /* public ArrayList<SignupModel> getData() {
        return data;
    }

    public void setData(ArrayList<SignupModel> data) {
        this.data = data;
    }*/
}
