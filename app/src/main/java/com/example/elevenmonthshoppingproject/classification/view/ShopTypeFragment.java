package com.example.elevenmonthshoppingproject.classification.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.elevenmonthshoppingproject.R;
import com.example.elevenmonthshoppingproject.adapter.RvTypeAdapter;
import com.example.elevenmonthshoppingproject.classification.contract.TypeContract;
import com.example.elevenmonthshoppingproject.classification.presenter.TypePresenterImpl;
import com.example.framwork.BaseMVPFragment;
import com.example.net.bean.TypeBean;

import java.util.ArrayList;
import java.util.List;

public class ShopTypeFragment extends BaseMVPFragment<TypePresenterImpl, TypeContract.TypeIView> implements TypeContract.TypeIView{

    private ListView listType;
    private ArrayAdapter<String> skrittypeAdapter;
    private RecyclerView rvShopmall;
    private RvTypeAdapter rvTypeAdapter;
    private TypePresenterImpl typePresenter;

    private String[] skirt={"小裙子","上衣","下装","外套","配件","包包","装","居家展评","办公文具","数码周边","游戏专区"};

    private List<TypeBean.ResultBean.ChildBean> typeResult=new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.shoptypefragment;
    }

    @Override
    protected void iniView(View view) {


        listType = view.findViewById(R.id.list_type);
        rvShopmall = view.findViewById(R.id.rv_shopmall);

        skrittypeAdapter=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,skirt);
        listType.setAdapter(skrittypeAdapter);

        //判断对应的
        listType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        if (typeResult!=null){
                            typeResult.clear();

                        }
                        typePresenter.getType();
                        rvTypeAdapter.notifyDataSetChanged();
                        break;
                    case 1:
                        if (typeResult!=null){
                            typeResult.clear();
                        }
                        typePresenter.getType();
                        rvTypeAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    @Override
    protected void iniData() {

    }

    @Override
    protected void iniPresenter() {
        typePresenter=new TypePresenterImpl();
    }

    @Override
    protected void iniHttpData() {
        typePresenter.attatch(this);
        if (typeResult!=null){
            typeResult.clear();
        }
        typePresenter.getType();

    }

    @Override
    public void onType(List<TypeBean.ResultBean> resultBeans) {

        for (int i = 0; i < resultBeans.size(); i++) {
          typeResult.addAll(resultBeans.get(i).getChild());
        }
            rvTypeAdapter=new RvTypeAdapter(R.layout.type_view,typeResult);
            rvShopmall.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
            rvShopmall.setAdapter(rvTypeAdapter);
            rvShopmall.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));



    }

    @Override
    public void onError(String code, String message) {
        Toast.makeText(getContext(), "错误"+code+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading(boolean isSuccess, String message) {

    }


}
