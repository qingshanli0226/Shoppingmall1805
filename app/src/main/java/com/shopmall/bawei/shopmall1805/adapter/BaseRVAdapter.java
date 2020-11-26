package com.shopmall.bawei.shopmall1805.adapter;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopmall.bawei.shopmall1805.R;

import java.util.ArrayList;

public abstract class BaseRVAdapter<T> extends RecyclerView.Adapter<BaseRVAdapter.BaseViewHolder> {

    private ArrayList<T> arrayList = new ArrayList<>();
    public IBaserecyClerLinsterner iBaserecyClerLinsterner;

    public void upDataList(ArrayList<T> arrayList) {
        this.arrayList.clear();
        this.arrayList.addAll(arrayList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseRVAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(i), viewGroup, false);
        return new BaseViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutId(position);

    }
    protected abstract int getLayoutId(int position);



    @Override
    public void onBindViewHolder(@NonNull BaseRVAdapter.BaseViewHolder viewHolder, int i) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (iBaserecyClerLinsterner!=null){
                            //实现回调接口
                            iBaserecyClerLinsterner.itemclick();
                        }
                    }
                });

                cover(viewHolder,i,arrayList.get(i));
    }

    protected abstract void cover(RecyclerView.ViewHolder viewHolder, int i, T t);

    @Override
    public int getItemCount() {
        return arrayList.size();
    }




    public class BaseViewHolder extends RecyclerView.ViewHolder{

        SparseArray<T> sparseArray = new SparseArray<>();

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public <V extends View> V getView(@IdRes int  id){

            if (sparseArray.get(id)==null){
                V id1 = itemView.findViewById(id);
                sparseArray.put(id, (T) id1);
            }

            return (V) sparseArray.get(id);
        }
    }

    public void setiBaserecyClerLinsterner(IBaserecyClerLinsterner iBaserecyClerLinsterner){
        this.iBaserecyClerLinsterner=iBaserecyClerLinsterner;
    }

    public interface IBaserecyClerLinsterner{
        void itemclick();
    }

}
