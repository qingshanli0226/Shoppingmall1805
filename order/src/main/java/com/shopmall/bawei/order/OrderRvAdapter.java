package com.shopmall.bawei.order;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.common.UrlHelper;
import com.shopmall.bawei.framework.BaseRvAdapter;
import com.shopmall.bawei.net.mode.ShopCarBean;

public class OrderRvAdapter extends BaseRvAdapter<ShopCarBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_order;
    }

    @Override
    protected void convert(ShopCarBean itemData, BaseViewHolder baseViewHolder, int position) {
        Glide.with(baseViewHolder.itemView.getContext()).load(UrlHelper.BASE_RESOURCE_IMAGE_URL+itemData.getUrl()).into((ImageView)baseViewHolder.getView(R.id.item_img));
        TextView itemName = baseViewHolder.getView(R.id.item_name);
        itemName.setText(itemData.getProductName());
        TextView itemPrice = baseViewHolder.getView(R.id.item_price);
        itemPrice.setText("￥"+itemData.getProductPrice());
        TextView itemNum = baseViewHolder.getView(R.id.item_num);
        itemNum.setText("X"+itemData.getProductNum());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
