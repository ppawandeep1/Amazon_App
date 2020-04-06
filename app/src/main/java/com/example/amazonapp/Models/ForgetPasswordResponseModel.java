package com.example.amazonapp.Models;

import com.example.amazonapp.Controllers.ForgetPassword;

public class ForgetPasswordResponseModel {
    public String success;
    public String message;

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
}
