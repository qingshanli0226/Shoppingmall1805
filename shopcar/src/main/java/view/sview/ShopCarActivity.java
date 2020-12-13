package view.sview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.shopmall.bawei.shopcar.R;

import java.util.List;

import framework.BaseActivity;
import framework.CacheManagerc;
import framework.mvpc.JsonPresenter;
import mode.InventoryBean;
import mode.OrderInfoBean;
import mode.ShopcarBean;
import view.ToolBar;
import view.adaper.ShopAdaper;
import view.contract.ShopcarContractc;
import view.loadinPage.ErrorBean;
import view.spresenter.ShopcarPresenterImplc;
@Route(path = "/shopcar/ShopCarActivity")
public class ShopCarActivity extends BaseActivity<JsonPresenter> implements ToolBar.IToolBarClickListner, View.OnClickListener, ShopcarContractc.IShopcarView  {
    private RecyclerView shopcarRv;
    private TextView totalPriceTv;
    private CheckBox allSelectCheckBox;
    private boolean newAllSelect;

    private ShopcarPresenterImplc shopcarPresenterImplc;
    private ShopAdaper shopAdaper;

    private boolean isEditMode = false; //该标记位，当为true时，该页面为编辑模式，可以删除列表的商品时速局。当为false时，当前页面为正常模式，可以支付
    private RelativeLayout normalLayout;//正常模式下的底部布局
    private RelativeLayout editLayout;//编辑模式下的底部布局
    private CheckBox editAllSelectCheckBox;

    //缓存
    private CacheManagerc.IShopcarDataChangeListener iShopcarDataChangeListener = new CacheManagerc.IShopcarDataChangeListener() {
        @Override
        public void onDataChanged(List<ShopcarBean> shopcarBeanList) {
            shopAdaper.updataData(shopcarBeanList);
        }

        @Override
        public void onOneDataChanged(int position, ShopcarBean shopcarBean) {
            shopAdaper.notifyItemChanged(position);
        }

        @Override
        public void onMoneyChanged(String moneyVilue) {
            totalPriceTv.setText(moneyVilue);
        }

        @Override
        public void onAllSelected(boolean isAllSelect) {
            if (isEditMode){
                editAllSelectCheckBox.setChecked(isAllSelect);
            }else {
                allSelectCheckBox.setChecked(isAllSelect);
            }
        }
    };
    @Override
    protected void createPresenter() {
        shopcarPresenterImplc = new ShopcarPresenterImplc();
    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void initData() {
        initiAlize();
        shopAdaper.setShopcarPresenter(shopcarPresenterImplc);
        List<ShopcarBean> shopcarBeanList = CacheManagerc.getInstance().getShopcarBeanList();
        shopAdaper.updataData(shopcarBeanList);
        totalPriceTv.setText(CacheManagerc.getInstance().getMoneyValue());

        //监听数据 去刷新UI
        CacheManagerc.getInstance().setiShopcarDataChangeListener(iShopcarDataChangeListener);
        if (CacheManagerc.getInstance().isAllSelected()){
            allSelectCheckBox.setChecked(true);//是否全选 true
        }else {
            allSelectCheckBox.setChecked(false);//是否全选 false
        }

        //设置全选的点击事件
        allSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allSelectCheckBox.isChecked()){
                    newAllSelect = true;
                    shopcarPresenterImplc.selectAllProduct(newAllSelect);
                }else {
                    newAllSelect = false;
                    shopcarPresenterImplc.selectAllProduct(newAllSelect);
                }
            }
        });

        //编辑模式下所有商品被选中
        editAllSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editAllSelectCheckBox.isChecked()){//编辑模式下所有商品被选中
                    CacheManagerc.getInstance().selectAllProductInEditMode(true);
                }else {
                    CacheManagerc.getInstance().selectAllProductInEditMode(false);
                }
            }
        });
    }



    private void initiAlize() {
        shopcarRv = (RecyclerView) findViewById(R.id.shopCarRv);//！！
        shopcarRv.setLayoutManager(new LinearLayoutManager(this));
        shopAdaper = new ShopAdaper();
        shopcarRv.setAdapter(shopAdaper);
        totalPriceTv = (TextView) findViewById(R.id.sumValue);//金额更新text
        allSelectCheckBox = (CheckBox) findViewById(R.id.allSelect);//是否全选
        normalLayout = (RelativeLayout) findViewById(R.id.normalLayout);//支付框布局点击事件
        editLayout = (RelativeLayout) findViewById(R.id.editLayout);//编辑框布局点击事件
        editAllSelectCheckBox = (CheckBox) findViewById(R.id.allEditSelect);//编辑框中的全选
        tooBar = findViewById(R.id.tooBar);
        findViewById(R.id.deleteBtn).setOnClickListener(this);
        findViewById(R.id.payBtn).setOnClickListener(this);

        //沙箱环境设置  未设置
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_shop_car;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.allSelect){
            List<ShopcarBean> deleteShopcarBeanList = CacheManagerc.getInstance().getDeleteShopcarBeanList();
            if (deleteShopcarBeanList.size()>0){

            }else {
                Toast.makeText(this, "当前没有数据", Toast.LENGTH_SHORT).show();
            }

        }else if (v.getId()==R.id.payBtn){

        }
    }
    //更新服务端购物车产品数量的接口
    @Override
    public void onProductNumChange(String result, int position, String newNum) {
        CacheManagerc.getInstance().updateProductNum(position,newNum);
    }
    //更新服务端购物车产品的选择
    @Override
    public void onProductSelected(String result, int position) {
        Toast.makeText(this, "该商品在购物车的选择发生了改变", Toast.LENGTH_SHORT).show();
        CacheManagerc.getInstance().updateProductSelected(position);
    }
    //全选购物车产品或者全不选
    @Override
    public void onAllSelected(String result) {
        Toast.makeText(this, "所有商品的选择状态发生了改变，全选状态是"+newAllSelect, Toast.LENGTH_SHORT).show();
        //更新本地缓存的数据选择状态
        CacheManagerc.getInstance().selectAllProduct(newAllSelect);
    }
    //从服务端购物车删除一个产品
    @Override
    public void onDeleteProducts(String result) {
        Toast.makeText(this, "删除购物车数据成功", Toast.LENGTH_SHORT).show();
        //在换粗中，将删除列表中的商品在购物车上删掉
        CacheManagerc.getInstance().processDeleteProducts();
    }
    //检查服务端多个产品是否充足
    @Override
    public void onInventory(List<InventoryBean> inventoryBean) {
        if (checkInventoryIsEnough(inventoryBean)){
            shopcarPresenterImplc.getOrderInfo(CacheManagerc.getInstance()
                    .getSelectedPreduceBeanList());
        }else {
            Toast.makeText(this, "库存不足", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean checkInventoryIsEnough(List<InventoryBean> inventoryBeans) {
        List<ShopcarBean> shopcarBeans = CacheManagerc.getInstance().getSelectedPreduceBeanList();
        for (InventoryBean inventoryBean : inventoryBeans ){
            for (ShopcarBean shopcarBean :shopcarBeans){
                if (inventoryBean.getProductId().equals(shopcarBean.getProductId())){
                    int inventotyNum = Integer.parseInt(inventoryBean.getProductNum());
                    int needNum = Integer.parseInt(inventoryBean.getProductNum());
                    if (needNum>inventotyNum){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    //像服务器下订单接口
    @Override
    public void onOrderInfo(OrderInfoBean orderInfoBean) {
        //f服务端已经成功下蛋 使用支付宝完成支付功能
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ShopCarActivity.this, "可以支付", Toast.LENGTH_SHORT).show();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void showLoaDing() {
        showLoaDing();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoading(isSuccess,errorBean);
    }

    @Override
    public void showEmpty() {
        showEmpty();
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {
        if (!isEditMode){//false 进入编辑模式
            isEditMode=true;
            //第一步 刷新toobarUI 此方法定义须注意toobar自定义view
            tooBar.setToolbarRightTv("完成");
            //更新列表
            shopAdaper.setEditMode(isEditMode);

            //更新底部的布局  此布局如何更新须要注意
            normalLayout.setVisibility(View.GONE);
            editLayout.setVisibility(View.VISIBLE);

            if (CacheManagerc.getInstance().isAllSelectInEditMode()){
                editAllSelectCheckBox.setChecked(true);
            }
        }else {
            isEditMode = false;
            tooBar.setToolbarRightTv("编辑");
            shopAdaper.setEditMode(isEditMode);
            normalLayout.setVisibility(View.VISIBLE);
            editLayout.setVisibility(View.GONE);
        }
    }
}