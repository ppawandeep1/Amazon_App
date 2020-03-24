package com.example.amazonapp.Controllers;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.amazonapp.R;

public class SignUpActivity extends AppCompatActivity {

    // variables
    EditText fname,lname,email,contactno,password,cnfpassword;
    Button signup;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        updateUI();
    }

    private void updateUI() {

        fname =(EditText)findViewById(R.id.firstName);
        lname =(EditText)findViewById(R.id.lastName);
        email =(EditText)findViewById(R.id.email_address);
        password =(EditText)findViewById(R.id.password);
        cnfpassword =(EditText)findViewById(R.id.confirm_password);
        contactno =(EditText)findViewById(R.id.contact_number);
        signup =(Button)findViewById(R.id.btn_signup);

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

    }
}
