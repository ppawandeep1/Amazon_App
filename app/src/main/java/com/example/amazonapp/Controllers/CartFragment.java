package com.example.amazonapp.Controllers;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amazonapp.Adapters.CartAdapter;
import com.example.amazonapp.Adapters.OrderHistoryAdapter;
import com.example.amazonapp.Models.CartModel;
import com.example.amazonapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class CartFragment extends Fragment {

    private RecyclerView recyclerView_cart;

    //String s1[], s2[]; // first array is for naming and second is for description
    //int images[] = {R.drawable.cloth1, R.drawable.cloth2, R.drawable.cloth3, R.drawable.cloth4, R.drawable.cloth5, R.drawable.cloth6, R.drawable.cloth7, R.drawable.cloth8};

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView_cart = v.findViewById(R.id.recylerViewCart);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView_cart.setLayoutManager(layoutManager);

        List<CartModel> cartModel = new ArrayList<>();
        cartModel.add(new CartModel(0, R.drawable.cloth5, "skirt", "$4999", 5));
        cartModel.add(new CartModel(0, R.drawable.cloth5, "skirt11", "$222", 2));
        cartModel.add(new CartModel(0, R.drawable.cloth5, "skirts22", "$339", 1));

        cartModel.add(new CartModel(0, "3", "$5098", "99"));

        CartAdapter cartAdapter = new CartAdapter(cartModel);
        recyclerView_cart.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        //s1 = getResources().getStringArray(R.array.clothes_names);
        //s2 = getResources().getStringArray(R.array.clothes_description);

        //OrderHistoryAdapter myAdapter =  new OrderHistoryAdapter(getContext(), s1, s2, images);
        //.setAdapter(myAdapter);
        recyclerView_cart.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }



}
