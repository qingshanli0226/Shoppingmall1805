package com.shopmall.bawei.shopmall1805.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.shopmall.bawei.shopmall1805.R;

public class CallcenterActivity extends AppCompatActivity {
    private WebView web;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callcenter);
        web = findViewById(R.id.web);
        web.loadUrl("https://www9.53kf.com/webCompany.php?kf_sign=jI3MDMTYwNU3NDEwMzk1MjgwMDY3MDExNzIwMDczNzc=&arg=10007377&style=1&kflist=off&kf=info@atguigu.com,public@atguigu.com,3069368606@qq.com,tingting@atguigu.com,wangya@atguigu.com,zhuchangqing@atguigu.com,wanggang@atguigu.com,mary@atguigu.com,atguigu,leilei@atguigu.com&zdkf_type=1&lnk_overflow=0&callback_id6ds=10021537,10070338,10105298,10178567,10220159,10221744,10224556,10274080,10283523,10283524&language=zh-cn&charset=gbk&referer=http%3A%2F%2Fwww.atguigu.com%2F&keyword=&tfrom=1&tpl=crystal_blue&uid=5c9de77485b46e53e950060141ebbb9d&is_group=&timeStamp=1606270577159&ucust_id=");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
    }
}
