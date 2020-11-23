package com.shopmall.bawei.shopmall1805.base;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.ArrayList;
import java.util.HashMap;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {

    private ArrayList<T> dataList = new ArrayList<>();

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(), viewGroup, false);
        return new BaseViewHolder(rootView);
    }

    protected abstract int getLayoutId();


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }




    public static class BaseViewHolder extends RecyclerView.ViewHolder{

        HashMap<Integer,View> viewHashMap = new HashMap<>();

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public <V extends View> V getView(@IdRes int id){

            View view = viewHashMap.get(id);
            if (view == null){
                view = itemView.findViewById(id);
                viewHashMap.put(id,view);
            }
            return (V) view;

        }


    }

}
