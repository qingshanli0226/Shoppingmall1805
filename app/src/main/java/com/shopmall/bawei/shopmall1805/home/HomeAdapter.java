package com.shopmall.bawei.shopmall1805.home;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.net.Constants;
import com.example.net.bean.MainBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.ChannelAdapter;
import com.shopmall.bawei.shopmall1805.adapter.HotAdapter;
import com.shopmall.bawei.shopmall1805.adapter.RecommendAdapter;
import com.shopmall.bawei.shopmall1805.adapter.SeckillAdapter;
import com.shoppmall.common.adapter.adapter.BaseRvAdapter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.Date;
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
                return R.layout.banner_item;
            case 1:
                return R.layout.channel_item;
            case 2:
                return R.layout.act_item;
            case 3:
                return R.layout.seckill_item;
            case 4:
                return R.layout.recommend_item;
            case 5:
                return R.layout.hot_item;
        }
        return R.layout.banner_item;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHoder, int viewType, Object o) {
        switch (viewType){
            case 0:
                Banner banner = baseViewHoder.getView(R.id.banner);
                List<MainBean.ResultBean.BannerInfoBean> bannerInfoBeans= (List<MainBean.ResultBean.BannerInfoBean>) o;
                banner.setImages(bannerInfoBeans);
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        MainBean.ResultBean.BannerInfoBean bean = (MainBean.ResultBean.BannerInfoBean) path;
                        Glide.with(context).load(Constants.BASE_URl_IMAGE+bean.getImage()).into(imageView);
                    }
                }).start();
                break;
            case 1:
                List<MainBean.ResultBean.ChannelInfoBean> channelInfoBeans = (List<MainBean.ResultBean.ChannelInfoBean>) o;
                RecyclerView rv = baseViewHoder.getView(R.id.rv_channel);
                rv.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL));
                ChannelAdapter adapter = new ChannelAdapter(channelInfoBeans,context);
                rv.setAdapter(adapter);
                break;
            case 2:
                List<MainBean.ResultBean.ActInfoBean> actInfoBeans = (List<MainBean.ResultBean.ActInfoBean>) o;
                ViewPager viewPager = baseViewHoder.getView(R.id.act_vp);
                viewPager.setPageMargin(10);
                viewPager.setOffscreenPageLimit(3);
                viewPager.setAdapter(new PagerAdapter() {
                    @Override
                    public int getCount() {
                        return actInfoBeans.size();
                    }

                    @Override
                    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                        return view==object;
                    }

                    @NonNull
                    @Override
                    public Object instantiateItem(@NonNull ViewGroup container, int position) {
                        ImageView view = new ImageView(context);
                        view.setScaleType(ImageView.ScaleType.FIT_XY);
                        //绑定数据
                        Glide.with(context)
                                .load(Constants.BASE_URl_IMAGE + actInfoBeans.get(position).getIcon_url())
                                .into(view);
                        container.addView(view);
                        return view;
                    }

                    @Override
                    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                        container.removeView((View) object);
                    }
                });

                break;
            case 3:
                MainBean.ResultBean.SeckillInfoBean seckillInfoBeans =(MainBean.ResultBean.SeckillInfoBean) o;

                tvTime = (TextView) baseViewHoder.getView(R.id.tv_time_seckill);
                RecyclerView recyclerView = (RecyclerView) baseViewHoder.getView(R.id.rv_seckill);
                if (isFirst) {
                    dt = (int) (Integer.parseInt(seckillInfoBeans.getEnd_time()) - (Integer.parseInt(seckillInfoBeans.getStart_time())));
                    isFirst = false;
                }
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
                List<MainBean.ResultBean.SeckillInfoBean.ListBean> beans = seckillInfoBeans.getList();
                SeckillAdapter seckillAdapter = new SeckillAdapter(beans, context);
                seckillAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        ARouter.getInstance().build("/detailpage/DetailActivity").withSerializable("good", beans.get(position)).withString("type","seckill").navigation();

                    }
                });
                recyclerView.setAdapter(seckillAdapter);
                handler.sendEmptyMessageDelayed(0, 1000);
                break;
            case 4:
                List<MainBean.ResultBean.RecommendInfoBean> recommendInfoBeans = (List<MainBean.ResultBean.RecommendInfoBean>) o;
                RecyclerView recommendrv = baseViewHoder.getView(R.id.rv_recommend);
                recommendrv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                RecommendAdapter recommendAdapter = new RecommendAdapter(recommendInfoBeans, context);
                recommendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        ARouter.getInstance().build("/detailpage/DetailActivity").withSerializable("good", recommendInfoBeans.get(position)).withString("type","recommend").navigation();

                    }
                });
                recommendrv.setAdapter(recommendAdapter);

                break;
            case 5:
                List<MainBean.ResultBean.HotInfoBean> hotInfoBeans = (List<MainBean.ResultBean.HotInfoBean>) o;
                RecyclerView hotrv = baseViewHoder.getView(R.id.rv_hot);
                hotrv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                HotAdapter hotAdapter = new HotAdapter(hotInfoBeans, context);
                hotAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        ARouter.getInstance().build("/detailpage/DetailActivity").withSerializable("good", hotInfoBeans.get(position)).withString("type","hot").navigation();

                    }
                });
                hotrv.setAdapter(hotAdapter);
                break;
        }
    }
    private boolean isFirst = true;
    private TextView tvTime;
    private int dt;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                dt = dt - 1000;
                SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
                tvTime.setText(sd.format(new Date(dt)));

                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0, 1000);
                if (dt == 0) {
                    handler.removeMessages(0);
                }
            }

        }
    };
    @Override
    public int getViewType(int position) {
        return position;
    }
}
