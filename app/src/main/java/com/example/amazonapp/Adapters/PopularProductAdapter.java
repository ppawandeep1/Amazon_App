package com.example.amazonapp.Adapters;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazonapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularProductAdapter extends RecyclerView.Adapter<PopularProductAdapter.ViewHolder>  {
    List<String> titles;
    List<String> images;
    Context context;
    LayoutInflater inflater;
    //UNABLE
    public PopularProductAdapter(Context ctx, List<String> titles,List<String> images){
        this.titles = titles;
        this.images = images;
        this.inflater = LayoutInflater.from(ctx);
        this.context=ctx;
    }


    @NonNull
    @Override
    public PopularProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.popularproduct_home,parent,false);
        return new PopularProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
       /* String ans=images.get(position);*/
        Picasso.with(context).load(images.get(position)).resize(150, 150).centerCrop().into(holder.gridIcon);
        //fit()
        //        //.resize(200, 200).centerInside().
        //        //.resize(200, 200).centerCrop()
        //different parameters
    }


    @Override
    public int getItemCount() {
        Log.e("ITEMCOUNT","INSIDE ITEMCOUNT");
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView gridIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtCategory);
            gridIcon = itemView.findViewById(R.id.imgCategory);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
