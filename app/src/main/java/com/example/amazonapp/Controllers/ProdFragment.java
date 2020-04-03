package com.example.amazonapp.Controllers;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.amazonapp.Adapters.ProdRecyclerAdapter;
import com.example.amazonapp.AsyncTasks.AsyncResponse;
import com.example.amazonapp.AsyncTasks.WebserviceCall;
import com.example.amazonapp.Helper.Config;
import com.example.amazonapp.Helper.Utils;
import com.example.amazonapp.Models.GetProductByCategory;
import com.example.amazonapp.Models.ResponseGetProductById;
import com.example.amazonapp.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ProdFragment extends Fragment {

    View view;
    private RecyclerView recyclerView;
    List<String> prodTitle;
    List<String> prodImgUrl;
    List<String> prodId;
    ProdRecyclerAdapter adapter;
    Context context;
    String categoryName;
    //Adapter myadapter;
    //LayoutManager layoutManager;


    public ProdFragment(Context context,String categoryName) {
        this.categoryName=categoryName;
        this.context=context;

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
        String[] keys=new String[]{"CompanyId"};
        String[] values=new String[]{"1"};
        final String JSONREQUEST= Utils.createJsonRequest(keys,values);
        String URL= Config.GETPRODCUTBYCATEGORY+categoryName;

        new WebserviceCall(context, URL, JSONREQUEST, "Getting products..", true, new AsyncResponse() {
            @Override
            public void onCallback(String response) {
                Log.d("response", response);
                ResponseGetProductById model = new Gson().fromJson(response, ResponseGetProductById.class);
                ArrayList<GetProductByCategory> productModel=model.getData();

                if (model.getSuccess().equals("1") ) {
                    Toast.makeText(view.getContext(), "" + response, Toast.LENGTH_SHORT).show();

                    /*  ArrayList<String> categoryName=new ArrayList<>();*/

                    ArrayList<String> categoryString=new ArrayList<>();
                    prodTitle=new ArrayList<>();
                    prodImgUrl=new ArrayList<>();
                    prodId=new ArrayList<>();
                    recyclerView = view.findViewById(R.id.recyclerr);
                    for(GetProductByCategory pm:productModel)
                    {

                        prodTitle.add(pm.getProductName());
                        prodImgUrl.add(pm.getImage());
                        prodId.add(pm.getProductId());
                    }


                    //binding cardview with api data
                    adapter = new ProdRecyclerAdapter(view.getContext(),prodTitle,prodImgUrl,prodId);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2, GridLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(adapter);








                } else if (model.getSuccess().equals("0")) {
                    Toast.makeText(view.getContext(), "" + model.getSuccess(), Toast.LENGTH_SHORT).show();

                }
            }
        }).execute();


    }

}
