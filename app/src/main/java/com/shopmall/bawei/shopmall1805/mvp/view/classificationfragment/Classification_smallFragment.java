package com.shopmall.bawei.shopmall1805.mvp.view.classificationfragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.ClassSelectAdapter;
import com.shopmall.bawei.shopmall1805.adapter.HomeAdapter;
import com.shopmall.bawei.shopmall1805.adapter.Hot_Adapter;
import com.shopmall.bawei.shopmall1805.mvp.contract.SContract;
import com.shopmall.bawei.shopmall1805.mvp.model.SModel;
import com.shopmall.bawei.shopmall1805.mvp.prsenter.SPresenter;

import java.util.ArrayList;
import java.util.List;

import baseurl.SkirstBean;
import baseurl.UrlHelp;
import mvp.ToastUtil;
import mvp.view.BaseFragment;


public class Classification_smallFragment extends BaseFragment<SPresenter> implements SContract.geteview {
private String uri;
    private   View view;
    private ListView clothesLv;
    private List<String> list = new   ArrayList();
    private RecyclerView viewById;
    private  ClassSelectAdapter homeAdapter;


    private List<Object> data=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(bandlayout(), container, false);

        initsmall();


        return view ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
        initdata();
    }




    private void initsmall() {
        clothesLv = (ListView) view.findViewById(R.id.clothes_lv);
        list.add("小裙子");
        list.add("上衣");
        list.add("下衣");
        list.add("外套");
        list.add("配件");
        list.add("包包");
        list.add("装扮");
        list.add("居家装扮");
        list.add("办公文具");
        list.add("数码周边");
        list.add("游戏专区");
        MyAdapter myAdapter = new MyAdapter();
        clothesLv.setAdapter(myAdapter);
        uri=UrlHelp.SKIRT_URL;

        clothesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView te = view.findViewById(R.id.class_te);

                    switch (position){
                        case 0:

                uri=UrlHelp.SKIRT_URL;

                            break;
                        case 1:

                            uri=UrlHelp.JACKET_URL;

                            break;
                        case 2:
                            uri=UrlHelp.PANTS_URL;

                            break;
                        case 3:

                            uri=UrlHelp.OVERCOAT_URL;
                            break;
                        case 4:

                            uri=UrlHelp.ACCESSORY_URL;

                            break;
                        case 5:
                            uri=UrlHelp.BAG_URL;


                            break;
                        case 6:
                            uri=UrlHelp.DRESS_UP_URL;


                            break;
                        case 7:

                            uri=UrlHelp.HOME_PRODUCTS_URL;

                            break;
                        case 8:
                            uri=UrlHelp.STATIONERY_URL;


                            break;
                        case 9:
                            uri=UrlHelp.DIGIT_URL;


                            break;
                            case 10:
                                uri=UrlHelp.GAME_URL;

                            break;


                    }
                    te.setTextColor(Color.RED);
                    UrlHelp.isBackHome=false;
                    ipresenter.inithomedata();
            }
        });

    }



    @Override
    public int bandlayout() {
        return R.layout.fragment_fen;
    }

    @Override
    public void initview() {
         viewById = view.findViewById(R.id.content_rv);

        viewById.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void initdata() {
        ipresenter =  new SPresenter(new SModel(),this);
        ipresenter.inithomedata();

    }

    @Override
    public String url() {
        return uri;
    }

    @Override
    public void getdata(List<SkirstBean.ResultBean> list) {
        List<SkirstBean.ResultBean.HotProductListBean> hot_product_list = list.get(0).getHot_product_list();
        List<SkirstBean.ResultBean.ChildBean> child = list.get(0).getChild();

            data.add(hot_product_list);
            data.add(child);


         homeAdapter = new ClassSelectAdapter(getContext());
        viewById.setAdapter(homeAdapter);
        homeAdapter.updataData(data);

    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           VH vh;
            if (convertView==null){
                 vh = new VH();
                convertView=LayoutInflater.from(getContext()).inflate(R.layout.clothes_layout,null);
                vh.class_te=convertView.findViewById(R.id.class_te);
                convertView.setTag(vh);
            }else {
                vh = (VH)convertView.getTag();

            }
            vh.class_te.setText(list.get(position));

            return convertView;
        }
    }
    class VH {
        TextView class_te;
    }
}