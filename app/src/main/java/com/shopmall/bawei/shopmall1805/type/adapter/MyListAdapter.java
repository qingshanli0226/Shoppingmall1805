package com.shopmall.bawei.shopmall1805.type.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shopmall.bawei.shopmall1805.R;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MyListAdapter extends BaseAdapter {

    private List<String> list;
    private Context context;

    public MyListAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
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
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list,null);
            viewHolder.textView = convertView.findViewById(R.id.list_title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position));
        return convertView;
    }

    private class ViewHolder {
        private TextView textView;
    }
}
