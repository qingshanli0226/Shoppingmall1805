package com.shopmall.bawei.shopmall1805.home;

import android.Manifest;
import android.os.Build;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.framework.BaseActivity;
import com.shopmall.bawei.framework.IPresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.view.HomeFragment;
import com.shopmall.bawei.shopmall1805.type.view.TypeTagFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<IPresenter, IView> implements IView {

    private List<Fragment> fragments = new ArrayList();

    private TypeTagFragment typeTagFragment;


    private int position;
    private RadioGroup rgMain;
    private RadioButton rbHome;
    private RadioButton rbType;
    private RadioButton rbCommunity;
    private RadioButton rbCart;
    private RadioButton rbUser;

    private FragmentManager manager;


    private Fragment mContext;


    private Fragment currentFragment;

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = manager.beginTransaction();
                switch (checkedId){
                    case R.id.rb_home:


                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;
                        break;
                    case R.id.rb_community:
//                        position = 2;
                        break;
                    case R.id.rb_cart:
//                        position = 3;
                        break;
                    case R.id.rb_user:
//                        position = 4;
                        break;

                }
                for (int i = 0; i < fragments.size() ; i++) {
                    if(position == i){
                        transaction.show(fragments.get(position));
                    }else{
                        transaction.hide(fragments.get(i));
                    }
                }
                transaction.commit();
            }

        });
        rgMain.check(R.id.rb_home);
    }


    @Override
    protected void initView() {
        fragments.add(new HomeFragment());
        rgMain = (RadioGroup) findViewById(R.id.rg_main);
        rbHome = (RadioButton) findViewById(R.id.rb_home);
        rbType = (RadioButton) findViewById(R.id.rb_type);
        rbCommunity = (RadioButton) findViewById(R.id.rb_community);
        rbCart = (RadioButton) findViewById(R.id.rb_cart);
        rbUser = (RadioButton) findViewById(R.id.rb_user);
        manager = getSupportFragmentManager();

        initPermission();

        typeTagFragment = new TypeTagFragment();
        fragments.add(typeTagFragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayoutId,fragments.get(0));
        fragmentTransaction.add(R.id.frameLayoutId,fragments.get(1));
        fragmentTransaction.hide(fragments.get(1));
        fragmentTransaction.commit();

    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//判断当前系统版本是不是大于等于23
            Toast.makeText(this, "系统版本大于23，需动态申请权限", Toast.LENGTH_SHORT).show();
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        } else {
            Toast.makeText(this, "系统版本低于23，所以无需动态申请权限", Toast.LENGTH_SHORT).show();
        }//这个就是在代码里做了版本适配(兼容适配),确保了应用程序在15到29之间，动态权限申请不会出现找不到方法的错误

    }

    @Override
    protected void initData() {

    }


    @Override
    public void onError(String msg) {

    }
    @Override
    protected void initPresenter() {

    }

    @Override
    public void showLoaDing() {
        showLoading();
    }


    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess,errorBean);
    }

    @Override
    public void showEmpty() {
        showEmpty();
    }
}
