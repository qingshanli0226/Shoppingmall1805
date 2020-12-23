package com.shopmall.bawei.shopmall1805.type.list;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.net.Constants;
import com.example.net.bean.GoodsBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shoppmall.common.adapter.adapter.BaseRvAdapter;

public class GoodAdapter extends BaseRvAdapter<GoodsBean.ResultBean.ChildBean> {
    private Context context;

    public GoodAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_ordinary_goods_right;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHoder, int viewType, GoodsBean.ResultBean.ChildBean childBean) {
        ImageView iv = baseViewHoder.getView(R.id.iv_ordinary_right);
        TextView tv = baseViewHoder.getView(R.id.tv_ordinary_right);
        Glide.with(context).load(Constants.BASE_URl_IMAGE+childBean.getPic()).into(iv);
        tv.setText(childBean.getName());
    }

    @Override
    public int getViewType(int position) {
        return 0;
    }
}
