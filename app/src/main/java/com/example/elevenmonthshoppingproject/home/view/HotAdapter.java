package com.example.elevenmonthshoppingproject.home.view;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.ShopMallContants;
import com.example.elevenmonthshoppingproject.R;
import com.example.elevenmonthshoppingproject.product.view.ProductDetailActivity;
import com.example.framwork.BaseRVAdapter;
import com.example.net.bean.HomeBean;


public class HotAdapter extends BaseRVAdapter<HomeBean.HotInfoBean> {
    @Override
    protected int getLayoutid(int viewtype) {
        return R.layout.hot_item_layout;
    }

    @Override
    protected int getViewtype(int postion) {
        return 0;
    }

    @Override
    protected void cover(final BaseViewHoder holder, int viewtype, final HomeBean.HotInfoBean hotInfoBean) {
        Glide.with(holder.itemView.getContext()).load(ShopMallContants.Gui_Url+hotInfoBean.getFigure()).into((ImageView) holder.getView(R.id.img_hot));
        TextView txthot = holder.getView(R.id.txt_hot);
        txthot.setText(hotInfoBean.getName()+"");
        ImageView hotimg = holder.getView(R.id.img_hot);
        hotimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
                intent.putExtra("hotid",hotInfoBean.getProduct_id());
                intent.putExtra("hotimg",ShopMallContants.Gui_Url+hotInfoBean.getFigure());
                intent.putExtra("hottxt",hotInfoBean.getName());
                intent.putExtra("hotprice",hotInfoBean.getCover_price());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }
}
