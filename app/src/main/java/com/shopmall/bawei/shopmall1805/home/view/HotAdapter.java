package com.shopmall.bawei.shopmall1805.home.view;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.common.ShopmallConstant;
import com.shopmall.bawei.framework.BaseRVAdapter;
import com.shopmall.bawei.net.mode.HomeBean;
import com.shopmall.bawei.shopmall1805.R;

public class HotAdapter extends BaseRVAdapter<HomeBean.HotInfoBean> {


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_hot_layout;
    }

    @Override
    protected void convert(HomeBean.HotInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.hotImg);
        Glide.with(baseViewHolder.itemView.getContext()).load(ShopmallConstant.BASE_RESOURCE_IMAGE_URL + itemData.getFigure()).into(imageView);
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
