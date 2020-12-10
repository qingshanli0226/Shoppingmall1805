package com.shopmall.bawei.shopmall1805.app.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.app.adapter.BaseRvAdapter;
import com.shopmall.bawei.shopmall1805.common.ConfigUrl;
import com.shopmall.bawei.shopmall1805.net.entity.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class HomeAdapter extends BaseRvAdapter<Object> {
    private Context context;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected int getLayoutId(int viewType) {
        switch (viewType){
            case 0:
                return R.layout.layout_home_banner;
            case 1:
                return R.layout.layout_home_channelinfo;
            case 2:
                return R.layout.layout_home_actinfo;
            case 3:
                return R.layout.layout_home_seckillinfo;
            case 4:
                return R.layout.layout_home_recomend;
            case 5:
                return R.layout.layout_home_hotinfo;
        }
        return R.layout.layout_home_banner;
    }
    @Override
    public void convert(BaseViewHolder holder, int viewType, Object o) {
        switch (viewType){
            case 0:
                List<HomeBean.ResultBean.BannerInfoBean> bannerInfoBeans = (List<HomeBean.ResultBean.BannerInfoBean>) o;
                Banner banner = holder.getView(R.id.item_banner);
                banner.setImages(bannerInfoBeans);
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        HomeBean.ResultBean.BannerInfoBean infoBean = (HomeBean.ResultBean.BannerInfoBean) path;
                        String image = infoBean.getImage();
                        Glide.with(context).load(ConfigUrl.BASE_IMAGE+image).into(imageView);
                    }
                });
                banner.start();
                break;
            case 1:
                final List<HomeBean.ResultBean.ChannelInfoBean> o1 = (List<HomeBean.ResultBean.ChannelInfoBean>) o;
                RecyclerView view = holder.getView(R.id.rv_ChannelInfo);
                view.setLayoutManager(new GridLayoutManager(context,5));
                HomeChannelInfoAdapter wanggeAdapter=new HomeChannelInfoAdapter(R.layout.layout_home_channelinfo_item, o1);
                wanggeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(context, ChannelInfoActivity.class);
                        intent.putExtra("name",o1.get(position).getChannel_name());
                        context.startActivity(intent);
                    }
                });
                view.setAdapter(wanggeAdapter);
                break;
            case 2:
                List<HomeBean.ResultBean.ActInfoBean> o2 =  (List<HomeBean.ResultBean.ActInfoBean>) o;
                Banner view1 = holder.getView(R.id.actiinfo_banner);
                view1.setImages(o2);
                view1.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        HomeBean.ResultBean.ActInfoBean actInfoBean = (HomeBean.ResultBean.ActInfoBean) path;
                        String icon_url = actInfoBean.getIcon_url();
                        Glide.with(context).load(ConfigUrl.BASE_IMAGE+icon_url).into(imageView);
                    }
                });
                view1.isAutoPlay(false);
                view1.start();
                break;
            case 3:
                List<HomeBean.ResultBean.SeckillInfoBean.ListBean> o3 = (List<HomeBean.ResultBean.SeckillInfoBean.ListBean>) o;
                RecyclerView view2 = holder.getView(R.id.seckillinfo_rv);
                view2.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
                HomeSeckillInfoAdapter seckillInfoAdapter=new HomeSeckillInfoAdapter(R.layout.layout_home_seckillinfo_item,o3);
                view2.setAdapter(seckillInfoAdapter);
                break;
            case 4:
                List<HomeBean.ResultBean.RecommendInfoBean> o4 =  (List<HomeBean.ResultBean.RecommendInfoBean>) o;
                RecyclerView view3 = holder.getView(R.id.recomend_rv);
                view3.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                HomeRecomendAdapter recomendAdapter = new HomeRecomendAdapter(R.layout.layout_home_recomend_item, o4);
                view3.setAdapter(recomendAdapter);
                break;
            case 5:
                List<HomeBean.ResultBean.HotInfoBean> o5 =  (List<HomeBean.ResultBean.HotInfoBean>) o;
                RecyclerView view4 = holder.getView(R.id.hotinfo_rv);
                view4.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                HomeHotinfoAdapter hotinfoAdapter=new HomeHotinfoAdapter(R.layout.layout_home_hotinfo_item,o5);
                hotinfoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                    }
                });
                view4.setAdapter(hotinfoAdapter);
                break;
        }
    }
    @Override
    public int getViewType(int position) {
        return position;
    }
}
