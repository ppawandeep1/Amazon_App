package com.example.amazonapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazonapp.Models.CartModel;
import com.example.amazonapp.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

    private List<CartModel> cartItemModelList;

    //constructor
    public CartAdapter(List<CartModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    // three methods

    @Override
    public int getItemViewType(int position) {

        switch (cartItemModelList.get(position).getType()){
            case 0:
                return CartModel.CART_ITEM;
                case 1:
                    return CartModel.TOTAL_AMOUNT;
            default:
                return -1;

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case CartModel.CART_ITEM:
                View cartItemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item_layout, viewGroup, false);
                return  new CartItemViewHolder(cartItemView);
            case CartModel.TOTAL_AMOUNT:
                View cartAmountView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_cart, viewGroup, false);
                return  new CartTotalAmountViewHolder(cartAmountView);
                default:
                    return null;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (cartItemModelList.get(position).getType()){
            case CartModel.CART_ITEM:
                int resource = cartItemModelList.get(position).getProductImage();
                String title = cartItemModelList.get(position).getProductTitle();
                String price = cartItemModelList.get(position).getProductPrice();
                //int quantity = cartItemModelList.get(position).getProductQuantity();

                ((CartItemViewHolder)viewHolder).setItemDetails(resource, title, price);
                break;
                case CartModel.TOTAL_AMOUNT:
                    String totalItemText = cartItemModelList.get(position).getTotalItems();
                    String totalShippingChargesText = cartItemModelList.get(position).getTotalShippingCharges();
                    String totalAmountText = cartItemModelList.get(position).gettotalItemsAmount();

                    ((CartTotalAmountViewHolder)viewHolder).setTotalAmountDetails(totalItemText, totalShippingChargesText, totalAmountText);
                    break;
                    default:
                        return;
        }
    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    class CartItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private TextView productTitle;
        private TextView productPrice;
        //private  TextView productQuantity;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            productPrice = itemView.findViewById(R.id.product_price);
            //productQuantity = itemView.findViewById(R.id.product_quantity);
        }
        private void setItemDetails(int resources, String title, String price){
            productImage.setImageResource(resources);
            productTitle.setText(title);
            productPrice.setText(price);
            //productQuantity.setText(quantity);

        }
    }

    class CartTotalAmountViewHolder extends RecyclerView.ViewHolder{

        private TextView totalItems;
        private TextView totalShippingCharges;
        private TextView totalItemsAmount;
        public CartTotalAmountViewHolder(@NonNull View itemView) {
            super(itemView);
            totalItems = itemView.findViewById(R.id.total_items);
            totalShippingCharges = itemView.findViewById(R.id.total_shipping_charges);
            totalItemsAmount = itemView.findViewById(R.id.total_price);
        }
        private void setTotalAmountDetails(String totalItemText,String totalShippingChargesText, String totalItemsAmountText){
            totalItems.setText(totalItemText);
            totalShippingCharges.setText(totalShippingChargesText);
            totalItemsAmount.setText(totalItemsAmountText);
        }
    }
}
