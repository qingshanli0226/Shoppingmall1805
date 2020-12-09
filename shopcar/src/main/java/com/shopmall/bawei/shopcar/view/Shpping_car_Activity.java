package com.shopmall.bawei.shopcar.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseActivity;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.shopmall.bawei.shopcar.R;

@Route(path="/goodcar/Shpping_car_Activity")
public class Shpping_car_Activity extends BaseActivity<IPresenter, IView> implements View.OnClickListener {
    private ImageButton ibShopcartBack;
    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llEmptyShopcart;
    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    private boolean isEditMode = false; // 当为true时进入编辑模式，当为false则为正常模式
    @Override
    protected void initpreseter() {

    }

    @Override
    protected void initdate() {
        ARouter.getInstance().inject(this);
    }

    @Override
    protected void initview() {
        //初始化控件
        ibShopcartBack = findViewById(R.id.ib_shopcart_back);
        tvShopcartEdit = findViewById(R.id.tv_shopcart_edit);
        recyclerview = findViewById(R.id.recyclerview);
        llEmptyShopcart = findViewById(R.id.ll_empty_shopcart);
        ivEmpty = findViewById(R.id.iv_empty);
        tvEmptyCartTobuy = findViewById(R.id.tv_empty_cart_tobuy);
        llCheckAll = findViewById(R.id.ll_check_all);
        checkboxAll = findViewById(R.id.checkbox_all);
        tvShopcartTotal = findViewById(R.id.tv_shopcart_total);
        btnCheckOut = findViewById(R.id.btn_check_out);
        llDelete = findViewById(R.id.ll_delete);
        cbAll = findViewById(R.id.cb_all);
        btnDelete = findViewById(R.id.btn_delete);
        btnCollection = findViewById(R.id.btn_collection);
        tvShopcartEdit.setOnClickListener(this);
    }

    @Override
    protected int getlayoutid() {
        return R.layout.activity_shopca;
    }

    @Override
    public void onClick(View v) {
        if (v == tvShopcartEdit){
            if (!isEditMode){//当前页面非编辑页面，则进入编辑页面
                isEditMode = true;//进入编辑模式
                tvShopcartEdit.setText("完成");
                llCheckAll.setVisibility(View.GONE);
                llDelete.setVisibility(View.VISIBLE);
            }else {
                isEditMode = false;
                llDelete.setVisibility(View.GONE);
                tvShopcartEdit.setText("完成");
                llCheckAll.setVisibility(View.VISIBLE);
            }
        }
    }
}
