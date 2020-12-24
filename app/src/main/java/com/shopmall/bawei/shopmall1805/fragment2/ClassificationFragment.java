package com.shopmall.bawei.shopmall1805.fragment2;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.deom.BaseFragment;
import com.bawei.deom.BaseUser;
import com.bawei.deom.countroller.SkirtCommuntroller;
import com.bawei.deom.countroller.SkirtImpl;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.apter.apter2.BugHotApter;
import com.shopmall.bawei.shopmall1805.user.GoodsBean;
import com.shopmall.bawei.shopmall1805.DetailsActivity;
import com.shopmall.bawei.shopmall1805.apter.apter2.BaseRVAdapter;
import com.shopmall.bawei.shopmall1805.apter.apter2.BugCommonlyUsedApter;

import java.util.ArrayList;
import java.util.List;

import bean.typebean.BugBean;

public class ClassificationFragment extends BaseFragment<SkirtImpl, SkirtCommuntroller.UsView>implements SkirtCommuntroller.UsView {
    private ListView zhonglei;
    private RecyclerView remai;
    private RecyclerView changyong;





    //    //集合
   public static ArrayList<String> arrayList=new ArrayList<>();







    //    //适配器
    public static ArrayAdapter<String> zhongleiApter;

  public static BugHotApter bugReApter;
  public static BugCommonlyUsedApter bugChangApter;

    @Override
    protected void initHttpData() {

    }

    @Override
    protected void inPrine() {

          prine=new SkirtImpl();
    }

    @Override
    protected void initData() {
         add();
        bugChangApter = new BugCommonlyUsedApter();
        bugReApter = new BugHotApter();
        prine.UserShow(BaseUser.SKIRT_URL,loadingPage);
        final String data[]=new String[]{"小裙子","上衣","下装","外套","配件","包包","装扮","居家宅品","办公文具","数码周边","游戏专区"};
        if (data!=null){
            data.clone();
        }

        zhongleiApter=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,data);
        zhonglei.setAdapter(zhongleiApter);
        zhonglei.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (data[position]){
                    case "小裙子":
                        prine.UserShow(BaseUser.SKIRT_URL,loadingPage);

                        break;
                    case "上衣":
                        prine.UserShow(BaseUser.JACKET_URL,loadingPage);
                        break;
                    case "下装":
                        prine.UserShow(BaseUser.PANTS_URL,loadingPage);

                        break;
                    case "外套":
                        prine.UserShow(BaseUser.OVERCOAT_URL,loadingPage);

                        break;
                    case "配件":
                        prine.UserShow(BaseUser.ACCESSORY_URL,loadingPage);

                        break;
                    case "包包":
                        prine.UserShow(BaseUser.BAG_URL,loadingPage);

                        break;

                    case "装扮":
                        prine.UserShow(BaseUser.DRESS_UP_URL,loadingPage);

                        break;
                    case "居家宅品":
                        prine.UserShow(BaseUser.HOME_PRODUCTS_URL,loadingPage);

                        break;
                    case "办公文具":
                        prine.UserShow(BaseUser.STATIONERY_URL,loadingPage);

                        break;
                    case "数码周边":
                        prine.UserShow(BaseUser.DIGIT_URL,loadingPage);

                        break;
                    case "游戏专区":
                        prine.UserShow(BaseUser.GAME_URL,loadingPage);

                        break;
                }
            }
        });




    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//         bugReApter.notifyDataSetChanged();
//         bugChangApter.notifyDataSetChanged();
//    }

    @Override
    public void onPause() {
        super.onPause();

    }

    private void add() {

        arrayList.add("小裙子");
        arrayList.add("上衣");
        arrayList.add("下装");
        arrayList.add("外套");
        arrayList.add("配件");
        arrayList.add("包包");
        arrayList.add("装扮");
        arrayList.add("居家宅品");
        arrayList.add("办公文具");
        arrayList.add("数码周边");
        arrayList.add("游戏专区");

    }

    @Override
    protected void initView(View view) {
        zhonglei = (ListView) view.findViewById(R.id.zhonglei);
        remai = (RecyclerView) view.findViewById(R.id.remai);
        changyong = (RecyclerView) view.findViewById(R.id.changyong);
    }
    @Override
    protected int getlayoutview() {
        return R.layout.fenlei;
    }
    @Override
    public void loading() {

    }

    @Override
    public void hideloading() {

    }



    @Override
    public void onUserView(final List<BugBean.ResultBean> list) {

        Toast.makeText(getContext(), ""+list.get(0).getName(), Toast.LENGTH_SHORT).show();


        changyong.setAdapter(bugChangApter);

        remai.setAdapter(bugReApter);

        changyong.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        remai.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        bugChangApter.updataData(list.get(0).getChild());
        bugChangApter.notifyDataSetChanged();
        bugReApter.updataData(list.get(0).getHot_product_list());

        bugReApter.setiRecyclerViewItemClickListener(new BaseRVAdapter.IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                  Toast.makeText(getContext(), "数据库", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("shangp",new GoodsBean(list.get(0).getHot_product_list().get(position).getP_catalog_id(),list.get(0).getHot_product_list().get(position).getCover_price(),list.get(0).getHot_product_list().get(position).getFigure(),list.get(0).getHot_product_list().get(position).getName()));
                startActivity(intent);
            }
        });
        bugReApter.notifyDataSetChanged();

    }



}
