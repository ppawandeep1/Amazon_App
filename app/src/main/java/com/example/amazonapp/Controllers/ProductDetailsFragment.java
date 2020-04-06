package com.example.amazonapp.Controllers;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amazonapp.Adapters.DBProductAdapter;
import com.example.amazonapp.Models.ProductDetail;
import com.example.amazonapp.R;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amazonapp.R;



public class ProductDetailsFragment extends Fragment {


    private DBProductAdapter dbProduct;
    private Button cart_btn;
    private ImageView product_img;
    private TextView product_title, product_price, product_qty;


    View view;
    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_product_details, container, false);

        dbProduct = new DBProductAdapter(getActivity(), null);
        product_img = (ImageView) product_img.findViewById(R.id.bookthumbnail);
        product_title = (TextView) product_title.findViewById(R.id.txttitle);
        product_price = (TextView) product_price.findViewById(R.id.product_price);
        product_qty = (TextView) product_qty.findViewById(R.id.txtQty);

        cart_btn = (Button) cart_btn.findViewById(R.id.add_to_cart_btn);
        //cart_btn.setOnClickListener(new OnClickListenerCreateStudent());
        cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getproductimg = product_img.getResources().toString();
                String getproducttitle = product_title.getText().toString();
                String getproductprice = product_price.getText().toString();
                String getproductqty = product_qty.getText().toString();

                dbProduct.insertData(1, 1, 1, getproducttitle, getproductqty, getproductprice, getproductimg);
            }
        });
        return view ;
    }





}