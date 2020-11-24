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

public class HomeXinAdapter extends BaseQuickAdapter<HomeData.ResultBean.RecommendInfoBean, BaseViewHolder> {

    public HomeXinAdapter(int layoutResId, @Nullable List<HomeData.ResultBean.RecommendInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeData.ResultBean.RecommendInfoBean item) {
        ImageView view = helper.getView(R.id.home_xin_image);
        helper.setText(R.id.home_xin_text1,item.getName());
        helper.setText(R.id.home_xin_text2,"￥"+item.getCover_price());
        Myglide.getMyglide().centercenglide(mContext,view, Constants.BASE_URl_IMAGE+item.getFigure());
    }
}
