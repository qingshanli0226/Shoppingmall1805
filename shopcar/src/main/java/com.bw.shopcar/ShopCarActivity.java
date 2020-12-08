package com.bw.shopcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.framework.BaseActivity;
import com.bw.framework.CacheManager;
import com.bw.net.bean.ShopCarBean;
import com.bw.shopcar.adapter.ShopCarAdapter;
import com.bw.shopcar.contract.ShopCarContract;
import com.bw.shopcar.presenter.ShopCarPresenter;
import com.shopmall.bawei.shopcar.R;

import java.util.List;


@Route(path = "/activity/activity_shopCart")
public class ShopCarActivity extends BaseActivity<ShopCarPresenter, ShopCarContract.ShopCarView> implements ShopCarContract.ShopCarView {

    private ImageButton ibShopcartBack;
    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;

    private ShopCarAdapter shopCarAdapter;



    @Override
    protected void initView() {
        super.initView();
        ARouter.getInstance().inject(this);

        ibShopcartBack = (ImageButton) findViewById(R.id.ib_shopcart_back);
        tvShopcartEdit = (TextView) findViewById(R.id.tv_shopcart_edit);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) findViewById(R.id.ll_delete);
        cbAll = (CheckBox) findViewById(R.id.cb_all);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnCollection = (Button) findViewById(R.id.btn_collection);
        ivEmpty = (ImageView) findViewById(R.id.iv_empty);
        tvEmptyCartTobuy = (TextView) findViewById(R.id.tv_empty_cart_tobuy);

        shopCarAdapter = new ShopCarAdapter();
        recyclerview.setAdapter(shopCarAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        shopCarAdapter.notifyDataSetChanged();
    }


    @Override
    protected void initPresenter() {
        super.initPresenter();
        httpPresenter = new ShopCarPresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        List<ShopCarBean> shopCarBeans = CacheManager.getInstance().getShopCarBeans();
        Log.i("---", "initData: "+shopCarBeans.size());
        shopCarAdapter.updataData(shopCarBeans);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopcar;
    }

    @Override
    public void onProductNumChange(String result, int position, String newNum) {

    }

    @Override
    public void onProductSelected(String result, int position) {

    }

    @Override
    public void onAllSelected(String result) {

    }

    @Override
    public void onDeleteProducts(String result) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showsLoaing() {

    }

    @Override
    public void hidesLoading(boolean isSuccess) {

    }

    @Override
    public void showEmpty() {

    }
}
