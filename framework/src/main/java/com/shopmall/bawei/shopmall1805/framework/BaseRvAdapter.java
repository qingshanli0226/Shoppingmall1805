package com.shopmall.bawei.shopmall1805.framework;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRvAdapter<T> extends RecyclerView.Adapter<BaseRvAdapter.BaseViewHolder> {
    private List<T> list=new ArrayList<>();
    private IBaseRecyclerViewListener iBaseRecyclerViewListener;

    public void upDataText(List<T> data){
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(getLayoutId(viewType), null);
        return new BaseViewHolder(inflate);
    }
    protected abstract int getLayoutId(int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(iBaseRecyclerViewListener!=null){
                    iBaseRecyclerViewListener.onItemClick(position);
                }
            }
        });
        convert(holder,getViewType(position),list.get(position));
    }

    public abstract void convert(BaseViewHolder holder, int viewType, T t);
    @Override
    public int getItemViewType(int position) {
        return getViewType(position);
    }
    public abstract int getViewType(int position);
    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class BaseViewHolder extends RecyclerView.ViewHolder{
        SparseArray<View> viewSparseArray = new SparseArray<>();
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public <V extends View> V getView(@IdRes int id){
            if(viewSparseArray.get(id) == null){
                V childView = itemView.findViewById(id);
                viewSparseArray.put(id,childView);
            }
            return (V) viewSparseArray.get(id);
        }
    }
    //设置点击事件
    public void setRecyclerViewListener(IBaseRecyclerViewListener listener) {
        this.iBaseRecyclerViewListener = listener;
    }
    //定义点击回调接口
    public interface IBaseRecyclerViewListener {
        void onItemClick(int position);
    }
}
