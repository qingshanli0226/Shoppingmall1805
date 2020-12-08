package com.shopmall.bawei.shopmall1805.home.fragment.classIfys;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.shopmall.bawei.shopmall1805.Adper.classify.LableAdper;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.fragment.jsonCallBack.JsonDataBack;

import java.util.ArrayList;
import java.util.List;

import framework.BaseFragment;
import framework.mvpc.JsonPresenter;
import mode.LableBean;
import view.loadinPage.ErrorBean;
import view.ToolBar;

public
class FragmentLable extends BaseFragment implements ToolBar.IToolBarClickListner {
    private RecyclerView Rvc;
    private List<LableBean.ResultBean> resultBeans = new ArrayList<>();
    private LableAdper biaoAdper;


    @Override
    protected void createPresenter() {
        presenter = new JsonPresenter(this);
    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void InitData() {
        Rvc = (RecyclerView) findViewById(R.id.Rvc);
        tooBar = (ToolBar) findViewById(R.id.tooBar);

        Rvc.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

        presenter.getshopcal(12,new JsonDataBack() {
            @Override
            public void javabean(LableBean e) {
                resultBeans.addAll(e.getResult());
                biaoAdper.notifyDataSetChanged();
            }

            @Override
            public void Error(String error) {

            }
        });

        biaoAdper = new LableAdper(R.layout.item_lable,resultBeans);
        Rvc.setAdapter(biaoAdper);
        biaoAdper.notifyDataSetChanged();


    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_lable;
    }

    @Override
    public void showLoaDing() {
        showLoaDing();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess,errorBean);
    }

    @Override
    public void showEmpty() {
        showEmpty();
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
}
