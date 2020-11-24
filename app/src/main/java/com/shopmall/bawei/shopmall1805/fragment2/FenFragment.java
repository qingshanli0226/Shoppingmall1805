package com.shopmall.bawei.shopmall1805.fragment2;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bawei.deom.BaseFragment;
import com.bawei.deom.BaseUser;
import com.bawei.deom.countroller.SkirtCommuntroller;
import com.bawei.deom.countroller.SkirtImpl;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.apter.apter2.BugChangApter;
import com.shopmall.bawei.shopmall1805.apter.apter2.BugReApter;
import com.shopmall.bawei.shopmall1805.apter.apter2.ZhongleiApter;

import java.util.ArrayList;
import java.util.List;

import bean.typebean.BugBean;

public class FenFragment extends BaseFragment<SkirtImpl, SkirtCommuntroller.UsView>implements SkirtCommuntroller.UsView {
    private RecyclerView zhonglei;
    private RecyclerView remai;
    private RecyclerView changyong;




//    //集合
    ArrayList<String> arrayList=new ArrayList<>();

    ArrayList<BugBean.ResultBean.HotProductListBean> BugRe=new ArrayList<>();

//    //适配器
    ZhongleiApter zhongleiApter;

    BugReApter bugReApter;
    BugChangApter bugChangApter;
    @Override
    protected void inPrine() {
          prine=new SkirtImpl();
    }

    @Override
    protected void initData() {
         add();


        zhongleiApter=new ZhongleiApter(R.layout.string,arrayList);
        zhonglei.setAdapter(zhongleiApter);
        zhonglei.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
         zhongleiApter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
             @Override
             public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (arrayList.get(position)){
                    case "小裙子":
                        prine.UserShow(BaseUser.SKIRT_URL);
                        prine.UserReShow(BaseUser.SKIRT_URL);

                        break;
                    case "上衣":
                        prine.UserShow(BaseUser.JACKET_URL);
                        prine.UserReShow(BaseUser.JACKET_URL);

                        Toast.makeText(getContext(), "上衣", Toast.LENGTH_SHORT).show();
                        break;
                    case "下装":
                        prine.UserShow(BaseUser.PANTS_URL);
                        prine.UserReShow(BaseUser.PANTS_URL);

                        break;
                    case "外套":
                        prine.UserShow(BaseUser.OVERCOAT_URL);
                        prine.UserReShow(BaseUser.OVERCOAT_URL);

                        break;
                    case "配件":
                        prine.UserShow(BaseUser.ACCESSORY_URL);
                        prine.UserReShow(BaseUser.ACCESSORY_URL);

                        break;
                    case "包包":
                        prine.UserShow(BaseUser.BAG_URL);
                        prine.UserReShow(BaseUser.BAG_URL);

                        break;

                    case "装扮":
                        prine.UserShow(BaseUser.DRESS_UP_URL);
                        prine.UserReShow(BaseUser.DRESS_UP_URL);

                        break;
                    case "居家宅品":
                        prine.UserShow(BaseUser.HOME_PRODUCTS_URL);
                        prine.UserReShow(BaseUser.HOME_PRODUCTS_URL);

                        break;
                    case "办公文具":
                        prine.UserShow(BaseUser.STATIONERY_URL);
                        prine.UserReShow(BaseUser.STATIONERY_URL);

                        break;
                    case "数码周边":
                        prine.UserShow(BaseUser.DIGIT_URL);
                        prine.UserReShow(BaseUser.DIGIT_URL);

                        break;
                    case "游戏专区":
                        prine.UserShow(BaseUser.GAME_URL);
                        prine.UserReShow(BaseUser.GAME_URL);

                        break;
                }

             }
         });
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
        zhonglei = (RecyclerView) view.findViewById(R.id.zhonglei);
        remai = (RecyclerView) view.findViewById(R.id.remai);
        changyong = (RecyclerView) view.findViewById(R.id.changyong);
        changyong.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));


        remai.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
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
    public void UserView(List<BugBean.ResultBean.ChildBean> list) {
        Log.e("============",""+list.get(0).getName());
          if (bugChangApter==null){
              bugChangApter=new BugChangApter(R.layout.skirtlayout,list);
              changyong.setAdapter(bugChangApter);
          }else {
              Log.e("changyong",""+list.get(0).getName());
              bugChangApter.getData().clear();
              bugChangApter.getData().addAll(list);

              bugChangApter.notifyDataSetChanged();
          }
    }

    @Override
    public void UserRe(List<BugBean.ResultBean.HotProductListBean> list) {
        Log.e("============",""+list.get(0).getName());
        if (bugReApter==null){
            bugReApter=new BugReApter(R.layout.jackchang,list);
            remai.setAdapter(bugReApter);
        }else {
            Log.e("remai",""+list.get(0).getName());
            bugReApter.getData().clear();
            bugReApter.getData().addAll(list);

            bugReApter.notifyDataSetChanged();
        }
    }


//    @Override
//    public void SkirtView(List<SkirtBean.ResultBean.ChildBean> list) {
//         Log.e("",""+list.size());
//         sk.addAll(list);
//         skirtApter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void SkirtReVIew(List<SkirtBean.ResultBean.HotProductListBean> list) {
//          skre.addAll(list);
//          sKirtReApter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void JackChangView(List<JackBean.ResultBean.ChildBean> childBeans) {
//        Jackchang.addAll(childBeans);
//        jackChangAPter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void JackRe(List<JackBean.ResultBean.HotProductListBean> childBeans) {
//        JackRe.addAll(childBeans);
//          jackReApter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void PaintChangView(List<Pants.ResultBean.ChildBean> childBeans) {
//       PantsChang.addAll(childBeans);
//       pantsChangApter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void PaintReShow(List<Pants.ResultBean.HotProductListBean> childBeans) {
//           PantsRe.addAll(childBeans);
//           pantsReApter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void OverCoatChangView(List<OverCoat.ResultBean.ChildBean> childBeans) {
//         OverCoatChange.addAll(childBeans);
//         overCoatChangeApter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void OverCoatReView(List<OverCoat.ResultBean.HotProductListBean> childBeans) {
//      OverCoatRe.addAll(childBeans);
//      overCoatReApter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void BugChangView(List<BugBean.ResultBean.ChildBean> childBeans) {
//        BugChang.addAll(childBeans);
//         bugChangApter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void BugtReView(List<BugBean.ResultBean.HotProductListBean> childBeans) {
//            BugRe.addAll(childBeans);
//            bugReApter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void DressupChangView(List<DressupBean.ResultBean.ChildBean> childBeans) {
//         DressupChang.addAll(childBeans);
//         dressupChangApter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void DressupReView(List<DressupBean.ResultBean.HotProductListBean> childBeans) {
//         DressupRe.addAll(childBeans);
//         dressupReApter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void HomproductsChangView(List<HomproductsBean.ResultBean.ChildBean> childBeans) {
//          HomproductsChang.addAll(childBeans);
//          homproductChangApter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void HomproductsReView(List<HomproductsBean.ResultBean.HotProductListBean> childBeans) {
//        HomproductsRe.addAll(childBeans);
//        homproductReApter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void StationeChangView(List<StationeryBean.ResultBean.ChildBean> childBeans) {
//         StationeryChang.addAll(childBeans);
//         stationChangApter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void StationeReView(List<StationeryBean.ResultBean.HotProductListBean> childBeans) {
//     StationeryRe.addAll(childBeans);
//     sKirtReApter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void DigitChangView(List<DigitBean.ResultBean.ChildBean> childBeans) {
//        DigitBeanChang.addAll(childBeans);
//        digitChangApter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void DigitReView(List<DigitBean.ResultBean.HotProductListBean> childBeans) {
//     DigitBeanRe.addAll(childBeans);
//     dressupReApter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void GameChangView(List<GameBean.ResultBean.ChildBean> childBeans) {
//        GameChang.addAll(childBeans);
//        gameChangApter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void GameReView(List<GameBean.ResultBean.HotProductListBean> childBeans) {
//      GameRe.addAll(childBeans);
//      gameReApter.notifyDataSetChanged();
//    }
}
