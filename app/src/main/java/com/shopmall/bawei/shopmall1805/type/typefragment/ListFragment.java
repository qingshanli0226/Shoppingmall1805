package com.shopmall.bawei.shopmall1805.type.typefragment;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.base.BaseFragment;
import com.example.net.Constants;
import com.example.net.bean.GoodsBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.type.list.ListContract;
import com.shopmall.bawei.shopmall1805.type.list.ListPresenterImpl;

public class ListFragment extends BaseFragment<ListPresenterImpl, ListContract.ListIView> implements ListContract.ListIView {
    private String[] urls = new String[]{Constants.SKIRT_URL, Constants.JACKET_URL, Constants.PANTS_URL, Constants.OVERCOAT_URL,
            Constants.ACCESSORY_URL, Constants.BAG_URL, Constants.DRESS_UP_URL, Constants.HOME_PRODUCTS_URL, Constants.STATIONERY_URL,
            Constants.DIGIT_URL, Constants.GAME_URL};
    private ListView lvLeft;
    private RecyclerView rvRight;
    private String[] titles = new String[]{"小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品", "办公文具", "数码周边", "游戏专区"};
    private ArrayAdapter<String> strAdapter;
    @Override
    protected void initDate() {
        strAdapter=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,titles);
        lvLeft.setAdapter(strAdapter);
        presenter.showGoods(urls[0]);
    }

    @Override
    protected void initLisenter() {

    }

    @Override
    protected void initView(View inflate) {
        lvLeft = (ListView) inflate.findViewById(R.id.lv_left);
        rvRight = (RecyclerView) inflate.findViewById(R.id.rv_right);
//        rvRight.setLayoutManager();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_list;
    }

    @Override
    public void onListOK(GoodsBean bean) {

    }

    @Override
    public void onError(String msg) {

    }
}