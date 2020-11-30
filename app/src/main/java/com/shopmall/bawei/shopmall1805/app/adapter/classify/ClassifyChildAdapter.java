package com.shopmall.bawei.shopmall1805.app.adapter.classify;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.common.ClothesBean;
import com.shopmall.bawei.shopmall1805.common.ConfigUrl;

import java.util.List;

public class ClassifyChildAdapter extends BaseQuickAdapter<ClothesBean.ResultBean.ChildBean, BaseViewHolder> {

    public ClassifyChildAdapter(int layoutResId, @Nullable List<ClothesBean.ResultBean.ChildBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClothesBean.ResultBean.ChildBean item) {
        Glide.with(mContext).load(ConfigUrl.BASE_IMAGE+item.getPic()).into((ImageView) helper.getView(R.id.fenlei_down_item_img));
        helper.setText(R.id.fenlei_down_item_tv,item.getName());
    }
}
