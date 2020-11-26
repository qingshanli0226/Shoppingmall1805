package framework;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract
class BaseRvAdper <T> extends  RecyclerView.Adapter<BaseRvAdper.BaseviewHoder>   {
    private IRecyclerViewItemClickListener iRecyclerViewItemClickListener;//这是一个？接口

    private ArrayList<T> dataList = new ArrayList<>();

    public void updataData(List<T> datas){//修改数据
        if (datas==null){
            return;
        }
        dataList.clear();
        dataList.addAll(datas);
        //万能适配器的刷新数据  理解
        notifyDataSetChanged();
    }
    public void removeOneData(T dataBean){//删除数据
        dataList.remove(dataBean);
        notifyDataSetChanged();
    }
    public void addOneData(T dataBean){
        dataList.add(dataBean);
        notifyDataSetChanged();
    }
    //根据viewType生成一个布局 类型在哪儿？？
    @NonNull
    @Override
    public BaseviewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(getlayourId(i), viewGroup, false);

        return new BaseviewHoder(inflate);
    }

    //传过来的布局
    protected abstract int getlayourId(int i);

    @Override
    public void onBindViewHolder(@NonNull BaseviewHoder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iRecyclerViewItemClickListener !=null){
                    iRecyclerViewItemClickListener.onItemClick(position);
                }
            }


        });
        T itemData = getItemData(position);
        convert(itemData,holder,position);
    }
    //通过position，将itemData转换成需要的类型，并且将baseViewHoder也转换成需要的viewHolder
    //？？
    protected abstract void convert(T itemData, BaseviewHoder holder, int position);

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




    public static class BaseviewHoder extends RecyclerView.ViewHolder{
        HashMap<Integer,View> viewHashMap = new HashMap<>();
        public BaseviewHoder(@NonNull View itemView) {
            super(itemView);
        }

        public <V extends View > V getView(@IdRes int id){
            View view = viewHashMap.get(id);
            if (view == null){
                view = itemView.findViewById(id);
                viewHashMap.put(id,view);
            }
            return (V) view;
        }

    }


    //接口
    public interface IRecyclerViewItemClickListener {
        void onItemClick(int position);
    }


    @Override
    public int getItemViewType(int position) {
        return getViewType(position);
    }

    //子类实现
    protected abstract int getViewType(int position);
}
