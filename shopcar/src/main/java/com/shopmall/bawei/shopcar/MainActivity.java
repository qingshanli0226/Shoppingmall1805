package com.shopmall.bawei.shopcar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.framework.MySql;
import com.example.net.ConfigUrl;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/duo/shopcar")
public class MainActivity extends AppCompatActivity {

    private String path;
    private RecyclerView recyclerView;
    private List<ShopEntity> list = new ArrayList<>();
    private ShopAdapter shopAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcar);

        recyclerView = findViewById(R.id.recyclerview);

        ARouter.getInstance().inject(this);
        Toast.makeText(this, "?????????", Toast.LENGTH_SHORT).show();


        MySql mySql = new MySql(this);
        SQLiteDatabase db = mySql.getWritableDatabase();
        Cursor shopcar = db.query("shopcar", null, null, null, null, null, null);
        while (shopcar.moveToNext()){
            String name = shopcar.getString(shopcar.getColumnIndex("Name"));
             path = shopcar.getString(shopcar.getColumnIndex("Path"));
            String money = shopcar.getString(shopcar.getColumnIndex("Money"));
            list.add(new ShopEntity(name,path,money));
            Log.i("wftt", "onCreate: "+name+path+money);
        }
        shopAdapter = new ShopAdapter(R.layout.item_shop,list);
        recyclerView.setAdapter(shopAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}