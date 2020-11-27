package View;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.shopmall.bawei.user.R;

import java.util.ArrayList;
import java.util.List;

import fragment.FragmentLogin;
import fragment.FragmentRegister;
import framework.BaseActivity;

public class UserActivity extends BaseActivity {
    public static NoScrollViewPager userViewPager;
    private List<Fragment> fragments = new ArrayList<>();
    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void initData() {
        fragments.add(new FragmentLogin());
        fragments.add(new FragmentRegister());

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

    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_user ;
    }


}
