package com.shopmall.bawei.shopmall1805.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.deom.BaseFragment;
import com.bawei.deom.countroller.UserCountroller;
import com.bawei.deom.countroller.UserIMPL;
import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.apter.zhuyeapter.ChanneInfoApter;

import com.shopmall.bawei.shopmall1805.apter.zhuyeapter.HontAPter;
import com.shopmall.bawei.shopmall1805.apter.zhuyeapter.MyRecommend_InfoApter;
import com.shopmall.bawei.shopmall1805.apter.zhuyeapter.SeckillApter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import bean.BaseBean;
import bean.HomeBean;
import bean.TAGBean;
import bean.typebean.SkirtBean;


public class FirstFragment extends BaseFragment<UserIMPL, UserCountroller.UserView> implements UserCountroller.UserView {
    private Banner bann;
    ArrayList<String> image=new ArrayList<>();
    private RecyclerView recyle;

    ArrayList<HomeBean.ChannelInfoBean> channel_info=new ArrayList<HomeBean.ChannelInfoBean>();
    private Banner ban;
    ArrayList<String> bans=new ArrayList<>();
    ArrayList<HomeBean.RecommendInfoBean> recommendInfoBeans=new ArrayList<>();
    private RecyclerView recyleRecommend;
    private RecyclerView seckillRecyle;
     ArrayList<HomeBean.SeckillInfoBean.ListBean> seckillInfoBeanArrayList=new ArrayList<>();
      SeckillApter seckillApter;
     ArrayList<HomeBean.HotInfoBean> hotInfoBeanArrayList=new ArrayList<>();
    private RecyclerView hotInfoRecyle;
    HontAPter hontAPter;



    ChanneInfoApter channeInfoApter;
    MyRecommend_InfoApter recommend_infoApter;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recommendInfoBeans.clear();
        recommend_infoApter.notifyDataSetChanged();
        channel_info.clear();
        channeInfoApter.notifyDataSetChanged();
    }

    @Override
    protected void inPrine() {
        prine=new UserIMPL();
        image.add("http://vueshop.glbuys.com//uploadfiles//1484285334.jpg");
        image.add("http://vueshop.glbuys.com//uploadfiles//1484285302.jpg");
        image.add("http://vueshop.glbuys.com//uploadfiles//1524206455.jpg");

            bans.add("http://49.233.0.68:8080/atguigu/img/operation/img/1478169868/1478761370286.png");
            bans.add("http://49.233.0.68:8080/atguigu/img//operation/img/1478763176/1478762941492.png");

    }

    @Override
    protected void initData() {

        prine.channelInfosSHow();

        //轮播图
    bann.setImages(image);
    bann.setImageLoader(new ImageLoader() {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    });
    bann.start();
    //
        ban.setImages(bans);
        ban.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        ban.start();
        //适配器1
        channeInfoApter=new ChanneInfoApter(R.layout.layout, channel_info);
        recyle.setAdapter(channeInfoApter);
        recyle.setLayoutManager(new StaggeredGridLayoutManager(6,StaggeredGridLayoutManager.VERTICAL));
        //适配器2
        recommend_infoApter=new MyRecommend_InfoApter(R.layout.recommend,recommendInfoBeans);
        recyleRecommend.setAdapter(recommend_infoApter);
        recyleRecommend.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        //适配器3
        seckillApter=new SeckillApter(R.layout.seckill,seckillInfoBeanArrayList);
        seckillRecyle.setAdapter(seckillApter);
        seckillRecyle.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        //适配器4
        hontAPter=new HontAPter(R.layout.hotinfo,hotInfoBeanArrayList);
        hotInfoRecyle.setAdapter(hontAPter);
        hotInfoRecyle.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    protected void initView(View view) {
        hotInfoRecyle = (RecyclerView) view.findViewById(R.id.hot_info_recyle);
        bann = (Banner) view.findViewById(R.id.bann);
        recyle = (RecyclerView) view.findViewById(R.id.recyle);
        ban = (Banner) view.findViewById(R.id.ban);
        recyleRecommend = (RecyclerView) view.findViewById(R.id.recyle_recommend);
        seckillRecyle = (RecyclerView) view.findViewById(R.id.seckill_recyle);
    }

    @Override
    protected int getlayoutview() {
        return R.layout.firstfragment;
    }



    @Override
    public void loading() {

    }

    @Override
    public void hideloading() {

    }

    @Override
    public void ChannelInfoBean(BaseBean<HomeBean> listBaseBean) {
        Toast.makeText(getContext(), ""+listBaseBean.getResult().getBanner_info(), Toast.LENGTH_SHORT).show();
//        Glide.with(getContext()).load(listBaseBean.getResult().getBanner_info()+"").into(ban);
        Log.e("",""+listBaseBean.getResult().getChannel_info().get(0).getChannel_name());
        Log.e("cc",""+listBaseBean.getResult().getChannel_info().size());

        this.channel_info.addAll(listBaseBean.getResult().getChannel_info());
        recommendInfoBeans.addAll(listBaseBean.getResult().getRecommend_info());
        HomeBean.SeckillInfoBean seckill_info = listBaseBean.getResult().getSeckill_info();

        seckillInfoBeanArrayList.addAll(listBaseBean.getResult().getSeckill_info().getList());

        hotInfoBeanArrayList.addAll(listBaseBean.getResult().getHot_info());

        hontAPter.notifyDataSetChanged();
        seckillApter.notifyDataSetChanged();
        recommend_infoApter.notifyDataSetChanged();
       channeInfoApter.notifyDataSetChanged();
    }

    @Override
    public void TagBiew(List<TAGBean.ResultBean> resultBeanList) {

    }



    //    @Override
//    public void ChannelInfoBean(BaseBean<HomeBean.ChannelInfoBean> listBaseBean) {
//        Toast.makeText(getContext(), ""+listBaseBean.getResult().getChannel_name(), Toast.LENGTH_SHORT).show();
//        listBaseBean.getResult();
//        channel_info.addAll(listBaseBean);
////         channel_info.addAll((Collection<? extends HomeBean.ChannelInfoBean>) listBaseBean);
//        Log.e("cq",""+listBaseBean.getResult().getChannel_name());
//
//        myRecommendInfoApter.notifyDataSetChanged();
//    }
}
