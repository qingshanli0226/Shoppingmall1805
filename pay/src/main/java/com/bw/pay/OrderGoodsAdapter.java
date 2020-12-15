package com.bw.pay;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.framework.BaseAdapter;
import com.bw.net.Contants;
import com.bw.net.bean.ShopCarBean;
import com.shopmall.bawei.pay.R;

public class OrderGoodsAdapter extends BaseAdapter<ShopCarBean> {

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_ordergoods;
    }

    @Override
    protected void convert(BaseViewHoder baseViewHoder, int viewType, ShopCarBean shopCarBean) {
        ImageView imageView = baseViewHoder.getView(R.id.item_goodImage);
        Glide.with(baseViewHoder.itemView.getContext()).load(Contants.BASE_URl_IMAGE+shopCarBean.getUrl()).into(imageView);
    }

    @Override
    public int getViewType(int position) {
        return position;
    }
}
