package com.shopmall.bawei.shopmall1805.adpter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.net.Confing;
import com.example.framework.BaseRVAdapter;
import com.example.net.bean.HomeBean;
import com.shopmall.bawei.shopmall1805.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class PrimereAdpter extends BaseRVAdapter<Object> {


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
    private void displayHot(Object itemData, final BaseViewHolder baseViewHolder) {
        List<HomeBean.HotInfoBean> hotInfoBeans = (List<HomeBean.HotInfoBean>)itemData;//强转成我们需要的类型
        RecyclerView hotRv = baseViewHolder.getView(R.id.gv_hot);
        hotRv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(),2));
        final HotAdapter hotAdapter = new HotAdapter();
        hotRv.setAdapter(hotAdapter);
        hotAdapter.updataData(hotInfoBeans);
//        hotAdapter.setiRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Intent intent = new Intent();
//                intent.putExtra("productId", hotAdapter.getItemData(position).getProduct_id());
//                intent.putExtra("productName", hotAdapter.getItemData(position).getName());
//                intent.putExtra("productPrice", hotAdapter.getItemData(position).getCover_price());
//                intent.putExtra("url", hotAdapter.getItemData(position).getFigure());
//                intent.setClass((Activity)(baseViewHolder.itemView.getContext()), ProductDetailActivity.class);
//                ((Activity)(baseViewHolder.itemView.getContext())).startActivity(intent);
//            }
//        });
    }
    private void displayRecommend(Object itemData, final BaseViewHolder baseViewHolder) {

        List<HomeBean.RecommendInfoBean> recommendInfoBeans = (List<HomeBean.RecommendInfoBean>)itemData;//强转成我们需要的类型
        RecyclerView commendRv = baseViewHolder.getView(R.id.rv_recommen);
        commendRv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(),3));
        final RecommendAdapter recommendAdapter = new RecommendAdapter();
        commendRv.setAdapter(recommendAdapter);
        recommendAdapter.updataData(recommendInfoBeans);
    }
    private void displaySeckill(Object itemData, final BaseViewHolder baseViewHolder) {

        List<HomeBean.SeckillInfoBean.ListBean> seckillInfoBeans = (List<HomeBean.SeckillInfoBean.ListBean>)itemData;//强转成我们需要的类型
        RecyclerView seckRv = baseViewHolder.getView(R.id.rv_seck);
        seckRv.setLayoutManager(new LinearLayoutManager(baseViewHolder.itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        final SeckAdapter seckAdapter = new SeckAdapter();
        seckRv.setAdapter(seckAdapter);
        seckAdapter.updataData(seckillInfoBeans);
    }


    private void displayAct(Object itemData, final BaseViewHolder baseViewHolder) {
        Log.d("LQS", "displayAct...");
        List<HomeBean.ActInfoBean> actInfoBeans = (List<HomeBean.ActInfoBean>)itemData;//强转成我们需要的类型
        RecyclerView actRv = baseViewHolder.getView(R.id.actRv);
        actRv.setLayoutManager(new LinearLayoutManager(baseViewHolder.itemView.getContext(),LinearLayoutManager.HORIZONTAL, false));

        ActAdapter actAdapter = new ActAdapter();
        actRv.setAdapter(actAdapter);
        actAdapter.updataData(actInfoBeans);
//        actAdapter.setiRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Intent intent = new Intent();
//                intent.setClass((Activity)(baseViewHolder.itemView.getContext()), LoginRegisterActivity.class);
//                ((Activity)(baseViewHolder.itemView.getContext())).startActivity(intent);
//            }
//        });
    }
    private void displayChannel(Object itemData, final BaseViewHolder baseViewHolder) {
        Log.d("LQS", "displayChannel...");
        List<HomeBean.ChannelInfoBean> channelInfoBeans = (List<HomeBean.ChannelInfoBean>)itemData;//强转成我们需要的类型
        RecyclerView channelRv = baseViewHolder.getView(R.id.channelRv);
        channelRv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(),5));

        ChannelAdapter channelAdapter = new ChannelAdapter();
        channelRv.setAdapter(channelAdapter);
        channelAdapter.updataData(channelInfoBeans);
//        channelAdapter.setiRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Intent intent = new Intent();
//                intent.setClass((Activity)(baseViewHolder.itemView.getContext()), LoginRegisterActivity.class);
//                ((Activity)(baseViewHolder.itemView.getContext())).startActivity(intent);
//            }
//        });
    }

    private void displayBanner(Object itemData, BaseViewHolder baseViewHolder) {
        List<HomeBean.BannerInfoBean> bannerInfoBeans = (List<HomeBean.BannerInfoBean>) itemData;//强转成我们需要的类型

        final Banner banner = baseViewHolder.getView(R.id.bannerContainer);

        List<String> imageUrls = new ArrayList<>();

        for (int i = 0; i <bannerInfoBeans.size() ; i++) {
            imageUrls.add(Confing.BASE_IMAGE+bannerInfoBeans.get(i).getImage());
        }

        banner.setImages(imageUrls);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load((String)path).into(imageView);
            }
        });
        banner.start();
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
