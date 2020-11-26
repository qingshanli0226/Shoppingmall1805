package com.shopmall.bawei.shopmall1805.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.ClothesBean;
import com.example.net.ConfigUrl;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public class BottomAdapter extends BaseQuickAdapter<ClothesBean.ResultBean.HotProductListBean, BaseViewHolder> {


    public BottomAdapter(int layoutResId, @Nullable List<ClothesBean.ResultBean.HotProductListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClothesBean.ResultBean.HotProductListBean item) {
        Glide.with(mContext).load(ConfigUrl.BASE_IMAGE+item.getFigure()).into((ImageView) helper.getView(R.id.imv_bottom));
    }
}
