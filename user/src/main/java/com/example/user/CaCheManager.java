package com.example.user;

import android.content.Context;

public class CaCheManager {
    public static CaCheManager caCheManager;
    private Context context;


    public CaCheManager(){

    }

    public static CaCheManager getInstance(){
           if (caCheManager==null){
               caCheManager=new CaCheManager();
           }
           return caCheManager;
    }

    public void init(Context context){
        this.context=context;
//        iniReceiver();
    }

//    private void iniReceiver() {
//        IntentFilter intentFilter = new IntentFilter();
//    }

//    private void getShopcarDataFromSever(){
//
//    }


}
