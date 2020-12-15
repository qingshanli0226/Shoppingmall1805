package com.shopmall.bawei.pay;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.net.bean.ShopcarBean;
import com.shopmall.bawei.framework.example.framework.BaseActivity;
import com.shopmall.bawei.framework.example.framework.IPresenter;
import com.shopmall.bawei.framework.example.framework.IView;
import com.shopmall.bawei.framework.example.framework.user.CacheManager;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/Pay/PayActivity")
public class PayActivity extends BaseActivity<IPresenter, IView> implements View.OnClickListener {

    private TextView tvUserName;
    private TextView tvUserTelphone;
    private TextView tvUserAddress;
    private Button btAddAddress;
    private RecyclerView rvShopcarList;
    private Button btRefer;
    private TextView tvTotal;
    private TextView tvCount;
    private List<ShopcarBean> shopcarBeanList = new ArrayList<>();

    @Override
    protected void initpreseter() {
        //获取从购物车已选择的商品集合
        shopcarBeanList = CacheManager.getInstance().getselectproductList();



    }

    @Override
    protected void initdate() {

    }

    @Override
    protected void initview() {
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvUserTelphone = (TextView) findViewById(R.id.tvUserTelphone);
        tvUserAddress = (TextView) findViewById(R.id.tvUserAddress);
        btAddAddress = (Button) findViewById(R.id.btAddAddress);
        rvShopcarList = (RecyclerView) findViewById(R.id.rvShopcarList);
        btRefer = (Button) findViewById(R.id.btRefer);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        tvCount = (TextView) findViewById(R.id.tvCount);

        btAddAddress.setOnClickListener(this);
        btRefer.setOnClickListener(this);


    }

    @Override
    protected int getlayoutid() {
        return R.layout.activity_pay;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btAddAddress) {

        } else if (id == R.id.btRefer) {

        }
    }
}
