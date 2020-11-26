package com.bawei.shopmall.home.view;

import android.content.Context;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.common.view.NetConfig;
import com.bawei.framework.BaseRvAdapter;
import com.bawei.net.mode.HomeBean;
import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends BaseRvAdapter<Object> {
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
                return R.layout.banner_viewpager;
            case CHANNEL_TYPE:
                return R.layout.channel_item;
            case ACT_TYPE:
                return R.layout.home_view_act;
            case SECKILL_TYPE:
                return R.layout.seckill_item;
            case RECOMMEND_TYPE:
                return R.layout.recommend_item;
            case HOT_TYPE:
                return R.layout.hot_item;
            default:return R.layout.banner_viewpager;
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

    private void displayRecommend(Object itemData, BaseViewHolder baseViewHolder) {
        List<HomeBean.ResultBean.RecommendInfoBean> recommendInfoBeans = (List<HomeBean.ResultBean.RecommendInfoBean>)itemData;
        RecyclerView recommendRv = baseViewHolder.getView(R.id.gv_recommend);
        recommendRv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(),3,GridLayoutManager.VERTICAL,false));
        RecommendAdapter recommendAdapter = new RecommendAdapter();
        recommendRv.setAdapter(recommendAdapter);
        recommendAdapter.updateData(recommendInfoBeans);
        recommendAdapter.setIRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
    }

    private void displaySeckill(Object itemData, BaseViewHolder baseViewHolder) {
        HomeBean.ResultBean.SeckillInfoBean seckillInfoBean = (HomeBean.ResultBean.SeckillInfoBean)itemData;
        List<HomeBean.ResultBean.SeckillInfoBean.ListBean> list = seckillInfoBean.getList();
        RecyclerView secKillRv = baseViewHolder.getView(R.id.rv_seckill);
        secKillRv.setLayoutManager(new LinearLayoutManager(baseViewHolder.itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        SecKillRvAdapter secKillRvAdapter = new SecKillRvAdapter();
        secKillRv.setAdapter(secKillRvAdapter);
        secKillRvAdapter.updateData(list);
        secKillRvAdapter.setIRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
    }

    private void displayHot(Object itemData, BaseViewHolder baseViewHolder) {
        List<HomeBean.ResultBean.HotInfoBean> hotInfoBeans = (List<HomeBean.ResultBean.HotInfoBean>)itemData;
        RecyclerView hotRv = baseViewHolder.getView(R.id.gv_hot);
        hotRv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(),2,GridLayoutManager.VERTICAL,false));
        HotAdapter hotAdapter = new HotAdapter();
        hotRv.setAdapter(hotAdapter);
        hotAdapter.updateData(hotInfoBeans);
        hotAdapter.setIRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
    }

    private void displayAct(Object itemData, BaseViewHolder baseViewHolder) {
        List<HomeBean.ResultBean.ActInfoBean> actInfoBeans = (List<HomeBean.ResultBean.ActInfoBean>)itemData;
        RecyclerView actRv = baseViewHolder.getView(R.id.actRv);
        actRv.setLayoutManager(new LinearLayoutManager(baseViewHolder.itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        ActAdapter actAdapter = new ActAdapter();
        actRv.setAdapter(actAdapter);
        actAdapter.updateData(actInfoBeans);
        actAdapter.setIRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }

    private void displayChannel(Object itemData, BaseViewHolder baseViewHolder) {
        List<HomeBean.ResultBean.ChannelInfoBean> channelInfoBeans = (List<HomeBean.ResultBean.ChannelInfoBean>) itemData;
        RecyclerView channelRv = baseViewHolder.getView(R.id.channelRv);
        channelRv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(),5));
        ChannelAdapter channelAdapter = new ChannelAdapter();
        channelRv.setAdapter(channelAdapter);
        channelAdapter.updateData(channelInfoBeans);
        channelAdapter.setIRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });

    }

    private void displayBanner(Object itemData, BaseViewHolder baseViewHolder) {

        List<HomeBean.ResultBean.BannerInfoBean> bannerInfoBeans = (List<HomeBean.ResultBean.BannerInfoBean>)itemData;//强转成我们需要的类型

        Banner banner = baseViewHolder.getView(R.id.banner);
        banner.setBannerAnimation(Transformer.Accordion);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load((String)path).into((imageView));
            }
        });
        List<String> imageUrls = new ArrayList<>();
        for (HomeBean.ResultBean.BannerInfoBean item : bannerInfoBeans){
            imageUrls.add(NetConfig.BASE_RESOURCE_IMAGE_URL+item.getImage());
        }
        banner.setImages(imageUrls);
        banner.setDelayTime(5000);
        banner.start();

    }

    @Override
    protected int getViewType(int position) {
        switch (position){
            case 0:return BANNER_TYPE;
            case 1:return CHANNEL_TYPE;
            case 2: return ACT_TYPE;
            case 3: return SECKILL_TYPE;
            case 4: return RECOMMEND_TYPE;
            case 5: return HOT_TYPE;
            default:
                return BANNER_TYPE;
        }
    }
}
