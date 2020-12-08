package com.bw.shopcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.net.bean.ShopCarBean;
import com.shopmall.bawei.shopcar.R;


@Route(path = "/activity/activity_shopCart")
public class ShopCarActivity extends AppCompatActivity {

    private ShopCarBean goods;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcar);

        ARouter.getInstance().inject(this);




    }
}
