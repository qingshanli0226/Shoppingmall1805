package com.shopmall.bawei.shopmall1805.fragment2;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.bawei.deom.BaseFragment;
import com.bawei.deom.countroller.UserCountroller;
import com.bawei.deom.countroller.UserIMPL;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.apter.apter2.BiaoQianApter;

import java.util.ArrayList;
import java.util.List;

import bean.BaseBean;
import bean.HomeBean;
import bean.TAGBean;

public class BiaoFragment extends BaseFragment<UserIMPL, UserCountroller.UserView>implements UserCountroller.UserView {
    ArrayList<TAGBean.ResultBean> arrayList=new ArrayList<>();
    private RecyclerView biaoqianRecyle;
    BiaoQianApter biaoQianApter;

    @Override
    protected void inPrine() {
       prine=new UserIMPL();
    }

    @Override
    protected void initData() {
      prine.TagShow();
      biaoQianApter=new BiaoQianApter(R.layout.taglayout,arrayList);
      biaoqianRecyle.setAdapter(biaoQianApter);
      biaoqianRecyle.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    protected void initView(View view) {
        biaoqianRecyle = (RecyclerView) view.findViewById(R.id.biaoqian_recyle);

    }

    @Override
    protected int getlayoutview() {
        return R.layout.biaoqian;
    }

    @Override
    public void ChannelInfoBean(BaseBean<HomeBean> listBaseBean) {

    }

    @Override
    public void TagBiew(List<TAGBean.ResultBean> resultBeanList) {
        Toast.makeText(getContext(), ""+resultBeanList.get(0).getName(), Toast.LENGTH_SHORT).show();
        arrayList.addAll(resultBeanList);
        biaoQianApter.notifyDataSetChanged();
    }




    @Override
    public void loading() {

    }

    @Override
    public void hideloading() {

    }
}
