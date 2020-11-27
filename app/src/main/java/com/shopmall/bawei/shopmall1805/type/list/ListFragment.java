package com.shopmall.bawei.shopmall1805.type.list;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.base.BaseFragment;
import com.example.net.Constants;
import com.example.net.bean.GoodsBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shoppmall.common.adapter.error.ErrorBean;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends BaseFragment<ListPresenterImpl, ListContract.ListIView> implements ListContract.ListIView {
    private String[] urls = new String[]{Constants.SKIRT_URL, Constants.JACKET_URL, Constants.PANTS_URL, Constants.OVERCOAT_URL,
            Constants.ACCESSORY_URL, Constants.BAG_URL, Constants.DRESS_UP_URL, Constants.HOME_PRODUCTS_URL, Constants.STATIONERY_URL,
            Constants.DIGIT_URL, Constants.GAME_URL};
    private ListView lvLeft;
    private RecyclerView rvRight;
    private ListHotAdapter strAdapter;
    private List<Object> list=new ArrayList<>();
    private ListGoodAdapter listGoodAdapter;
    @Override
    protected void initDate() {
        strAdapter=new ListHotAdapter(getContext());
        lvLeft.setAdapter(strAdapter);
        presenter.showGoods(urls[0]);
    }

    @Override
    protected void initLisenter() {
        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                strAdapter.changeSelected(i);
                presenter.showGoods(urls[i]);
            }
        });
    }

    @Override
    protected void initView(View inflate) {
        presenter=new ListPresenterImpl();
        lvLeft = (ListView) inflate.findViewById(R.id.lv_left);
        rvRight = (RecyclerView) inflate.findViewById(R.id.rv_right);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        rvRight.setLayoutManager(manager);
        listGoodAdapter=new ListGoodAdapter(getContext());
        rvRight.setAdapter(listGoodAdapter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_list;
    }

    @Override
    public void onListOK(GoodsBean bean) {
        list.clear();
        List<GoodsBean.ResultBean> result = bean.getResult();
        List<GoodsBean.ResultBean.ChildBean> child = result.get(0).getChild();
        List<GoodsBean.ResultBean.HotProductListBean> hots = result.get(0).getHot_product_list();
        list.add(hots);
        list.add(child);
        listGoodAdapter.updataData(list);
    }

    @Override
    public void onError(String msg) {
        Log.i("Yoyo", msg);
    }

    @Override
    public void showloading() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }

    @Override
    public void showEmpty() {

    }
}