package com.bawei.shopmall.user;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.common.view.MyToolBar;
import com.bawei.shopmall.ARouterPath;
import com.shopmall.bawei.shopmall1805.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    private MyToolBar myToolbar;
    private View inflate;
    private ImageView ib_user_icon_avator;
    private TextView tv_username;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        inflate = inflater.inflate(R.layout.fragment_user, container, false);
        initView();
        ARouter.getInstance().inject(this);
        ib_user_icon_avator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ARouterPath.LoginRegisterActivity).navigation();
            }
        });
        return inflate;
    }

    private void initView() {
        ib_user_icon_avator = inflate.findViewById(R.id.ib_user_icon_avator);
        tv_username = inflate.findViewById(R.id.tv_username);
    }
}
