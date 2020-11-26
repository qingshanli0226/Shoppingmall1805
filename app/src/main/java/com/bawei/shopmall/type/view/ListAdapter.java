package com.bawei.shopmall.type.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shopmall.bawei.shopmall1805.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {
    private List<String> list = new ArrayList<>();
    private Context context;
    private int selected = 0;

    public ListAdapter(Context context,List<String> list){
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = View.inflate(context, R.layout.item_type,null);
            holder = new ViewHolder();
            holder.tv_name = convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(list.get(position));
        if(selected == position){
            convertView.setBackgroundResource(R.drawable.type_item_background_selector);
            holder.tv_name.setTextColor(Color.parseColor("#fd3f3f"));
        } else {
            convertView.setBackgroundResource(R.drawable.bg2);
            holder.tv_name.setTextColor(Color.parseColor("#323437"));
        }


        return convertView;
    }


    public void changeSelected(int position){
        if(position != selected){
            selected = position;
            notifyDataSetChanged();
        }
    }


    class ViewHolder {
        private TextView tv_name;
    }

}
