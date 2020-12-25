package com.shopmall.bawei.shopmall1805.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.net.BaseBean;
import com.example.net.ConfigUrl;
import com.example.net.FindPayBean;
import com.example.net.HttpRetrofitManager;
import com.example.net.INetPresetenterWork;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.UserPayAdapter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserPayActivity extends AppCompatActivity {

    private RecyclerView recPay;
    private UserPayAdapter userPayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pay);
        initView();

        new HttpRetrofitManager().getHttpRetrofitManager().getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .getFindPay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<List<FindPayBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<List<FindPayBean>> listBaseBean) {
                        Log.i("pay", "onNext: "+listBaseBean.getResult());
                        userPayAdapter = new UserPayAdapter(R.layout.item_pay,listBaseBean.getResult());
                        recPay.setAdapter(userPayAdapter);
                        recPay.setLayoutManager(new LinearLayoutManager(UserPayActivity.this));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        recPay = (RecyclerView) findViewById(R.id.rec_pay);
    }
}