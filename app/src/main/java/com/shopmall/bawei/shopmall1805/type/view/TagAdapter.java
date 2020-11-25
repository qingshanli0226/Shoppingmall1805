package com.shopmall.bawei.shopmall1805.type.view;

import android.graphics.Color;
import android.widget.TextView;

import com.shopmall.bawei.framework.BaseRvAdapter;
import com.shopmall.bawei.net.TagBean;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public class TagAdapter extends BaseRvAdapter<TagBean.ResultBean> {
    private int[] colors = {Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"),
            Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"), Color.parseColor("#f0a420"),
            Color.parseColor("#f0839a"), Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2")
    };

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_tab_gridview;
    }

    @Override
    protected void convert(TagBean.ResultBean itemData, BaseViewHolder baseViewHolder, int position) {
        TextView tv_tag = baseViewHolder.getView(R.id.tv_tag);
        tv_tag.setText(itemData.getName());
        tv_tag.setTextColor(colors[position % colors.length]);
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
