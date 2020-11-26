package com.bawei.shopmall.type.view;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.common.view.MyToolBar;
import com.bawei.framework.BaseFragment;
import com.bawei.net.TypeBean;
import com.bawei.shopmall.type.contract.TypeContract;
import com.bawei.shopmall.type.contract.TypeImpl;
import com.shopmall.bawei.shopmall1805.R;

import java.util.ArrayList;
import java.util.List;

public class ListFragment<P extends TypeImpl, V extends TypeContract.ITypeView> extends BaseFragment<P, V> implements TypeContract.ITypeView {
    private MyToolBar toolbar;
    private ListView lvLeft;
    private RecyclerView rvRight;

    private List<String> list = new ArrayList<>();

    private TypeAdapter adapter;
    private ListAdapter lvAdapter;

    private TypeBean.ResultBean resultBean;


    @Override
    public void onResume() {
        super.onResume();
        Log.i("TAG", "onResume: " + rvRight);
        rvRight.setAdapter(adapter);
    }

    @Override
    protected void initView() {
        lvLeft = (ListView) findViewById(R.id.lv_left);
        rvRight = (RecyclerView) findViewById(R.id.rv_right);
        rvRight.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new TypeAdapter();
        Log.i("TAG", "initView: " + rvRight);
        rvRight.setAdapter(adapter);
        list.add(getActivity().getString(R.string.skirt));
        list.add(getActivity().getString(R.string.jacket));
        list.add(getActivity().getString(R.string.Bottoms));
        list.add(getActivity().getString(R.string.loose_coat));
        list.add(getActivity().getString(R.string.parts));
        list.add(getActivity().getString(R.string.Bag));
        list.add(getActivity().getString(R.string.Dress_up));
        list.add(getActivity().getString(R.string.Home_furnishings));
        list.add(getActivity().getString(R.string.Office_stationery));
        list.add(getActivity().getString(R.string.Digital_perimeter));
        list.add(getActivity().getString(R.string.Game_Zone));
        lvAdapter = new ListAdapter(getContext(), list);
        lvLeft.setAdapter(lvAdapter);

    }

    @Override
    protected void initData() {
        httpPresenter.getSkirt();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initListener() {
        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvAdapter.changeSelected(position);
                lvAdapter.notifyDataSetChanged();
                lvAdapter.changeSelected(position);
                switch (position) {
                    case 0:
                        httpPresenter.getSkirt();
                        break;
                    case 1:
                        httpPresenter.getJacket();
                        break;
                    case 2:
                        httpPresenter.getPants();
                        break;
                    case 3:
                        httpPresenter.getOvercoat();
                        break;
                    case 4:
                        httpPresenter.getAccessory();
                        break;
                    case 5:
                        httpPresenter.getBag();
                        break;
                    case 6:
                        httpPresenter.getDress();
                        break;
                    case 7:
                        httpPresenter.getProduct();
                        break;
                    case 8:
                        httpPresenter.getStationery();
                        break;
                    case 9:
                        httpPresenter.getDigit();
                        break;
                    case 10:
                        httpPresenter.getGame();
                        break;
                    default:
                        httpPresenter.getSkirt();
                        break;
                }
            }
        });
        lvLeft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void initPresenter() {
        httpPresenter = (P) new TypeImpl();
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onError(String msg) {
        Log.i("TAG", "onError: " + msg);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    public void updateAdapter(TypeBean.ResultBean resultBean) {
        adapter.clearData();
        adapter.addOneData(resultBean.getHot_product_list());
        adapter.addOneData(resultBean.getChild());
    }

    @Override
    public void onType(TypeBean typeBean) {
        List<TypeBean.ResultBean> result = typeBean.getResult();
        updateAdapter(result.get(0));
        for (TypeBean.ResultBean item : result) {
            Log.i("TAG", "onType: " + item.getName());
        }
    }
}
