package com.example.elevenmonthshoppingproject.home.view;



import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.ShopMallContants;
import com.example.elevenmonthshoppingproject.R;
import com.example.framwork.BaseRVAdapter;
import com.example.net.bean.HomeBean;

public class ChannelAdapter extends BaseRVAdapter<HomeBean.ChannelInfoBean> {
    @Override
    protected int getLayoutid(int viewtype) {
        return R.layout.channel_layout;
    }

    @Override
    protected int getViewtype(int postion) {
        return 0;
    }

    @Override
    protected void cover(final BaseViewHoder holder, int viewtype, final HomeBean.ChannelInfoBean channelInfoBean) {
        ImageView channelimg = holder.getView(R.id.channelImg);
        TextView txtchannelname = holder.getView(R.id.txt_channelname);
        txtchannelname.setText(channelInfoBean.getChannel_name());
        Glide.with(holder.itemView.getContext()).load(ShopMallContants.Gui_Url+channelInfoBean.getImage()).into(channelimg);
//        holder.getView(R.id.channelImg).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(holder.itemView.getContext(), ShowShoppingActivity.class);
//                intent.putExtra("channelimg",Contants.Gui_Url+channelInfoBean.getImage());
//                holder.itemView.getContext().startActivity(intent);
//            }
//        });

}
}
