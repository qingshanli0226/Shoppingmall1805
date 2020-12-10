package com.shopmall.bawei.shopmall1805;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.shopmall1805.home.adapter.HomePagerAdapter;
import com.shopmall.bawei.shopmall1805.adapter.BaseVpAdapter;
import com.shopmall.bawei.shopmall1805.fragment.ClassificationFragment;
import com.shopmall.bawei.shopmall1805.fragment.FindFragment;
import com.shopmall.bawei.shopmall1805.home.view.FirstFragment;
import com.shopmall.bawei.shopmall1805.fragment.MyFragment;
import com.shopmall.bawei.shopmall1805.shopcar.view.ShopCarFragment;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private ArrayList<Integer> selectedIconRes = new ArrayList<>();         //tab选中图标集合
    private ArrayList<Integer> unselectedIconRes = new ArrayList<>();       //tab未选中图标集合
    private ArrayList<String> titleRes = new ArrayList<>();                 //tab标题集合
    private ArrayList<Fragment> fsRes = new ArrayList<>();                  //fragment集合
    private List<CustomTabEntity> data = new ArrayList<>();                 //CommonTabLayout 所需数据集合
    private BaseVpAdapter mPager;
    private CommonTabLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mPager = (BaseVpAdapter) findViewById(R.id.m_pager);
        mPager.setscrollable(false);
        mLayout = (CommonTabLayout) findViewById(R.id.m_layout);

    }

    private void initData() {
//图片选中资源
        selectedIconRes.add(R.drawable.main_home_press);
        selectedIconRes.add(R.drawable.main_type_press);
        selectedIconRes.add(R.drawable.main_community_press);
        selectedIconRes.add(R.drawable.main_cart_press);
        selectedIconRes.add(R.drawable.main_user_press);

        //图片未选中资源
        unselectedIconRes.add(R.drawable.main_home);
        unselectedIconRes.add(R.drawable.main_type);
        unselectedIconRes.add(R.drawable.main_community);
        unselectedIconRes.add(R.drawable.main_cart);
        unselectedIconRes.add(R.drawable.main_user);

        //标题资源
        titleRes.add("首页");
        titleRes.add("分类");
        titleRes.add("发现");
        titleRes.add("购物车");
        titleRes.add("个人中心");

        //fragment数据
        fsRes.add(new FirstFragment());
        fsRes.add(new ClassificationFragment());
        fsRes.add(new FindFragment());
        fsRes.add(new ShopCarFragment());
        fsRes.add(new MyFragment());

        //设置数据
        for (int i = 0; i < titleRes.size(); i++) {
            final int index = i;
            data.add(new CustomTabEntity() {
                @Override
                public String getTabTitle() {
                    return titleRes.get(index);
                }

                @Override
                public int getTabSelectedIcon() {
                    return selectedIconRes.get(index);
                }

                @Override
                public int getTabUnselectedIcon() {
                    return unselectedIconRes.get(index);
                }
            });
        }
        //设置数据
        mLayout.setTabData((ArrayList<CustomTabEntity>) data);
        mPager.setAdapter(new HomePagerAdapter(getSupportFragmentManager(),fsRes));
    }
    private void initListener() {
        //TabLayout监听
        mLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                //显示相应的item界面
                mPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        //ViewPager监听
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                //设置相应选中图标和颜色
                mLayout.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //设置默认第0个
        mPager.setCurrentItem(0);
    }

}
