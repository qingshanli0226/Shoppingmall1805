package com.shopmall.bawei.shopmall1805.util;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.shopmall.bawei.shopmall1805.R;

public class ToolBarManager {

     private  Toolbar toolbar ;
     private Context context;

    public ToolBarManager(Context context) {
       this.context = context;
    }

    public void initToolBarManager(){
        View inflate = View.inflate(context, R.layout.toolbar, null);
        toolbar = inflate.findViewById(R.id.toolbar);
         toolbar.setTitle("1111111111111");
         toolbar.setTitleTextColor(Color.WHITE);

         toolbar.setNavigationIcon(R.drawable.white_radius);
     }



}
