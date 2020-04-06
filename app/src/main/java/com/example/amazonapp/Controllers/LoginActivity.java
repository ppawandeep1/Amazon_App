package com.example.amazonapp.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.amazonapp.AsyncTasks.AsyncResponse;
import com.example.amazonapp.AsyncTasks.WebserviceCall;
import com.example.amazonapp.Helper.Config;
import com.example.amazonapp.Helper.Utils;
import com.example.amazonapp.Models.CategoryModel;
import com.example.amazonapp.Models.LoginModel;
import com.example.amazonapp.Models.LoginResponseModel;
import com.example.amazonapp.Models.ResponseModel;
import com.example.amazonapp.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    TextView forgetpassword;
    TextView signUp;
    Button btnLogin;
    EditText userName;
    EditText password;
    LoginResponseModel model;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //finding EditText
        userName=findViewById(R.id.userName);
        password=findViewById(R.id.password);

        //for going to registration pag i.e sign up page
        signUp=findViewById(R.id.sign_up_action);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        /*on click of sign in i.e login we will try to fetch the
         data from the api and its success 1 then we will redirect
          to home page and display login info, i.e username*/

        btnLogin=findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pass=password.getText().toString();
                final String username=userName.getText().toString();
                if(pass.isEmpty()){
                    password.setError("Please Enter Password");
                }
                else if(username.isEmpty()){
                    userName.setError("Please enter your Email id");
                }
                else
                {
                    String[] keys=new String[]{"email","password"};
                    String[] values=new String[]{username,pass};
                    String jsonReq= Utils.createJsonRequest(keys,values);

                    String URL= Config.LOGIN_USER;
                    new WebserviceCall(LoginActivity.this, URL, jsonReq, "Login...!!", true, new AsyncResponse() {
                        @Override
                        public void onCallback(String response) {
                            Log.d("myapp", response);

                            LoginResponseModel model = new Gson().fromJson(response, LoginResponseModel.class);
                            LoginModel loginModels=model.getData();
                                                  SharedPreferences preferences=getSharedPreferences("AmazonApp", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor=preferences.edit();

                            //                           Toast.makeText(LoginActivity.this,model.getMessage() , Toast.LENGTH_SHORT).show();
                            if (model.getSuccess().equals("1")) {
                              editor.putString(
                                        "Fname","Welcome "+
                                        loginModels.getFname());
                                editor.putString("CustomerId",loginModels.getCustomerId());
                                editor.putString("Token",model.getToken());
                                editor.putString("Email",loginModels.getEmail());
                                editor.putString("customerName",loginModels.getFname());
                                editor.putBoolean("IsAutho",true);
                                editor.commit();
                                Toast.makeText(LoginActivity.this, ""+model.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else if (model.getSuccess().equals("0") ) {
                                Toast.makeText(LoginActivity.this, ""+model.getMessage(), Toast.LENGTH_SHORT).show();
                                editor.putBoolean("IsAutho",false);
                            }
                        }
                    }).execute();
                }
                }


        });
        /*toolbar.findViewById(R.id.bar);
        toolbar.getMenu().findItem(R.id.login).setVisible(false);*/
    }
}
