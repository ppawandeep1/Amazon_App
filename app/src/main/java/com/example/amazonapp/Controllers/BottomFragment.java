package com.example.amazonapp.Controllers;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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
                        Login login =new Login();
                        FragmentManager manager=getFragmentManager();
                        manager.beginTransaction().replace(R.id.main_layout,login,login.getTag()).commit();

                        break;
                       // Toast.makeText(getActivity(), "Login", Toast.LENGTH_SHORT).show();
                        //break;
                    case R.id.contact_us:
                        Toast.makeText(getActivity(), "Contact US", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:
                        Toast.makeText(getActivity(), "Log out ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.order_history:
                        Toast.makeText(getActivity(), "Oder History ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.profile:
                        ProfilePage profile =new ProfilePage();
                        FragmentManager managerpofile=getFragmentManager();
                        managerpofile.beginTransaction().replace(R.id.main_layout,profile,profile.getTag()).commit();


                        break;

                }
                return true;
            }
        });
    }

}
