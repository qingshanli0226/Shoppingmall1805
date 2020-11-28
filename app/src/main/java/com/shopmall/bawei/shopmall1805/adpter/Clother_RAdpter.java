package com.shopmall.bawei.shopmall1805.adpter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.framework.BaseRVAdapter;
import com.example.net.Confing;
import com.example.net.bean.ClothesBean;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public class Clother_RAdpter extends BaseRVAdapter<ClothesBean.ResultBean.HotProductListBean> {

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_re_fen;
    }

    @Override
    protected void convert(ClothesBean.ResultBean.HotProductListBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.iv_re);
        Glide.with(baseViewHolder.itemView.getContext()).load(Confing.BASE_IMAGE +itemData.getFigure()).into(imageView);
        TextView textView = baseViewHolder.getView(R.id.tv_re);
        textView.setText(""+itemData.getCover_price());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
