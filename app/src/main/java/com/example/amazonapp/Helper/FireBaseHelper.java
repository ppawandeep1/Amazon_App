package com.example.amazonapp.Helper;


import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazonapp.Controllers.CartFragment;
import com.example.amazonapp.Models.CartFireBase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FireBaseHelper {


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference_Customer;
    private DatabaseReference databaseReference_CartProduct;


    public FireBaseHelper() {
        databaseReference_CartProduct = FirebaseDatabase.getInstance().getReference("CartProduct");

    }



    public  void InsertCart(String customer_id,String product_id,String qty,String product_name,String  price,String imgUrl)
    {
        /*
        *To insert data in cart
         */
        /*databaseReference_CartProduct = FirebaseDatabase.getInstance().getReference("CartProduct");*/
        String id  = databaseReference_CartProduct.push().getKey();
        CartFireBase cartFireBase= new CartFireBase();
        cartFireBase.setCustomer_id(customer_id);
        cartFireBase.setProduct_id(product_id);
        cartFireBase.setProductImage(imgUrl);
        cartFireBase.setProductPrice(price);
        cartFireBase.setProductQuantity(qty);
        cartFireBase.setProduct_name(product_name);
        cartFireBase.isActive();
        cartFireBase.isPurchase();

        databaseReference_CartProduct.child(id).setValue(cartFireBase);
    }
/*
*to view the items in the cart
 */
    public  void ViewCart(final String _CustomerId, final RecyclerView viewId, final TextView totalItem, final TextView totalShippingcost, final TextView totlaItemAmount)

    {
        final ArrayList<CartFireBase> _fireBasesCart=new ArrayList<CartFireBase>();;
       databaseReference_CartProduct = FirebaseDatabase.getInstance().getReference("CartProduct");

        databaseReference_CartProduct.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                CartFireBase cartProduct;
                final String Cust_id = _CustomerId;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    String isPurchase = postSnapshot.child("isPurchase").getValue().toString();
                    String Customer_id = postSnapshot.child("customer_id").getValue().toString();
                   String isActive=postSnapshot.child("isActive").getValue().toString();
                    if (isPurchase.equals("false") && Customer_id.equals(_CustomerId) && isActive.equals("true")) {
                        cartProduct = new CartFireBase();
                        cartProduct.setSnapId(postSnapshot.getKey());
                        cartProduct.setProduct_name(postSnapshot.child("product_name").getValue().toString());
                        cartProduct.setProductQuantity(postSnapshot.child("productQuantity").getValue().toString());
                        cartProduct.setProductImage(postSnapshot.child("productImage").getValue().toString());
                        cartProduct.setProduct_id(postSnapshot.child("product_id").getValue().toString());
                        cartProduct.setProductPrice(postSnapshot.child("productPrice").getValue().toString());
                        cartProduct.setCustomer_id(postSnapshot.child("customer_id").getValue().toString());
                        cartProduct.setPurchase(Boolean.valueOf(postSnapshot.child("isPurchase").getValue().toString()));
                        cartProduct.setPurchase(Boolean.valueOf(postSnapshot.child("isActive").getValue().toString()));

                        _fireBasesCart.add(cartProduct);
                    }

                }
                CartFragment fragment=new CartFragment();
                fragment.callCartAdapter(_fireBasesCart,viewId,totalItem,totalShippingcost,totlaItemAmount);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());
            }
        });
    }
/*To remove an item from the cart

 */
    public void removeItem(String snapId){
        databaseReference_CartProduct = FirebaseDatabase.getInstance().getReference("CartProduct").child(snapId);
        databaseReference_CartProduct.removeValue();
    }
/*
*To update the items in the cart
 */
    public void updateItemQty(String snapId,String qty){
        databaseReference_CartProduct = FirebaseDatabase.getInstance().getReference("CartProduct").child(snapId);

        databaseReference_CartProduct.child("productQuantity").setValue(qty);
    }
    /*
    To update all the purchase order to isPurchase=true
     */

    public void updatePurchaseOrder(ArrayList<String> arrsnapId){
        for(int i=0;i<arrsnapId.size();i++){
            databaseReference_CartProduct = FirebaseDatabase.getInstance().getReference("CartProduct").child(arrsnapId.get(i));

            databaseReference_CartProduct.child("isPurchase").setValue("true");

        }
    }


}
