package com.example.amazonapp.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amazonapp.AsyncTasks.AsyncResponse;
import com.example.amazonapp.AsyncTasks.WebserviceCall;

import com.example.amazonapp.Helper.Config;
import com.example.amazonapp.Helper.Utils;
import com.example.amazonapp.Models.CategoryModel;
import com.example.amazonapp.Models.ResponseModel;
import com.example.amazonapp.R;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import com.google.gson.Gson;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener{
    private static final String TAG =MainActivity.class.getName() ;

    Toolbar toolbar_top;
   //for header
    BottomAppBar bottomAppBar;
    //for bottom
    Spinner spinner_category;

    TextView txtUserWlcm;
    //Hiding menu items on different items
    Toolbar toolbar;
    //
    public static String category;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(toolbar_top);

        txtUserWlcm=findViewById(R.id.txtWlcmUser);
        SharedPreferences myPrefs = getSharedPreferences("AmazonApp", Context.MODE_PRIVATE);


        txtUserWlcm.setText(myPrefs.getString("Fname","Welcome Guest"));




        final HomeFragment fragment=new HomeFragment(MainActivity.this);
        if (findViewById(R.id.main_layout) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_layout, fragment).commit();


            bottomAppBar = findViewById(R.id.bar);
            setSupportActionBar(bottomAppBar);
        }


        ///--------------------------------------------------------------------------------------

        spinner_category=(Spinner)findViewById(R.id.spinner_category);

        spinner_category.setOnItemSelectedListener(this);
        //CAtegory List...//

        String[] keys=new String[]{"CompanyId"};
        String[] values=new String[]{"1"};
        final String JSONREQUEST= Utils.createJsonRequest(keys,values);
        String URL= Config.GET_CATEGORIES;

        new WebserviceCall(MainActivity.this, URL, JSONREQUEST, "Getting catgeories..", true, new AsyncResponse() {
            @Override
            public void onCallback(String response) {
                Log.d("response", response);
                ResponseModel model = new Gson().fromJson(response, ResponseModel.class);
                ArrayList<CategoryModel> categoryModel=model.getData();

                if (model.getSuccess().equals("1") ) {
                    //Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();

                    ArrayList<String> categoryString=new ArrayList<>();


                    for(CategoryModel cm:categoryModel)
                    {
                        categoryString.add(cm.getCategoryname());

                    }

                    ArrayAdapter<String> bindCategory=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,categoryString);
                    bindCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinner_category.setAdapter(bindCategory);






                } else if (model.getSuccess().equals("0")) {
                    Toast.makeText(MainActivity.this, "" + model.getSuccess(), Toast.LENGTH_SHORT).show();

                }
            }
        }).execute();


        //Spinner put data using api

        //SearchView binding with API
        SearchView simpleSearchView = (SearchView) findViewById(R.id.search); // inititate a search view

        // perform set on query text listener event
        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // do something on text submit
                //Toast.makeText(MainActivity.this," "+query,Toast.LENGTH_SHORT).show();
                //calling the api


                Fragment newFragment = new SearchProduct(MainActivity.this,category,query);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack if needed
                transaction.replace(R.id.main_layout, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
            // do something when text changes

                return false;
            }
        });



    }


//not able to use the network connection method
    //Bottom Navigation menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                Fragment homeFrag = null;
                homeFrag = new HomeFragment(MainActivity.this);


                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack if needed
                transaction.replace(R.id.main_layout, homeFrag);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
                break;
            case R.id.menu:
                SharedPreferences myPrefs = getSharedPreferences("AmazonApp", Context.MODE_PRIVATE);
                String user_name=myPrefs.getString("Fname",null);
                if(user_name!=null){
                    BottomSheetDialogFragment bottomSheetDialogFragment = new BottomFragment();
                    bottomSheetDialogFragment.show(getSupportFragmentManager(),bottomSheetDialogFragment.getClass().getSimpleName());
                }
                else {
                    BottomSheetDialogFragment bottomSheetDialogFragment = new GuestUserNav();
                    bottomSheetDialogFragment.show(getSupportFragmentManager(),bottomSheetDialogFragment.getClass().getSimpleName());
                }

                //Toast.makeText(this, "Menu ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cart:
                Fragment selectedFragment = null;
                selectedFragment = new CartFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, selectedFragment).commit();


                break;

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        category= (String) spinner_category.getSelectedItem();



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}