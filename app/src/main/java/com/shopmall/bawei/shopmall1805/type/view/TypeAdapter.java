package com.shopmall.bawei.shopmall1805.type.view;

import com.shopmall.bawei.framework.BaseRvAdapter;
import com.shopmall.bawei.net.TypeBean;
import com.shopmall.bawei.shopmall1805.R;

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

    }

    private void displayHot(Object itemData, BaseViewHolder baseViewHolder) {

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
