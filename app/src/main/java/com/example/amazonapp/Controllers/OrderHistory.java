package com.example.amazonapp.Controllers;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amazonapp.Adapters.OrderHistoryAdapter;
import com.example.amazonapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderHistory extends Fragment {

    RecyclerView recyclerView;

    String s1[], s2[]; // first array is for naming and second is for description
    int images[] = {R.drawable.cloth1, R.drawable.cloth2, R.drawable.cloth3, R.drawable.cloth4, R.drawable.cloth5, R.drawable.cloth6, R.drawable.cloth7, R.drawable.cloth8};

    public OrderHistory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView = (RecyclerView)getView().findViewById(R.id.recylerView);

        s1 = getResources().getStringArray(R.array.clothes_names);
        s2 = getResources().getStringArray(R.array.clothes_description);

        OrderHistoryAdapter myAdapter =  new OrderHistoryAdapter(getContext(), s1, s2, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_history, container, false);
    }

}
