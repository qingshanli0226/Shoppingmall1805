package com.shopmall.bawei.shopmall1805.activity;

import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.framework.BaseActivity;
import com.bw.framework.IPresenter;
import com.bw.framework.IView;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.fragment.CardFragment;
import com.shopmall.bawei.shopmall1805.fragment.FindFragment;
import com.shopmall.bawei.shopmall1805.home.HomeFragment;
import com.shopmall.bawei.shopmall1805.type.TypeFragment;
import com.shopmall.bawei.shopmall1805.fragment.UserFragment;

public class MainActivity extends BaseActivity<IPresenter, IView> {

    private FrameLayout frameLayout;
    private RadioGroup group;
    private RadioButton rd1,rd2,rd3,rd4,rd5;
    private HomeFragment homeFragment;
    private FindFragment findFragment;
    private TypeFragment typeFragment;
    private UserFragment userFragment;
    private CardFragment cardFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();

        ARouter.getInstance().inject(this);

        frameLayout = findViewById(R.id.frame_layout);
        group = findViewById(R.id.group);
        rd1 = findViewById(R.id.rd1);
        rd2 = findViewById(R.id.rd2);
        rd3 = findViewById(R.id.rd3);
        rd4 = findViewById(R.id.rd4);
        rd5 = findViewById(R.id.rd5);

        homeFragment = new HomeFragment();
        typeFragment = new TypeFragment();
        findFragment = new FindFragment();
        cardFragment = new CardFragment();
        userFragment = new UserFragment();

         getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, homeFragment)
                .add(R.id.frame_layout, typeFragment)
                .add(R.id.frame_layout, findFragment)
                 .add(R.id.frame_layout, cardFragment)
                .add(R.id.frame_layout, userFragment).commit();

         getSupportFragmentManager().beginTransaction().show(homeFragment)
                 .hide(typeFragment)
                 .hide(findFragment)
                 .hide(cardFragment)
                 .hide(userFragment).commit();

        group.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.rd1:
                    getSupportFragmentManager().beginTransaction().show(homeFragment)
                            .hide(typeFragment)
                            .hide(findFragment)
                            .hide(cardFragment)
                            .hide(userFragment).commit();
                    break;
                case R.id.rd2:
                    getSupportFragmentManager().beginTransaction().show(typeFragment)
                            .hide(homeFragment)
                            .hide(findFragment)
                            .hide(cardFragment)
                            .hide(userFragment).commit();
                    break;
                case R.id.rd3:
                    getSupportFragmentManager().beginTransaction().show(findFragment)
                            .hide(typeFragment)
                            .hide(homeFragment)
                            .hide(cardFragment)
                            .hide(userFragment).commit();
                    break;
                case R.id.rd4:
                    getSupportFragmentManager().beginTransaction().show(cardFragment)
                            .hide(typeFragment)
                            .hide(findFragment)
                            .hide(homeFragment)
                            .hide(userFragment).commit();
//                    ARouter.getInstance().build("/activity/activity_shopCart").navigation();
                    break;
                case R.id.rd5:
                    getSupportFragmentManager().beginTransaction().show(userFragment)
                            .hide(typeFragment)
                            .hide(homeFragment)
                            .hide(cardFragment)
                            .hide(findFragment).commit();
                    break;
            }
        });

    }

}
