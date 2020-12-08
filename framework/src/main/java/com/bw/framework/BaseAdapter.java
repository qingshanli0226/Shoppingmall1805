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

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.BaseViewHoder> {

    private List<T> dataList = new ArrayList<>();
    private IBaseRecyclerViewListener iBaseRecyclerViewListener;
    public void updataData(List<T> data) {
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View rootView = layoutInflater.inflate(getLayoutId(viewType), viewGroup,false);
        return new BaseViewHoder(rootView);
    }

    protected abstract int getLayoutId(int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseViewHoder baseViewHoder, final int position) {
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

    protected abstract void convert(BaseViewHoder baseViewHoder, int viewType,T t);

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public abstract int getViewType(int position);

    @Override
    public int getItemViewType(int position) {
        return getViewType(position);
    }

    public static class BaseViewHoder extends RecyclerView.ViewHolder {
        SparseArray<View> viewSparseArray = new SparseArray<>();

        public BaseViewHoder(View rootView) {
            super(rootView);
        }

        //通过该方法可以取itemView里面子控件
        public <V extends View> V getView(@IdRes int id) {//做了内存优化，避免花费大量时间生成的子控件，直接被丢弃，我们可以先暂时存储一下，下次再用时直接在集合中获取就可以了
            if (viewSparseArray.get(id) == null) {
                V childView = itemView.findViewById(id);
                viewSparseArray.put(id, childView);
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
