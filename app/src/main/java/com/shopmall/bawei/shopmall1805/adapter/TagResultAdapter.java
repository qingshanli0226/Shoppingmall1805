package com.shopmall.bawei.shopmall1805.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bean.TagData;

import java.util.List;
import java.util.Random;

public class TagResultAdapter extends BaseQuickAdapter<TagData.ResultBean, BaseViewHolder> {
    private Random random=new Random();
    private int[] colors = {Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"),
            Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"), Color.parseColor("#f0a420"),
            Color.parseColor("#f0839a"), Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2")};
    public TagResultAdapter(int layoutResId, @Nullable List<TagData.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TagData.ResultBean item) {
        TextView view = helper.getView(R.id.tag_text);
        view.setTextColor(colors[random.nextInt(colors.length)]);
        helper.setText(R.id.tag_text,item.getName());
    }
}
