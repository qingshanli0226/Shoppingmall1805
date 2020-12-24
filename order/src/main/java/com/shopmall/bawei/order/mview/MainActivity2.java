package cn.bw.textprojectone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements RefreshLayout2.IRefreshListener {
    private RefreshLayout2 refreshLayout;
    private ListView listView;
    private MessageAdaper messageAdaper ;
    private ArrayList<String> arrayList = new ArrayList<>();
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);


        refreshLayout = (RefreshLayout2) findViewById(R.id.refreshLayout);
        listView = (ListView) findViewById(R.id.listView);

        refreshLayout.addRefreshListener(this);

        messageAdaper = new MessageAdaper(this,arrayList);
        listView.setAdapter(messageAdaper);
        refreshData(0);
    }

    @Override
    public void onRefresh() {
        refreshData(1);
        Log.i("pppp","上拉刷新11111");
    }

    private void refreshData(int i) {
        if (i==1){
            count =0;
            arrayList.clear();
            for (int c=0;c<20;c++){
                arrayList.add("这个数据是"+count++);
            }
            messageAdaper.notifyDataSetChanged();
        }else {
            for (int c=0;c<20;c++){
                arrayList.add("这个数据是"+count++);
            }
            messageAdaper.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadMore() {
        Log.i("pppp","下拉加载11111");
        refreshData(0);
    }

    @Override
    public void onRefreshComplete() {

    }


}