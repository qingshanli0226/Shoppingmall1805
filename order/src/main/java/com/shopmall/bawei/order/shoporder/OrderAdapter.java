package com.shopmall.bawei.order.shoporder;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.net.Constants;
import com.example.net.bean.ShopCarBean;
import com.shopmall.bawei.order.R;
import com.shoppmall.common.adapter.adapter.BaseRvAdapter;

public class OrderAdapter extends BaseRvAdapter<ShopCarBean.ResultBean>{
    private Context context;

    public OrderAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_order;
    }

    @Override
    protected void convert(final BaseRvAdapter.BaseViewHolder baseViewHoder, int viewType, final ShopCarBean.ResultBean resultBean) {
        ImageView ivOrder = baseViewHoder.getView(R.id.iv_order);
        TextView tvDescOrder = baseViewHoder.getView(R.id.tv_desc_order);
        TextView tvPriceOrder= baseViewHoder.getView(R.id.tv_price_order);
        TextView tvNumOrder = baseViewHoder.getView(R.id.tv_number_order);

        Glide.with(context).load(Constants.BASE_URl_IMAGE+resultBean.getUrl()).into(ivOrder);
        tvDescOrder.setText(resultBean.getProductName());
        tvPriceOrder.setText("¥"+(Float.parseFloat(resultBean.getProductPrice()+"")*Integer.parseInt(resultBean.getProductNum())));
        tvNumOrder.setText("数量:"+resultBean.getProductNum());

    }

    @Override
    public int getViewType(int position) {
        return position;
    }
}
