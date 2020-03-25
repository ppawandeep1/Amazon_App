package com.example.amazonapp.Controllers;

import android.content.Intent;
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
import com.example.amazonapp.Helper.Config;
import com.example.amazonapp.R;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    // variables
    private EditText fname,lname,email,contactno, postalcode, address, password,cnfpassword;
    private Spinner city, country, province;
    //countryid, provinceid, companyid
    private Button signup;

    //AwesomeValidation awesomeValidation;

    //variable for calling api
    String URL_CREATE_USER= Config.CREATE_USER;

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
        city =(Spinner) findViewById(R.id.city);
        //***
        postalcode =(EditText)findViewById(R.id.postal_code);
        address =(EditText)findViewById(R.id.address);
        password =(EditText)findViewById(R.id.password);
        cnfpassword =(EditText)findViewById(R.id.confirm_password);

        signup =(Button)findViewById(R.id.btn_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
                if (v==signup) {
                    startActivity(new Intent(SignUpActivity.this, SignUpActivity.class));
                }
            }
        });

        //awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //updateUI();
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

        if (TextUtils.isEmpty(get_lastname)) {
            lname.setError("Please enter your last name");
            lname.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(get_email).matches()) {
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(get_password)) {
            email.setError("Enter a valid password");
            email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(get_conf_password)&& TextUtils.equals(get_password, get_conf_password)) {
            cnfpassword.setError("Enter your confirmation password");
            cnfpassword.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(get_contactno)) {
            contactno.setError("Enter a contact number");
            contactno.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(get_postalcode)) {
            postalcode.setError("Enter a contact number");
            postalcode.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(get_postalcode)) {
            postalcode.setError("Enter a contact number");
            postalcode.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(get_address)) {
            address.setError("Enter a address");
            address.requestFocus();
            return;
        }

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL_CREATE_USER,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Toast.makeText(getApplication(), response, Toast.LENGTH_SHORT).show();
                        //Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUpActivity.this, "error", Toast.LENGTH_SHORT).show();
                        // error
                        //Log.d("Error.Response", response);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String>  params = new HashMap<String, String>();

                params.put("Fname", get_firstname);
                params.put("Lname", get_lastname);
                params.put("email", get_email);
                params.put("phonenumber", get_contactno);
                params.put("countryid", "3");
                params.put("provinceid", "4");
                params.put("city", "montreal");
                params.put("postal", get_postalcode);
                params.put("address", get_address);
                params.put("password", get_password);
                params.put("companyid", "1");

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(postRequest);
    }

    /*private void updateUI()
    {

        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        awesomeValidation.addValidation(SignUpActivity.this,R.id.firstName, "[a-zA-Z\\s]+",R.string.first_name_error);
        awesomeValidation.addValidation(SignUpActivity.this,R.id.lastName,"[a-zA-Z\\s]+",R.string.last_name_error);
        awesomeValidation.addValidation(SignUpActivity.this,R.id.email_address, Patterns.EMAIL_ADDRESS,R.string.email_error);
        awesomeValidation.addValidation(SignUpActivity.this,R.id.contact_number, RegexTemplate.TELEPHONE,R.string.contact_error);
        awesomeValidation.addValidation(SignUpActivity.this,R.id.password,regexPassword,R.string.password_error);
        awesomeValidation.addValidation(SignUpActivity.this,R.id.confirm_password,R.id.password,R.string.confirm_password_error);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (awesomeValidation.validate())
                {

                    Toast.makeText(SignUpActivity.this, "Data save Succesfully in the Database.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SignUpActivity.this, "Fill all the fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }*/
}
