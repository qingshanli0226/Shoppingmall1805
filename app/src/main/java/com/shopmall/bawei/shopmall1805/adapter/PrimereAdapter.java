package com.shopmall.bawei.shopmall1805.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.ConfigUrl;
import com.example.net.HomeBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.ui.activity.XiangActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class PrimereAdapter extends BaseMultiItemQuickAdapter<HomeBean, BaseViewHolder> {

    private List<String> imv = new ArrayList<>();
    private ItemPrimereAdapter itemPrimereAdapter;
    private ItemFourAdapter itemFourAdapter;
    private ItemFiveAdapter itemFiveAdapter;
    private ItemSixAdapter itemSixAdapter;
    private List<String> imv1 = new ArrayList<>();

    public PrimereAdapter(List<HomeBean> data) {
        super(data);
        addItemType(0,R.layout.layout_item);

    }

    @Override
    protected void convert(BaseViewHolder helper, final HomeBean item) {
        switch (item.getI()){
            case 0:
                Banner banner = helper.getView(R.id.banner);
                for (int i=0;i<item.getBanner_info().size();i++){
                    imv.add(ConfigUrl.BASE_IMAGE+item.getBanner_info().get(i).getImage());
                }
                banner.setImages(imv);
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(path).into(imageView);
                    }
                });
                banner.start();
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(mContext, XiangActivity.class);
                        intent.putExtra("path",ConfigUrl.BASE_IMAGE+item.getBanner_info().get(position).getImage());
                        mContext.startActivity(intent);
                    }
                });


//                break;
//            case 1:
                RecyclerView view = helper.getView(R.id.rec_item2);
                itemPrimereAdapter = new ItemPrimereAdapter(R.layout.item2_item,item.getChannel_info());
                view.setAdapter(itemPrimereAdapter);
                view.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL));

                itemPrimereAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(mContext, XiangActivity.class);
                        intent.putExtra("path",ConfigUrl.BASE_IMAGE+item.getChannel_info().get(position).getImage());
                        mContext.startActivity(intent);
                    }
                });

//                break;
//            case 2:
                Banner vp = helper.getView(R.id.vp_item3);
                for (int i=0;i<item.getAct_info().size();i++){
                    imv1.add(ConfigUrl.BASE_IMAGE+item.getAct_info().get(i).getIcon_url());
                }
                vp.setImages(imv1);
                vp.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(path).into(imageView);
                    }
                });
                vp.start();

                vp.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(mContext, XiangActivity.class);
                        intent.putExtra("path",ConfigUrl.BASE_IMAGE+item.getAct_info().get(position).getIcon_url());
                        mContext.startActivity(intent);
                    }
                });

                RecyclerView view1 = helper.getView(R.id.rec);
                itemFourAdapter = new ItemFourAdapter(R.layout.item4_item,item.getSeckill_info().getList());
                view1.setAdapter(itemFourAdapter);
                view1.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));

                itemFourAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(mContext, XiangActivity.class);
                        intent.putExtra("path",ConfigUrl.BASE_IMAGE+item.getSeckill_info().getList().get(position).getFigure());
                        mContext.startActivity(intent);
                    }
                });

                RecyclerView view2 = helper.getView(R.id.rec_item5);
                itemFiveAdapter = new ItemFiveAdapter(R.layout.item5_item,item.getRecommend_info());
                view2.setAdapter(itemFiveAdapter);
                view2.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

                itemFiveAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(mContext, XiangActivity.class);
                        intent.putExtra("path",item.getRecommend_info().get(position).getFigure());
                        mContext.startActivity(intent);
                    }
                });

                RecyclerView helperView = helper.getView(R.id.rec_item6);
                itemSixAdapter = new ItemSixAdapter(R.layout.item6_item,item.getHot_info());
                helperView.setAdapter(itemSixAdapter);
                helperView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

                itemSixAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(mContext, XiangActivity.class);
                        intent.putExtra("path",item.getHot_info().get(position).getFigure());
                        mContext.startActivity(intent);
                    }
                });

                break;
        }
    }
}
