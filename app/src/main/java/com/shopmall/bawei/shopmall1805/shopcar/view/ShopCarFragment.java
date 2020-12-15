package com.shopmall.bawei.shopmall1805.shopcar.view;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.common2.GetShopCarBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.goods.view.GoodsCount;
import com.shopmall.bawei.shopmall1805.shopcar.adapter.ShopCarAdapter;
import com.shopmall.bawei.shopmall1805.shopcar.contract.ShopCarContract;
import com.shopmall.bawei.shopmall1805.shopcar.presenter.ShopCarPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import mvp.CacheManager;
import mvp.view.BaseMVPFragment;

public class ShopCarFragment extends BaseMVPFragment<ShopCarPresenter, ShopCarContract.IGetShopCar> implements ShopCarContract.IGetShopCar {
    private TextView shopChange;
    private CheckBox shopSelect;
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

        Integer size = shopcarBeanList.size();

        EventBus.getDefault().post(size);
        Log.i("TAG", "initData: "+size);
        if(shopcarBeanList!=null){
            GoodsCount.shopcarcount = shopcarBeanList.size();
            shopCarAdapter=new ShopCarAdapter(R.layout.item_shopcar,shopcarBeanList);

            shopCarAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()){
                        case R.id.shop_Radio:
                           CheckBox checkBox=  view.findViewById(R.id.shop_Radio);
                           if (checkBox.isChecked()){
                               String productPrice = shopcarBeanList.get(position).getProductPrice();
                               Float i = Float.parseFloat(productPrice);
                               int i1 = Integer.parseInt(shopcarBeanList.get(position).getProductNum());
                                GoodsCount.shopcountadd=i*i1;
                               shopCount.setText("￥"+GoodsCount.shopcountadd);
                           }else {
                               String productPrice = shopcarBeanList.get(position).getProductPrice();
                               Float i = Float.parseFloat(productPrice);
                               int i1 = Integer.parseInt(shopcarBeanList.get(position).getProductNum());
                               GoodsCount.shopcountadd= GoodsCount.shopcountadd-i*i1;
                               shopCount.setText("￥"+GoodsCount.shopcountadd);
                           }
                            break;
                    }
                }
            });
            shopCarAdapter.notifyDataSetChanged();
        }
    }
    @Override
    protected void initView() {

        shopRv = (RecyclerView) findViewById(R.id.shop_rv);
        shopChange = (TextView) findViewById(R.id.shop_change);
        shopSelect = (CheckBox) findViewById(R.id.shop_select);
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