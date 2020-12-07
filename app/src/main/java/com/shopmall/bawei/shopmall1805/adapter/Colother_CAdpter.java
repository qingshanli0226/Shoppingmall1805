package com.shopmall.bawei.shopmall1805.adpter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.net.Confing;
import com.example.net.bean.ClothesBean;
import com.shopmall.bawei.framework.example.framework.BaseRVAdapter;
import com.shopmall.bawei.shopmall1805.R;

public class Colother_CAdpter extends BaseRVAdapter<ClothesBean.ResultBean.ChildBean> {

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_ch_fen;
    }

    @Override
    protected void convert(ClothesBean.ResultBean.ChildBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.iv_ch);
        Glide.with(baseViewHolder.itemView.getContext()).load(Confing.BASE_IMAGE+itemData.getPic()).into(imageView);
        TextView textView = baseViewHolder.getView(R.id.tv_ch);
        textView.setText(""+itemData.getName());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
