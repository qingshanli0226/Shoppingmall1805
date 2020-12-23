package com.shopmall.bawei.shopmall1805.type.list;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.net.Constants;
import com.example.net.bean.GoodsBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shoppmall.common.adapter.adapter.BaseRvAdapter;

public class HotGoodAdapter extends BaseRvAdapter<GoodsBean.ResultBean.HotProductListBean> {
    private Context context;

    public HotGoodAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_ordinary_right;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHoder, int viewType, GoodsBean.ResultBean.HotProductListBean hotProductListBean) {
        ImageView iv = baseViewHoder.getView(R.id.iv_ordinary_right);
        TextView tv = baseViewHoder.getView(R.id.tv_ordinary_right);
        Glide.with(context).load(Constants.BASE_URl_IMAGE+hotProductListBean.getFigure()).into(iv);
        tv.setText("¥"+hotProductListBean.getCover_price());
        tv.setTextColor(Color.RED);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/detailpage/DetailActivity").withSerializable("good",hotProductListBean).withString("type","product").navigation();
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/detailpage/DetailActivity").withSerializable("good",hotProductListBean).withString("type","product").navigation();
            }
        });
    }

    @Override
    public int getViewType(int position) {
        return 0;
    }
}
