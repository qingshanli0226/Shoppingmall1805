package com.shopmall.bawei.shopmall1805.type.tag;

import android.graphics.Color;
import android.widget.TextView;

import com.example.net.bean.TagBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shoppmall.common.adapter.adapter.BaseRvAdapter;

import java.util.ArrayList;
import java.util.List;

public class TagAdapter extends BaseRvAdapter<TagBean.ResultBean> {
    private List<Integer> colors= new ArrayList<>();
    @Override
    protected int getLayoutId(int viewType) {
        colors.add(Color.parseColor("#123456"));
        colors.add(Color.parseColor("#abc789"));
        colors.add(Color.parseColor("#000ddd"));
        colors.add(Color.parseColor("#aaa123"));
        colors.add(Color.parseColor("#ff00ff"));
        colors.add(Color.parseColor("#a1d2c3"));
        colors.add(Color.parseColor("#0000ff"));
        colors.add(Color.parseColor("#00aa00"));
        colors.add(Color.parseColor("#00cc0a"));
        return R.layout.item_tab_rv;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHoder, int viewType, TagBean.ResultBean resultBean) {
        TextView tv = baseViewHoder.getView(R.id.tv_tag);
        tv.setText(resultBean.getName()+"");
        int v = (int) (Math.random() * colors.size());
        tv.setTextColor(colors.get(v));

    }

    @Override
    public int getViewType(int position) {
        return 0;
    }
}
