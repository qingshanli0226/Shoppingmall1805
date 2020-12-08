package com.bw.shopcar.adapter;

import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.framework.BaseAdapter;
import com.bw.net.Contants;
import com.bw.net.bean.ShopCarBean;
import com.shopmall.bawei.shopcar.R;

public class ShopCarAdapter extends BaseAdapter<ShopCarBean> {

    private CheckBox cbGov;
    private ImageView ivGov;
    private TextView tvDescGov;
    private TextView tvPriceGov;
    private ImageButton addProductBtn;
    private TextView productNum;
    private ImageButton subProductBtn;

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_shopcar;
    }

    @Override
    protected void convert(BaseViewHoder baseViewHoder, int viewType, ShopCarBean shopCarBean) {

        cbGov = (CheckBox) baseViewHoder.itemView.findViewById(R.id.cb_gov);
        ivGov = (ImageView) baseViewHoder.itemView.findViewById(R.id.iv_gov);
        tvDescGov = (TextView) baseViewHoder.itemView.findViewById(R.id.tv_desc_gov);
        tvPriceGov = (TextView) baseViewHoder.itemView.findViewById(R.id.tv_price_gov);
        addProductBtn = (ImageButton) baseViewHoder.itemView.findViewById(R.id.addProductBtn);
        productNum = (TextView) baseViewHoder.itemView.findViewById(R.id.productNum);
        subProductBtn = (ImageButton) baseViewHoder.itemView.findViewById(R.id.subProductBtn);


        Glide.with(baseViewHoder.itemView.getContext()).load(Contants.BASE_URl_IMAGE+shopCarBean.getUrl()).into(ivGov);
        tvPriceGov.setText(shopCarBean.getProductPrice());
        tvDescGov.setText(shopCarBean.getProductName());




    }

    @Override
    public int getViewType(int position) {
        return 0;
    }
}
