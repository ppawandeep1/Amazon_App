package com.example.amazonapp.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.amazonapp.Adapters.CategoryAdapter;
import com.example.amazonapp.Adapters.PopularProductAdapter;
import com.example.amazonapp.AsyncTasks.AsyncResponse;
import com.example.amazonapp.AsyncTasks.WebserviceCall;
import com.example.amazonapp.Async_Tasks_One.AsyncResponse_One;
import com.example.amazonapp.Async_Tasks_One.WebserviceCall_One;
import com.example.amazonapp.Helper.Config;
import com.example.amazonapp.Helper.Utils;
import com.example.amazonapp.Models.CategoryModel;
import com.example.amazonapp.Models.PopularProductModel;
import com.example.amazonapp.Models.PopularProductResponseModel;
import com.example.amazonapp.Models.ResponseModel;
import com.example.amazonapp.R;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener{
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

        setSupportActionBar(toolbar_top);

        spinner_category=(Spinner)findViewById(R.id.spinner_category);
        spinner_category.setOnItemSelectedListener(this);
        //CAtegory List...//
        PopularProduct();
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
                    Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();

                  /*  ArrayList<String> categoryName=new ArrayList<>();*/

                   ArrayList<String> categoryString=new ArrayList<>();


                    for(CategoryModel cm:categoryModel)
                    {
                        categoryString.add(cm.getCategoryname());
                        titles.add(cm.getCategoryname());
                    }

                    ArrayAdapter<String> bindCategory=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,categoryString);
                    bindCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinner_category.setAdapter(bindCategory);
                    //binding cardview with api data
                    adapter = new CategoryAdapter(MainActivity.this, titles);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.VERTICAL, false);
                    categoryList.setLayoutManager(gridLayoutManager);
                    categoryList.setAdapter(adapter);

                    //will call api of popular product
                    popularProductAdapter=new PopularProductAdapter(MainActivity.this,titles);

                    GridLayoutManager gridLayoutManagerProduct = new GridLayoutManager(MainActivity.this, 1, GridLayoutManager.HORIZONTAL, false);

                    productList.setLayoutManager(gridLayoutManagerProduct);
                    productList.setAdapter(popularProductAdapter);





                } else if (model.getSuccess().equals("0")) {
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
    public void PopularProduct(){
      /*  String[] keys=new String[]{"CompanyId"};
        String[] values=new String[]{"1"};
        final String JSONREQUEST= Utils.createJsonRequest(keys,values);*/
        String URL= Config.POPULAR_PRODUCT;

        new WebserviceCall_One(MainActivity.this, URL, "Menu", true, new AsyncResponse_One() {
            @Override
            public void onCallback_One(String response) {
                Log.d("kj", response);
                try {
                    JSONArray j = new JSONArray(response);
                    for (int i = 0; i < j.length(); i++) {
                        JSONObject obj = j.getJSONObject(i);
                        /*menuid = obj.getString("menuid");
                        menuname = obj.getString("menuname");*/
                        //Toast.makeText(Home_Page_Navigation.this, "hii"+menuname, Toast.LENGTH_SHORT).show();
//                        m_date=obj.getString("date");
//                        quantity=obj.getString("quantity");
                        // Toast.makeText(Home_Page_Navigation.this, "qty"+quantity, Toast.LENGTH_SHORT).show();
                        /*addingValueToHasMap(menuid,menuname);*/
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
               /* Next15 ca=new Next15(Next_15_Days_Menu.this,R.layout.custom_list_15days_menu,menulist);
                type.setAdapter(ca);

                type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        pos = i+1;
                        showitem.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                String dateselected = date.getText().toString();
                                if (dateselected.isEmpty()){
                                    date.setError("ye bhai date to nakh");

                                }
                                else {
                                    Intent in = new Intent(Next_15_Days_Menu.this, next_15day_item_menu.class);
                                    in.putExtra("date", dateselected);
                                    in.putExtra("pos", pos);
                                    startActivity(in);
                                }

                            }
                        });
                    }});*/

            }
        }).execute();

    }

    //Bottom Navigation menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu:
                BottomSheetDialogFragment bottomSheetDialogFragment = new BottomFragment(getApplicationContext());
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }*/
}
