package com.shopmall.bawei.shopcar.view;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.shopcar.R;

public class ShopCarFragment extends BaseFragment<BasePresenter, IView> {

    private TextView tvShopcartEdit;
    private CheckBox checkboxAll;
    private Button btnCheckOut;
    private Button btnDelete;
    private TextView tvShopcartTotal;
    private LinearLayout llDelete;
    private CheckBox cbAll;


    @Override
    protected int layoutId() {
        return R.layout.fragment_shoppingcart;
    }

    @Override
    protected void initView() {
        tvShopcartEdit = (TextView) findViewById(R.id.tv_shopcart_edit);
        checkboxAll = (CheckBox) findViewById(R.id.checkbox_all);
        btnCheckOut = (Button) findViewById(R.id.btn_check_out);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        tvShopcartTotal = (TextView) findViewById(R.id.tv_shopcart_total);
        llDelete = (LinearLayout) findViewById(R.id.ll_delete);
        cbAll = (CheckBox) findViewById(R.id.cb_all);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
}
