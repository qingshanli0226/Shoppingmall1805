package com.shopmall.bawei.shopmall1805.apter.apter2;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

import bean.typebean.BugBean;

public class BugReApter extends BaseQuickAdapter<BugBean.ResultBean.HotProductListBean, BaseViewHolder> {
    public BugReApter(int layoutResId, @Nullable List<BugBean.ResultBean.HotProductListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BugBean.ResultBean.HotProductListBean item) {
        Log.e("==========","1111111111111111111");
        Log.e("images",""+item.getFigure());
        Glide.with(mContext).load("http://49.233.0.68:8080/atguigu/img/"+item.getFigure()).into((ImageView)helper.getView(R.id.jcimage));
        helper.setText(R.id.jctext,item.getCover_price());
    }
}
