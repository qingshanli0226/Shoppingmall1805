package com.shopmall.bawei.shopmall1805.home.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.common.UrlHelper;
import com.shopmall.bawei.framework.BaseRvAdapter;
import com.shopmall.bawei.net.mode.GoodsBean;
import com.shopmall.bawei.net.mode.HomeBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.goodsdesc.view.GoodsInfoActivity;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends BaseRvAdapter<Object> {
    public static final String GOODS_BEAN = "goods_bean";
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
                return R.layout.banner_viewpager;
            case CHANNEL_TYPE:
                return R.layout.channel_item;
            case ACT_TYPE:
                return R.layout.home_view_act;
            case SECKILL_TYPE:
                return R.layout.seckill_item;
            case RECOMMEND_TYPE:
                return R.layout.recommend_item;
            case HOT_TYPE:
                return R.layout.hot_item;
            default:return R.layout.banner_viewpager;
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

    private void displayRecommend(Object itemData, final BaseViewHolder baseViewHolder) {
        final List<HomeBean.RecommendInfoBean> recommendInfoBeans = (List<HomeBean.RecommendInfoBean>)itemData;
        RecyclerView recommendRv = baseViewHolder.getView(R.id.gv_recommend);
        Log.i("TAG", "displayRecommend: "+recommendInfoBeans);
        recommendRv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(),3,GridLayoutManager.VERTICAL,false));
        RecommendAdapter recommendAdapter = new RecommendAdapter();
        recommendRv.setAdapter(recommendAdapter);
        recommendAdapter.updateData(recommendInfoBeans);
        recommendAdapter.setIRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String cover_price = recommendInfoBeans.get(position).getCover_price();
                String name = recommendInfoBeans.get(position).getName();
                String figure = recommendInfoBeans.get(position).getFigure();
                String product_id = recommendInfoBeans.get(position).getProduct_id();
                GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id);
//
                Intent intent = new Intent(baseViewHolder.itemView.getContext(), GoodsInfoActivity.class);
                intent.putExtra(GOODS_BEAN, goodsBean);
                baseViewHolder.itemView.getContext().startActivity(intent);
                /**
                 *
                 *
                 *
                 *
                 *
                 */
            }
        });
    }

    private void displaySeckill(Object itemData, final BaseViewHolder baseViewHolder) {
        final HomeBean.SeckillInfoBean seckillInfoBean = (HomeBean.SeckillInfoBean)itemData;
        final List<HomeBean.SeckillInfoBean.ListBean> list = seckillInfoBean.getList();
        RecyclerView secKillRv = baseViewHolder.getView(R.id.rv_seckill);
        secKillRv.setLayoutManager(new LinearLayoutManager(baseViewHolder.itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        SecKillRvAdapter secKillRvAdapter = new SecKillRvAdapter();
        secKillRv.setAdapter(secKillRvAdapter);
        secKillRvAdapter.updateData(list);
        secKillRvAdapter.setIRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                HomeBean.SeckillInfoBean.ListBean listBean = seckillInfoBean.getList().get(position);
                String name = listBean.getName();
                String cover_price = listBean.getCover_price();
                String figure = listBean.getFigure();
                String product_id = listBean.getProduct_id();
                GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id);
//
                Intent intent = new Intent(baseViewHolder.itemView.getContext(), GoodsInfoActivity.class);
                intent.putExtra(GOODS_BEAN, goodsBean);
                baseViewHolder.itemView.getContext().startActivity(intent);

                /**
                 *
                 *
                 *
                 *
                 *
                 */
            }
        });
    }

    private void displayHot(Object itemData, final BaseViewHolder baseViewHolder) {
        final List<HomeBean.HotInfoBean> hotInfoBeans = (List<HomeBean.HotInfoBean>)itemData;
        RecyclerView hotRv = baseViewHolder.getView(R.id.gv_hot);
        hotRv.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(),2,GridLayoutManager.VERTICAL,false));
        HotAdapter hotAdapter = new HotAdapter();
        hotRv.setAdapter(hotAdapter);
        hotAdapter.updateData(hotInfoBeans);
        hotAdapter.setIRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String cover_price = hotInfoBeans.get(position).getCover_price();
                String name = hotInfoBeans.get(position).getName();
                String figure = hotInfoBeans.get(position).getFigure();
                String product_id = hotInfoBeans.get(position).getProduct_id();
                GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id);

                Intent intent = new Intent(baseViewHolder.itemView.getContext(), GoodsInfoActivity.class);
                intent.putExtra(GOODS_BEAN, goodsBean);
                baseViewHolder.itemView.getContext().startActivity(intent);
                /**
                 *
                 *
                 *
                 *
                 *
                 */
            }
        });
    }

    private void displayAct(Object itemData, BaseViewHolder baseViewHolder) {
        List<HomeBean.ActInfoBean> actInfoBeans = (List<HomeBean.ActInfoBean>)itemData;
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
        List<HomeBean.ChannelInfoBean> channelInfoBeans = (List<HomeBean.ChannelInfoBean>) itemData;
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

    private void displayBanner(Object itemData, final BaseViewHolder baseViewHolder) {

        final List<HomeBean.BannerInfoBean> bannerInfoBeans = (List<HomeBean.BannerInfoBean>)itemData;//强转成我们需要的类型

        Banner banner = baseViewHolder.getView(R.id.banner);
        banner.setBannerAnimation(Transformer.Accordion);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load((String)path).into((imageView));
            }
        });
        List<String> imageUrls = new ArrayList<>();
        for (HomeBean.BannerInfoBean item : bannerInfoBeans){
            imageUrls.add(UrlHelper.BASE_RESOURCE_IMAGE_URL+item.getImage());
        }
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if(position < bannerInfoBeans.size() ){
                    int option = bannerInfoBeans.get(position).getOption();
                    String product_id = "";
                    String name = "";
                    String cover_price = "";
                    if (position == 0) {
                        product_id = "627";
                        cover_price = "32.00";
                        name = "剑三T恤批发";
                    } else if (position == 1) {
                        product_id = "21";
                        cover_price = "8.00";
                        name = "同人原创】剑网3 剑侠情缘叁 Q版成男 口袋胸针";
                    } else {
                        product_id = "1341";
                        cover_price = "50.00";
                        name = "【蓝诺】《天下吾双》 剑网3同人本";
                    }
                    String image = bannerInfoBeans.get(position).getImage();
                    GoodsBean goodsBean = new GoodsBean(name, cover_price, image, product_id);
                    Intent intent = new Intent(baseViewHolder.itemView.getContext(), GoodsInfoActivity.class);
                    intent.putExtra("goods_bean", goodsBean);
                    baseViewHolder.itemView.getContext().startActivity(intent);
                }
            }
        });

        banner.setImages(imageUrls);
        banner.setDelayTime(3000);
        banner.start();

    }

    @Override
    protected int getViewType(int position) {
        switch (position){
            case 0:return BANNER_TYPE;
            case 1:return CHANNEL_TYPE;
            case 2: return ACT_TYPE;
            case 3: return SECKILL_TYPE;
            case 4: return RECOMMEND_TYPE;
            case 5: return HOT_TYPE;
            default:
                return BANNER_TYPE;
        }
    }
}
