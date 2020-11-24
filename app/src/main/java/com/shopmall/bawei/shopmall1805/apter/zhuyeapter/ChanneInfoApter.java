package com.shopmall.bawei.shopmall1805.apter.zhuyeapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bean.BaseBean;
import bean.HomeBean;

import static com.bawei.deom.BaseUser.BASE_IMAGE;

public class ChanneInfoApter extends BaseQuickAdapter<HomeBean.ChannelInfoBean, BaseViewHolder> {
    public ChanneInfoApter(int layoutResId, @Nullable List<HomeBean.ChannelInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ChannelInfoBean item) {
        Glide.with(mContext).load("http://49.233.0.68:8080/atguigu/img"+item.getImage()).into((ImageView)helper.getView(R.id.image));
        helper.setText(R.id.texts,item.getChannel_name());
    }


//    @Override
//    protected void convert(BaseViewHolder helper, ArrayList<HomeBean.ChannelInfoBean> item) {
//
//            helper.setText(R.id.texts,item.get(10).getChannel_name());
//
//
//    }
}
