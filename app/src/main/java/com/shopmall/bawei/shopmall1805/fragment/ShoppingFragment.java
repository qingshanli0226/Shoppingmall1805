package com.shopmall.bawei.shopmall1805.fragment;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alipay.sdk.app.EnvUtils;
import com.bawei.deom.BaseFragment;
import com.bawei.deom.CacheManager;
import com.bawei.deom.selectedordelete.ShopcarContract;
import com.bawei.deom.selectedordelete.ShopcarPresenterImpl;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.activity.TheorderActivity;
import com.shopmall.bawei.shopmall1805.apter.ShopcarAdapter;

import java.util.List;

import bean.ConfirmServerPayResultBean;
import bean.FindForPayBean;
import bean.GetOrderInfo;
import bean.InventoryBean;
import bean.Shoppingcartproducts;

@Route(path = "/fragment/ShoppingFragment")
public class ShoppingFragment extends BaseFragment<ShopcarPresenterImpl, ShopcarContract.SelectedandDeletedCountrollerView>implements ShopcarContract.SelectedandDeletedCountrollerView, CacheManager.IShopcarDataChangeListener {

    private ImageButton ibShopcartBack;
    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    ShopcarAdapter shopcarAdapter;
    private  boolean isEditMode=false;
    private  boolean newAllselect;


    @Override
    protected void initHttpData() {

    }

    @Override
    protected void inPrine() {
        loadingPage.showSuccessView();
        prine=new ShopcarPresenterImpl();
        shopcarAdapter.setShopcarPresenter(prine);
    }
    @Override
    protected void initData() {


        List<Shoppingcartproducts.ResultBean> shopcarBeanlist = CacheManager.getInstance().getShopcarBeanlist();//获得缓存中集合列表

        Log.e("chicun",shopcarBeanlist.size()+"");
        shopcarAdapter.updataData(shopcarBeanlist);//添加数据源
        recyclerview.setAdapter(shopcarAdapter);//设置适配器
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        tvShopcartTotal.setText(CacheManager.getInstance().getMoneyValue());//设置金钱

        if (CacheManager.getInstance().isALLSelected()){//如果为false
            checkboxAll.setChecked(true);//那么将正常模式下的全选设置为true
        }else {
            cbAll.setChecked(false);//为真将编辑模式下的全选设置成false

        }

         checkboxAll.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (checkboxAll.isChecked()){//如果被选中
                     newAllselect=true;
                     prine.selectAllProduct(newAllselect);//进行网络请求
                 }else {
                     newAllselect=false;//如果不被选中
                     prine.selectAllProduct(newAllselect);
                     CacheManager.getInstance().getSelectedProductBeanList().clear();
                 }
             }
         });
         cbAll.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (cbAll.isChecked()){//如果编辑模式下被选中
                     CacheManager.getInstance().selectAllProductInEditMode(true);//将为真传到缓存selectAllProductInEditMode中
                 }else {
                     CacheManager.getInstance().selectAllProductInEditMode(false);
                 }
             }
         });
        tvShopcartEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEditMode){
                    isEditMode=true;;//这是在编辑模式
                    tvShopcartEdit.setText("完成");
                    shopcarAdapter.setEditMode(isEditMode);
                    llCheckAll.setVisibility(View.GONE);
                    llDelete.setVisibility(View.VISIBLE);
                    if (CacheManager.getInstance().isALLSelectedInEditMode()){//如果是在编辑模式全选的情况下就将所有的数据队列的长度给删除队列一次性删除
                     cbAll.setChecked(true);//将编辑模式下的全选按钮设置为真
                    }
                }else {
                    isEditMode=false;
                    tvShopcartEdit.setText("编辑");
                    shopcarAdapter.setEditMode(isEditMode);
                    llCheckAll.setVisibility(View.VISIBLE);
                    llDelete.setVisibility(View.GONE);
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击删除的时候
                Toast.makeText(getContext(), "1111", Toast.LENGTH_SHORT).show();
                List<Shoppingcartproducts.ResultBean> deleteshocarBeanlist = CacheManager.getInstance().getDeleteshocarBeanlist();//获得缓存中的删除队列
                Log.e("删除的尺寸",deleteshocarBeanlist.size()+"");
                if (deleteshocarBeanlist.size()>0){//如果删除的队列长度大于0
                    prine.deleteProducts(deleteshocarBeanlist);//调用服务端接口进行删除
                }else {
                    Toast.makeText(getContext(), "没有数据删除了", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        btnCheckOut.setText(""+CacheManager.getInstance().getSelectedProductBeanList().size());
       Log.e("选择队列的长度",""+CacheManager.getInstance().getSelectedProductBeanList().size());
        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                CacheManager.getInstance().getSelectedProductBeanList().clear();
                Intent intent=new Intent(getContext(), TheorderActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void initView(View view) {

       CacheManager.getInstance().setShopcarDataChangeListerner(this);
        ibShopcartBack = (ImageButton) view.findViewById(R.id.ib_shopcart_back);
        tvShopcartEdit = (TextView) view.findViewById(R.id.tv_shopcart_edit);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) view.findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) view.findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) view.findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) view.findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) view.findViewById(R.id.ll_delete);
        cbAll = (CheckBox) view.findViewById(R.id.cb_all);
        btnDelete = (Button) view.findViewById(R.id.btn_delete);
        btnCollection = (Button) view.findViewById(R.id.btn_collection);
        shopcarAdapter=new ShopcarAdapter();
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);//设置沙箱环境.
    }

    @Override
    protected int getlayoutview() {
        return R.layout.shoppfragment;
    }


    @Override
    public void onProductNumChange(String result, int position, String newNum) {
      CacheManager.getInstance().updateProductNum(position,newNum);
    }

    @Override
    public void onAllSelected(String request) {
        Toast.makeText(getContext(), "所以商品的选择状态发生了改变全选状态为"+newAllselect, Toast.LENGTH_SHORT).show();
         CacheManager.getInstance().selectAllProduct(newAllselect);//全选的状态存到缓存中
        CacheManager.getInstance().getSelectedProductBeanList().clear();
        btnCheckOut.setText("去支付"+CacheManager.getInstance().getSelectedProductBeanList().size());
    }

    @Override
    public void ononProductSelected(String result, int postion) {
        Toast.makeText(getContext(), "商品选择发生变化", Toast.LENGTH_SHORT).show();
        CacheManager.getInstance().updateProductSelected(postion);//将商品选择的变化放入缓存中方便一会更具状态进行操作
         CacheManager.getInstance().getSelectedProductBeanList().clear();
           btnCheckOut.setText("去支付"+CacheManager.getInstance().getSelectedProductBeanList().size());
    }

    @Override
    public void onDeleteProducts(String result) {
        Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
          CacheManager.getInstance().processDeleteProducts();//从缓存中进行删除
    }

    @Override
    public void onInventory(List<InventoryBean> inventoryBean) {

    }



    @Override
    public void onOrderInfo(GetOrderInfo orderInfoBean) {

    }

    @Override
    public void onConfirmServerPayResult(ConfirmServerPayResultBean confirmServerPayResultBean) {

    }

    @Override
    public void onFindForPay(List<FindForPayBean.ResultBean> list) {

    }

    @Override
    public void loading() {

    }

    @Override
    public void hideloading() {

    }

    @Override
    public void onDataChanged(List<Shoppingcartproducts.ResultBean> shopcarBeanlist) {
        shopcarAdapter.updataData(shopcarBeanlist);//刷新数据源
    }

    @Override
    public void onOneDataChanger(int pstion, Shoppingcartproducts.ResultBean shopcarBeanlist) {
          shopcarAdapter.notifyItemChanged(pstion);//刷新某一条数据
    }

    @Override
    public void onMeneyChanged(String moneyValue) {
        tvShopcartTotal.setText(moneyValue);//刷新金钱
    }

    @Override
    public void onAllSelected(boolean isAllSelect) {
           if (isEditMode){
                cbAll.setChecked(isAllSelect);//刷新选择全选和不全选
           }else {
               checkboxAll.setChecked(isAllSelect);
           }

    }

    @Override
    public void onAllSelectedNum(int num) {
     Log.e("尺寸刷新",""+num);
     btnCheckOut.setText(""+num);
    }

}
