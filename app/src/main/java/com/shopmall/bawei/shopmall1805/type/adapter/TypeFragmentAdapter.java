package com.shopmall.bawei.shopmall1805.type.adapter;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bw.framework.BaseAdapter;
import com.bw.net.bean.SkirtBean;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public class TypeFragmentAdapter extends BaseAdapter<Object> {
    @Override
    protected int getLayoutId(int viewType) {

        switch (viewType){
            case 0:
              return R.layout.item_refer;
            case 1:
                return R.layout.item_userfal;

        }
        return R.layout.item_refer;
    }

    @Override
    protected void convert(BaseViewHoder baseViewHoder, int viewType, Object o) {
        GridLayoutManager linearLayoutManager = new GridLayoutManager(baseViewHoder.itemView.getContext(),3);
        switch (viewType){
            case 0:

                List<SkirtBean.ResultBean.HotProductListBean> hotProductListBeans = (List<SkirtBean.ResultBean.HotProductListBean>) o;
                RecyclerView referRv = baseViewHoder.itemView.findViewById(R.id.rv_refer);
                MyReferAdapter myReferAdapter = new MyReferAdapter();
                myReferAdapter.updataData(hotProductListBeans);
                referRv.setAdapter(myReferAdapter);
                referRv.setLayoutManager(linearLayoutManager);
                myReferAdapter.notifyDataSetChanged();
                break;
            case 1:

                RecyclerView userfalRv = baseViewHoder.itemView.findViewById(R.id.rv_userfal);
                List<SkirtBean.ResultBean.ChildBean> childBeans = (List<SkirtBean.ResultBean.ChildBean>) o;
                MyUserfalAdapter myUserfalAdapter = new MyUserfalAdapter();
                myUserfalAdapter.updataData(childBeans);
                userfalRv.setAdapter(myUserfalAdapter);
                userfalRv.setLayoutManager(linearLayoutManager);
                myUserfalAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public int getViewType(int position) {
        return position;
    }
}
