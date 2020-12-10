package com.shopmall.bawei.shopmall1805.home.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.common2.HomeBean;
import com.example.common2.UrlHelp;
import com.shopmall.bawei.shopmall1805.goods.GoodsListActivity;
import com.shopmall.bawei.shopmall1805.R;

import com.shopmall.bawei.shopmall1805.adapter.BaseRvAdapter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import java.util.List;


public class HomeMultiLayoutAdapter extends BaseRvAdapter {
    private Context context;


    public HomeMultiLayoutAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected int getLayoutId(int viewType) {
        switch (viewType){
            case 0:

                return R.layout.home_banner_layout;
            case 1:

                return R.layout.home_classification_layout;
            case 2:

                return R.layout.home_advertisement_layout;
            case 3:

                return R.layout.home_discount_layout;
            case 4:

               return R.layout.home_new_layout;
            case 5:

                return R.layout.home_recommend_layout;
        }
        return R.layout.home_banner_layout;
    }

    @Override
    protected void convert(BaseViewHoder baseViewHoder, int viewType, Object o) {
            switch (viewType){
                case 0:
                    List<HomeBean.BannerInfoBean> h_1 = (List<HomeBean.BannerInfoBean>)o;
                    Banner banner = baseViewHoder.getView(R.id.m_banner);
                    banner.setImages(h_1);
                    banner.setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            HomeBean.BannerInfoBean bean=  (HomeBean.BannerInfoBean)path;
                            Glide.with(context).load(UrlHelp.BASE_URl_IMAGE+bean.getImage()).into(imageView);
                        }
                    });
                    banner.start();
                    break;
                case 1:
                    List<HomeBean.ChannelInfoBean> h_2 = (List<HomeBean.ChannelInfoBean>)o;
                    RecyclerView rv = baseViewHoder.getView(R.id.fen_rv);
                    HomeClassificationAdapter homeClassificationAdapter = new HomeClassificationAdapter(R.layout.item_home_classification, h_2);
                    rv.setAdapter(homeClassificationAdapter);
                    rv.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL));
                    break;
                case 2:
                    List<HomeBean.ActInfoBean> h_3 = (List<HomeBean.ActInfoBean>)o;
                    Banner g_banner = baseViewHoder.getView(R.id.guang_banner);
                    g_banner.setImages(h_3);
                    g_banner.isAutoPlay(false);
                    g_banner.setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            HomeBean.ActInfoBean bean=  (HomeBean.ActInfoBean)path;
                            Glide.with(context).load(UrlHelp.BASE_URl_IMAGE+bean.getIcon_url()).into(imageView);
                        }
                    });
                    g_banner.start();
                    break;
                case 3:
                    final List<HomeBean.SeckillInfoBean.ListBean> h_4 = (List<HomeBean.SeckillInfoBean.ListBean>)o;
                    RecyclerView you_re = baseViewHoder.getView(R.id.you_re);
                    HomeDiscountAdapter homeDiscountAdapter = new HomeDiscountAdapter(R.layout.itme_home_discount, h_4);
                    you_re.setAdapter(homeDiscountAdapter);
                    you_re.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
                    homeDiscountAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent(context, GoodsListActivity.class);


                            intent.putExtra("goods_image",UrlHelp.BASE_URl_IMAGE+h_4.get(position).getFigure());
                            intent.putExtra("goods_title",h_4.get(position).getName());
                            intent.putExtra("goods_pay",h_4.get(position).getCover_price());

                            context.startActivity(intent);
                        }
                    });
                    break;
                case 4:


                    final List<HomeBean.RecommendInfoBean> h5 = (List<HomeBean.RecommendInfoBean>)o;
                    final RecyclerView xin_re = baseViewHoder.getView(R.id.xin_re);
                    HomeNewAdapter homeNewAdapter = new HomeNewAdapter(R.layout.itme_home_new, h5);
                    xin_re.setAdapter(homeNewAdapter);
                    xin_re.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

                    homeNewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent(context, GoodsListActivity.class);

                            intent.putExtra("goods_image",UrlHelp.BASE_URl_IMAGE+h5.get(position).getFigure());
                            intent.putExtra("goods_title",h5.get(position).getName());
                            intent.putExtra("goods_pay",h5.get(position).getCover_price());

                            context.startActivity(intent);
                        }
                    });
                    break;
                case 5:
                    final List<HomeBean.HotInfoBean> h_6 = (List<HomeBean.HotInfoBean>)o;
                    RecyclerView hot_te = baseViewHoder.getView(R.id.hot_re);
                    HomeHotAdapter homeHotAdapter = new HomeHotAdapter(R.layout.itme_home_hot, h_6);
                    hot_te.setAdapter(homeHotAdapter);
                    hot_te.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    homeHotAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent(context, GoodsListActivity.class);


                            intent.putExtra("goods_image",UrlHelp.BASE_URl_IMAGE+h_6.get(position).getFigure());
                            intent.putExtra("goods_title",h_6.get(position).getName());
                            intent.putExtra("goods_pay",h_6.get(position).getCover_price());

                            context.startActivity(intent);
                        }
                    });


                    break;
            }
    }

    @Override
    public int getViewType(int position) {
        return position;
    }


}
