package com.shopmall.bawei.shopmall1805.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.common.Constants;
import com.shopmall.net.bean.HomeData;
import com.shopmall.net.glide.Myglide;

import java.util.List;

public class HometitleAdapter extends BaseQuickAdapter<HomeData.ResultBean.ChannelInfoBean, BaseViewHolder> {
    public HometitleAdapter(int layoutResId, @Nullable List<HomeData.ResultBean.ChannelInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeData.ResultBean.ChannelInfoBean item) {
        ImageView view = helper.getView(R.id.home1_iamge);
        Myglide.getMyglide().circenglide(mContext,view, Constants.BASE_URl_IMAGE+item.getImage());
        helper.setText(R.id.home1_text,item.getChannel_name());
    }
}
