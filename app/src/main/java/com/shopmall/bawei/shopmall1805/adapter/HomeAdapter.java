package com.shopmall.bawei.shopmall1805.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.GoodsInfoActivity;
import com.shopmall.common.Constants;
import com.shopmall.framework.adapter.BaseRVAdapter;
import com.shopmall.net.bean.DetailsData;
import com.shopmall.net.bean.HomeData;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends BaseRVAdapter<Object> {
    private final int BANNER_TYPE = 0;
    private final int CHANNEL_TYPE = 1;
    private final int ACT_TYPE = 2;
    private final int SECKILL_TYPE = 3;
    private final int RECOMMEND_TYPE = 4;
    private final int HOT_TYPE = 5;

    @Override
    protected int getLayoutId(int viewType) {
        switch (viewType) {
            case BANNER_TYPE:
                return R.layout.home_view_banner;
            case CHANNEL_TYPE:
                return R.layout.home_view_channel;
            case ACT_TYPE:
                return R.layout.home_view_act;
            case SECKILL_TYPE:
                return R.layout.home_view_seckill;
            case RECOMMEND_TYPE:
                return R.layout.home_view_recommend;
            case HOT_TYPE:
                return R.layout.home_view_hot;
            default:return R.layout.home_view_banner;
        }
    }

    @Override
    protected void convert(Object itemData, BaseViewHolder baseViewHolder, int position) {
        switch (position) {
            case 0: displayBanner(itemData, baseViewHolder);break;
            case 1: displayChannel(itemData, baseViewHolder);break;
            case 2: displayAct(itemData, baseViewHolder);break;
            case 3: displaySeckill(itemData, baseViewHolder);break;
            case 4: displayRecommend(itemData, baseViewHolder);break;
            case 5: displayHot(itemData, baseViewHolder);break;
            default:displayBanner(itemData,baseViewHolder);
        }
    }

    private void displayBanner(Object itemData, BaseViewHolder baseViewHolder) {
        List<HomeData.ResultBean.BannerInfoBean> bannerInfoBeans = (List<HomeData.ResultBean.BannerInfoBean>)itemData;

        final Banner banner = baseViewHolder.getView(R.id.bannerContainer);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load((String)path).into(imageView);
            }
        });
        ArrayList<String> imageUrls = new ArrayList<>();
        for (HomeData.ResultBean.BannerInfoBean item:bannerInfoBeans){
            imageUrls.add(Constants.BASE_URl_IMAGE+item.getImage());
        }
        banner.setImages(imageUrls);
        banner.setDelayTime(5000);
        banner.start();
    }

    private void displayChannel(Object itemData, BaseViewHolder baseViewHolder) {
        List<HomeData.ResultBean.ChannelInfoBean> channelInfoBeans = (List<HomeData.ResultBean.ChannelInfoBean>)itemData;

        RecyclerView channelRv = baseViewHolder.getView(R.id.rv_channel);
        channelRv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(),5));

        ChannelAdapter channelAdapter = new ChannelAdapter();
        channelRv.setAdapter(channelAdapter);
        channelAdapter.updataData(channelInfoBeans);
        channelAdapter.setiRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }

    private void displayAct(Object itemData, BaseViewHolder baseViewHolder) {
        List<HomeData.ResultBean.ActInfoBean> actInfoBeans = (List<HomeData.ResultBean.ActInfoBean>)itemData;
        RecyclerView actRv = baseViewHolder.getView(R.id.actRv);
        actRv.setLayoutManager(new LinearLayoutManager(baseViewHolder.itemView.getContext(),LinearLayoutManager.HORIZONTAL, false));

        ActAdapter actAdapter = new ActAdapter();
        actRv.setAdapter(actAdapter);
        actAdapter.updataData(actInfoBeans);

    }

    private void displaySeckill(Object itemData, BaseViewHolder baseViewHolder) {
        List<HomeData.ResultBean.SeckillInfoBean.ListBean> seckillInfoBeans = (List<HomeData.ResultBean.SeckillInfoBean.ListBean>)itemData;

        RecyclerView seckillRv  = baseViewHolder.getView(R.id.rv_seckill);
        seckillRv.setLayoutManager(new LinearLayoutManager(baseViewHolder.itemView.getContext(),LinearLayoutManager.HORIZONTAL, false));

        SeckillAdapter seckillAdapter = new SeckillAdapter();
        seckillRv.setAdapter(seckillAdapter);
        seckillAdapter.updataData(seckillInfoBeans);
    }

    private void displayRecommend(final Object itemData, final BaseViewHolder baseViewHolder) {
        List<HomeData.ResultBean.RecommendInfoBean> recommendInfoBeans = (List<HomeData.ResultBean.RecommendInfoBean>)itemData;
        RecyclerView recommendRv  = baseViewHolder.getView(R.id.rv_recommend);
        recommendRv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(),3));

        RecommendAdapter recommendAdapter = new RecommendAdapter();
        recommendRv.setAdapter(recommendAdapter);
        recommendAdapter.updataData(recommendInfoBeans);

        recommendAdapter.setiRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                HomeData.ResultBean.RecommendInfoBean recommendInfoBean = ((List<HomeData.ResultBean.RecommendInfoBean>) itemData).get(position);
                DetailsData detailsData = new DetailsData(recommendInfoBean.getProduct_id(), recommendInfoBean.getName(), recommendInfoBean.getCover_price(), Constants.BASE_URl_IMAGE + recommendInfoBean.getFigure());
                Intent intent = new Intent();
                intent.putExtra("details",detailsData);
                intent.setClass((Activity)(baseViewHolder.itemView.getContext()), GoodsInfoActivity.class);
                ((Activity)(baseViewHolder.itemView.getContext())).startActivity(intent);
            }
        });
    }

    private void displayHot(final Object itemData, final BaseViewHolder baseViewHolder) {
        List<HomeData.ResultBean.HotInfoBean> hotInfoBeans = (List<HomeData.ResultBean.HotInfoBean>)itemData;
        RecyclerView hotRv  = baseViewHolder.getView(R.id.rv_hot);
        hotRv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(),2));

        final HotAdapter hotAdapter = new HotAdapter();
        hotRv.setAdapter(hotAdapter);
        hotAdapter.updataData(hotInfoBeans);

        hotAdapter.setiRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                HomeData.ResultBean.HotInfoBean hotInfoBean = ((List<HomeData.ResultBean.HotInfoBean>) itemData).get(position);
                DetailsData detailsData = new DetailsData(hotInfoBean.getProduct_id(), hotInfoBean.getName(), hotInfoBean.getCover_price(), Constants.BASE_URl_IMAGE + hotInfoBean.getFigure());
                Intent intent = new Intent();
                intent.putExtra("details",detailsData);
                intent.setClass((Activity)(baseViewHolder.itemView.getContext()), GoodsInfoActivity.class);
                ((Activity)(baseViewHolder.itemView.getContext())).startActivity(intent);
            }
        });
    }

    @Override
    protected int getViewType(int position) {
        switch (position) {
            case 0: return BANNER_TYPE;
            case 1: return CHANNEL_TYPE;
            case 2: return ACT_TYPE;
            case 3: return SECKILL_TYPE;
            case 4: return RECOMMEND_TYPE;
            case 5: return HOT_TYPE;
            default:
                return BANNER_TYPE;
        }
    }
}
