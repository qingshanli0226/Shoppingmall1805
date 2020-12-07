package com.shopmall.bawei.shopcar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.MySql;

@Route(path = "/duo/shopcar")
public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcar);
        ARouter.getInstance().inject(this);
        Toast.makeText(this, "?????????", Toast.LENGTH_SHORT).show();

        MySql mySql = new MySql(this);
        SQLiteDatabase db = mySql.getWritableDatabase();
        Cursor shopcar = db.query("shopcar", null, null, null, null, null, null);
        while (shopcar.moveToNext()){
            String name = shopcar.getString(shopcar.getColumnIndex("Name"));
            String path = shopcar.getString(shopcar.getColumnIndex("Path"));
            String money = shopcar.getString(shopcar.getColumnIndex("Money"));
            Log.i("wftt", "onCreate: "+name+path+money);
        }
    }
}