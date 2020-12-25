package com.shopmall.bawei.order;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.shopmall.framework.adapter.BaseRVAdapter;
import com.shopmall.net.bean.ShopcarBean;

public class OrderAdapter extends BaseRVAdapter<ShopcarBean.ResultBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_order;
    }

    @Override
    protected void convert(ShopcarBean.ResultBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView itemOrderImage = (ImageView) baseViewHolder.itemView.findViewById(R.id.item_order_image);
        TextView itemOrderName = (TextView) baseViewHolder.itemView.findViewById(R.id.item_order_name);
        TextView itemOrderMoney = (TextView) baseViewHolder.itemView.findViewById(R.id.item_order_money);
        TextView itemOrderNum = (TextView) baseViewHolder.itemView.findViewById(R.id.item_order_num);

        Glide.with(baseViewHolder.itemView.getContext())
                .load(itemData.getUrl()).transform(new CenterCrop()).into(itemOrderImage);
        itemOrderName.setText(itemData.getProductName());
        itemOrderNum.setText("x"+itemData.getProductNum());
        itemOrderMoney.setText("￥"+itemData.getProductPrice());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
