package com.bawei.shopmall.type.view;

import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.common.view.NetConfig;
import com.bawei.framework.BaseRvAdapter;
import com.bawei.net.mode.TypeBean;
import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;

public class ChildAdapter extends BaseRvAdapter<TypeBean.ResultBean.ChildBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_ordinary;
    }

    @Override
    protected void convert(TypeBean.ResultBean.ChildBean itemData, BaseViewHolder baseViewHolder, int position) {
        Glide.with(baseViewHolder.itemView.getContext())
                .load(NetConfig.BASE_RESOURCE_IMAGE_URL + itemData.getPic())
                .into((ImageView)baseViewHolder.getView(R.id.iv_ordinary_right));
        TextView tv = baseViewHolder.getView(R.id.tv_ordinary_right);
        tv.setText(itemData.getName());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
