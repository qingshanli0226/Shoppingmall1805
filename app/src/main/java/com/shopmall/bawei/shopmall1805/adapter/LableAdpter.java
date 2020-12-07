package com.shopmall.bawei.shopmall1805.adpter;

import android.widget.TextView;

import com.example.net.bean.Biaobean;
import com.shopmall.bawei.framework.example.framework.BaseRVAdapter;
import com.shopmall.bawei.shopmall1805.R;

public class LableAdpter extends BaseRVAdapter<Biaobean.ResultBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_tab_gridview;
    }

    @Override
    protected void convert(Biaobean.ResultBean itemData, BaseViewHolder baseViewHolder, int position) {
        TextView textView = baseViewHolder.getView(R.id.tv_tag);
        textView.setText(itemData.getName()+"");
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
