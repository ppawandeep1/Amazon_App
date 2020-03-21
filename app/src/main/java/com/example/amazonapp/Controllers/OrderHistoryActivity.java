package com.example.amazonapp.Controllers;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazonapp.Adapters.OrderHistoryAdapter;
import com.example.amazonapp.R;

public class OrderHistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String s1[], s2[]; // first array is for naming and second is for description
    int images[] = {R.drawable.cloth1, R.drawable.cloth2, R.drawable.cloth3, R.drawable.cloth4, R.drawable.cloth5, R.drawable.cloth6, R.drawable.cloth7, R.drawable.cloth8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recylerView);

        s1 = getResources().getStringArray(R.array.clothes_names);
        s2 = getResources().getStringArray(R.array.clothes_description);

        OrderHistoryAdapter myAdapter =  new OrderHistoryAdapter(this, s1, s2, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
