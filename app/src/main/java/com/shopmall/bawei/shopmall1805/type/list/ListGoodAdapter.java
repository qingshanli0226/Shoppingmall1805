package com.shopmall.bawei.shopmall1805.type.list;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.net.bean.GoodsBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shoppmall.common.adapter.adapter.BaseRvAdapter;

import java.util.List;

public class ListGoodAdapter extends BaseRvAdapter<Object> {
    private Context context;
    public ListGoodAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected int getLayoutId(int viewType) {
        switch (viewType) {
            case 0:
                return R.layout.item_hot_right;
            case 1:
                return R.layout.item_goods_right;
        }
        return R.layout.item_hot_right;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHoder, int viewType, Object o) {
        switch (viewType){
            case 0:
                List<GoodsBean.ResultBean.HotProductListBean> hotProductListBeans = (List<GoodsBean.ResultBean.HotProductListBean>) o;
                RecyclerView hotrv = baseViewHoder.getView(R.id.rv_hot_right);
                GridLayoutManager manager = new GridLayoutManager(context,1);
                manager.setOrientation(RecyclerView.HORIZONTAL);
                hotrv.setLayoutManager(manager);
                HotGoodAdapter adapter = new HotGoodAdapter(context);
                hotrv.setAdapter(adapter);
                adapter.addData(hotProductListBeans);
                break;
            case 1:
                List<GoodsBean.ResultBean.ChildBean> childBeans = (List<GoodsBean.ResultBean.ChildBean>) o;
                RecyclerView goodsrv = baseViewHoder.getView(R.id.rv_goods_right);
                goodsrv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                GoodAdapter goodAdapter = new GoodAdapter(context);
                goodsrv.setAdapter(goodAdapter);
                goodAdapter.addData(childBeans);
                break;
        }
    }

    @Override
    public int getViewType(int position) {
        return position;
    }
}
