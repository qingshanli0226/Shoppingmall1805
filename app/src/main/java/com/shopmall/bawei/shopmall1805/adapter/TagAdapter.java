package com.shopmall.bawei.shopmall1805.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.net.bean.TagBean;

import java.util.List;
import java.util.Random;

public class TagAdapter extends BaseQuickAdapter<TagBean.ResultBean, BaseViewHolder> {
        private Random random = new Random();
        private int[] colors = {Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"),
                Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"), Color.parseColor("#f0a420"),
                Color.parseColor("#f0839a"), Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2")
        };
    public TagAdapter(int layoutResId, @Nullable List<TagBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TagBean.ResultBean item) {
        helper.setText(R.id.tv_tag,item.getName());
        helper.setTextColor(R.id.tv_tag,colors[random.nextInt(colors.length)]);
    }
}
