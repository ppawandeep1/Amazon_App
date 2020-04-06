package com.example.amazonapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazonapp.Controllers.OrderHistory;
import com.example.amazonapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder> {
/*
*Adapter for displaying the Order history
 */
    ArrayList<String> img;
    ArrayList<String> qty;
    ArrayList<String> price;
    Context context;

    public OrderHistoryAdapter(Context ct, ArrayList<String> img,ArrayList<String> qty, ArrayList<String> price) {
        context = ct;
        this.price=price;
        this.qty=qty;
        this.img=img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mytitle.setText(price.get(position));
        Picasso.with(context).load(img.get(position)).resize(150, 150).centerCrop().into(holder.myImage);
        holder.myDescription.setText(qty.get(position));
       /* holder.mytitle.setText(data1[position]);
        holder.myDescription.setText(data2[position]);
        holder.myImage.setImageResource(images[position]);*/

    }

    @Override
    public int getItemCount() {
        return img.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        int view_type;
        //variables for list
        TextView mytitle, myDescription;
        ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
                mytitle = itemView.findViewById(R.id.order_title);
                myDescription = itemView.findViewById(R.id.order_qty);
                myImage = itemView.findViewById((R.id.order_image));
        }
    }
}
