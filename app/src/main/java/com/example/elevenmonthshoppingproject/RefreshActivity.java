package com.example.elevenmonthshoppingproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RefreshActivity extends AppCompatActivity implements RefreshLayout.IRefreshListerner{



    private ListAdapter listAdapter;
    private RefreshLayout refreshLayout;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_refresh);
        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.addIRefreshListerner(this);

        initListView();
    }

    private void initListView() {
        ListView listView = findViewById(R.id.listView);
        listAdapter = new ListAdapter();
        listView.setAdapter(listAdapter);
        for(int i =0; i<50;i++) {
            data.add(100+i+"");
        }

        listAdapter.notifyDataSetChanged();

    }

    @Override
    public void onRefresh() {
        Toast.makeText(this, "启动网络请求，加载数据", Toast.LENGTH_SHORT).show();
        for(int i =0; i<50;i++) {
            data.add(0, i+"");
        }

        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefreshComplete() {
    }


    private List<String> data = new ArrayList<>();
    private class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(RefreshActivity.this).inflate(R.layout.item_layout, parent, false);
            TextView textView = view.findViewById(R.id.textTv1);
            textView.setText(data.get(position));
            return view;
        }
    }
}
