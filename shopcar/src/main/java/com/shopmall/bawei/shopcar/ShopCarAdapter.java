package com.shopmall.bawei.shopcar;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.view.NumberAddSubView;
import com.example.net.Constants;
import com.example.net.bean.ShopCarBean;
import com.shoppmall.common.adapter.adapter.BaseRvAdapter;

public class ShopCarAdapter extends BaseRvAdapter<ShopCarBean.ResultBean> {
    private Context context;
    private IOnShopCarItemChildClickListener listener;
    public ShopCarAdapter(Context context, IOnShopCarItemChildClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_shop_cart;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHoder, int viewType, final ShopCarBean.ResultBean resultBean) {
        final CheckBox cbGov = baseViewHoder.getView(R.id.cb_gov);
        ImageView ivGov = baseViewHoder.getView(R.id.iv_gov);
        TextView tvDescGov = baseViewHoder.getView(R.id.tv_desc_gov);
        TextView tvPriceGov = baseViewHoder.getView(R.id.tv_price_gov);
        final NumberAddSubView numberAddSubView = baseViewHoder.getView(R.id.numberAddSubView);
        cbGov.setChecked(resultBean.isProductSelected());
        Glide.with(context).load(Constants.BASE_URl_IMAGE+resultBean.getUrl()).into(ivGov);
        tvDescGov.setText(resultBean.getProductName());
        tvPriceGov.setText("¥"+(Float.parseFloat(resultBean.getProductPrice()+"")*Integer.parseInt(resultBean.getProductNum())));
        cbGov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onProductSelectChange(baseViewHoder.getPosition(),cbGov.isSelected());
            }
        });
        numberAddSubView.setValue(Integer.parseInt(resultBean.getProductNum()));
        numberAddSubView.setOnNumberChangeListener(new NumberAddSubView.OnNumberChangeListener() {
            @Override
            public void addNumber(View view, int value) {
                listener.onProductNumChange(resultBean.getProductId(),numberAddSubView.getValue(),resultBean.getProductName(),resultBean.getUrl(),resultBean.getProductPrice()+"");
            }

            @Override
            public void subNumner(View view, int value) {
                listener.onProductNumChange(resultBean.getProductId(),numberAddSubView.getValue(),resultBean.getProductName(),resultBean.getUrl(),resultBean.getProductPrice()+"");
            }
        });
    }

    @Override
    public int getViewType(int position) {
        return position;
    }

}
