package com.shopmall.bawei.shopmall1805.mvp.view.classificationfragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.ClassificationSmallMultiLayoutAdapter;
import com.shopmall.bawei.shopmall1805.mvp.contract.ClassificationContract;
import com.shopmall.bawei.shopmall1805.mvp.model.ClassificationModel;
import com.shopmall.bawei.shopmall1805.mvp.prsenter.ClassificationPresenter;

import java.util.ArrayList;
import java.util.List;

import baseurl.SkirstBean;
import baseurl.UrlHelp;
import mvp.view.BaseFragment;


public class ClassificationSmallFragment extends BaseFragment<ClassificationPresenter> implements ClassificationContract.geteview {
    private String uri;
    private View view;
    private ListView clothesLv;
    private List<String> list = new ArrayList();
    private RecyclerView viewById;
    private ClassificationSmallMultiLayoutAdapter homeAdapter;


    private List<Object> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(bandlayout(), container, false);

        initsmall();


        return view;
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
        uri = UrlHelp.SKIRT_URL;
        final MyAdapter myAdapter = new MyAdapter();
        clothesLv.setAdapter(myAdapter);
        uri = UrlHelp.SKIRT_URL;
        clothesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            myAdapter.changeSelect(position);


                switch (position) {
                    case 0:

                        uri = UrlHelp.SKIRT_URL;

                        break;
                    case 1:

                        uri = UrlHelp.JACKET_URL;

                        break;
                    case 2:
                        uri = UrlHelp.PANTS_URL;

                        break;
                    case 3:
                        uri = UrlHelp.OVERCOAT_URL;

                        break;
                    case 4:
                        uri = UrlHelp.ACCESSORY_URL;

                        break;
                    case 5:
                        uri = UrlHelp.BAG_URL;

                        break;
                    case 6:
                        uri = UrlHelp.DRESS_UP_URL;

                        break;
                    case 7:
                        uri = UrlHelp.HOME_PRODUCTS_URL;

                        break;
                    case 8:
                        uri = UrlHelp.STATIONERY_URL;

                        break;
                    case 9:
                        uri = UrlHelp.DIGIT_URL;

                        break;
                    case 10:
                        uri = UrlHelp.GAME_URL;

                        break;


                }

                ipresenter.inithomedata();
            }
        });

    }


    @Override
    public int bandlayout() {
        return R.layout.fragment_classification_small;
    }

    @Override
    public void initview() {
        viewById = view.findViewById(R.id.content_rv);

        viewById.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void initdata() {
        ipresenter = new ClassificationPresenter(new ClassificationModel(), this);
        ipresenter.inithomedata();

    }

    @Override
    public String url() {
        return uri;
    }

    @Override
    public void getdata(List<SkirstBean.ResultBean> list) {
        data.clear();
        List<SkirstBean.ResultBean.HotProductListBean> hot_product_list = list.get(0).getHot_product_list();
        List<SkirstBean.ResultBean.ChildBean> child = list.get(0).getChild();

        data.add(hot_product_list);
        data.add(child);

        homeAdapter = new ClassificationSmallMultiLayoutAdapter(getContext());

        viewById.setAdapter(homeAdapter);
        homeAdapter.updataData(data);

    }

    class MyAdapter extends BaseAdapter {
        private int isselect=0;
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
            if (convertView == null) {
                vh = new VH();
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_classification_text_color, null);
                vh.class_te = convertView.findViewById(R.id.class_te);
                convertView.setTag(vh);
            } else {
                vh = (VH) convertView.getTag();

            }
            if(position==isselect){
             convertView.setBackgroundColor(Color.WHITE);
             vh.class_te.setTextColor(Color.RED);
            }else {
                convertView.setBackgroundColor(Color.LTGRAY);
                vh.class_te.setTextColor(Color.BLACK);
            }
            vh.class_te.setText(list.get(position));

            return convertView;
        }
        public void changeSelect(int position){
            if (position !=isselect){
                isselect = position;
                notifyDataSetChanged();
            }
        }

    }

    class VH {
        TextView class_te;
    }
}