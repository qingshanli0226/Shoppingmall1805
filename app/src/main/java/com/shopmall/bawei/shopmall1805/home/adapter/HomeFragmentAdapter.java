package com.shopmall.bawei.shopmall1805.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.net.Contants;
import com.bw.net.bean.HomeFragmentBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.activity.DetailsActivity;
import com.shopmall.bawei.shopmall1805.base.BaseAdapter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class HomeFragmentAdapter extends BaseAdapter<Object> {



    @Override
    protected int getLayoutId(int viewType) {
        switch (viewType) {
            case 0:
                return R.layout.item_banner;
            case 1:
                return R.layout.item_channel;
            case 2:
                return R.layout.item_act;
            case 3:
                return R.layout.item_recommend;
            case 4:
                return R.layout.item_seckill;
            case 5:
            return R.layout.item_hot;
        }
        return R.layout.item_banner;
    }

    @Override
    protected void convert(BaseViewHoder baseViewHoder, int viewType, Object itemData) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(baseViewHoder.itemView.getContext());
        switch (viewType){
            case 0:
                List<HomeFragmentBean.ResultBean.BannerInfoBean> list = (List<HomeFragmentBean.ResultBean.BannerInfoBean>) itemData;
                List<String> images = new ArrayList<>();
                Banner banner = baseViewHoder.itemView.findViewById(R.id.banner);
                for (int i = 0; i < list.size(); i++) {
                    images.add(list.get(i).getImage());
                }

                banner.setImages(images);
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(Contants.BASE_URl_IMAGE+path).into(imageView);
                    }
                });

                banner.start();
                break;
            case 1:
                RecyclerView channelRv = baseViewHoder.itemView.findViewById(R.id.rv_channel);
                List<HomeFragmentBean.ResultBean.ChannelInfoBean> channel_Info = (List<HomeFragmentBean.ResultBean.ChannelInfoBean>) itemData;
                MyChannelAdapter myChannelAdapter = new MyChannelAdapter(R.layout.item_channel_data,channel_Info);
                channelRv.setAdapter(myChannelAdapter);
                channelRv.setLayoutManager(new GridLayoutManager(baseViewHoder.itemView.getContext(),5));

                break;
            case 2:
                RecyclerView actRv = baseViewHoder.itemView.findViewById(R.id.rv_act);
                MyActAdapter myActAdapter = new MyActAdapter();
                List<HomeFragmentBean.ResultBean.ActInfoBean> act_Info = (List<HomeFragmentBean.ResultBean.ActInfoBean>) itemData;
                myActAdapter.updataData(act_Info);
                actRv.setAdapter(myActAdapter);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                actRv.setLayoutManager(linearLayoutManager);

//                myActAdapter.setRecyclerViewListener(position -> {
//                    HomeFragmentBean.ResultBean.ActInfoBean listBean = act_Info.get(position);
//                    Intent intent = new Intent();
//                    intent.setAction("act_Info");
//                    intent.putExtra("act_Info",  listBean);
//                    intent.setClass(baseViewHoder.itemView.getContext(), DetailsActivity.class);
//                    baseViewHoder.itemView.getContext().startActivity(intent);
//                });

                break;
            case 3:
                RecyclerView recommendRv = baseViewHoder.itemView.findViewById(R.id.rv_recommend);
                MyRecommendAdapter myRecommendAdapter = new MyRecommendAdapter();
                List<HomeFragmentBean.ResultBean.RecommendInfoBean> recommend_Info = (List<HomeFragmentBean.ResultBean.RecommendInfoBean>) itemData;
                myRecommendAdapter.updataData(recommend_Info);
                recommendRv.setAdapter(myRecommendAdapter);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recommendRv.setLayoutManager(linearLayoutManager);

                myRecommendAdapter.setRecyclerViewListener(position -> {
                    HomeFragmentBean.ResultBean.RecommendInfoBean listBean = recommend_Info.get(position);
                    Intent intent = new Intent();
                    intent.setAction("recommend_Info");
                    intent.putExtra("recommend_Info",  listBean);
                    intent.setClass(baseViewHoder.itemView.getContext(), DetailsActivity.class);
                    baseViewHoder.itemView.getContext().startActivity(intent);
                });

                break;
            case 4:
                RecyclerView seckillRv = baseViewHoder.itemView.findViewById(R.id.rv_seckill);
                MySeckillAdapter mySeckillAdapter = new MySeckillAdapter();
                List<HomeFragmentBean.ResultBean.SeckillInfoBean.ListBean> listBeans = (List<HomeFragmentBean.ResultBean.SeckillInfoBean.ListBean>) itemData;
                mySeckillAdapter.updataData(listBeans);
                seckillRv.setAdapter(mySeckillAdapter);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(baseViewHoder.itemView.getContext(), 2);
                gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

                seckillRv.setLayoutManager(gridLayoutManager);

                mySeckillAdapter.setRecyclerViewListener(position -> {
                    HomeFragmentBean.ResultBean.SeckillInfoBean.ListBean listBean = listBeans.get(position);
                    Intent intent = new Intent();
                    intent.setAction("listBeans");
                    intent.putExtra("listBeans",  listBean);
                    intent.setClass(baseViewHoder.itemView.getContext(), DetailsActivity.class);
                    baseViewHoder.itemView.getContext().startActivity(intent);
                });
                break;
            case 5:
                RecyclerView hotRv = baseViewHoder.itemView.findViewById(R.id.rv_hot);
                MyHotAdapter myHotAdapter = new MyHotAdapter();
                List<HomeFragmentBean.ResultBean.HotInfoBean> hot_Info = (List<HomeFragmentBean.ResultBean.HotInfoBean>) itemData;
                myHotAdapter.updataData(hot_Info);
                hotRv.setAdapter(myHotAdapter);
                hotRv.setLayoutManager(new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL));

                myHotAdapter.setRecyclerViewListener(position -> {
                    HomeFragmentBean.ResultBean.HotInfoBean hotInfoBean = hot_Info.get(position);
                    Intent intent = new Intent();
                    intent.putExtra("hotInfo", hotInfoBean);
                    intent.setAction("hotInfo");
                    intent.setClass(baseViewHoder.itemView.getContext(), DetailsActivity.class);
                    baseViewHoder.itemView.getContext().startActivity(intent);
                });
                break;
        }
    }

    @Override
    public int getViewType(int position) {
        return position;
    }
}
