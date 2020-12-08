package com.shopmall.bawei.shopmall1805.Classification.view.classificationfragment;

import android.graphics.Color;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common2.SkirstBean;
import com.example.common2.UrlHelp;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.Classification.adapter.ClassificationSmallMultiLayoutAdapter;
import com.shopmall.bawei.shopmall1805.Classification.contract.ClassificationContract;
import com.shopmall.bawei.shopmall1805.Classification.prsenter.ClassificationPresenter;

import java.util.ArrayList;
import java.util.List;
import mvp.view.BaseMVPFragment;


public class ClassificationSmallFragment extends BaseMVPFragment<ClassificationPresenter,ClassificationContract.IClassification> implements  ClassificationContract.IClassification {
    private String uri;
    private ListView clothesLv;
    private List<String> list = new ArrayList();
    private RecyclerView viewById;
    private ClassificationSmallMultiLayoutAdapter homeAdapter;
    private TextView errorTv;
    private RelativeLayout normalContent;

    private List<Object> data = new ArrayList<>();



    @Override
    protected int getLayoutId() {
        Log.i("TAG", "getLayoutId: ");
        return R.layout.fragment_classification_small;

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        initsmall();
        viewById = findViewById(R.id.content_rv);
        Log.i("", "initView: ");

     viewById.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        viewById.setLayoutManager(new LinearLayoutManager(getContext()));

    }



    private void initsmall() {
        clothesLv = (ListView) findViewById(R.id.clothes_lv);
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

                ihttpPresenter.getIClassificationData(uri);

            }
        });

    }


    @Override
    public void showError(String code, String message) {
        errorTv.setVisibility(View.VISIBLE);
        normalContent.setVisibility(View.GONE);
        errorTv.setText(message + " 点击刷新数据");
    }

    @Override
    public void showLoaing() {
        errorTv.setVisibility(View.GONE);
        normalContent.setVisibility(View.VISIBLE);

        errorTv.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onClassification(SkirstBean skirstBean) {
        data.clear();

        List<SkirstBean.HotProductListBean> hot_product_list = skirstBean.getHot_product_list();
        List<SkirstBean.ChildBean> child = skirstBean.getChild();

        data.add(hot_product_list);
        data.add(child);

        homeAdapter = new ClassificationSmallMultiLayoutAdapter(getContext());

        viewById.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
        homeAdapter.updataData(data);

    }

    @Override
    protected void initHttpData() {

        ihttpPresenter.getIClassificationData(uri);
    }

    @Override
    protected void initPresenter() {
        ihttpPresenter=new ClassificationPresenter();
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