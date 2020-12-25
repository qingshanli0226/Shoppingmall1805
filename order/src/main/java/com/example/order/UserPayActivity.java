package com.shopmall.bawei.shopmall1805.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.net.bean.OrderInfoBean;
import com.shopmall.bawei.shopmall1805.R;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/ui/UserPayActivity")
public class UserPayActivity extends AppCompatActivity {

    private RecyclerView rvUserPay;

    private List<OrderInfoBean> listResultSuccess = new ArrayList<>();
    private List<OrderInfoBean> listResultFalse = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pay);
        initView();
        ARouter.getInstance().inject(this);


    }

    private void initView() {
        rvUserPay = (RecyclerView) findViewById(R.id.rvUserPay);
    }
}
