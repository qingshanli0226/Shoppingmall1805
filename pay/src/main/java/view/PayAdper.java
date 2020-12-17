package view;


import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.pay.R;

import java.util.List;

import mode.ShopcarBean;

public
class PayAdper extends BaseQuickAdapter<ShopcarBean,BaseViewHolder> {

    public PayAdper(int layoutResId, @Nullable List<ShopcarBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopcarBean item) {
        helper.setText(R.id.textPayone,item.getProductPrice());
        helper.setText(R.id.textPayTow,item.getProductName());
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE+item.getUrl())
                .into((ImageView)helper.getView(R.id.Image_pay));
    }
}
