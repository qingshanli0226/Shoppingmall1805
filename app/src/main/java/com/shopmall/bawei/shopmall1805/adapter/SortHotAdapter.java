package com.shopmall.bawei.shopmall1805.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.common.Constants;
import com.shopmall.net.bean.SortData;
import com.shopmall.net.glide.Myglide;

import java.util.List;

public class SortHotAdapter extends BaseQuickAdapter<SortData.ResultBean.HotProductListBean, BaseViewHolder> {
    public SortHotAdapter(int layoutResId, @Nullable List<SortData.ResultBean.HotProductListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SortData.ResultBean.HotProductListBean item) {
        ImageView view = helper.getView(R.id.hot1_image);
        helper.setText(R.id.hot1_text,item.getCover_price());
        Myglide.getMyglide().centercenglide(mContext,view, Constants.BASE_URl_IMAGE+item.getFigure());
    }
}
