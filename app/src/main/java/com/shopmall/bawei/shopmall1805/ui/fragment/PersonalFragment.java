package com.shopmall.bawei.shopmall1805.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.net.ShopUserManger;
import com.shopmall.bawei.shopmall1805.R;


public class PersonalFragment extends Fragment {

    private TextView textView,tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal, container, false);

        textView = view.findViewById(R.id.tv_username);
        tv = view.findViewById(R.id.tv_user_location);
        textView.setText(ShopUserManger.getInstance().getName());

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance()
                        .build("/duoduo/user")
                        .navigation();
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance()
                        .build("/order/address")
                        .navigation();
            }
        });

        return view;
    }
}