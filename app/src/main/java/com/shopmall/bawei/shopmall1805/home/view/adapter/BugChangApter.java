package com.shopmall.bawei.shopmall1805.home.view.adapter;


import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.net.typebean.BugBean;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;


public class BugChangApter extends BaseQuickAdapter<BugBean.ResultBean.ChildBean, BaseViewHolder> {
    public BugChangApter(int layoutResId, @Nullable List<BugBean.ResultBean.ChildBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BugBean.ResultBean.ChildBean item) {
        Log.e("==========","22222222222");
        Log.e("image",item.getPic());
        Glide.with(mContext).load("http://49.233.0.68:8080/atguigu/img/"+item.getPic()).into((ImageView)helper.getView(R.id.reimage));
        helper.setText(R.id.retext,item.getName());
    }
}
