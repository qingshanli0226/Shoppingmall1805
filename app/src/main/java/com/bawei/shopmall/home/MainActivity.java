package com.bawei.shopmall.home;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.common.view.NetConfig;
import com.bawei.framework.BaseActivity;
import com.bawei.framework.CacheManager;
import com.bawei.framework.IPresenter;
import com.bawei.framework.IView;
import com.bawei.framework.ShopUserManager;
import com.bawei.net.mode.ShopcarBean;
import com.bawei.shopcar.ShopcarFragment;
import com.bawei.shopmall.find.FindFragment;
import com.bawei.shopmall.home.view.HomeFragment;
import com.bawei.shopmall.type.view.TypeTagFragment;
import com.bawei.shopmall.user.UserFragment;
import com.shopmall.bawei.shopmall1805.R;

import java.util.ArrayList;
import java.util.List;

@Route(path = NetConfig.MAIN_MAINACTIVITY)
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
    private TextView tv_username;

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
                switch (checkedId) {
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
                for (int i = 0; i < fragments.size(); i++) {
                    if (position == i) {
                        if (position == 3) {
                            if (ShopUserManager.getInstance().isUserLogin()) {
                                transaction.show(fragments.get(position));
                            } else {
                                ARouter.getInstance().build("/user/LoginRegisterActivity").navigation();
                            }
                        }
                        transaction.show(fragments.get(position));
                    } else {
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
        typeTagFragment = new TypeTagFragment();
        fragments.add(typeTagFragment);
        fragments.add(new FindFragment());
        fragments.add(new ShopcarFragment());
        fragments.add(new UserFragment());

        rgMain = (RadioGroup) findViewById(R.id.rg_main);
        rbHome = (RadioButton) findViewById(R.id.rb_home);
        rbType = (RadioButton) findViewById(R.id.rb_type);
        rbCommunity = (RadioButton) findViewById(R.id.rb_community);
        rbCart = (RadioButton) findViewById(R.id.rb_cart);
        rbUser = (RadioButton) findViewById(R.id.rb_user);
        manager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayoutId, fragments.get(0));
        fragmentTransaction.add(R.id.frameLayoutId, fragments.get(1));
        fragmentTransaction.add(R.id.frameLayoutId, fragments.get(2));
        fragmentTransaction.add(R.id.frameLayoutId, fragments.get(3));
        fragmentTransaction.add(R.id.frameLayoutId, fragments.get(4));
        fragmentTransaction.hide(fragments.get(1));
        fragmentTransaction.hide(fragments.get(2));
        fragmentTransaction.hide(fragments.get(3));
        fragmentTransaction.hide(fragments.get(4));
        fragmentTransaction.commit();

        ARouter.getInstance().inject(this);

        updateShopcarCount();

        initShopcarDataChangeLinstener();
    }

    @Override
    protected void initData() {
    }


    @Override
    protected void initPresenter() {

    }

    private CacheManager.IShopcarDataChangeListener iShopcarDataChangeListener = new CacheManager.IShopcarDataChangeListener() {
        @Override
        public void onDataChanged(List<ShopcarBean> shopcarBeanList) {
            int size = shopcarBeanList.size();
            rbCart.setText("购物车:" + size);
        }

        @Override
        public void onOneDataChanged(int postion, ShopcarBean shopcarBean) {

        }

        @Override
        public void onMoneyChanged(String moneyValue) {

        }

        @Override
        public void onAllSelected(boolean isAllSelect) {

        }
    };

    private void initShopcarDataChangeLinstener() {
        CacheManager.getInstance().setShopcarDataChangeListener(iShopcarDataChangeListener);
    }

    private void updateShopcarCount() {
        if (ShopUserManager.getInstance().isUserLogin()) {
            int count = CacheManager.getInstance().getShopcarBeanList().size();
            if (count != 0) {
                rbCart.setText("购物车:" + count);
            } else {
                Toast.makeText(this, "当前用户没登录", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unSetShopcarDataChangerListener(iShopcarDataChangeListener);
    }
}
