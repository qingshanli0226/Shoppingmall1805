package com.bw.order;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.framework.BaseActivity;
import com.bw.framework.IPresenter;
import com.bw.framework.IView;
import com.bw.order.bean.MyTobBean;
import com.bw.order.fragment.AllOrderFragment;
import com.bw.order.fragment.ForPayFragment;
import com.bw.order.fragment.ForSendFragment;
import com.bw.order.view.MyPageAdapter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.order.R;

import java.util.ArrayList;
import java.util.List;

@Route( path = "/order/AllOrderActivity")
public class AllOrderActivity extends BaseActivity<IPresenter, IView> {

    private CommonTabLayout commonTabLayout;
    private ArrayList<CustomTabEntity> customTabEntities = new ArrayList<>();
    private ViewPager viewPager;
    private MyPageAdapter myPageAdapter;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_allorder;
    }

    @Override
    protected void initView() {
        super.initView();
        ARouter.getInstance().inject(this);

        viewPager = findViewById(R.id.vp);
        commonTabLayout = findViewById(R.id.commonTab);

        fragments.add(new AllOrderFragment());
        fragments.add(new ForPayFragment());
        fragments.add(new ForSendFragment());
        myPageAdapter = new MyPageAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(myPageAdapter);

        customTabEntities.add(new MyTobBean("全部",0,0));
        customTabEntities.add(new MyTobBean("待付款",0,0));
        customTabEntities.add(new MyTobBean("待收货",0,0));

        commonTabLayout.setTabData(customTabEntities);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                commonTabLayout.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });




    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
}
