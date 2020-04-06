package com.example.amazonapp.Controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amazonapp.AsyncTasks.AsyncResponse;
import com.example.amazonapp.AsyncTasks.WebserviceCall;
import com.example.amazonapp.Helper.Config;
import com.example.amazonapp.Helper.Utils;
import com.example.amazonapp.Models.ForgetPasswordResponseModel;
import com.example.amazonapp.R;
import com.google.gson.Gson;

public class ForgetPassword extends AppCompatActivity {

    Button get_password_btn;
    EditText userEmail;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_forgetpassword);

        //finding EditText
        userEmail = findViewById(R.id.edit_email);

        get_password_btn = findViewById(R.id.forget_btn);

        get_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email_edittext = userEmail.getText().toString();
                if (email_edittext.isEmpty())
                {
                    userEmail.setError("Please enter your Email id");
                }
                else
                {
                    String[] keys = new String[]{"email"};
                    String[] values = new String[]{email_edittext};
                    String jsonReq = Utils.createJsonRequest(keys, values);

                    String FORGETPASSWORDURL = Config.FORGETPASSWORD;
                    new WebserviceCall(ForgetPassword.this, FORGETPASSWORDURL, jsonReq, "Getting forget password", true, new AsyncResponse() {
                        @Override
                        public void onCallback(String response) {
                            Log.d("myapp", response);

                            ForgetPasswordResponseModel model = new Gson().fromJson(response, ForgetPasswordResponseModel.class);

                            // Toast.makeText(LoginActivity.this,model.getMessage() , Toast.LENGTH_SHORT).show();
                            if (model.getSuccess().equals("1")) {

                                Toast.makeText(ForgetPassword.this, ""+model.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ForgetPassword.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else if (model.getSuccess().equals("0") ) {
                                Toast.makeText(ForgetPassword.this, ""+model.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).execute();

                }
            }
        });
    }
}
