package com.shopmall.bawei.shopmall1805.type.view;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.common.UrlHelper;
import com.shopmall.bawei.framework.BaseRvAdapter;
import com.shopmall.bawei.net.TypeBean;
import com.shopmall.bawei.shopmall1805.R;

public class ChildAdapter extends BaseRvAdapter<TypeBean.ResultBean.ChildBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_ordinary;
    }

    @Override
    protected void convert(TypeBean.ResultBean.ChildBean itemData, BaseViewHolder baseViewHolder, int position) {
        Glide.with(baseViewHolder.itemView.getContext())
                .load(UrlHelper.BASE_RESOURCE_IMAGE_URL + itemData.getPic())
                .into((ImageView)baseViewHolder.getView(R.id.iv_ordinary_right));
        TextView tv = baseViewHolder.getView(R.id.tv_ordinary_right);
        tv.setText(itemData.getName());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
