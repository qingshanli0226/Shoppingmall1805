package com.shopmall.bawei.shopmall1805.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.framework.BaseFragment;
import com.example.net.ClothesBean;
import com.example.net.ConfigUrl;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.XiangActivity;
import com.shopmall.bawei.shopmall1805.adapter.BottomAdapter;
import com.shopmall.bawei.shopmall1805.adapter.LeftAdapter;
import com.shopmall.bawei.shopmall1805.adapter.RightAdapter;
import com.shopmall.bawei.shopmall1805.contract.SmallskirtContract;
import com.shopmall.bawei.shopmall1805.entity.NameEntitiy;
import com.shopmall.bawei.shopmall1805.presenter.SmallPresenter;

import java.util.ArrayList;
import java.util.List;


public class BlankFragment extends BaseFragment<SmallPresenter, SmallskirtContract.SmallskirtView> implements SmallskirtContract.SmallskirtView{

    private RecyclerView recyclerView2,recyclerView3;
    private ListView listView;
    private List<String> list = new ArrayList<>();
    private RightAdapter rightAdapter;
    private BottomAdapter bottomAdapter;
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void initView(View inflate) {
        listView = inflate.findViewById(R.id.rec_left);
        recyclerView2 = inflate.findViewById(R.id.rec_top);
        recyclerView3 = inflate.findViewById(R.id.rec_bottom);
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new SmallPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void initData() {
        httpPresenter.getData2();
        list.add("小裙子");
        list.add("上衣");
        list.add("下装");
        list.add("外套");
        list.add("配件");
        list.add("包包");
        list.add("装扮");
        list.add("居家宅品");
        list.add("办公文具");
        list.add("数码周边");
        list.add("游戏专区");

        arrayAdapter = new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        httpPresenter.getData1();
                        rightAdapter.notifyDataSetChanged();
                        break;
                    case 1:
                        httpPresenter.getData2();
                        rightAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        httpPresenter.getData3();
                        rightAdapter.notifyDataSetChanged();
                        break;
                    case 3:
                        httpPresenter.getData4();
                        rightAdapter.notifyDataSetChanged();
                        break;
                    case 4:
                        httpPresenter.getData5();
                        rightAdapter.notifyDataSetChanged();
                        break;
                    case 5:
                        httpPresenter.getData6();
                        rightAdapter.notifyDataSetChanged();
                        break;
                    case 6:
                        httpPresenter.getData7();
                        rightAdapter.notifyDataSetChanged();
                        break;
                    case 7:
                        httpPresenter.getData8();
                        rightAdapter.notifyDataSetChanged();
                        break;
                    case 8:
                        httpPresenter.getData9();
                        rightAdapter.notifyDataSetChanged();
                        break;
                    case 9:
                        httpPresenter.getData10();
                        rightAdapter.notifyDataSetChanged();
                        break;
                    case 10:
                        httpPresenter.getData11();
                        rightAdapter.notifyDataSetChanged();
                        break;
                    case 11:
                        httpPresenter.getData12();
                        rightAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });


    }


    @Override
    public void getViewData2(final List<ClothesBean.ResultBean> clotheslist) {

        rightAdapter = new RightAdapter(R.layout.item_top,clotheslist.get(0).getChild());
        recyclerView2.setAdapter(rightAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        bottomAdapter = new BottomAdapter(R.layout.item_bottom,clotheslist.get(0).getHot_product_list());
        recyclerView3.setAdapter(bottomAdapter);
        recyclerView3.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

        rightAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getContext(), ""+"??", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), XiangActivity.class);
                intent.putExtra("path", ConfigUrl.BASE_IMAGE+clotheslist.get(0).getChild().get(position).getPic());
                startActivity(intent);
            }
        });

        bottomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getContext(), ""+"??", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), XiangActivity.class);
                intent.putExtra("path", ConfigUrl.BASE_IMAGE+clotheslist.get(0).getHot_product_list().get(position).getFigure());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onError(String message) {

    }
}