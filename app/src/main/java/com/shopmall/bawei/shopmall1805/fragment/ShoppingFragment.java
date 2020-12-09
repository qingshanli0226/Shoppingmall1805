package com.shopmall.bawei.shopmall1805.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.deom.BaseFragment;
import com.bawei.deom.CacheManager;
import com.bawei.deom.IPrine;
import com.bawei.deom.IView;
import com.bawei.deom.countroller.SkirtCommuntroller;
import com.bawei.deom.countroller.SkirtImpl;
import com.bawei.deom.countroller.UserCountroller;
import com.bawei.deom.countroller.UserIMPL;
import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.DaoSession;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.ShangTitle;
import com.shopmall.bawei.shopmall1805.ShopmallApplication;
import com.shopmall.bawei.shopmall1805.DetailsActivity;
import com.shopmall.bawei.shopmall1805.apter.ShopcarAdapter;
import com.shopmall.bawei.shopmall1805.fragment2.FenFragment;

import java.util.List;

import bean.Shoppingcartproducts;
import bean.typebean.BugBean;
@Route(path = "/fragment/ShoppingFragment")
public class ShoppingFragment extends BaseFragment<IPrine, IView> {
//    private ImageView pic;
//    private TextView name;
//    private TextView price;
//    private Button jia;
//    private TextView num;
//    private Button jian;
//    private DaoSession daoSession;
//    int index=1;
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
    ShopcarAdapter shopcarAdapter;



    @Override
    protected void inPrine() {
        loadingPage.showSuccessView();
    }

    @Override
    protected void initData() {

//      List<Shoppingcartproducts.ResultBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanlist();
        List<Shoppingcartproducts.ResultBean> shopcarBeanlist = CacheManager.getInstance().getShopcarBeanlist();
        Log.e("chicun",shopcarBeanlist.size()+"");
        shopcarAdapter.updataData(shopcarBeanlist);

        recyclerview.setAdapter(shopcarAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    protected void initView(View view) {


        ibShopcartBack = (ImageButton) view.findViewById(R.id.ib_shopcart_back);
        tvShopcartEdit = (TextView) view.findViewById(R.id.tv_shopcart_edit);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);

        llCheckAll = (LinearLayout) view.findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) view.findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) view.findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) view.findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) view.findViewById(R.id.ll_delete);
        cbAll = (CheckBox) view.findViewById(R.id.cb_all);
        btnDelete = (Button) view.findViewById(R.id.btn_delete);
        btnCollection = (Button) view.findViewById(R.id.btn_collection);
        shopcarAdapter=new ShopcarAdapter();
    }

    @Override
    protected int getlayoutview() {
        return R.layout.shoppfragment;
    }


}
