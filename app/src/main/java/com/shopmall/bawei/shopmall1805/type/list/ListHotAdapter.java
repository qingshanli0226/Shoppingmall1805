package com.shopmall.bawei.shopmall1805.type.list;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shopmall.bawei.shopmall1805.R;

public class ListHotAdapter extends BaseAdapter {
    private Context mContext;
    private int mSelect = 0;//选中项
    private String[] titles = new String[]{"小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品", "办公文具", "数码周边", "游戏专区"};

    public ListHotAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int i) {
        return titles[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_type, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) view.findViewById(R.id.tv_title);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_name.setText(titles[i]);

        if (mSelect == i) {
            view.setBackgroundResource(R.drawable.type_item_background_selector);  //选中项背景
            holder.tv_name.setTextColor(Color.parseColor("#fd3f3f"));
        } else {
            view.setBackgroundResource(R.drawable.bg2);  //其他项背景
            holder.tv_name.setTextColor(Color.parseColor("#323437"));
        }
        return view;
    }

    public void changeSelected(int positon) { //刷新方法
        if (positon != mSelect) {
            mSelect = positon;
            notifyDataSetChanged();
        }
    }

    static class ViewHolder {
        private TextView tv_name;
    }
}
