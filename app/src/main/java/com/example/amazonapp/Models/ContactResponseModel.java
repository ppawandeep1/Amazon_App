package com.example.amazonapp.Models;

import java.util.ArrayList;

public class ContactResponseModel {
    public String success;
    public ContactModel data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ContactModel getData() {
        return data;
    }

    public void setData(ContactModel data) {
        this.data = data;
    }
}
