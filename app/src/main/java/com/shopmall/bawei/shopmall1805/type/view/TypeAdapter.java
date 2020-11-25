package com.shopmall.bawei.shopmall1805.type.view;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shopmall.bawei.framework.BaseRvAdapter;
import com.shopmall.bawei.net.mode.TypeBean;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public class TypeAdapter extends BaseRvAdapter<Object> {
    private final int HOT = 0;
    private final int ORDINARY = 1;

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

    private void displayOrdinary(Object itemData, BaseViewHolder baseViewHolder) {
        List<TypeBean.ResultBean.ChildBean> childBeans = (List<TypeBean.ResultBean.ChildBean>) itemData;
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

    private void displayHot(Object itemData, BaseViewHolder baseViewHolder) {
        List<TypeBean.ResultBean.HotProductListBean> hots = (List<TypeBean.ResultBean.HotProductListBean>) itemData;
        RecyclerView rvOrdinary = baseViewHolder.getView(R.id.rv_hot_right);
        HotAdapter hotAdapter = new HotAdapter();
        rvOrdinary.setLayoutManager(new LinearLayoutManager(baseViewHolder.itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        rvOrdinary.setAdapter(hotAdapter);
        hotAdapter.updateData(hots);
        hotAdapter.setIRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {

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
