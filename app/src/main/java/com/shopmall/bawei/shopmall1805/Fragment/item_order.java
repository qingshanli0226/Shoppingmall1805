package com.shopmall.bawei.shopmall1805.Fragment;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.Adper.ActAdpter;
import com.shopmall.bawei.shopmall1805.Adper.SeckAdper;
import com.shopmall.bawei.shopmall1805.Adper.channelAdper;
import com.shopmall.bawei.shopmall1805.Adper.reamAdpter;
import com.shopmall.bawei.shopmall1805.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import framework.BaseFragment;
import framework.Userc;
import framework.mvpc.jsonPresenter;
import mode.HomeBean;
import view.Constants;

public
class item_order extends BaseFragment {
    private Banner BannerFragMent;
    private RecyclerView WangRv;
    private Banner BannerFragMent2;
    private RecyclerView zhuRv;
    private RecyclerView SeckFragment;
    private RecyclerView receFragment;
    private reamAdpter reamAdpter;
    private SeckAdper SeckAdper;
    public List<HomeBean.ResultBean.BannerInfoBean> Bannerlist = new ArrayList<>();
    public List<HomeBean.ResultBean.ChannelInfoBean> channelInfoBeanslist =new ArrayList<>();
    public List<HomeBean.ResultBean.HotInfoBean> hotInfoBeanList = new ArrayList<>();
    public List<HomeBean.ResultBean.SeckillInfoBean.ListBean> seckillInfoBeans = new ArrayList<>();
    public List<HomeBean.ResultBean.RecommendInfoBean> recommendInfoBeans = new ArrayList<>();
    public List<String> stringList = new ArrayList<>();
    private ActAdpter actAdpter;
    private com.shopmall.bawei.shopmall1805.Adper.channelAdper channelAdper;
    @Override
    protected void createPresenter() {
        Presenter = new jsonPresenter(this);
    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void InitData(View inflate) {

        BannerFragMent = (Banner) inflate.findViewById(R.id.Banner_FragMent);
        WangRv = (RecyclerView) inflate.findViewById(R.id.Wang_rv);
        BannerFragMent2 = (Banner) inflate.findViewById(R.id.Banner_FragMent2);
        zhuRv = (RecyclerView) inflate.findViewById(R.id.zhu_rv);
        SeckFragment = (RecyclerView) inflate.findViewById(R.id.Seck_fragment);
        receFragment = (RecyclerView) inflate.findViewById(R.id.rece_fragment);

        WangRv.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL));
        zhuRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        SeckFragment.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        receFragment.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        Presenter.getHomeurl(new Userc() {
            @Override
            public void Susses(HomeBean e) {
                Log.i("====","成功");
                Bannerlist.addAll(e.getResult().getBanner_info()) ;
                channelInfoBeanslist.addAll(e.getResult().getChannel_info());
                hotInfoBeanList.addAll(e.getResult().getHot_info());
                seckillInfoBeans.addAll(e.getResult().getSeckill_info().getList());
                recommendInfoBeans.addAll(e.getResult().getRecommend_info());
                stringList.add(Bannerlist.get(0).getImage());
                stringList.add(Bannerlist.get(1).getImage());
                stringList.add(Bannerlist.get(2).getImage());
                BannerFragMent.setImages(stringList);
                BannerFragMent2.setImages(stringList);
                BannerFragMent2.setDelayTime(3000);

                BannerFragMent.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(Constants.BASE_URl_IMAGE+path).into(imageView);
                    }
                });
                BannerFragMent2.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(Constants.BASE_URl_IMAGE+path).into(imageView);
                    }
                });
                BannerFragMent.start();
                BannerFragMent2.start();

                actAdpter = new ActAdpter(R.layout.item_hot,hotInfoBeanList);
                zhuRv.setAdapter(actAdpter);
                actAdpter.notifyDataSetChanged();

                Log.i("====","这是channer的数据"+channelInfoBeanslist.toString());
                channelAdper = new channelAdper(R.layout.item_channel,channelInfoBeanslist);
                WangRv.setAdapter(channelAdper);
                channelAdper.notifyDataSetChanged();

                SeckAdper = new SeckAdper(R.layout.item_seck,seckillInfoBeans);
                Log.i("====","这是首页seck集合数据"+seckillInfoBeans.size());
                SeckFragment.setAdapter(SeckAdper);
                SeckAdper.notifyDataSetChanged();

                reamAdpter = new reamAdpter(R.layout.item_remn,recommendInfoBeans);
                Log.i("====","这是首页ream集合数据"+recommendInfoBeans.size());
                receFragment.setAdapter(reamAdpter);
                reamAdpter.notifyDataSetChanged();



            }



            @Override
            public void Error(String error) {
                Log.i("====","收到的错误是");
            }
        });
    }


    @Override
    protected int getlayoutId() {
        return R.layout.item_order;
    }


}
