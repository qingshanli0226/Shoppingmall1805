package com.shopmall.bawei.shopmall1805.apter.apter2;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

import bean.typebean.BugBean;

public class BugChangApter extends BaseRVAdapter<BugBean.ResultBean.ChildBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.skirtlayout;
    }

    @Override
    protected void convert(BugBean.ResultBean.ChildBean itemData, BaseViewHolder baseViewHolder, int position) {
          Glide.with(baseViewHolder.itemView.getContext()).load("http://49.233.0.68:8080/atguigu/img/"+itemData.getPic()).into((ImageView)baseViewHolder.getView(R.id.reimage));
        TextView view = baseViewHolder.getView(R.id.retext);
        view.setText(itemData.getName()+"");
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
//    public BugChangApter(int layoutResId, @Nullable List<BugBean.ResultBean.ChildBean> data) {
//        super(layoutResId, data);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, BugBean.ResultBean.ChildBean item) {
//        Log.e("==========","22222222222");
//        Log.e("image",item.getPic());
//        Glide.with(mContext).load("http://49.233.0.68:8080/atguigu/img/"+item.getPic()).into((ImageView)helper.getView(R.id.reimage));
//        helper.setText(R.id.retext,item.getName());
//    }
}
