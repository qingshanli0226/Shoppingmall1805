package com.shopmall.bawei.shopmall1805.Adper;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

import mode.HomeBean;
import view.Constants;

public
class reamAdpter extends BaseQuickAdapter<HomeBean.ResultBean.RecommendInfoBean, BaseViewHolder> {


    public reamAdpter(int layoutResId, @Nullable List<HomeBean.ResultBean.RecommendInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ResultBean.RecommendInfoBean item) {
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE+item.getFigure())
                .into((ImageView)helper.getView(R.id.remn_image));
        Log.i("=====","这是打印的数据"+item.getName());
        helper.setText(R.id.remn_text_one,item.getName());
        helper.setText(R.id.remn_text_tow,item.getCover_price());
    }
}
