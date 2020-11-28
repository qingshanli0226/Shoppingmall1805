package com.shopmall.bawei.shopmall1805.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bean.SortData;
import com.shopmall.glide.Myglide;

import java.util.List;

public class SortChildAdapter extends BaseQuickAdapter<SortData.ResultBean.ChildBean, BaseViewHolder> {
    public SortChildAdapter(int layoutResId, @Nullable List<SortData.ResultBean.ChildBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SortData.ResultBean.ChildBean item) {
        ImageView view = helper.getView(R.id.child1_image);
        helper.setText(R.id.child1_text,item.getName());
        Myglide.getMyglide().centercenglide(mContext,view, Constants.BASE_URl_IMAGE+item.getPic());
    }
}
