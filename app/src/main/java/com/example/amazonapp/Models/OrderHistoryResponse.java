package com.example.amazonapp.Models;

import com.example.amazonapp.Controllers.OrderHistory;

import java.util.ArrayList;

public class OrderHistoryResponse {
    public String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<OrderHistoryModel> getData() {
        return data;
    }

    public void setData(ArrayList<OrderHistoryModel> data) {
        this.data = data;
    }

    public ArrayList<OrderHistoryModel> data;
}
