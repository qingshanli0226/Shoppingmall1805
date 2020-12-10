package com.shopmall.bawei.shopmall1805.apter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;



import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.DetailsActivity;
import com.shopmall.bawei.shopmall1805.apter.apter.ActAdapter;
import com.shopmall.bawei.shopmall1805.apter.apter.CommonlyUsedApter;
import com.shopmall.bawei.shopmall1805.apter.apter.HotAdapter;
import com.shopmall.bawei.shopmall1805.apter.apter.RecommendAdapter;
import com.shopmall.bawei.shopmall1805.apter.apter.SkirtAdapter;
import com.shopmall.bawei.shopmall1805.apter.apter2.BaseRVAdapter;
import com.shopmall.bawei.shopmall1805.login.LoginActivity;
import com.shopmall.bawei.shopmall1805.user.GoodsBean;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import bean.HomeBean;

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
        final List<HomeBean.HotInfoBean> hotInfoBeans = (List<HomeBean.HotInfoBean>)itemData;//强转成我们需要的类型
        RecyclerView hotRv = baseViewHolder.getView(R.id.gv_hot);
        hotRv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(),2));
        final HotAdapter hotAdapter = new HotAdapter();
        hotRv.setAdapter(hotAdapter);
        hotAdapter.updataData(hotInfoBeans);
        //点击跳转页面
        hotAdapter.setiRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                GoodsBean goodsBean = new GoodsBean(hotInfoBeans.get(position).getProduct_id(),hotInfoBeans.get(position).getCover_price(),"http://49.233.0.68:8080/atguigu/img/"+hotInfoBeans.get(position).getFigure(),hotInfoBeans.get(position).getName());
                Intent intent = new Intent(baseViewHolder.itemView.getContext(), DetailsActivity.class);
                intent.putExtra("shangp", goodsBean);
                baseViewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    private void displayRecommend(Object itemData, final BaseViewHolder baseViewHolder) {
        final List<HomeBean.RecommendInfoBean> recommendInfoBeans = (List<HomeBean.RecommendInfoBean>)itemData;//强转成我们需要的类型
        RecyclerView commendRv = baseViewHolder.getView(R.id.rv_recommen);
        commendRv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(),3));
        final RecommendAdapter recommendAdapter = new RecommendAdapter();
        commendRv.setAdapter(recommendAdapter);
        recommendAdapter.updataData(recommendInfoBeans);
        //点击跳转页面
        recommendAdapter.setiRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {

                GoodsBean goodsBean = new GoodsBean(recommendInfoBeans.get(position).getProduct_id(),recommendInfoBeans.get(position).getCover_price(),"http://49.233.0.68:8080/atguigu/img/"+recommendInfoBeans.get(position).getFigure(),recommendInfoBeans.get(position).getName());

                Intent intent = new Intent(baseViewHolder.itemView.getContext(), DetailsActivity.class);
                intent.putExtra("shangp", goodsBean);
                baseViewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    private void displaySeckill(Object itemData, final BaseViewHolder baseViewHolder) {
        final List<HomeBean.SeckillInfoBean.ListBean> seckillInfoBeans = (List<HomeBean.SeckillInfoBean.ListBean>)itemData;//强转成我们需要的类型
        RecyclerView seckRv = baseViewHolder.getView(R.id.rv_seck);
        seckRv.setLayoutManager(new LinearLayoutManager(baseViewHolder.itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        final SkirtAdapter seckAdapter = new SkirtAdapter();
        seckRv.setAdapter(seckAdapter);
        seckAdapter.updataData(seckillInfoBeans);
        //点击跳转页面
        seckAdapter.setiRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                GoodsBean goodsBean = new GoodsBean(seckillInfoBeans.get(position).getProduct_id(),seckillInfoBeans.get(position).getCover_price(),"http://49.233.0.68:8080/atguigu/img/"+seckillInfoBeans.get(position).getFigure(),seckillInfoBeans.get(position).getName());
                Intent intent = new Intent(baseViewHolder.itemView.getContext(), DetailsActivity.class);
                intent.putExtra("shangp", goodsBean);
                baseViewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    private void displayAct(Object itemData, final BaseViewHolder baseViewHolder) {
        final List<HomeBean.ActInfoBean> actInfoBeans = (List<HomeBean.ActInfoBean>)itemData;//强转成我们需要的类型
        RecyclerView actRv = baseViewHolder.getView(R.id.actRv);
        actRv.setLayoutManager(new LinearLayoutManager(baseViewHolder.itemView.getContext(),LinearLayoutManager.HORIZONTAL, false));
        ActAdapter actAdapter = new ActAdapter();
        actRv.setAdapter(actAdapter);
        actAdapter.updataData(actInfoBeans);
//        //点击跳转页面
        actAdapter.setiRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position < actInfoBeans.size()) {
                    String product_id = "";
                    String name = "";
                    String cover_price = "";
                    if (position == 0) {
                        product_id = "627";
                        cover_price = "100.00";
                        name = "剑三T恤批发";
                    } else if (position == 1) {
                        product_id = "21";
                        cover_price = "99.00";
                        name = "【同人原创】剑网3 剑侠情缘叁 Q版成男 口袋胸针";
                    }
                    String image = actInfoBeans.get(position).getIcon_url();
                    GoodsBean goodsBean = new GoodsBean(product_id,cover_price,"http://49.233.0.68:8080/atguigu/img/"+actInfoBeans.get(position).getIcon_url(),name);

                    Intent intent = new Intent(baseViewHolder.itemView.getContext(), DetailsActivity.class);
                    intent.putExtra("shangp", goodsBean);
                    baseViewHolder.itemView.getContext().startActivity(intent);
                }
            }
        });
    }

    private void displayChannel(Object itemData, final BaseViewHolder baseViewHolder) {
        List<HomeBean.ChannelInfoBean> channelInfoBeans = (List<HomeBean.ChannelInfoBean>)itemData;//强转成我们需要的类型
        RecyclerView channelRv = baseViewHolder.getView(R.id.channelRv);
        channelRv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(),5));

        CommonlyUsedApter channelAdapter = new CommonlyUsedApter();
        channelRv.setAdapter(channelAdapter);
        channelAdapter.updataData(channelInfoBeans);
        channelAdapter.setiRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.setClass((Activity)(baseViewHolder.itemView.getContext()), LoginActivity.class);
                ((Activity)(baseViewHolder.itemView.getContext())).startActivity(intent);
            }
        });
    }
    private void displayBanner(final Object itemData, final BaseViewHolder baseViewHolder) {
        final List<HomeBean.BannerInfoBean> bannerInfoBeans = (List<HomeBean.BannerInfoBean>) itemData;//强转成我们需要的类型

        final Banner banner = baseViewHolder.getView(R.id.bannerContainer);

        List<String> imageUrls = new ArrayList<>();

        for (int i = 0; i <bannerInfoBeans.size() ; i++) {
            imageUrls.add("http://49.233.0.68:8080/atguigu/img/"+bannerInfoBeans.get(i).getImage());
        }

        banner.setImages(imageUrls);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load((String)path).into(imageView);
            }
        });
        banner.start();
        //点击跳转页面
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if(position  < bannerInfoBeans.size()){
                    int option = bannerInfoBeans.get(position ).getOption();
                    String product_id = "";
                    String name = "";
                    String cover_price = "";
                    if (position  == 0) {
                        product_id = "627";
                        cover_price = "32.00";
                        name = "剑三T恤批发";
                    } else if (position  == 1) {
                        product_id = "21";
                        cover_price = "8.00";
                        name = "同人原创】剑网3 剑侠情缘叁 Q版成男 口袋胸针";
                    } else {
                        product_id = "1341";
                        cover_price = "50.00";
                        name = "【蓝诺】《天下吾双》 剑网3同人本";
                    }
                    String image = bannerInfoBeans.get(position).getImage();
                    GoodsBean goodsBean = new GoodsBean(product_id,cover_price,"http://49.233.0.68:8080/atguigu/img/"+bannerInfoBeans.get(position).getImage(),name);

                    Intent intent = new Intent(baseViewHolder.itemView.getContext(), DetailsActivity.class);
                    intent.putExtra("shangp", goodsBean);
                    baseViewHolder.itemView.getContext().startActivity(intent);
                }
            }
        });
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
