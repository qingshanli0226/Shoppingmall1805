package com.bawei.shopmall.home.view;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.common.view.UrlHelper;
import com.bawei.framework.base.BaseRvAdapter;
import com.bawei.net.bean.HomeBean;
import com.bawei.shopmall.R;
import com.bumptech.glide.Glide;

public class RecommendAdapter extends BaseRvAdapter<HomeBean.ResultBean.RecommendInfoBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_recommend_grid_view;
    }

    @Override
    protected void convert(HomeBean.ResultBean.RecommendInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        String url = UrlHelper.BASE_RESOURCE_IMAGE_URL + itemData.getFigure();
        Glide.with(baseViewHolder.itemView.getContext())
                .load(UrlHelper.BASE_RESOURCE_IMAGE_URL+itemData.getFigure())
                .into((ImageView) baseViewHolder.getView(R.id.iv_recommend));
        Log.i("TAG", "convert: "+url);
        TextView tv_name = baseViewHolder.getView(R.id.tv_name);
        tv_name.setText(itemData.getName());
        TextView tv_price = baseViewHolder.getView(R.id.tv_price);
        tv_price.setText("￥"+itemData.getCover_price());

    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
