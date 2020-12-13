package com.example.elevenmonthshoppingproject.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.common.ShopMallContants;
import com.example.elevenmonthshoppingproject.R;
import com.example.net.bean.TypeBean;

import java.util.List;

public class RvTypeAdapter extends BaseQuickAdapter<TypeBean.ResultBean.ChildBean, BaseViewHolder> {
    public RvTypeAdapter(int layoutResId, @Nullable List<TypeBean.ResultBean.ChildBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TypeBean.ResultBean.ChildBean item) {
        Glide.with(mContext).load(ShopMallContants.Gui_Url+item.getPic()).into((ImageView) helper.getView(R.id.type_img));
        helper.setText(R.id.txt_type,item.getName()+"");

    }
}
