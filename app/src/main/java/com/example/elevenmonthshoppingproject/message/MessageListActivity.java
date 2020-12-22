package com.example.elevenmonthshoppingproject.message;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.elevenmonthshoppingproject.R;
import com.example.framwork.BaseMVPActivity;
import com.example.framwork.IPresenter;
import com.example.framwork.IView;
import com.example.framwork.ShopCarGreen;
import com.example.framwork.sql.MySqlOpenHelper;
import com.example.framwork.view.manager.MessageManager;
import com.example.net.bean.MoneyBean;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/message/MessageListActivity")
public class MessageListActivity extends BaseMVPActivity<IPresenter, IView> {
    private MessageAdapter messageAdapter;
    private MySqlOpenHelper mySqlOpenHelper;
    private   MoneyBean moneyBean;
    private List<MoneyBean> moneyBeans=new ArrayList<>();
    @Override
    protected void iniHttpView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_list;
    }

    @Override
    protected void iniView() {

        mySqlOpenHelper=new MySqlOpenHelper(this,"MoneyHouse.db", null, 2);
        SQLiteDatabase  sqLiteDatabase=mySqlOpenHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("house", null, null, null, null, null, null);

        while (cursor.moveToNext()){
            String price = cursor.getString(cursor.getColumnIndex("price"));
             moneyBean = new MoneyBean(price);
             moneyBeans.add(moneyBean);
        }
        for (int i = 0; i < moneyBeans.size(); i++) {
            Log.i("---",""+moneyBeans.get(i).money);
        }




        RecyclerView messageRv = findViewById(R.id.messageRV);
        messageRv.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter();
        messageRv.setAdapter(messageAdapter);

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {



    }
}
