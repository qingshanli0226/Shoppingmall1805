package com.shopmall.bawei.shopcar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

//@Route(path="/goodcar/Shpping_car_Activity")
public class Shpping_car_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopca);
//        ARouter.getInstance().inject(this);

        Toast.makeText(this, "aaaa", Toast.LENGTH_SHORT).show();
    }
}
