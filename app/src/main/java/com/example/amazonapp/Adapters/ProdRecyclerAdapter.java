package com.example.amazonapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazonapp.Controllers.ProductDetailsFragment;
import com.example.amazonapp.Models.ProductDetails;
import com.example.amazonapp.R;

import java.util.ArrayList;

public class ProdRecyclerAdapter  extends RecyclerView.Adapter<ProdRecyclerAdapter.MyViewHolders> {



        private ArrayList<ProductDetails> array;
        private Context context;


        public ProdRecyclerAdapter( ArrayList<ProductDetails> arrayl, Context mContext) {

            this.array  = arrayl;
            this.context = mContext;
        }

        @NonNull
        @Override
        public MyViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view ;
            LayoutInflater mInflater = LayoutInflater.from(context);
            view = mInflater.inflate(R.layout.prodcardview,parent,false);
            return new MyViewHolders(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolders holder, int position) {

            // Prod prod = array.get(position);
            holder.tv_title.setText(array.get(position).getTitle());
            holder.img.setImageResource(array.get(position).getThumbnail());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    ProductDetailsFragment produFragment = new ProductDetailsFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, produFragment).addToBackStack(null).commit();

                    Spinner spinner = v.findViewById(R.id.spiner);



                }
            });

        }

        @Override
        public int getItemCount() {
            return array.size();
        }

        public  class MyViewHolders extends RecyclerView.ViewHolder{

            TextView tv_title;
            ImageView img;
            //CardView cardView ;


            public MyViewHolders(@NonNull View itemView) {
                super(itemView);


                tv_title = (TextView) itemView.findViewById(R.id.book_title_id);
                img = (ImageView) itemView.findViewById(R.id.book_img_id);
                //cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            }
        }
    }


