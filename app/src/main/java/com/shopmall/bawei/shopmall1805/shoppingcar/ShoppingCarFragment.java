package com.shopmall.bawei.shopmall1805.shoppingcar;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.base.BaseFragment;
import com.shopmall.bawei.shopmall1805.R;


public class ShoppingCarFragment extends BaseFragment {
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
    protected void initView(View inflate) {


        tvShopcartEdit = (TextView) inflate.findViewById(R.id.tv_shopcart_edit);
        recyclerview = (RecyclerView) inflate.findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) inflate.findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) inflate.findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) inflate.findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) inflate.findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) inflate.findViewById(R.id.ll_delete);
        cbAll = (CheckBox) inflate.findViewById(R.id.cb_all);
        btnDelete = (Button) inflate.findViewById(R.id.btn_delete);
        btnCollection = (Button) inflate.findViewById(R.id.btn_collection);
        llEmptyShopcart = (LinearLayout) inflate.findViewById(R.id.ll_empty_shopcart);
        ivEmpty = (ImageView) inflate.findViewById(R.id.iv_empty);
        tvEmptyCartTobuy = (TextView) inflate.findViewById(R.id.tv_empty_cart_tobuy);
        showEmptyCarPage();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_shopping_car;
    }

}