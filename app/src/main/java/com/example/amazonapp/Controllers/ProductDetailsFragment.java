package com.example.amazonapp.Controllers;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amazonapp.Adapters.ProdRecyclerAdapter;
import com.example.amazonapp.AsyncTasks.AsyncResponse;
import com.example.amazonapp.AsyncTasks.WebServiceCallGet;
import com.example.amazonapp.AsyncTasks.WebserviceCall;
import com.example.amazonapp.Helper.Config;
import com.example.amazonapp.Helper.Utils;
import com.example.amazonapp.Models.GetProductByCategory;
import com.example.amazonapp.Models.ProductDetail;
import com.example.amazonapp.Models.ResponseGetProductById;
import com.example.amazonapp.Models.ResponseProductDetail;
import com.example.amazonapp.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ProductDetailsFragment extends Fragment {

    View view;
    String prodId;
    Context context;
    ResponseProductDetail responseProductDetail;
    ImageView prodImage;
    TextView prodTitle,prodDesc,itemInStock,itemPrice,itemSku;
    EditText txtQty;
    Button addtoCart;
    public ProductDetailsFragment(Context context,String prodId) {
        this.context=context;
        // Required empty public constructor
        this.prodId=prodId;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_product_details, container, false);
        prodImage=view.findViewById(R.id.productImage);
        prodTitle=view.findViewById(R.id.productTitle);
        prodDesc=view.findViewById(R.id.productDesc);
        itemInStock=view.findViewById(R.id.itemInSTock);
        itemPrice=view.findViewById(R.id.itemPrice);
        itemSku=view.findViewById(R.id.itemSku);
        txtQty=view.findViewById(R.id.txtQty);
        addtoCart=view.findViewById(R.id.addTocart);

        init();
        return view ;    }
    private  void init(){

        String URL= Config.PRODUCTDETAILS+prodId+"&CompanyId=1";

        new WebServiceCallGet(context, URL, null, "Getting products Details..", true, new AsyncResponse() {
            @Override
            public void onCallback(String response) {
                Log.d("response", response);
                ResponseProductDetail model = new Gson().fromJson(response, ResponseProductDetail.class);
               ArrayList<ProductDetail> productDetails=model.getData();

                if (model.getSuccess().equals("1")) {
                    Toast.makeText(view.getContext(), "" + response, Toast.LENGTH_SHORT).show();
                    Picasso.with(context).load(productDetails.get(0).getImage()).resize(150, 150).centerCrop().into(prodImage);
                    prodDesc.setText(productDetails.get(0).getDescription());
                    prodTitle.setText(productDetails.get(0).getDescription());
                    itemInStock.setText(productDetails.get(0).getAvailableQty());
                    itemPrice.setText(productDetails.get(0).getRetailPrice());
                    itemSku.setText(productDetails.get(0).getSKU());
                } else if (model.getSuccess().equals("0")) {
                    Toast.makeText(view.getContext(), "" + model.getSuccess(), Toast.LENGTH_SHORT).show();

                }
            }
        }).execute();


    }


}
