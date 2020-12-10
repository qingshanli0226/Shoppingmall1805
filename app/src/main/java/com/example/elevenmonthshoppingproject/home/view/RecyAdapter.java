package com.example.elevenmonthshoppingproject.home.view;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.common.ShopMallContants;
import com.example.elevenmonthshoppingproject.R;
import com.example.framwork.BaseRVAdapter;
import com.example.net.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class RecyAdapter extends BaseRVAdapter<Object> {

    private final  int BANNER_TYPE=0;
    private final  int CHANNER=1;
    private final int ACT=2;
    private final  int HOT=3;
    private final int RECOMMOND=4;
    private final int SECKILL=5;
    @Override
    protected int getLayoutid(int viewtype) {
        switch (viewtype){
            case BANNER_TYPE:
                return R.layout.bannerview;
            case CHANNER:
                return R.layout.home_channel;
            case ACT:
                return R.layout.home_actview;
            case HOT:
                return R.layout.home_hotview;
            case RECOMMOND:
                return R.layout.recomonde_view;
            case SECKILL:
                return R.layout.seckill_view;

        }
        return R.layout.bannerview;
    }

    @Override
    protected int getViewtype(int postion) {
        switch (postion){
            case  0:  return   BANNER_TYPE;
            case 1: return  CHANNER;
            case 2: return  ACT;
            case 3: return HOT;
            case 4: return RECOMMOND;
            case 5: return SECKILL;
        }
        return BANNER_TYPE;
    }

    @Override
    protected void cover(BaseViewHoder holder, int viewtype, Object o) {
        switch (viewtype){
            case 0:
                displaybanner(o,holder);
                break;
            case 1:
                displaychannel(o,holder);
                break;
            case 2:
                displayact(o,holder);
                break;
            case 3:
                displayhot(o,holder);
                break;
            case 4:
                displayrecommonde(o,holder);
                break;
            case 5:
                displayseckill(o,holder);
                break;
        }
    }

    private void displayseckill(Object o, BaseViewHoder holder) {
        Log.i("---","664");
        List<HomeBean.SeckillInfoBean.ListBean> seckillInfoBeans= (List<HomeBean.SeckillInfoBean.ListBean>) o;
        Log.i("---","666"+seckillInfoBeans);
        RecyclerView recysckeill = holder.getView(R.id.recy_sckeill);
        recysckeill.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(),3));
        SeckillAdapter seckillAdapter = new SeckillAdapter();
        recysckeill.setAdapter(seckillAdapter);
        recysckeill.addItemDecoration(new DividerItemDecoration(holder.itemView.getContext(),DividerItemDecoration.VERTICAL));
        seckillAdapter.updatelist(seckillInfoBeans);
        seckillAdapter.setBaseRVAdapterlinterner(new IBaseRecyclerLinsterner() {
            @Override
            public void onItemclick(int position) {

            }
        });

    }

    private void displayrecommonde(Object o, BaseViewHoder holder) {
        List<HomeBean.RecommendInfoBean> recommendInfoBeans= (List<HomeBean.RecommendInfoBean>) o;
        RecyclerView recyrecommond = holder.getView(R.id.recy_recommond);
        recyrecommond.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        RecommondAdapter recommondAdapter = new RecommondAdapter();
        recyrecommond.setAdapter(recommondAdapter);
        recyrecommond.addItemDecoration(new DividerItemDecoration(holder.itemView.getContext(),DividerItemDecoration.HORIZONTAL));
        recommondAdapter.updatelist(recommendInfoBeans);
        recommondAdapter.setBaseRVAdapterlinterner(new IBaseRecyclerLinsterner() {
            @Override
            public void onItemclick(int position) {

            }
        });


    }

    private void displayhot(Object o, BaseViewHoder holder) {
        List<HomeBean.HotInfoBean> hotInfoBeans= (List<HomeBean.HotInfoBean>) o;
        RecyclerView recyhot = holder.getView(R.id.recy_hot);
        recyhot.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(),4));
        HotAdapter hotAdapter = new HotAdapter();
        recyhot.setAdapter(hotAdapter);
        recyhot.addItemDecoration(new DividerItemDecoration(holder.itemView.getContext(),DividerItemDecoration.VERTICAL));
        hotAdapter.updatelist(hotInfoBeans);
        hotAdapter.setBaseRVAdapterlinterner(new IBaseRecyclerLinsterner() {
            @Override
            public void onItemclick(int position) {

            }
        });

    }

    private void displayact(Object o, BaseViewHoder holder) {
            List<HomeBean.ActInfoBean> actInfoBeans= (List<HomeBean.ActInfoBean>) o;
        RecyclerView actrv = holder.getView(R.id.actRv);
        actrv.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        ActAdapter actAdapter = new ActAdapter();
        actrv.setAdapter(actAdapter);
        actAdapter.updatelist(actInfoBeans);
        actAdapter.setBaseRVAdapterlinterner(new IBaseRecyclerLinsterner() {
            @Override
            public void onItemclick(int position) {

            }
        });
    }

    private void displaychannel(Object o,final BaseViewHoder holder) {
        List<HomeBean.ChannelInfoBean> channelInfoBeans = (List<HomeBean.ChannelInfoBean>)o;
        RecyclerView rv_ni = holder.getView(R.id.recycler3);
        Log.i("---","111"+rv_ni);
        rv_ni.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(),5));
        ChannelAdapter channelAdapter = new ChannelAdapter();
        rv_ni.setAdapter(channelAdapter);
        channelAdapter.updatelist(channelInfoBeans);
        channelAdapter.setBaseRVAdapterlinterner(new IBaseRecyclerLinsterner() {
            @Override
            public void onItemclick(int position) {

            }
        });

    }

    private void displaybanner(Object o, BaseViewHoder holder) {
        List<HomeBean.BannerInfoBean> bannerInfoBeans = (List<HomeBean.BannerInfoBean>) o;
       final Banner banner = holder.getView(R.id.banner);
        final List<String> banimgs=new ArrayList<>();
        for (int i = 0; i < bannerInfoBeans.size(); i++) {
            banimgs.add(ShopMallContants.Gui_Url+bannerInfoBeans.get(i).getImage());
        }
        banner.setImages(banimgs);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load((String) path).into(imageView);
            }
        }).start();

    }
}
