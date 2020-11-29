package com.shopmall.bawei.framework;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//定义一个万能适配器,支持多布局的，可以适配不同类型的数据，又能适配不同ItemView
public abstract class BaseRvAdapter<T> extends RecyclerView.Adapter<BaseRvAdapter.BaseViewHolder> {
    private IRecyclerViewItemClickListener iRecyclerViewItemClickListener;
    private ArrayList<T> dataList = new ArrayList<>();

    public void updateData(List<T> data){
        if(data == null){
            return;
        }
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }

//    public void replaceData(T dataBean){
//        dataList.clear();
//        dataList.add(dataBean);
//        notifyDataSetChanged();
//    }

    public void clearData(){
        dataList.clear();
    }

    public void removeOneData(T dataBean){
        dataList.remove(dataBean);
        notifyDataSetChanged();
    }

    public void addOneData(T dataBean){
        dataList.add(dataBean);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //因为需要适配不同的布局，所以需要提供一个抽象方法，让子类把布局文件传递过来
        View rootView = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType),parent,false);
        return new BaseViewHolder(rootView);
    }

    protected abstract int getLayoutId(int viewType);//让子类根据viewType类型返回指定的布局文件

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iRecyclerViewItemClickListener != null){
                    iRecyclerViewItemClickListener.onItemClick(position);//设置Item的点击事件
                }
            }
        });

        T itemData = getItemData(position);
        convert(itemData,holder,position);;//通过position，将itemData转换成需要的类型，并且将baseViewHolder也转换成需要的viewHolder
    }
    //需要子类来，渲染UI
    protected abstract void convert(T itemData,BaseViewHolder baseViewHolder,int position);

    public T getItemData(int position){
        return dataList.get(position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public IRecyclerViewItemClickListener getIRecyclerViewItemClickListener(){
        return iRecyclerViewItemClickListener;
    }

    public void setIRecyclerViewItemClickListener(IRecyclerViewItemClickListener iRecyclerViewItemClickListener){
        this.iRecyclerViewItemClickListener = iRecyclerViewItemClickListener;
    }


    //定义一个ViewHolder，可以适配不同的UI
    public static class BaseViewHolder extends RecyclerView.ViewHolder{
        //Integer是id，View是控件，所有的控件的父类都是View,使用HashMap来存储这些Item里的所有View.findViewById,是比较耗时的
        HashMap<Integer, View> viewHashMap = new HashMap<>();
        public BaseViewHolder(View rootView) {
            super(rootView);
        }

        //泛型方法，可以通过它获取view，并且强制转换成需要的view类型,可以参考系统的findViewById
        public <V extends View> V getView(int id){
            View view = viewHashMap.get(id);
            if(view == null){
                view = itemView.findViewById(id);
                viewHashMap.put(id,view);
            }

            return (V)view;
        }
    }
    public interface  IRecyclerViewItemClickListener{
        void onItemClick(int position);
    }
    //通过这个position来返回一种对应的布局类型，让子类来指定
    @Override
    public int getItemViewType(int position) {
        return getViewType(position);
    }
    //让子类来实现
    protected abstract int getViewType(int position);
}
