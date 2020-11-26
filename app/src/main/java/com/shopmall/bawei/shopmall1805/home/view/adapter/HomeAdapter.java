package com.shopmall.bawei.shopmall1805.home.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.common.UrlHelper;
import com.shopmall.bawei.framework.BaseRvAdapter;
import com.shopmall.bawei.net.mode.HomeBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.ShoppingActivity;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends BaseRvAdapter<Object> {
    private final int BANNER_TYPE = 0;
    private final int CHANNEL_TYPE = 1;
    private final int ACT_TYPE = 2;
    private final int HOT_TYPE = 5;
    private final int RECOMMEND_TYPE = 4;
    private final int SECKILL_TYPE = 3;

    @Override
    protected int getLayoutId(int viewType) {
        switch (viewType) {
            case BANNER_TYPE:
                return R.layout.banner_viewpager;
            case CHANNEL_TYPE:
                return R.layout.channel_item;
            case ACT_TYPE:
                return R.layout.home_view_act;
            case HOT_TYPE:
                return R.layout.hot_item;
            case RECOMMEND_TYPE:
                return R.layout.recommend_item;
            case SECKILL_TYPE:
                return R.layout.seckill_item;
            default:return R.layout.banner_viewpager;
        }
    }

    @Override
    protected void convert(Object itemData, BaseViewHolder baseViewHolder, int position) {
        switch (position) {
            case 0: displayBanner(itemData, baseViewHolder);break;
            case 1: displayChannel(itemData, baseViewHolder);break;
            case 2: displayAct(itemData, baseViewHolder);break;
            case 5: displayHot(itemData, baseViewHolder);break;
            case 4: displayRecommend(itemData, baseViewHolder);break;
            case 3: displaySeckill(itemData, baseViewHolder);break;
            default:displayBanner(itemData,baseViewHolder);
        }
    }

    private void displayRecommend(final Object itemData, final BaseViewHolder baseViewHolder) {
        final List<HomeBean.ResultBean.RecommendInfoBean> recommendInfoBeans = (List<HomeBean.ResultBean.RecommendInfoBean>)itemData;
        RecyclerView recommendRv = baseViewHolder.getView(R.id.gv_recommend);
        Log.i("TAG", "displayRecommend: "+recommendInfoBeans);
        recommendRv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(),3,GridLayoutManager.VERTICAL,false));
        RecommendAdapter recommendAdapter = new RecommendAdapter();
        recommendRv.setAdapter(recommendAdapter);
        recommendAdapter.updateData(recommendInfoBeans);
        recommendAdapter.setIRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("TAG", "onItemClick: "+recommendInfoBeans.get(position).getName());
                Intent intent = new Intent(baseViewHolder.itemView.getContext(), ShoppingActivity.class);
                intent.putExtra("bean",recommendInfoBeans.get(position));
                baseViewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    private void displaySeckill(Object itemData, BaseViewHolder baseViewHolder) {
        HomeBean.ResultBean.SeckillInfoBean seckillInfoBean = (HomeBean.ResultBean.SeckillInfoBean)itemData;
        final List<HomeBean.ResultBean.SeckillInfoBean.ListBean> list = seckillInfoBean.getList();
        RecyclerView secKillRv = baseViewHolder.getView(R.id.rv_seckill);
        secKillRv.setLayoutManager(new LinearLayoutManager(baseViewHolder.itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        SecKillRvAdapter secKillRvAdapter = new SecKillRvAdapter();
        secKillRv.setAdapter(secKillRvAdapter);
        secKillRvAdapter.updateData(list);
        secKillRvAdapter.setIRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("TAG", "onItemClick: "+list.get(position).getName());
            }
        });
    }

    private void displayHot(Object itemData, BaseViewHolder baseViewHolder) {
        final List<HomeBean.ResultBean.HotInfoBean> hotInfoBeans = (List<HomeBean.ResultBean.HotInfoBean>)itemData;
        RecyclerView hotRv = baseViewHolder.getView(R.id.gv_hot);
        hotRv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(),2,GridLayoutManager.VERTICAL,false));
        HotAdapter hotAdapter = new HotAdapter();
        hotRv.setAdapter(hotAdapter);
        hotAdapter.updateData(hotInfoBeans);
        hotAdapter.setIRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("TAG", "onItemClick: "+hotInfoBeans.get(position).getName());
            }
        });
    }

    private void displayAct(Object itemData, BaseViewHolder baseViewHolder) {
        final List<HomeBean.ResultBean.ActInfoBean> actInfoBeans = (List<HomeBean.ResultBean.ActInfoBean>)itemData;
        RecyclerView actRv = baseViewHolder.getView(R.id.actRv);
        actRv.setLayoutManager(new LinearLayoutManager(baseViewHolder.itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        ActAdapter actAdapter = new ActAdapter();
        actRv.setAdapter(actAdapter);
        actAdapter.updateData(actInfoBeans);
        actAdapter.setIRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("TAG", "onItemClick: "+actInfoBeans.get(position).getName());
            }
        });
    }

    private void displayChannel(Object itemData, BaseViewHolder baseViewHolder) {
        final List<HomeBean.ResultBean.ChannelInfoBean> channelInfoBeans = (List<HomeBean.ResultBean.ChannelInfoBean>) itemData;
        RecyclerView channelRv = baseViewHolder.getView(R.id.channelRv);
        channelRv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(),5));
        ChannelAdapter channelAdapter = new ChannelAdapter();
        channelRv.setAdapter(channelAdapter);
        channelAdapter.updateData(channelInfoBeans);
        channelAdapter.setIRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("TAG", "onItemClick: "+channelInfoBeans.get(position).getChannel_name());
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
        if (imageUrls!=null){
            for (HomeBean.ResultBean.BannerInfoBean item : bannerInfoBeans){
                imageUrls.add(UrlHelper.BASE_RESOURCE_IMAGE_URL+item.getImage());
            }
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
            case 5: return HOT_TYPE;
            case 4: return RECOMMEND_TYPE;
            case 3: return SECKILL_TYPE;
            default:
                return BANNER_TYPE;
        }
    }
}
