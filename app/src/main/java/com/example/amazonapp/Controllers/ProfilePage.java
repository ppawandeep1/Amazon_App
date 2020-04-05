package com.example.amazonapp.Controllers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.amazonapp.AsyncTasks.AsyncResponse;
import com.example.amazonapp.AsyncTasks.WebServiceCallGet;
import com.example.amazonapp.AsyncTasks.WebserviceCall;
import com.example.amazonapp.Helper.Config;
import com.example.amazonapp.Helper.Utils;
import com.example.amazonapp.Models.CountryModel;
import com.example.amazonapp.Models.CountryResponseModel;
import com.example.amazonapp.Models.ProfilePageModel;
import com.example.amazonapp.Models.ProfileResponseModel;
import com.example.amazonapp.Models.ResponseSignupModel;
import com.example.amazonapp.R;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static com.example.amazonapp.Controllers.SignUpActivity.countryId;
import static com.example.amazonapp.Controllers.SignUpActivity.provinceId;


public class ProfilePage extends Fragment implements View.OnClickListener {


    View view;
    Button btn_edit, btn_save;
    EditText edit_fname, edit_lname, edit_email, edit_cnumber, edit_pcode, edit_address;
    Spinner sp_ctry, sp_city, sp_province;
    Context context;



    public ProfilePage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_profile, container, false);

        return view ;
    }


    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        btn_edit = view.findViewById(R.id.btn_update);
        btn_save = view.findViewById(R.id.btn_savechanges);
        edit_fname = view.findViewById(R.id.firstName_pr);

        edit_lname = view.findViewById(R.id.lastName_pr);
        edit_email = view.findViewById(R.id.emailaddress_pr);
        edit_cnumber = view.findViewById(R.id.contactnumber_pr);
        edit_pcode = view.findViewById(R.id.postalcode_pr);
        edit_address = view.findViewById(R.id.address_pr);

        //Spinner
        sp_ctry = view.findViewById(R.id.country_pr);
        sp_city = view.findViewById(R.id.city_pr);
        sp_province = view.findViewById(R.id.province_pr);

        edit_fname.setEnabled(false);
        btn_edit.setText("Edit");

        btn_edit.setOnClickListener(this);


        btn_save.setOnClickListener(this);

        String[] keys = new String[]{"userId"};
        String[] values = new String[]{"1"};
        final String JSONREQUEST = Utils.createJsonRequest(keys, values);
        final String PROFILEURL = Config.GETPROFILE;

        new WebServiceCallGet(context, PROFILEURL, JSONREQUEST, "Getting Profile..", true, new AsyncResponse() {
            @Override
            public void onCallback(String response) {
                Log.d("response", response);
                ProfileResponseModel model = new Gson().fromJson(response, ProfileResponseModel.class);
                ArrayList<ProfilePageModel> profilePageModels = model.getData();
                // HashMap<Integer, String> P_Hash=new HashMap<Integer, String>();


                if (model.getSuccess().equals("1")) {
                    // Toast.makeText(SignUpActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                    ArrayList<String> profileString = new ArrayList<>();
                    for (ProfilePageModel pm : profilePageModels) {
                        profileString.add(pm.getFirstname());
                        profileString.add(pm.getLastname());
                        profileString.add(pm.getEAdrress());
                        profileString.add(pm.getPhnumber());
                        profileString.add(pm.getPostalcode());
                        profileString.add(pm.getAddress());
                        /*counterInt.add(cm.getCountryId());
                        P_Hash.put(Integer.parseInt(cm.getCountryId()),cm.getName());*/
                    }
                    //ArrayAdapter<HashMap<Integer, String>> bindCategory = new ArrayAdapter<HashMap<Integer,String>>(SignUpActivity.this, android.R.layout.simple_spinner_item);
                   /* ArrayAdapter<String> bindCategory=new ArrayAdapter<String>(SignUpActivity.this,android.R.layout.simple_spinner_item,counteryString);
                    //bindCategory.add(P_Hash);
                    bindCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    country.setAdapter(bindCategory);*/
                } else if (model.getSuccess().equals("0")) {
                    Toast.makeText(view.getContext(), "" + model.getSuccess(), Toast.LENGTH_SHORT).show();
                }
            }
        }).execute();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_update:  //Your edit button
                enableDisableEditText();
                break;

            default:
                break;
        }

    }

    //method for enable or disable edittext
    private void enableDisableEditText() {
        edit_fname.setFocusableInTouchMode(true);
        edit_lname.setFocusableInTouchMode(true);
        edit_email.setFocusableInTouchMode(true);
        edit_cnumber.setFocusableInTouchMode(true);
        sp_ctry.setFocusableInTouchMode(true);
        sp_city.setFocusableInTouchMode(true);
        sp_province.setFocusableInTouchMode(true);
        edit_pcode.setFocusableInTouchMode(true);
        edit_address.setFocusableInTouchMode(true);
        if (edit_fname.isEnabled()) {
            edit_fname.setEnabled(false);
            ;
            // btn_edit.setText("Edit");
        } else {
            edit_fname.setEnabled(true);

            // btn_edit.setText("Done");
        }



    }





}
