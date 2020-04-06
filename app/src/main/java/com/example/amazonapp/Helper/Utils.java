package com.example.amazonapp.Helper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Win10 on 10-03-2017.
 */

public class Utils{
    /***
     * Create json request
     * @param keys
     * @param values
     * @return String jsonObject
     */

   ///for createJsonRequest
    public static String createJsonRequest(String keys[], String values[]){
        JSONObject jsonObject = new JSONObject();
        try {
            for (int i = 0; i < keys.length; i++) {
                jsonObject.put(keys[i],values[i]);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }
/*
To create JSON object for body
 */
}
