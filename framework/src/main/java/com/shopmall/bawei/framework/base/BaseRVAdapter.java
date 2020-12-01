package com.shopmall.bawei.framework.base;

import android.annotation.SuppressLint;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public abstract class BaseRVAdapter<T> extends RecyclerView.Adapter<BaseRVAdapter.BaseViewHolder> {
    private IRecyclerViewItemClickListener iRecyclerViewItemClickListener;
    private ArrayList<T> dataList = new ArrayList<>();

    public void updataData(List<T> datas) {
        if (datas== null) {
            return;
        }
        dataList.clear();
        dataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void removeOneData(T dataBean) {
        dataList.remove(dataBean);
        notifyDataSetChanged();
    }

    public void addOneData(T dataBean) {
        dataList.add(dataBean);
        notifyDataSetChanged();
    }

    @NonNull
    @Override

    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(viewType), viewGroup, false);
        return new BaseViewHolder(rootView);
    }

    protected abstract int getLayoutId(int viewType);//让子类根据viewType类型返回指定的布局文件

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, @SuppressLint("RecyclerView") final int position) {
        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iRecyclerViewItemClickListener!=null) {
                    iRecyclerViewItemClickListener.onItemClick(position);//设置Item的点击事件
                }
            }
        });

        T itemData = getItemData(position);
        convert(itemData, baseViewHolder, position);//通过position，将itemData转换成需要的类型，并且将baseViewHoder也转换成需要的viewHolder

    }

    protected abstract void convert(T itemData, BaseViewHolder baseViewHolder, int position);

    public T getItemData(int position) {
        return dataList.get(position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public IRecyclerViewItemClickListener getiRecyclerViewItemClickListener() {
        return iRecyclerViewItemClickListener;
    }

    public void setiRecyclerViewItemClickListener(IRecyclerViewItemClickListener iRecyclerViewItemClickListener) {
        this.iRecyclerViewItemClickListener = iRecyclerViewItemClickListener;
    }


    public static class BaseViewHolder extends RecyclerView.ViewHolder {

        HashMap<Integer, View> viewHashMap = new HashMap<>();

        public BaseViewHolder(View rootView) {
            super(rootView);
        }


        public <V extends View> V getView(@IdRes int id) {
            View view = viewHashMap.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                viewHashMap.put(id, view);
            }

            return (V)view;
        }
    }

    public interface IRecyclerViewItemClickListener {
        void onItemClick(int position);
    }


    @Override
    public int getItemViewType(int position) {
        return getViewType(position);
    }



    protected abstract int getViewType(int position);
}
