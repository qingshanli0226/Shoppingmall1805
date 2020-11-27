package com.shopmall.bawei.shopmall1805.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.deom.BaseFragment;
import com.bawei.deom.IPrine;
import com.bawei.deom.countroller.SkirtCommuntroller;
import com.bawei.deom.countroller.SkirtImpl;
import com.bawei.deom.countroller.UserCountroller;
import com.bawei.deom.countroller.UserIMPL;
import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.DaoSession;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.ShangTitle;
import com.shopmall.bawei.shopmall1805.ShopmallApplication;
import com.shopmall.bawei.shopmall1805.XIangqingActivity;
import com.shopmall.bawei.shopmall1805.fragment2.FenFragment;

import java.util.List;

import bean.typebean.BugBean;
@Route(path = "/fragment/ShoppingFragment")
public class ShoppingFragment extends BaseFragment<SkirtImpl, SkirtCommuntroller.UsView>implements SkirtCommuntroller.UsView {
    private ImageView pic;
    private TextView name;
    private TextView price;
    private Button jia;
    private TextView num;
    private Button jian;
    private DaoSession daoSession;
    int index=1;



    @Override
    protected void inPrine() {
        prine=new SkirtImpl();
    }

    @Override
    protected void initData() {
        daoSession=((ShopmallApplication)getActivity().getApplication()).getDaoSession();
        List<ShangTitle> shangTitles = daoSession.loadAll(ShangTitle.class);
        ARouter.getInstance().inject(this);
        for (int i=0;i<shangTitles.size();i++){
            Glide.with(getContext()).load("http://49.233.0.68:8080/atguigu/img/"+shangTitles.get(i).getPic()).into(pic);
            name.setText(shangTitles.get(i).getName());
            price.setText(shangTitles.get(i).getPrice());
        }
        num.setText("1");
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index==99){
                    num.setText(""+index);
                    Toast.makeText(getContext(), "商品已经"+index+"不支持购买", Toast.LENGTH_SHORT).show();
                }else {
                    num.setText(""+index++);
                }

            }
        });
        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index==0){
                    num.setText(""+index);
                    Toast.makeText(getContext(), "商品已经"+index+"不支持购买", Toast.LENGTH_SHORT).show();
                }else {
                    index--;
                    num.setText(""+index);
                }
            }
        });
    }

    @Override
    protected void initView(View view) {
        pic = (ImageView) view.findViewById(R.id.pic);
        name = (TextView) view.findViewById(R.id.name);
        price = (TextView) view.findViewById(R.id.price);
        jia = (Button) view.findViewById(R.id.jia);
        num = (TextView) view.findViewById(R.id.num);
        jian = (Button) view.findViewById(R.id.jian);
    }

    @Override
    protected int getlayoutview() {
        return R.layout.shoppfragment;
    }


    @Override
    public void loading() {

    }

    @Override
    public void hideloading() {

    }

    @Override
    public void showLoadingPage2() {
        showLoadingPage();
    }

    @Override
    public void showErrorPage2(String errorMsg) {
        showErrorPage(errorMsg);
    }

    @Override
    public void showEmptyPage2() {
        showEmptyPage();
    }

    @Override
    public void showSuccessView2() {
        showSuccessView();
    }

    @Override
    public void UserView(List<BugBean.ResultBean> list) {

    }
}
