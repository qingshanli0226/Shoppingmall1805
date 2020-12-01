package com.bawei.shopmall.type.view;

import android.content.Intent;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.framework.BaseRvAdapter;
import com.bawei.net.mode.TypeBean;
import com.bawei.shopmall.details.DetailsGoodsBean;
import com.bawei.shopmall.details.GoodsInfoActivity;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public class TypeAdapter extends BaseRvAdapter<Object> {
    private final int HOT = 0;
    private final int ORDINARY = 1;

    public static final String GOODS_BEAN = "goods_bean";

    @Override
    protected int getLayoutId(int viewType) {
        switch (viewType){
            case HOT:
                return R.layout.item_hot_right;
            case ORDINARY:
                return R.layout.item_ordinary_right;
            default:return R.layout.item_hot_right;
        }
    }

    @Override
    protected void convert(Object itemData, BaseViewHolder baseViewHolder, int position) {
        switch (position){
            case 0:displayHot(itemData,baseViewHolder);break;
            case 1:displayOrdinary(itemData,baseViewHolder);break;
        }
    }

    private void displayOrdinary(Object itemData, final BaseViewHolder baseViewHolder) {
        final List<TypeBean.ResultBean.ChildBean> childBeans = (List<TypeBean.ResultBean.ChildBean>) itemData;
        RecyclerView rvOrdinary = baseViewHolder.getView(R.id.rv_ordinary_right);
        ChildAdapter childAdapter = new ChildAdapter();
        rvOrdinary.setLayoutManager(new GridLayoutManager(baseViewHolder.itemView.getContext(),3,GridLayoutManager.VERTICAL,false));
        rvOrdinary.setAdapter(childAdapter);
        childAdapter.updateData(childBeans);
        childAdapter.setIRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
    }

    private void displayHot(Object itemData, final BaseViewHolder baseViewHolder) {
        final List<TypeBean.ResultBean.HotProductListBean> hots = (List<TypeBean.ResultBean.HotProductListBean>) itemData;
        RecyclerView rvOrdinary = baseViewHolder.getView(R.id.rv_hot_right);
        HotAdapter hotAdapter = new HotAdapter();
        rvOrdinary.setLayoutManager(new LinearLayoutManager(baseViewHolder.itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        rvOrdinary.setAdapter(hotAdapter);
        hotAdapter.updateData(hots);
        hotAdapter.setIRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String cover_price = hots.get(position).getCover_price();
                String name = hots.get(position).getName();
                String figure = hots.get(position).getFigure();
                String product_id = hots.get(position).getProduct_id();
                DetailsGoodsBean goodsBean = new DetailsGoodsBean(name, cover_price, figure, product_id);

                Intent intent = new Intent(baseViewHolder.itemView.getContext(), GoodsInfoActivity.class);
                intent.putExtra(GOODS_BEAN, goodsBean);
                baseViewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    protected int getViewType(int position) {
        switch (position){
            case 0:return HOT;
            case 1:return ORDINARY;
            default:return HOT;
        }
    }
}
