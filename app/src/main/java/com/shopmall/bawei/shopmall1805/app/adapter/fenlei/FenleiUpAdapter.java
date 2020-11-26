package com.shopmall.bawei.shopmall1805.app.adapter.fenlei;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.common.ClothesBean;
import com.shopmall.bawei.shopmall1805.common.ConfigUrl;

import java.util.List;

public class FenleiUpAdapter extends BaseQuickAdapter<ClothesBean.ResultBean.HotProductListBean, BaseViewHolder> {

    public FenleiUpAdapter(int layoutResId, @Nullable List<ClothesBean.ResultBean.HotProductListBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, ClothesBean.ResultBean.HotProductListBean item) {
        Glide.with(mContext).load(ConfigUrl.BASE_IMAGE+item.getFigure()).into((ImageView) helper.getView(R.id.fenlei_up_item_img));
        helper.setText(R.id.fenlei_up_item_tv,item.getCover_price());
    }
}
