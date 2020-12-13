package com.shopmall.bawei.shopmall1805.shopcar.view;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common2.GetShopCarBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.goods.view.GoodsCount;
import com.shopmall.bawei.shopmall1805.shopcar.adapter.ShopCarAdapter;
import com.shopmall.bawei.shopmall1805.shopcar.contract.ShopCarContract;
import com.shopmall.bawei.shopmall1805.shopcar.presenter.ShopCarPresenter;

import java.util.List;

import mvp.CacheManager;
import mvp.view.BaseMVPFragment;

public class ShopCarFragment extends BaseMVPFragment<ShopCarPresenter, ShopCarContract.IGetShopCar> implements ShopCarContract.IGetShopCar {
    private TextView shopChange;
    private RadioButton shopSelect;
    private TextView shopCount;
    private Button shopPay;
    private RecyclerView shopRv;
    private ShopCarAdapter  shopCarAdapter;
    private List<GetShopCarBean> shopcarBeanList;
    @Override
    protected void initHttpData() {
    }

    @Override
    protected void initPresenter() {
        ihttpPresenter = new ShopCarPresenter();

    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }
    @Override
    protected void initData() {
         shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        Log.i("TAG", "initData: "+shopcarBeanList.size());
        if(shopcarBeanList!=null){
            GoodsCount.shopcarcount = shopcarBeanList.size();

            shopCarAdapter=new ShopCarAdapter(R.layout.item_shopcar,shopcarBeanList);
            shopCarAdapter.notifyDataSetChanged();
        }
    }
    @Override
    protected void initView() {

        shopRv = (RecyclerView) findViewById(R.id.shop_rv);
        shopChange = (TextView) findViewById(R.id.shop_change);
        shopSelect = (RadioButton) findViewById(R.id.shop_select);
        shopCount = (TextView) findViewById(R.id.shop_count);
        shopPay = (Button) findViewById(R.id.shop_pay);
        shopRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        shopRv.setAdapter(shopCarAdapter);
        if(shopcarBeanList!=null){

            shopCarAdapter.notifyDataSetChanged();
        }


    }
    @Override
    public void onGetShopCar(GetShopCarBean getShopCarBean) {

    }

    @Override
    public void showError(String code, String message) {

    }

    @Override
    public void showLoaing() {

    }

    @Override
    public void hideLoading() {

    }
}