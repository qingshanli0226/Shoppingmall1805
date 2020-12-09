package com.shopmall.bawei.shopmall1805.app.adapter.classify;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.net.entity.ClothesBean;
import com.shopmall.bawei.shopmall1805.common.ConfigUrl;

import java.util.List;

public class ChannelInfoAdapter extends BaseQuickAdapter<ClothesBean.ResultBean.HotProductListBean, BaseViewHolder> {


    public ChannelInfoAdapter(int layoutResId, @Nullable List<ClothesBean.ResultBean.HotProductListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClothesBean.ResultBean.HotProductListBean item) {
        Glide.with(mContext).load(ConfigUrl.BASE_IMAGE+item.getFigure()).into((ImageView) helper.getView(R.id.item_detail_img));
        helper.setText(R.id.item_detail_price,item.getCover_price());
        helper.setText(R.id.item_detail_title,item.getName());

    }
}
