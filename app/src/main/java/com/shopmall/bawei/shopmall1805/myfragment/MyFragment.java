package com.shopmall.bawei.shopmall1805.myfragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.myfragment.view.ReceivingAddressMainActivity;
import com.shopmall.bawei.user.view.UserMainActivity;


public class MyFragment extends Fragment {
    private ImageButton ibUserIconAvator;
    private TextView tvUsername;
    private  View view;
    private TextView tvUserLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_my, container, false);

        tvUserLocation = (TextView) view.findViewById(R.id.tv_user_location);

        ibUserIconAvator = (ImageButton) view.findViewById(R.id.ib_user_icon_avator);
        tvUsername = (TextView) view.findViewById(R.id.tv_username);
        ibUserIconAvator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserMainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        tvUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        tvUserLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ReceivingAddressMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        return view;
    }
}