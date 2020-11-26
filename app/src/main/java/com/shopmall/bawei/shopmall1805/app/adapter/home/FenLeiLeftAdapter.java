package com.shopmall.bawei.shopmall1805.app.adapter.home;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public class FenLeiLeftAdapter extends RecyclerView.Adapter<FenLeiLeftAdapter.MyHolder> {

    private List<String> list;
    private Context context;

    public FenLeiLeftAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_fen_left, null);
        return new MyHolder(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.tv.setText(list.get(position));
        holder.tv.setGravity(View.TEXT_ALIGNMENT_CENTER);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getListener.onclick(position);
                notifyDataSetChanged();
            }
        });
        if(position == getmPosition()){
            holder.tv.setTextColor(Color.RED);
            holder.itemView.setBackgroundColor(Color.WHITE);
        }else {
            holder.tv.setTextColor(Color.BLACK);
            holder.itemView.setBackgroundColor(Color.parseColor("#E8E6E6"));
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.fen_left_item_tv);
        }
    }
    public interface GetListener{
        void onclick(int position);
    }
    private GetListener getListener;

    public void setGetListener(GetListener getListener) {
        this.getListener = getListener;
    }
    private int mPosition;

    public int getmPosition() {
        return mPosition;
    }
    public void setmPosition(int mPosition){
            this.mPosition = mPosition;
    }
}
