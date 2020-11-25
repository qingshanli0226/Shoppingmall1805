package com.shopmall.bawei.shopmall1805.type.view;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.common.UrlHelper;
import com.shopmall.bawei.framework.BaseRvAdapter;
import com.shopmall.bawei.net.mode.TypeBean;
import com.shopmall.bawei.shopmall1805.R;

public class HotAdapter extends BaseRvAdapter<TypeBean.ResultBean.HotProductListBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_hot;
    }

    @Override
    protected void convert(TypeBean.ResultBean.HotProductListBean itemData, BaseViewHolder baseViewHolder, int position) {
        Glide.with(baseViewHolder.itemView.getContext())
                .load(UrlHelper.BASE_RESOURCE_IMAGE_URL+itemData.getFigure())
                .into((ImageView)baseViewHolder.getView(R.id.iv_hot2));
        TextView tv_price = baseViewHolder.getView(R.id.tv_price2);
        tv_price.setText("￥"+itemData.getCover_price());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
