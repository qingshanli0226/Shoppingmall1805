package com.shopmall.bawei.shopcar.view;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.framework.BaseFragment;
import com.example.framework.CacheManager;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.example.net.bean.ShopcarBean;
import com.shopmall.bawei.shopcar.R;
import com.shopmall.bawei.shopcar.adpter.ShopCarAdpter;

import java.util.List;


public class ShopCarFragment extends BaseFragment<IPresenter, IView> implements View.OnClickListener,CacheManager.IShopcarDataCharListerter {

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
    private boolean isEditMode = false; // 当为true时进入编辑模式，当为false则为正常模式
    private ShopCarAdpter shopCarAdpter;
    @Override
    protected void initPreseter() {

    }

    @Override
    protected void initView(View inflate) {
        //初始化控件
        ibShopcartBack = inflate.findViewById(R.id.ib_shopcart_back);
        tvShopcartEdit = inflate.findViewById(R.id.tv_shopcart_edit);
        recyclerview = inflate.findViewById(R.id.recyclerview);
        shopCarAdpter = new ShopCarAdpter();
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setAdapter(shopCarAdpter);
        llCheckAll = inflate.findViewById(R.id.ll_check_all);
        checkboxAll = inflate.findViewById(R.id.checkbox_all);
        tvShopcartTotal = inflate.findViewById(R.id.tv_shopcart_total);
        btnCheckOut = inflate.findViewById(R.id.btn_check_out);
        llDelete = inflate.findViewById(R.id.ll_delete);
        cbAll = inflate.findViewById(R.id.cb_all);
        btnDelete = inflate.findViewById(R.id.btn_delete);
        btnCollection = inflate.findViewById(R.id.btn_collection);
        //去监听数据的改变，然后刷新UI
        CacheManager.getInstance().setshopcarChangedListenter(this);
    }

    @Override
    protected void initdate() {
        tvShopcartEdit.setOnClickListener(this);
        List<ShopcarBean> shopcarList = CacheManager.getInstance().getShopcarList();
        shopCarAdpter.updataData(shopcarList);
    }

    @Override
    protected int getlayoutid() {
        return R.layout.fragment_shopping_car;
    }

    @Override
    public void onClick(View v) {
        if (v == tvShopcartEdit){
            if (! isEditMode){//当前页面非编辑页面，则进入编辑页面
                isEditMode = true;//进入编辑模式
                llCheckAll.setVisibility(View.GONE);
                tvShopcartEdit.setText("完成");
                llDelete.setVisibility(View.VISIBLE);
            }else {
                isEditMode = false;
                tvShopcartEdit.setText("编辑");
                llDelete.setVisibility(View.GONE);
                llCheckAll.setVisibility(View.VISIBLE);
            }
        }
    }
    //拿到要刷新的购物车新数据
    @Override
    public void ondataChanged(List<ShopcarBean> shopcarBeanList) {
        shopCarAdpter.updataData(shopcarBeanList);
    }
    //刷新购物车上的商品数量
    @Override
    public void onOneChanged(int position, ShopcarBean shopcarBean) {
        shopCarAdpter.notifyItemChanged(position);
    }

    @Override
    public void onManeyvhanged(String moneyValue) {

    }

    @Override
    public void onAllselected(boolean isAllSelect) {

    }
}
