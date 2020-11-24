package com.bawei.shopmall.home.view;

import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.common.view.UrlHelper;
import com.bawei.framework.base.BaseRvAdapter;
import com.bawei.net.bean.HomeBean;
import com.bawei.shopmall.R;
import com.bumptech.glide.Glide;

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
