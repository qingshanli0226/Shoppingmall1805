package com.shopmall.bawei.shopmall1805.shoppingcar;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.base.BaseFragment;
import com.shopmall.bawei.shopmall1805.R;


public class ShoppingCarFragment extends BaseFragment {
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    private LinearLayout llEmptyShopcart;
    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;

    @Override
    protected void initDate() {

    }

    @Override
    protected void initLisenter() {

    }

    @Override
    protected void initView() {
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView)findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) findViewById(R.id.ll_delete);
        cbAll = (CheckBox) findViewById(R.id.cb_all);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnCollection = (Button) findViewById(R.id.btn_collection);
        llEmptyShopcart = (LinearLayout) findViewById(R.id.ll_empty_shopcart);
        ivEmpty = (ImageView) findViewById(R.id.iv_empty);
        tvEmptyCartTobuy = (TextView) findViewById(R.id.tv_empty_cart_tobuy);
        showEmptyCarPage();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_shopping_car;
    }

}