package com.shopmall.bawei.shopmall1805.typefragment.view;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.framework.BaseFragment;
import com.example.framework.BaseRVAdapter;
import com.example.net.Confing;
import com.example.net.bean.ClothesBean;
import com.shopmall.bawei.shopmall1805.typefragment.presenter.ClothesPresenter;
import com.shopmall.bawei.shopmall1805.ui.activity.view.GoodinfoActivity;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adpter.Clother_RAdpter;
import com.shopmall.bawei.shopmall1805.adpter.Colother_CAdpter;
import com.shopmall.bawei.shopmall1805.bean.PrimereBean;
import com.shopmall.bawei.shopmall1805.typefragment.contract.ClothesContract;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_fen extends BaseFragment<ClothesPresenter, ClothesContract.SkertView> implements ClothesContract.SkertView {
    private ListView rvFen;
    private RecyclerView rvFenRe;
    private RecyclerView rvFenChang;
    private ArrayAdapter<String> adapter ;
    private Clother_RAdpter clother_rAdpter;
    private Colother_CAdpter colother_cAdpter;

    private List<ClothesBean.ResultBean.HotProductListBean> hotProductListBeans = new ArrayList<>();
    private List<ClothesBean.ResultBean.ChildBean> childBeans = new ArrayList<>();
    private String[] data = null;
    @Override
    protected void initPreseter() {
        httpresetnter = new ClothesPresenter();
    }

    @Override
    protected void initView(View inflate) {
        rvFen = inflate.findViewById(R.id.rv_fen);
        rvFenRe = inflate.findViewById(R.id.rv_fen_re);
        rvFenChang = inflate.findViewById(R.id.rv_fen_chang);
        //点击事件

    }

    @Override
    protected void initdate() {
        //默认显示小裙子
        skirt();
        //添加数据
        if (data!=null){
            data.clone();
        }
        data = new String[]{"小裙子","上衣","下装","外套","配件","包包","装","居家展评","办公文具","数码周边","游戏专区"};
        adapter = new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,data);
        rvFen.setAdapter(adapter);
        //点击判断分支对应的数据源
        rvFen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        httpresetnter.getskert();
                        break;
                    case 1:
                        httpresetnter.getjecket();
                        break;
                    case 2:
                        httpresetnter.ononpants();
                        break;
                    case 3:
                        httpresetnter.onovercoat();
                        break;
                    case 4:
                        httpresetnter.onaccessory();
                        break;
                    case 5:
                        httpresetnter.onbag();
                        break;
                    case 6:
                        httpresetnter.ondress();
                        break;
                    case 7:
                        httpresetnter.onproduct();
                        break;
                    case 8:
                        httpresetnter.onstation();
                        break;
                    case 9:
                        httpresetnter.ondigit();
                        break;
                    case 10:
                        httpresetnter.ongame();
                        break;
                }
            }
        });

    }

    private void skirt() {
        httpresetnter.getskert();
        rvFenRe.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        clother_rAdpter = new Clother_RAdpter();
        rvFenRe.setAdapter(clother_rAdpter);

        rvFenChang.setLayoutManager(new GridLayoutManager(getContext(), 3));
        colother_cAdpter = new Colother_CAdpter();
        rvFenChang.setAdapter(colother_cAdpter);

        clother_rAdpter.updataData(hotProductListBeans);
        colother_cAdpter.updataData(childBeans);

    }

    @Override
    protected int getlayoutid() {
        return R.layout.fragment_fen;
    }


    @Override
    public void onErroy(String message) {

    }

    @Override
    public void showsloading() {
        showloading();
    }

    @Override
    public void hideloading() {
        hideLoading();
    }

    @Override
    public void showErroy(String message) {
        showerror(message);
    }

    @Override
    public void showEmpty() {
        showEnpty();
    }

    //请求到的各个接口数据，并且进行适配
    @Override
    public void onjscket(List<ClothesBean.ResultBean> skertbean) {
        clothes(skertbean);
    }

    private void clothes(final List<ClothesBean.ResultBean> skertbean) {
        hotProductListBeans.addAll(skertbean.get(0).getHot_product_list());
        childBeans.addAll(skertbean.get(0).getChild());
        if (clother_rAdpter == null && colother_cAdpter == null) {
            rvFenRe.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            clother_rAdpter = new Clother_RAdpter();
            rvFenRe.setAdapter(clother_rAdpter);

            rvFenChang.setLayoutManager(new GridLayoutManager(getContext(), 3));
            colother_cAdpter = new Colother_CAdpter();
            rvFenChang.setAdapter(colother_cAdpter);
        } else {
            clother_rAdpter.updataData(skertbean.get(0).getHot_product_list());
            colother_cAdpter.updataData(skertbean.get(0).getChild());

            clother_rAdpter.notifyDataSetChanged();
            colother_cAdpter.notifyDataSetChanged();
            //hot点击跳转页面
            clother_rAdpter.setiRecyclerViewItemClickListener(new BaseRVAdapter.IRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    PrimereBean goodsBean = new PrimereBean(skertbean.get(0).getHot_product_list().get(position).getProduct_id(),skertbean.get(0).getHot_product_list().get(position).getName(),skertbean.get(0).getHot_product_list().get(position).getCover_price(), skertbean.get(0).getHot_product_list().get(position).getFigure());

                    Intent intent = new Intent(getContext(), GoodinfoActivity.class);
                    intent.putExtra("goods_bean", goodsBean);
                    getContext().startActivity(intent);
                }
            });
            //child点击跳转页面
            colother_cAdpter.setiRecyclerViewItemClickListener(new BaseRVAdapter.IRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    PrimereBean goodsBean = new PrimereBean(skertbean.get(0).getChild().get(position).getParent_id(),skertbean.get(0).getChild().get(position).getName(),skertbean.get(0).getChild().get(position).getParent_id(), skertbean.get(0).getChild().get(position).getPic());

                    Intent intent = new Intent(getContext(), GoodinfoActivity.class);
                    intent.putExtra("goods_bean", goodsBean);
                    getContext().startActivity(intent);
                }
            });
        }
    }
}
