package com.example.amazonapp.Controllers;


import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.amazonapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomFragment extends BottomSheetDialogFragment {
    NavigationView navigationView;


    public BottomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navigationView=view.findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.login:
                       /* Login login =new Login();
                        FragmentManager manager=getFragmentManager();*/
                       // FragmentTransaction fragmentTransaction=manager.beginTransaction();
                        /*manager.beginTransaction().replace(R.id.main_layout,login,login.getTag()).commit();
                        navigationView.setVisibility(View.GONE);*/
                        /*fragmentTransaction.replace(R.id.container, login);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();*/
                        Intent intent=new Intent(getActivity(),LoginActivity.class);
                        getActivity().startActivity(intent);

                        break;
                       // Toast.makeText(getActivity(), "Login", Toast.LENGTH_SHORT).show();
                        //break;
                    case R.id.contact_us:
                        Toast.makeText(getActivity(), "Contact US", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:
                        Toast.makeText(getActivity(), "Log out ", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder AlertLogout=new AlertDialog.Builder(getActivity());
                        AlertLogout.setMessage("Do you want to Signout ?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("AmazonApp", Context.MODE_PRIVATE);

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.clear();
                                editor.commit();
                                Intent logout = new Intent(getActivity(), MainActivity.class);
                                startActivity(logout);


                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        AlertLogout.create().show();
                        /*AlertDialog alertDialog=AlertLogout.create();
                        alertDialog.setTitle("Sign out");
                        alertDialog.show();*/


                        break;
                    case R.id.order_history:
                        OrderHistory orderHistory=new OrderHistory();

                        break;
                    case R.id.order_history:
                        OrderHistory orderHistory=new OrderHistory();


                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(android.R.id.content, orderHistory);
                        fragmentTransaction.commit();

                        Toast.makeText(getActivity(), "Oder History ", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.profile:
                        ProfilePage profile =new ProfilePage();
                        FragmentManager managerpofile=getFragmentManager();
                        managerpofile.beginTransaction().replace(R.id.main_layout,profile,profile.getTag()).commit();
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + menuItem.getItemId());
                }
                return true;
            }
        });
    }

}
