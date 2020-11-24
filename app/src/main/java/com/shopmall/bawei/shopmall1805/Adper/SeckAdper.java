package com.shopmall.bawei.shopmall1805.Adper;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

import mode.HomeBean;
import view.Constants;

public
class SeckAdper extends BaseQuickAdapter<HomeBean.ResultBean.SeckillInfoBean.ListBean, BaseViewHolder> {
    public SeckAdper(int layoutResId, @Nullable List<HomeBean.ResultBean.SeckillInfoBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ResultBean.SeckillInfoBean.ListBean item) {
        Glide.with(mContext)
                .load(Constants.BASE_URl_IMAGE+item.getFigure()).into((ImageView)helper.getView(R.id.seck_image));

        helper.setText(R.id.seck_text_one,item.getCover_price());
        helper.setText(R.id.seck_text_tow,item.getName());
    }
}
