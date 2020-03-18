package com.example.amazonapp.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.amazonapp.Adapters.CategoryAdapter;
import com.example.amazonapp.Adapters.PopularProductAdapter;
import com.example.amazonapp.R;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView categoryList, productList;
   Toolbar toolbar_top;
    List<String> titles;
    CategoryAdapter adapter;
    PopularProductAdapter popularProductAdapter;
    BottomAppBar bottomAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar_top = findViewById(R.id.top_navigation_view);
        setSupportActionBar(toolbar_top);
       // toolbar_top.setLogo(R.drawable.logo);
        //getSupportActionBar().setTitle("Logo");
        //toolbar_top.setSubtitle("welcome");

        //CAtegory List...//
        categoryList = findViewById(R.id.categoryList);
        productList=findViewById(R.id.popularproduct);
        bottomAppBar=findViewById(R.id.bar);
        setSupportActionBar(bottomAppBar);
        titles = new ArrayList<>();

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
