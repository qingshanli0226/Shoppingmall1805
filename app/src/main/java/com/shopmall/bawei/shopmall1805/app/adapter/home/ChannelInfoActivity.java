package com.shopmall.bawei.shopmall1805.app.adapter.home;


import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.app.adapter.classify.ChannelInfoAdapter;
import com.shopmall.bawei.shopmall1805.app.contract.ClassifyLeftContract;
import com.shopmall.bawei.shopmall1805.app.presenter.ClassifyLeftPresenterImpl;
import com.shopmall.bawei.shopmall1805.net.entity.ClothesBean;
import com.shopmall.bawei.shopmall1805.common.ConfigUrl;
import com.shopmall.bawei.shopmall1805.common.ShopmallConstant;
import com.shopmall.bawei.shopmall1805.framework.BaseMVPActivity;

import java.util.ArrayList;
import java.util.List;

public class ChannelInfoActivity extends BaseMVPActivity<ClassifyLeftPresenterImpl, ClassifyLeftContract.FenleiView> implements ClassifyLeftContract.FenleiView {
    private String url = ConfigUrl.SKIRT_URL;
    private  String name;
    private RecyclerView rvChannelInfo;
    private ChannelInfoAdapter channelInfoAdapter;
    private List<ClothesBean.ResultBean.HotProductListBean> listBeans=new ArrayList<>();


    @Override
    protected void initData() {

    }
    @Override
    protected void initView() {


        rvChannelInfo = findViewById(R.id.rv_ChannelInfo);
        rvChannelInfo.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        channelInfoAdapter = new ChannelInfoAdapter(R.layout.item_detail_channeinfo,listBeans);
        rvChannelInfo.setAdapter(channelInfoAdapter);

        channelInfoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ChannelInfoActivity.this, DetailsActivity.class);
                String figure = listBeans.get(position).getFigure();
                String name = listBeans.get(position).getName();
                String cover_price = listBeans.get(position).getCover_price();
                intent.putExtra("figure",figure);
                intent.putExtra("name",name);
                intent.putExtra("cover_price",cover_price);
                startActivity(intent);
            }
        });

    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_channel_info;
    }
    @Override
    protected void initPresenter() {
       httpPresenter = new ClassifyLeftPresenterImpl();
        name = getIntent().getStringExtra("name");
        changerData(name);
    }
    private void changerData(String name) {
        Log.i("TAG", "changerData: "+name);
        switch (name){
            case ShopmallConstant.FU_SHI:
                url = ConfigUrl.SKIRT_URL;
                break;
            case ShopmallConstant.YOU_XI:
                url = ConfigUrl.JACKET_URL;
                break;
            case ShopmallConstant.DONG_MAN:
                url = ConfigUrl.PANTS_URL;
                break;
            case ShopmallConstant.ZHAUNG_BAN:
                url = ConfigUrl.OVERCOAT_URL;
                break;
            case ShopmallConstant.GU_FENG:
                url = ConfigUrl.ACCESSORY_URL;
                break;
            case ShopmallConstant.MAN_ZHAN:
                url = ConfigUrl.BAG_URL;
                break;
            case ShopmallConstant.WEN_JU:
                url = ConfigUrl.DRESS_UP_URL;
                break;
            case ShopmallConstant.LING_SHI:
                url = ConfigUrl.HOME_PRODUCTS_URL;
                break;
            case ShopmallConstant.SOU_SHI:
                url = ConfigUrl.STATIONERY_URL;
                break;
            case ShopmallConstant.GENG_DUO:
                url = ConfigUrl.DIGIT_URL;
                break;
        }
        httpPresenter.getFenLeiView(url);
    }
    @Override
    public void showLoaing() {

    }
    @Override
    public void hideLoading() {

    }
    @Override
    public void showEmpty() {

    }
    @Override
    public void onFenleiData(List<ClothesBean.ResultBean> resultBeanList) {
        Log.i("TAG", "onFenleiData: "+resultBeanList);
        listBeans.clear();
        List<ClothesBean.ResultBean.HotProductListBean> hot_product_list = resultBeanList.get(0).getHot_product_list();
        listBeans.addAll(hot_product_list);
        channelInfoAdapter.notifyDataSetChanged();
    }
}