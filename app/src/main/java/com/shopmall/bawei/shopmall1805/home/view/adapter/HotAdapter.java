package com.shopmall.bawei.shopmall1805.home.view.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.common.UrlHelper;
import com.shopmall.bawei.framework.BaseRvAdapter;
import com.shopmall.bawei.net.mode.HomeBean;
import com.shopmall.bawei.shopmall1805.R;

public class HotAdapter extends BaseRvAdapter<HomeBean.ResultBean.HotInfoBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_hot_grid_view;
    }

    @Override
    protected void convert(HomeBean.ResultBean.HotInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        Glide.with(baseViewHolder.itemView.getContext())
                .load(UrlHelper.BASE_RESOURCE_IMAGE_URL + itemData.getFigure())
                .into((ImageView)baseViewHolder.getView(R.id.iv_hot));
        TextView tv_name = baseViewHolder.getView(R.id.tv_name);
        TextView tv_price = baseViewHolder.getView(R.id.tv_price);
        tv_name.setText(itemData.getName());
        tv_price.setText("￥"+itemData.getCover_price());

    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
