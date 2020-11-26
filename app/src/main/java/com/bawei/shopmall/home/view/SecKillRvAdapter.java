package com.bawei.shopmall.home.view;

import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.common.view.UrlHelper;
import com.bawei.framework.BaseRvAdapter;
import com.bawei.net.mode.HomeBean;
import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;

public class SecKillRvAdapter extends BaseRvAdapter<HomeBean.ResultBean.SeckillInfoBean.ListBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_seckill;
    }

    @Override
    protected void convert(HomeBean.ResultBean.SeckillInfoBean.ListBean itemData, BaseViewHolder baseViewHolder, int position) {
        Glide.with(baseViewHolder.itemView.getContext())
                .load(UrlHelper.BASE_RESOURCE_IMAGE_URL+itemData.getFigure())
                .into((ImageView)baseViewHolder.getView(R.id.iv_figure));
        TextView tv_price = baseViewHolder.getView(R.id.tv_cover_price);
        tv_price.setText("￥"+itemData.getCover_price());
        TextView tv_price2 = baseViewHolder.getView(R.id.tv_origin_price);
        tv_price2.setText("￥"+itemData.getOrigin_price());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
