package com.shopmall.bawei.shopmall1805.Adper;

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
class ziAdper extends BaseQuickAdapter<ClothesBean.ResultBean.ChildBean, BaseViewHolder> {
    public ziAdper(int layoutResId, @Nullable List<ClothesBean.ResultBean.ChildBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClothesBean.ResultBean.ChildBean item) {
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE+item.getPic())
                .into((ImageView)helper.getView(R.id.tlite_er_image));
        helper.setText(R.id.tlite_er_text,item.getName());
    }
}
