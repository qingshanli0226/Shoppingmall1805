package com.shopmall.bawei.shopcar;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.ConfigUrl;

import java.util.List;

public class ShopAdapter extends BaseQuickAdapter<ShopEntity, BaseViewHolder> {
    public ShopAdapter(int layoutResId, @Nullable List<ShopEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopEntity item) {
        Glide.with(mContext).load(ConfigUrl.BASE_IMAGE+item.getPath()).into((ImageView) helper.getView(R.id.imv_item));
        helper.setText(R.id.tv_item,item.getName());
        helper.setText(R.id.tv_money,item.getMoney());
        CheckBox checkBox = helper.getView(R.id.checkbox_item);
        if (item.getChecked()){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }

        if (item.getVisibility()){
            checkBox.setVisibility(View.VISIBLE);
        }else {
            checkBox.setVisibility(View.GONE);
        }
    }
}
