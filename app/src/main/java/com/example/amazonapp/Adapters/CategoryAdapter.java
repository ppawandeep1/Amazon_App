package com.example.amazonapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazonapp.AsyncTasks.AsyncResponse;
import com.example.amazonapp.AsyncTasks.WebserviceCall;
import com.example.amazonapp.Controllers.MainActivity;
import com.example.amazonapp.Controllers.ProdFragment;
import com.example.amazonapp.Helper.Config;
import com.example.amazonapp.Helper.Utils;
import com.example.amazonapp.Models.CategoryModel;
import com.example.amazonapp.Models.ResponseGetProductById;
import com.example.amazonapp.Models.ResponseModel;
import com.example.amazonapp.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    List<String> titles;
    LayoutInflater inflater;
    Context context;
/*
*Category Adapter for all the category will bind single xml layout multile times as per the
 */
    CoordinatorLayout main_layout;
    RecyclerView category,product;

    ProdRecyclerAdapter prodRecyclerAdapter;
    public CategoryAdapter(Context ctx, List<String> titles){
        this.titles = titles;
        this.context=ctx;
        this.inflater = LayoutInflater.from(ctx);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_home,parent,false);


        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(titles.get(position));


    }

    @Override
    public int getItemCount () {
        return titles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.category_home);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    main_layout=v.findViewById(R.id.main_layout);
                    //Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    //calling
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    ProdFragment productFragment = new ProdFragment(v.getContext(),titles.get(getAdapterPosition()));



                    activity.getSupportFragmentManager().beginTransaction();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, productFragment).addToBackStack(null).commit();




                }
            });
        }
    }


}