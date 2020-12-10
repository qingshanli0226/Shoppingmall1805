package com.example.elevenmonthshoppingproject.home.view;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.common.ShopMallContants;
import com.example.elevenmonthshoppingproject.R;
import com.example.framwork.BaseRVAdapter;
import com.example.net.bean.HomeBean;

public class ActAdapter extends BaseRVAdapter<HomeBean.ActInfoBean> {
    @Override
    protected int getLayoutid(int viewtype) {
        return R.layout.act_layout;
    }

    @Override
    protected int getViewtype(int postion) {
        return 0;
    }

    @Override
    protected void cover(final BaseViewHoder holder, int viewtype, final HomeBean.ActInfoBean actInfoBean) {
        Glide.with(holder.itemView.getContext()).load(ShopMallContants.Gui_Url+actInfoBean.getIcon_url()).into((ImageView) holder.getView(R.id.img_actpic4));
//        holder.getView(R.id.img_actpic4).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(holder.itemView.getContext(), ShowShoppingActivity.class);
//                intent.putExtra("actimg",Contants.Gui_Url+actInfoBean.getIcon_url());
//                holder.itemView.getContext().startActivity(intent);
//            }
//        });
    }
}
