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

public class RightAdapter extends BaseQuickAdapter<ClothesBean.ResultBean.ChildBean, BaseViewHolder> {


    public RightAdapter(int layoutResId, @Nullable List<ClothesBean.ResultBean.ChildBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClothesBean.ResultBean.ChildBean item) {
        Glide.with(mContext).load(ConfigUrl.BASE_IMAGE+item.getPic()).into((ImageView) helper.getView(R.id.imv_top));
    }
}
