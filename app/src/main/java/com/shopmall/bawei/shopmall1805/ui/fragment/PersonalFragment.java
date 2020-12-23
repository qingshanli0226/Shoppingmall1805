package com.shopmall.bawei.shopmall1805.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.net.ShopUserManger;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.ui.activity.MessageActivity;


public class PersonalFragment extends Fragment {

    private TextView textView,tv;
    private ImageButton imageButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal, container, false);

        textView = view.findViewById(R.id.tv_username);
        tv = view.findViewById(R.id.tv_user_location);
        imageButton = view.findViewById(R.id.ib_user_message);

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

        //跳转消息页面
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MessageActivity.class));
            }
        });

        return view;
    }
}