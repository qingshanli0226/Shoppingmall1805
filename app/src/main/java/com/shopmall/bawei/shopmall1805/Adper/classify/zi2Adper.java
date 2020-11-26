package com.shopmall.bawei.shopmall1805.Adper.classify;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

import mode.ClothesBean;
import view.Constants;

public
class zi2Adper extends BaseQuickAdapter<ClothesBean.ResultBean.HotProductListBean, BaseViewHolder> {

    public zi2Adper(int layoutResId, @Nullable List<ClothesBean.ResultBean.HotProductListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClothesBean.ResultBean.HotProductListBean item) {
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE+item.getFigure())
                .into((ImageView)helper.getView(R.id.channel_Image_one));
        helper.setText(R.id.channel_text_tow,item.getCover_price());
    }
}
