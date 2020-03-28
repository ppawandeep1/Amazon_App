package com.example.amazonapp.Controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.amazonapp.AsyncTasks.AsyncResponse;
import com.example.amazonapp.AsyncTasks.WebserviceCall;
import com.example.amazonapp.Helper.Config;
import com.example.amazonapp.Helper.Utils;
import com.example.amazonapp.Models.ResponseSignupModel;
import com.example.amazonapp.Models.SignupModel;
import com.example.amazonapp.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    // variables
    private EditText fname,lname,email,contactno, city, postalcode, address, password,cnfpassword;
    private Spinner country, province;
    private Button signup;
    ResponseSignupModel signupModelmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fname =(EditText)findViewById(R.id.firstName);
        lname =(EditText)findViewById(R.id.lastName);
        email =(EditText)findViewById(R.id.email_address);
        contactno =(EditText)findViewById(R.id.contact_number);
        //spinner
        country =(Spinner) findViewById(R.id.country);
        province =(Spinner) findViewById(R.id.province);
        //***
        city = (EditText) findViewById(R.id.city);
        postalcode =(EditText)findViewById(R.id.postal_code);
        address =(EditText)findViewById(R.id.address);
        password =(EditText)findViewById(R.id.password);
        cnfpassword =(EditText)findViewById(R.id.confirm_password);

        signup =(Button)findViewById(R.id.btn_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser(){

        final String get_firstname = fname.getText().toString().trim();
        final String get_lastname = lname.getText().toString().trim();
        final String get_email = email.getText().toString().trim();
        final String get_contactno = contactno.getText().toString().trim();
        //final String get_city = city.get.toString().trim();
        final String get_postalcode = postalcode.getText().toString().trim();
        final String get_address = address.getText().toString().trim();
        final String get_password = password.getText().toString().trim();
        final String get_conf_password = cnfpassword.getText().toString().trim();


        //first we will do the validations

        if (TextUtils.isEmpty(get_firstname)) {
            fname.setError("Please enter your first name");
            fname.requestFocus();
            return;
        }

        else if (TextUtils.isEmpty(get_lastname)) {
            lname.setError("Please enter your last name");
            lname.requestFocus();
            return;
        }

        else if (!Patterns.EMAIL_ADDRESS.matcher(get_email).matches()) {
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }

        else if (TextUtils.isEmpty(get_password)) {
            email.setError("Enter a valid password");
            email.requestFocus();
            return;
        }

        else if (TextUtils.isEmpty(get_conf_password)&& TextUtils.equals(get_password, get_conf_password)) {
            cnfpassword.setError("Enter your confirmation password");
            cnfpassword.requestFocus();
            return;
        }

        else if (TextUtils.isEmpty(get_contactno)) {
            contactno.setError("Enter a contact number");
            contactno.requestFocus();
            return;
        }

        else if (TextUtils.isEmpty(get_postalcode)) {
            postalcode.setError("Enter a contact number");
            postalcode.requestFocus();
            return;
        }

        else if (TextUtils.isEmpty(get_postalcode)) {
            postalcode.setError("Enter a contact number");
            postalcode.requestFocus();
            return;
        }

        else if (TextUtils.isEmpty(get_address)) {
            address.setError("Enter a address");
            address.requestFocus();
            return;
        }

        else {
            String[] keys=new String[]{"Fname","Lname","email","phonenumber","countryid","provinceid","city","postal","address","password","companyid"};
            String[] values=new String[]{get_firstname,get_lastname,get_email,get_contactno, "3", "3","montreal",get_postalcode,get_address,get_password, "1"};
            String jsonReq= Utils.createJsonRequest(keys,values);

            //variable for calling api
            String URL_CREATE_USER= Config.GET_CATEGORIES;

            new WebserviceCall(SignUpActivity.this, URL_CREATE_USER, jsonReq, "Login...!!", true, new AsyncResponse() {
                @Override
                public void onCallback(String response) {
                    signupModelmodel = new Gson().fromJson(response, ResponseSignupModel.class);
                      if (signupModelmodel.getSuccess().equals("1")) {
                        Toast.makeText(SignUpActivity.this, ""+signupModelmodel.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else if (signupModelmodel.getSuccess().equals("0") ) {
                        Toast.makeText(SignUpActivity.this, ""+signupModelmodel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }).execute();
        }
    }
}
