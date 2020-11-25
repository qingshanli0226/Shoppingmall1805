package com.shopmall.bawei.shopmall1805;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class CallcenterActivity extends AppCompatActivity {
    private WebView web;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callcenter);
        web = findViewById(R.id.web);
        web.loadUrl("https://jdcs.jd.com/index.action?pid=100008348530");
        web.getSettings();
        web.addJavascriptInterface(1,"111");
    }
}
