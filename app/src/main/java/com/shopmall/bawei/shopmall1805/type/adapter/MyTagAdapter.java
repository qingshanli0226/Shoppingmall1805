package com.shopmall.bawei.shopmall1805.type.adapter;

import android.widget.TextView;

import com.bw.net.bean.TagBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.base.BaseAdapter;

public class MyTagAdapter extends BaseAdapter<TagBean.ResultBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_tag;
    }

    @Override
    protected void convert(BaseViewHoder baseViewHoder, int viewType, TagBean.ResultBean resultBean) {
        TextView textView = baseViewHoder.itemView.findViewById(R.id.tagTitle);
        textView.setText(resultBean.getName());
    }

    @Override
    public int getViewType(int position) {
        return position;
    }
}
