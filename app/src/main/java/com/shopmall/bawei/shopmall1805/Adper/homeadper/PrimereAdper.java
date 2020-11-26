package com.shopmall.bawei.shopmall1805.Adper.homeadper;

import android.content.Context;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import framework.BaseRvAdper;
import mode.HomeBean;
import view.Constants;

public
class PrimereAdper extends BaseRvAdper<Object> {
    private final  int banner_Type = 0;
    private final  int channel_Type = 1;
    private final  int act_Type = 2;
    private final  int secktll_Type = 3;
    private final  int recommend_Type = 4;
    private final  int hot_Type = 5;
    @Override
    protected int getlayourId(int i) {
        switch (i){
            case banner_Type: return R.layout.home_view_banner;
            case channel_Type: return R.layout.home_view_channel;
            case act_Type: return R.layout.home_view_act;
            case secktll_Type: return R.layout.home_view_seckill;
            case recommend_Type: return R.layout.home_view_recommend;
            case hot_Type: return R.layout.home_view_hot;
        }
        return R.layout.home_view_banner;
    }

    @Override
    protected void convert(Object itemData, BaseviewHoder holder, int position) {
        switch (position) {
            case 0: displayBanner(itemData, holder);break;
            case 1: displayChannel(itemData, holder);break;
            case 2: displayAct(itemData, holder);break;
            case 3: displaySeckill(itemData, holder);break;
            case 4: displayRecommend(itemData, holder);break;
            case 5: displayHot(itemData, holder);break;
            default:displayBanner(itemData,holder);
        }
    }

    private void displayHot(Object itemData, BaseviewHoder holder) {
        final List<HomeBean.ResultBean.HotInfoBean> hotInfoBeans = (List<HomeBean.ResultBean.HotInfoBean>)itemData;//强转成我们需要的类型
        RecyclerView hotRv = holder.getView(R.id.gv_hot);
        hotRv.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(),2));
        final HotAdapter hotAdapter = new HotAdapter();
        hotRv.setAdapter(hotAdapter);
        hotAdapter.updataData(hotInfoBeans);

    }

    private void displayRecommend(Object itemData, BaseviewHoder holder) {
        final List<HomeBean.ResultBean.RecommendInfoBean> recommendInfoBeans = (List<HomeBean.ResultBean.RecommendInfoBean>)itemData;
        RecyclerView commendRv = holder.getView(R.id.rv_recommen);
        commendRv.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(),3));
        final RecommendAdapter recommendAdapter = new RecommendAdapter();
        commendRv.setAdapter(recommendAdapter);
        recommendAdapter.updataData(recommendInfoBeans);
    }

    private void displaySeckill(Object itemData, BaseviewHoder holder) {
        final List<HomeBean.ResultBean.SeckillInfoBean.ListBean> seckillInfoBeans = (List<HomeBean.ResultBean.SeckillInfoBean.ListBean>)itemData;
        RecyclerView seckRv = holder.getView(R.id.rv_seck);
        seckRv.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        final SeckAdapter seckAdapter = new SeckAdapter();
        seckRv.setAdapter(seckAdapter);
        seckAdapter.updataData(seckillInfoBeans);
    }

    private void displayAct(Object itemData, BaseviewHoder holder) {
        final List<HomeBean.ResultBean.ActInfoBean> actInfoBeans = (List<HomeBean.ResultBean.ActInfoBean>)itemData;//强转成我们需要的类型
        RecyclerView actRv = holder.getView(R.id.actRv);
        actRv.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(),LinearLayoutManager.HORIZONTAL, false));

        ActAdapter actAdapter = new ActAdapter();
        actRv.setAdapter(actAdapter);
        actAdapter.updataData(actInfoBeans);


    }

    private void displayChannel(Object itemData, BaseviewHoder holder) {
        Log.d("LQS", "displayChannel...");
        List<HomeBean.ResultBean.ChannelInfoBean> channelInfoBeans = (List<HomeBean.ResultBean.ChannelInfoBean>)itemData;//强转成我们需要的类型
        RecyclerView channelRv = holder.getView(R.id.channelRv);
        channelRv.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(),5));

        ChannelAdapter channelAdper = new ChannelAdapter();
        channelRv.setAdapter(channelAdper);
        channelAdper.updataData(channelInfoBeans);

    }

    private void displayBanner(Object itemData, BaseviewHoder holder) {
        final List<HomeBean.ResultBean.BannerInfoBean> bannerInfoBeans = (List<HomeBean.ResultBean.BannerInfoBean>) itemData;
        final Banner banner = holder.getView(R.id.bannerContainer);

        List<String> imags = new ArrayList<>();
        for (int i = 0; i<bannerInfoBeans.size();i++){
            imags.add(Constants.BASE_URl_IMAGE+bannerInfoBeans.get(i).getImage());
        }
        banner.setImages(imags);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load((String)path).into(imageView);
            }
        });

        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
    }

    @Override
    protected int getViewType(int position) {
        switch (position){
            case 0:return banner_Type;
            case 1:return channel_Type;
            case 2:return act_Type;
            case 3:return secktll_Type;
            case 4:return recommend_Type;
            case 5:return hot_Type;
        }
        return banner_Type;
    }

}
