package com.shopmall.bawei.shopmall1805.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.SortChildAdapter;
import com.shopmall.bawei.shopmall1805.adapter.SortHotAdapter;
import com.shopmall.common.Constants;
import com.shopmall.framework.base.BaseMVPFragment;
import com.shopmall.framework.constart.Constart;
import com.shopmall.framework.mvptest.presenter.SortPresenter;
import com.shopmall.net.bean.SortData;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends BaseMVPFragment<SortPresenter> implements Constart.SortConstartView {
    private ListView listviewSort;
    private RecyclerView recycleSort;
    private ArrayAdapter<String> arrayAdapter;
    private RecyclerView recycle2Sort;
    private List<String> title=new ArrayList<>();
    private SortHotAdapter sortHotAdapter;
    private SortChildAdapter sortChildAdapter;

    @Override
    protected void createViewid(View inflate) {
        listviewSort = inflate.findViewById(R.id.listview_sort);
        recycleSort = inflate.findViewById(R.id.recycle_sort);
        recycle2Sort =inflate.findViewById(R.id.recycle2_sort);

        recycleSort.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recycle2Sort.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    protected void createEnvent() {
        mPresenter.Sort(Constants.SKIRT_URL2,loadingPage);
        listviewSort.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        mPresenter.Sort(Constants.SKIRT_URL2,loadingPage);
                        break;
                    case 1:
                        mPresenter.Sort(Constants.JACKET_URL2,loadingPage);
                        break;
                    case 2:
                        mPresenter.Sort(Constants.PANTS_URL2,loadingPage);
                        break;
                    case 3:
                        mPresenter.Sort(Constants.OVERCOAT_URL2,loadingPage);
                        break;
                    case 4:
                        mPresenter.Sort(Constants.ACCESSORY_URL2,loadingPage);
                        break;
                    case 5:
                        mPresenter.Sort(Constants.BAG_URL2,loadingPage);
                        break;
                    case 6:
                        mPresenter.Sort(Constants.DRESS_UP_URL2,loadingPage);
                        break;
                    case 7:
                        mPresenter.Sort(Constants.HOME_PRODUCTS_URL2,loadingPage);
                        break;
                    case 8:
                        mPresenter.Sort(Constants.STATIONERY_URL2,loadingPage);
                        break;
                    case 9:
                        mPresenter.Sort(Constants.DIGIT_URL2,loadingPage);
                        break;
                    case 10:
                        mPresenter.Sort(Constants.GAME_URL2,loadingPage);
                        break;
                }
            }
        });
    }

    @Override
    protected void createData() {
        title.add("小裙子");
        title.add("上衣");
        title.add("下装");
        title.add("外套");
        title.add("配件");
        title.add("包包");
        title.add("装扮");
        title.add("居家宅品");
        title.add("办公文具");
        title.add("数码周边");
        title.add("游戏专区");
        /**
         * list适配器
         */
        arrayAdapter=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,title){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView view =(TextView) super.getView(position, convertView, parent);
                view.setGravity(Gravity.CENTER);
                return view;
            }
        };
        listviewSort.setAdapter(arrayAdapter);
    }

    @Override
    protected int fragmentid() {
        return R.layout.fragment_list;
    }

    @Override
    protected void createPresenter() {
        mPresenter=new SortPresenter(this);
    }

    @Override
    public void Success(Object... objects) {
        SortData sortData=(SortData)objects[0];
        Toast.makeText(getContext(), ""+sortData, Toast.LENGTH_SHORT).show();

        if (sortHotAdapter==null&&sortChildAdapter==null){
            sortHotAdapter=new SortHotAdapter(R.layout.sort1_buju,sortData.getResult().get(0).getHot_product_list());
            recycleSort.setAdapter(sortHotAdapter);

            sortChildAdapter=new SortChildAdapter(R.layout.sort2_buju,sortData.getResult().get(0).getChild());
            recycle2Sort.setAdapter(sortChildAdapter);
        }else {
            sortHotAdapter.getData().clear();
            sortChildAdapter.getData().clear();

            sortHotAdapter.getData().addAll(sortData.getResult().get(0).getHot_product_list());
            sortChildAdapter.getData().addAll(sortData.getResult().get(0).getChild());

            sortHotAdapter.notifyDataSetChanged();
            sortChildAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void Error(String s) {
        Toast.makeText(getContext(), ""+s, Toast.LENGTH_SHORT).show();
    }
}