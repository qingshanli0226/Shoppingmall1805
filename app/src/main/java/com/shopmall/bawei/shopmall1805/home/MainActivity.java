package com.shopmall.bawei.shopmall1805.home;

import android.Manifest;
import android.os.Build;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.framework.BaseActivity;
import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.framework.IPresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.framework.ShopUserManager;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.view.fragment.HomeFragment;
import com.shopmall.bawei.shopmall1805.home.view.fragment.MineFragment;
import com.shopmall.bawei.shopmall1805.home.view.fragment.ShopcarFragment;
import com.shopmall.bawei.shopmall1805.home.view.fragment.TypeFragment;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity<IPresenter, IView> {

    private Fragment[] fragments = new Fragment[] {
            new HomeFragment(),
            new TypeFragment(),
            new MineFragment(),
            new ShopcarFragment(),
            new MineFragment()
    };
    private List<Fragment> list = new ArrayList<>();

    private Fragment currentFragment;

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {

        ARouter.getInstance().inject(this);

        list.add(new HomeFragment());
        list.add(new TypeFragment());
        list.add(new MineFragment());
        list.add(new ShopcarFragment());
        list.add(new MineFragment());
        RadioGroup rg = findViewById(R.id.rg);
        switchFragment(0);
        initPermission();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb1:
                        switchFragment(0);
                        break;
                    case R.id.rb2:
                        switchFragment(1);
                        break;
                    case R.id.rb3:
                        switchFragment(2);
                        break;
                    case R.id.rb4:
                        if (!ShopUserManager.getInstance().isUserLogin()) {//如果当前用户没有登录，则跳转到登录界面
                            Toast.makeText(MainActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                            //通过key来表明，从哪个地方进入到登录页面的，方便我们登录成功时，根据这个key做具体的跳转
                            ARouter.getInstance().build("/usr/LoginRegisterActivity").navigation();//跳转到loginActivity
                            //finish();
                        }else {
                            switchFragment(3);
                        }
                        break;
                    case R.id.rb5:
                        switchFragment(4);
                        break;
                }
            }
        });

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
    protected void initPresenter() {

    }
    private void switchFragment(int index) {
        Fragment fragment = list.get(index);
        if (currentFragment == fragment){
            return;
        }

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if (currentFragment!=null){
            fragmentTransaction.hide(currentFragment);
        }
        if (fragment.isAdded()){
            fragmentTransaction.show(fragment).commit();
        }else {
            fragmentTransaction.add(R.id.frameLayoutId,fragment,fragment.getClass().getSimpleName()).commit();
        }
        currentFragment = fragment;
    }
}
