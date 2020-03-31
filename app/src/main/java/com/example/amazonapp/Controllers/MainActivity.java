package com.example.amazonapp.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.amazonapp.Adapters.CategoryAdapter;
import com.example.amazonapp.Adapters.PopularProductAdapter;
import com.example.amazonapp.AsyncTasks.AsyncResponse;
import com.example.amazonapp.AsyncTasks.WebserviceCall;
import com.example.amazonapp.Helper.Config;
import com.example.amazonapp.Helper.Utils;
import com.example.amazonapp.Models.CategoryModel;
import com.example.amazonapp.Models.CategoryResponseModel;
import com.example.amazonapp.R;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG =MainActivity.class.getName() ;
    RecyclerView categoryList, productList;
   Toolbar toolbar_top;
    List<String> titles;
    CategoryAdapter adapter;
    PopularProductAdapter popularProductAdapter;
    BottomAppBar bottomAppBar;
    Spinner spinner_category;

    //Calling api using volley

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new ProdFragment())
                .disallowAddToBackStack()
                .commit();
        setSupportActionBar(toolbar_top);
       // toolbar_top.setLogo(R.drawable.logo);
        //getSupportActionBar().setTitle("Logo");
        //toolbar_top.setSubtitle("welcome");

        //CAtegory List...//
       /* spinner_category=findViewById(R.id.spinner_category);
        spinner_category.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);*/
        String[] keys=new String[]{"CompanyId"};
        String[] values=new String[]{"1"};
        final String JSONREQUEST= Utils.createJsonRequest(keys,values);
        String URL= Config.GET_CATEGORIES;

        new WebserviceCall(MainActivity.this, URL, JSONREQUEST, "Getting catgeories..", true, new AsyncResponse() {
            @Override
            public void onCallback(String response) {
                Log.d("response", response);
                CategoryResponseModel model = new Gson().fromJson(response, CategoryResponseModel.class);
                ArrayList<CategoryModel> categoryModel=model.getData();

                if (model.getSuccess() == "1") {
                    Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                  /*  ArrayList<String> categoryName=new ArrayList<>();*/

                   ArrayList<String> categoryString=new ArrayList<>();


                    for(CategoryModel cm:categoryModel){
                        categoryString.add(cm.getCategoryname());                    }
                    ArrayAdapter<String> bindCategory=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,categoryString);
                    spinner_category.setAdapter(bindCategory);
                    /*Intent intent = new Intent(Feedback.this, Home_Page_Navigation.class);
                    startActivity(intent);*/
                } else if (model.getSuccess() == "0") {
                    Toast.makeText(MainActivity.this, "" + model.getSuccess(), Toast.LENGTH_SHORT).show();

                }
            }
        }).execute();





        categoryList = findViewById(R.id.categoryList);
        productList=findViewById(R.id.popularproduct);
        bottomAppBar=findViewById(R.id.bar);
        setSupportActionBar(bottomAppBar);
        titles = new ArrayList<>();
        //Spinner put data using api

        //add title names of category by calling from api

        titles.add("One");
        titles.add("Two");
        titles.add("Three");
        titles.add("Four");

        adapter = new CategoryAdapter(this, titles);
        popularProductAdapter=new PopularProductAdapter(this,titles);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManagerProduct = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);

        productList.setLayoutManager(gridLayoutManagerProduct);
        productList.setAdapter(popularProductAdapter);
        categoryList.setLayoutManager(gridLayoutManager);
        categoryList.setAdapter(adapter);
    }

    /*private void BindCategory() {


        StringRequest postRequest = new StringRequest(Request.Method.POST, getCategoryURL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("CompanyId", "1");


                return params;
            }
        };
        requestQueue.add(postRequest);

    }*/

    //Bottom Navigation menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu:
                BottomSheetDialogFragment bottomSheetDialogFragment = new BottomFragment();
                bottomSheetDialogFragment.show(getSupportFragmentManager(),bottomSheetDialogFragment.getClass().getSimpleName());
                //Toast.makeText(this, "Menu ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cart:
                Toast.makeText(this, "Cart ", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
}
