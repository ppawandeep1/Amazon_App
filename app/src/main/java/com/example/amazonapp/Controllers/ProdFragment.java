package com.example.amazonapp.Controllers;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amazonapp.Adapters.ProdRecyclerAdapter;
import com.example.amazonapp.Models.ProductDetails;
import com.example.amazonapp.R;

import java.util.ArrayList;

public class ProdFragment extends Fragment {

    View view;
    private RecyclerView recyclerView;
    //Adapter myadapter;
    //LayoutManager layoutManager;

    ArrayList<ProductDetails> lstprod;
    public ProdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_prod, container, false);
        init();
        return view ;

    }

    private  void init(){
        lstprod= new ArrayList<>();

        lstprod.add(new ProductDetails("The Vegitarian",R.drawable.cms));
        lstprod.add(new ProductDetails("The Wild Robot",R.drawable.cms));
        lstprod.add(new ProductDetails("Maria Semples",R.drawable.cms));
        lstprod.add(new ProductDetails("The Martian",R.drawable.cms));
        lstprod.add(new ProductDetails("He Died with...",R.drawable.cms));
        lstprod.add(new ProductDetails("The Vegitarian",R.drawable.cms));
        lstprod.add(new ProductDetails("The Wild Robot",R.drawable.cms));
        lstprod.add(new ProductDetails("Maria Semples",R.drawable.cms));
        lstprod.add(new ProductDetails("The Martian",R.drawable.cms));
        lstprod.add(new ProductDetails("He Died with...",R.drawable.cms));


        recyclerView = view.findViewById(R.id.recyclerr);
//        recyclerView.setHasFixedSize(true);

        //layoutManager = new GridLayoutManager(this,2,VERTICAL,true);
        ProdRecyclerAdapter myAdapter = new ProdRecyclerAdapter(lstprod,getActivity());
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
    }

}
