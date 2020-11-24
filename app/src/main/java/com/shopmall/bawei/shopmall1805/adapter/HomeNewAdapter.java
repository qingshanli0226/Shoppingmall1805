package com.shopmall.bawei.shopmall1805.adapter;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.common.Constants;
import com.shopmall.net.bean.HomeData;
import com.shopmall.net.glide.Myglide;

import java.util.List;

public class HomeNewAdapter extends BaseQuickAdapter<HomeData.ResultBean.SeckillInfoBean.ListBean, BaseViewHolder> {
    public HomeNewAdapter(int layoutResId, @Nullable List<HomeData.ResultBean.SeckillInfoBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeData.ResultBean.SeckillInfoBean.ListBean item) {
        ImageView view = helper.getView(R.id.home_new_image);
        TextView view1 = helper.getView(R.id.home_new_text2);
        view1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        helper.setText(R.id.home_new_text1,"￥"+item.getCover_price());
        helper.setText(R.id.home_new_text2,"￥"+item.getCover_price());
        Myglide.getMyglide().centercenglide(mContext,view, Constants.BASE_URl_IMAGE+item.getFigure());
    }
}
