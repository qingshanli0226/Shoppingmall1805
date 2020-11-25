package com.shopmall.bawei.shopmall1805.home.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.common.ShopmallConstant;
import com.shopmall.bawei.shopmall1805.R;


public class MineFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        ARouter.getInstance().inject(this);
        View rootView = inflater.inflate(R.layout.fragment_mine, container, false);
        rootView.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ShopmallConstant.LOGIN_ACTIVITY_PATH).withInt(ShopmallConstant.TO_LOGIN_KEY,ShopmallConstant.TO_LOGIN_FROM_MINE_FRAGMENT).navigation();
            }
        });

        return rootView;
    }
}
