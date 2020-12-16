package com.shopmall.bawei.order;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/me/order")
public class OrderActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();



        SharedPreferences dia1 = getSharedPreferences("dia", MODE_PRIVATE);
        boolean dis = dia1.getBoolean("dis", false);

        if (dis){
            Toast.makeText(this, "2525", Toast.LENGTH_SHORT).show();
        }else {
            dialogShow();
        }

    }

    private void dialogShow() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.icon_more);
        builder.setTitle("设置地址");
        builder.setMessage("请设置收货地址");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(OrderActivity.this, "跳转设置收货地址页面", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(OrderActivity.this,TakeActivity.class));
                SharedPreferences dia = getSharedPreferences("dia", MODE_PRIVATE);
                SharedPreferences.Editor edit = dia.edit();
                edit.putBoolean("dis",true);
                edit.commit();
            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(OrderActivity.this, "取消跳转", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);
    }
}