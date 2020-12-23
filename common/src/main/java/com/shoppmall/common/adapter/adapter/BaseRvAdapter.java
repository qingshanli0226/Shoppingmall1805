package com.shoppmall.common.adapter.adapter;

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
    private List<T> dataList=new ArrayList<>();
    private IBaseRecyclerViewListener iBaseRecyclerViewListener;
    public void updataData(List<T> data){
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }
    public void removeData(List<T> data){
        dataList.remove(data);
        notifyDataSetChanged();
    }
    public void addData(List<T> data){
        dataList.addAll(data);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(getLayoutId(viewType), parent, false);
        return new BaseViewHolder(view);
    }

    protected abstract int getLayoutId(int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHoder, final int position) {
        baseViewHoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iBaseRecyclerViewListener!=null) {
                    iBaseRecyclerViewListener.onItemClick(position);//当用户点击了列表的ItemView后，要回调接口
                }
            }
        });
        convert(baseViewHoder, getViewType(position),dataList.get(position));
    }

    protected abstract void convert(BaseViewHolder baseViewHoder, int viewType,T t);

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getViewType(position);
    }

    public abstract int getViewType(int position);

    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        SparseArray<View> viewSparseArray=new SparseArray<>();
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public <V extends View> V getView(@IdRes int id) {
            if(viewSparseArray.get(id)==null){
                V childView=itemView.findViewById(id);
                viewSparseArray.put(id,childView);
            }
            return (V)viewSparseArray.get(id);
        }
    }
    public void setRecyclerViewListener(IBaseRecyclerViewListener listener){
        this.iBaseRecyclerViewListener=listener;
    }
    public interface IBaseRecyclerViewListener{
        void onItemClick(int position);
    }
}
