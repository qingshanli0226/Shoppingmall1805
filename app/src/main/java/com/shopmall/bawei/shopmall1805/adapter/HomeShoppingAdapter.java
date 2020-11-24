package com.shopmall.bawei.shopmall1805.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bean.HomeData;
import com.shopmall.glide.Myglide;

import java.util.List;

public class HomeShoppingAdapter extends BaseQuickAdapter<HomeData.ResultBean.HotInfoBean, BaseViewHolder> {
    public HomeShoppingAdapter(int layoutResId, @Nullable List<HomeData.ResultBean.HotInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeData.ResultBean.HotInfoBean item) {
       ImageView view = helper.getView(R.id.home_shpping_image);
       helper.setText(R.id.home_shpping_name,item.getName());
       helper.setText(R.id.home_shpping_price,"￥"+item.getCover_price());
        Myglide.getMyglide().centercenglide(mContext,view, Constants.BASE_URl_IMAGE+item.getFigure());
    }
}
