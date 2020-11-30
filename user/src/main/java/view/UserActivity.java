package view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.user.R;

import java.util.ArrayList;
import java.util.List;

import framework.BaseActivity;
import framework.ShopUserManager;
import view.fragment.FragmentLogin;
import view.fragment.FragmentRegister;

@Route(path = "/usr/LoginRegisterActivity")
public class UserActivity extends BaseActivity {
    private  int toLoginFromIndex = -1;

    public static NoScrollViewPager userViewPager;
    private List<Fragment> fragments = new ArrayList<>();
    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void initData() {
        fragments.add(new FragmentLogin());
        fragments.add(new FragmentRegister());
        userViewPager = (NoScrollViewPager) findViewById(R.id.userViewPager);
        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        };

        userViewPager.setAdapter(pagerAdapter);

        Intent intent = getIntent();
        toLoginFromIndex= intent.getIntExtra(ShopmallConstant.TO_LOGIN_KEY, -1);
        setLog("5",""+toLoginFromIndex);
        ARouter.getInstance().inject(this);

    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_user ;
    }

    //获取跳转的index值
    public int getToLoginFromIndex() {
        return toLoginFromIndex;
    }

    public interface INameInterface {
        void setName(String name);
    }

}
