package com.bawei.shopmall.home;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bawei.framework.BaseActivity;
import com.bawei.framework.IPresenter;
import com.bawei.framework.IView;
import com.bawei.shopmall.home.view.HomeFragment;
import com.bawei.shopmall.type.view.TypeTagFragment;
import com.bawei.shopmall.user.UserFragment;
import com.shopmall.bawei.shopmall1805.R;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity<IPresenter, IView> {

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
                        position = 2;
                        break;
                    case R.id.rb_cart:
                        position = 3;
                        break;
                    case R.id.rb_user:
                        position = 4;
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

        typeTagFragment = new TypeTagFragment();
        fragments.add(typeTagFragment);
        fragments.add(new UserFragment());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayoutId,fragments.get(0));
        fragmentTransaction.add(R.id.frameLayoutId,fragments.get(1));
        fragmentTransaction.add(R.id.frameLayoutId,fragments.get(2));
        fragmentTransaction.hide(fragments.get(1));
        fragmentTransaction.hide(fragments.get(2));
        fragmentTransaction.commit();
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initPresenter() {

    }
}
