package com.shopmall.bawei.shopmall1805.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.BuildConfig;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import baseurl.UrlHelp;

public class HomeAdapter extends BaseRvAdapter {
    private Context context;


    public HomeAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected int getLayoutId(int viewType) {
        switch (viewType){
            case 0:

                return R.layout.banner_layout;
            case 1:

                return R.layout.fen_layout;
            case 2:

                return R.layout.guang_layout;
            case 3:

                return R.layout.you_layout;
            case 4:

               return R.layout.xin_layout;
            case 5:

                return R.layout.tui_layout;
        }
        return R.layout.banner_layout;
    }

    @Override
    protected void convert(BaseViewHoder baseViewHoder, int viewType, Object o) {
            switch (viewType){
                case 0:
                    List<HomeBean.ResultBean.BannerInfoBean> h_1 = (List<HomeBean.ResultBean.BannerInfoBean>)o;
                    Banner banner = baseViewHoder.getView(R.id.m_banner);
                    banner.setImages(h_1);
                    banner.setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            HomeBean.ResultBean.BannerInfoBean bean=  (HomeBean.ResultBean.BannerInfoBean)path;
                            Glide.with(context).load(UrlHelp.BASE+UrlHelp.BASE_URl_IMAGE+bean.getImage()).into(imageView);
                        }
                    });
                    banner.start();
                    break;
                case 1:
                    List<HomeBean.ResultBean.ChannelInfoBean> h_2 = (List<HomeBean.ResultBean.ChannelInfoBean>)o;
                    RecyclerView rv = baseViewHoder.getView(R.id.fen_rv);
                    FenAdapter fenAdapter = new FenAdapter(R.layout.fenitem_layout, h_2);
                    rv.setAdapter(fenAdapter);
                    rv.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL));
                    break;
                case 2:
                    List<HomeBean.ResultBean.ActInfoBean> h_3 = (List<HomeBean.ResultBean.ActInfoBean>)o;
                    Banner g_banner = baseViewHoder.getView(R.id.guang_banner);
                    g_banner.setImages(h_3);
                    g_banner.isAutoPlay(false);
                    g_banner.setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            HomeBean.ResultBean.ActInfoBean bean=  (HomeBean.ResultBean.ActInfoBean)path;
                            Glide.with(context).load(UrlHelp.BASE+UrlHelp.BASE_URl_IMAGE+bean.getIcon_url()).into(imageView);
                        }
                    });
                    g_banner.start();
                    break;
                case 3:
                    List<HomeBean.ResultBean.SeckillInfoBean.ListBean> h_4 = (List<HomeBean.ResultBean.SeckillInfoBean.ListBean>)o;
                    RecyclerView you_re = baseViewHoder.getView(R.id.you_re);
                    Log.i("TAG", "convert: "+h_4);
                    YouAdapter youAdapter = new YouAdapter(R.layout.youitme_layout, h_4);
                    you_re.setAdapter(youAdapter);
                    you_re.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
                    break;
                case 4:
                    List<HomeBean.ResultBean.RecommendInfoBean> h_5 = (List<HomeBean.ResultBean.RecommendInfoBean>)o;
                    RecyclerView xin_re = baseViewHoder.getView(R.id.xin_re);
                    XinAdapter xinAdapter = new XinAdapter(R.layout.xinitme_layout, h_5);
                    xin_re.setAdapter(xinAdapter);
                    xin_re.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                    break;
                case 5:
                    List<HomeBean.ResultBean.HotInfoBean> h_6 = (List<HomeBean.ResultBean.HotInfoBean>)o;
                    RecyclerView hot_te = baseViewHoder.getView(R.id.hot_re);
                    HotAdapter hotAdapter = new HotAdapter(R.layout.hotitme_layout, h_6);
                    hot_te.setAdapter(hotAdapter);
                    hot_te.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    break;
            }
    }

    @Override
    public int getViewType(int position) {
        return position;
    }


}
