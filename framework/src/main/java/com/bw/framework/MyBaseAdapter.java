package com.bw.framework;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public abstract class MyBaseAdapter<T> extends RecyclerView.Adapter<MyBaseAdapter.MyBaseViewHolder> {

    private List<T> list = new ArrayList<>();
    private MyBaseRecyclerViewListener myBaseRecyclerViewListener;


    public void updataData(List<T> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyBaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View rootView = layoutInflater.inflate(getLayoutId(viewType), null);
        return new MyBaseViewHolder(rootView);
    }

    protected abstract int getLayoutId(int viewType);


    @Override
    public void onBindViewHolder(@NonNull MyBaseViewHolder myBaseViewHolder, final int i) {
        myBaseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myBaseRecyclerViewListener != null){
                    myBaseRecyclerViewListener.onItemClick(i);
                }
            }
        });
        convert(myBaseViewHolder,getViewType(i),list.get(i));
    }

    protected abstract void convert(MyBaseViewHolder myBaseViewHolder, int viewType, T t);

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getViewType(position);
    }

    protected abstract int getViewType(int position);

    public static class MyBaseViewHolder extends RecyclerView.ViewHolder{

        SparseArray<View> viewSparseArray = new SparseArray<>();

        public MyBaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public <V extends View> V getView(@IdRes int id){
            if (viewSparseArray.get(id) == null){
                V childView = itemView.findViewById(id);
                viewSparseArray.put(id,childView);
            }
            return (V) viewSparseArray.get(id);
        }
    }

    public void setMyBaseRecyclerViewListener(MyBaseRecyclerViewListener listener){
        this.myBaseRecyclerViewListener = listener;
    }


    public interface MyBaseRecyclerViewListener {
        void onItemClick(int position);
    }
}
