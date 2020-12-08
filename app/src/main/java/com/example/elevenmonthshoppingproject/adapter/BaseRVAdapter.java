package com.example.elevenmonthshoppingproject.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * extends自建基类ViewHolder  新建sparry暂存   判断是否空放入集合
 * 实现3方法   创建集合引入数据  实现构造方法调用  初始化  抽象方法 返回给自建holder
 * 绑定holder  （接口方法自定义）   gettypeviewid  getitmetypeview 主要返回布局id
 * @param <T>
 */
public abstract class BaseRVAdapter<T> extends RecyclerView.Adapter<BaseRVAdapter.BaseViewHoder> {

    private List<T> datelist=new ArrayList<>();
    private IBaseRecyclerLinsterner iBaseRecyclerLinsterner;

    public void updatelist(List<T> datelist) {
        this.datelist.clear();
        this.datelist.addAll(datelist);
        notifyDataSetChanged();
    }
//    public void addOneData(T dataBean) {
//        datelist.add(dataBean);
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public BaseViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(getLayoutid(viewType), parent, false);
        return new BaseViewHoder(view);
    }

    protected abstract int getLayoutid(int viewtype);

    @Override
    public void onBindViewHolder(@NonNull BaseViewHoder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iBaseRecyclerLinsterner!=null){
                    iBaseRecyclerLinsterner.onItemclick(position);
                }
            }
        });

        cover(holder,getViewtype(position),datelist.get(position));

    }

    @Override
    public int getItemViewType(int position) {
        return getViewtype(position);
    }

    protected abstract int getViewtype(int postion);

    protected abstract void cover(BaseViewHoder holder, int viewtype, T t);

    @Override
    public int getItemCount() {
        return datelist.size();
    }

    public static class BaseViewHoder extends RecyclerView.ViewHolder{
        SparseArray<View> sparseArray=new SparseArray<>();
        public BaseViewHoder(@NonNull View itemView) {
            super(itemView);
        }
        //暂存 走集合
        public <V extends  View> V getView(@IdRes int id){
            if (sparseArray.get(id)==null){
                V childid = itemView.findViewById(id);
                sparseArray.put(id,childid);
            }
            return (V) sparseArray.get(id);
        }
    }
    //实现点击事件的方法
    public void setBaseRVAdapterlinterner(IBaseRecyclerLinsterner linsterner) {
        this.iBaseRecyclerLinsterner = linsterner;
    }
    //实现接口回调
    public interface IBaseRecyclerLinsterner{
        void onItemclick(int position);
    }
}
