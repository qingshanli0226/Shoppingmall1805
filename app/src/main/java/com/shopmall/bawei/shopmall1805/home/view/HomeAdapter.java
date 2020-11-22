package com.shopmall.bawei.shopmall1805.home.view;

import android.content.Context;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.common.UrlHelper;
import com.shopmall.bawei.framework.BaseRvAdapter;
import com.shopmall.bawei.net.mode.HomeBean;
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
    private final int HOT_TYPE = 3;
    private final int RECOMMEND_TYPE = 4;
    private final int SECKILL_TYPE = 5;

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
//            case 3: displayHot(itemData, baseViewHolder);break;
//            case 4: displayRecommend(itemData, baseViewHolder);break;
//            case 5: displaySeckill(itemData, baseViewHolder);break;
            default:displayBanner(itemData,baseViewHolder);
        }
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
                /**
                 *
                 *
                 *
                 *
                 */
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
                /**
                 *
                 *
                 *
                 *
                 *
                 *
                 *
                 */
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
            imageUrls.add(UrlHelper.BASE_RESOURCE_IMAGE_URL+item.getImage());
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
            case 3: return HOT_TYPE;
            case 4: return RECOMMEND_TYPE;
            case 5: return SECKILL_TYPE;
            default:
                return BANNER_TYPE;
        }
    }
}
