package com.bawei.shopmall.shopcar.view;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.*;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.bawei.shopmall.shopcar.contract.ShopcarContract;
import com.bawei.shopmall.shopcar.presenter.ShopcarPresenterImpl;
import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.framework.BaseMVPActivity;
import com.shopmall.bawei.framework.CacheManager;
import com.shopmall.bawei.framework.dao.ShopcarMessage;
import com.shopmall.bawei.framework.view.BottomBar;
import com.shopmall.bawei.framework.view.ToolBar;
import com.shopmall.bawei.framework.view.manager.MessageManager;
import com.shopmall.bawei.net.mode.InventoryBean;
import com.shopmall.bawei.net.mode.OrderInfoBean;
import com.shopmall.bawei.net.mode.ShopcarBean;
import com.shopmall.bawei.shopcar.R;
import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;


@Route(path = "/shopcar/ShopcarActivity")
public class ShopcarActivity extends BaseMVPActivity<ShopcarPresenterImpl, ShopcarContract.IShopcarView> implements ShopcarContract.IShopcarView, View.OnClickListener {

    private RecyclerView shopcarRv;
    private ShopcarAdapter shopcarAdapter;
    private TextView totalPriceTv;
    private CheckBox allSelectCheckBox;
    private boolean newAllSelect;
    private boolean isEditMode = false; //该标记位，当为true时，该页面为编辑模式，可以删除列表的商品时速局。当为false时，当前页面为正常模式，可以支付
    private RelativeLayout normalLayout;//正常模式下的底部布局
    private RelativeLayout editLayout;//编辑模式下的底部布局
    private CheckBox editAllSelectCheckBox;


    @Override
    protected void initPresenter() {
        httpPresenter = new ShopcarPresenterImpl();
        shopcarAdapter.setShopcarPresenter(httpPresenter);
    }

    private CacheManager.IShopcarDataChangeListener iShopcarDataChangeListener = new CacheManager.IShopcarDataChangeListener() {
        @Override
        public void onDataChanged(List<ShopcarBean> shopcarBeanList) {
            shopcarAdapter.updataData(shopcarBeanList);
        }

        @Override
        public void onOneDataChanged(int position, ShopcarBean shopcarBean) {
            shopcarAdapter.notifyItemChanged(position);//只更新这个位置的Item UI
        }

        @Override
        public void onMoneyChanged(String moneyValue) {
            totalPriceTv.setText(moneyValue);
        }

        @Override
        public void onAllSelected(boolean isAllSelect) {
            if (isEditMode) {
                editAllSelectCheckBox.setChecked(isAllSelect);
            } else {
                allSelectCheckBox.setChecked(isAllSelect);
            }
        }
    };

    @Override
    protected void initData() {
        //获取购物车数据
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        shopcarAdapter.updataData(shopcarBeanList);
        totalPriceTv.setText(CacheManager.getInstance().getMoneyValue());

        //去监听数据的改变,改变后去刷新UI
        CacheManager.getInstance().setShopcarDataChangeListener(iShopcarDataChangeListener);
        if (CacheManager.getInstance().isAllSelected()) {
            allSelectCheckBox.setChecked(true);
        } else {
            allSelectCheckBox.setChecked(false);
        }

        //设置全选的点击事件
        //设置全选的点击事件
        allSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allSelectCheckBox.isChecked()) {
                    newAllSelect = true;
                    httpPresenter.selectAllProduct(newAllSelect);
                } else {
                    newAllSelect = false;
                    httpPresenter.selectAllProduct(newAllSelect);
                }
            }
        });

        editAllSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editAllSelectCheckBox.isChecked()) {//在编辑模式下，所有商品都被选中了
                    CacheManager.getInstance().selectAllProductInEditMode(true);
                } else {
                    CacheManager.getInstance().selectAllProductInEditMode(false);
                }
            }
        });
    }

    @Override
    protected void initView() {
        shopcarRv = findViewById(R.id.shopcarRv);
        shopcarRv.setLayoutManager(new LinearLayoutManager(this));
        shopcarAdapter = new ShopcarAdapter();
        shopcarRv.setAdapter(shopcarAdapter);
        totalPriceTv = findViewById(R.id.sumValue);
        allSelectCheckBox = findViewById(R.id.allSelect);
        normalLayout = findViewById(R.id.normalLayout);
        editLayout = findViewById(R.id.editLayout);
        editAllSelectCheckBox = findViewById(R.id.allEditSelect);
        findViewById(R.id.deleteBtn).setOnClickListener(this);
        findViewById(R.id.payBtn).setOnClickListener(this);

        //沙箱环境
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);//设置沙箱环境.
    }

    @Override
    public void onRightClick() {
        super.onRightClick();
        if (!isEditMode) {//如果当前页面时非编辑模式，那么则进入编辑模式
            isEditMode = true;//进入编辑模式
            //第一步刷新toolbar的UI
            toolbar.setToolbarRightTv("完成");
            //更新列表
            shopcarAdapter.setEditMode(isEditMode);
            //更新底部布局
            normalLayout.setVisibility(View.GONE);
            editLayout.setVisibility(View.VISIBLE);
            if (CacheManager.getInstance().isAllSelectInEditMode()) {
                editAllSelectCheckBox.setChecked(true);
            }
        } else {//从编辑模式进入到正常模式
            isEditMode = false;
            toolbar.setToolbarRightTv("编辑");
            shopcarAdapter.setEditMode(isEditMode);
            normalLayout.setVisibility(View.VISIBLE);
            editLayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopcar;
    }

    @Override
    public void onProductNumChange(String result, int position, String newNum) {
        showMessage("修改产品数量成功");

        //当服务端的商品数据发生改变后，本地缓存的商品数据的数量也要改变，保证和服务端数据一致
        CacheManager.getInstance().updateProductNum(position, newNum);
    }

    @Override
    public void onProductSelected(String result, int position) {
        //该回调代表当前该商品在购物车选择的状态发生了改变
        showMessage("该商品在购物车的选择发生改变");
        //需要保证服务端购物车该商品选择的状态和本地该商品在购物车上选择的状态一致性
        CacheManager.getInstance().updateProductSelected(position);
    }

    @Override
    public void onAllSelected(String result) {
        showMessage("所有的商品的选择状态发生了改变,全选状态为:" + newAllSelect);
        //更新本地缓存的数据的选择状态
        CacheManager.getInstance().selectAllProduct(newAllSelect);
    }

    @Override
    public void onDeleteProducts(String result) {
        showMessage("删除购物车数据成功");
        //在缓存中，将删除列表中商品在购物车上删掉
        CacheManager.getInstance().processDeleteProducts();
    }

    private boolean checkInventoryIsEnough(List<InventoryBean> inventoryBeans) {
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getSelectedProductBeanList();
        for(InventoryBean inventoryBean:inventoryBeans) {
            for(ShopcarBean shopcarBean:shopcarBeanList) {
                if (inventoryBean.getProductId().equals(shopcarBean.getProductId())) {
                    int inventoryNum = Integer.parseInt(inventoryBean.getProductNum());
                    int needNum = Integer.parseInt(inventoryBean.getProductNum());
                    if (needNum > inventoryNum) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public void onInventory(List<InventoryBean> inventoryBean) {

        if (checkInventoryIsEnough(inventoryBean)) {//库存充足的条件
            //充足情况下，向服务端下订单
            httpPresenter.getOrderInfo(CacheManager.getInstance().getSelectedProductBeanList());
        } else {
            showMessage(((String)inventoryBean.get(0).getProductName()) + "库存不充足");
        }
    }

    @Override
    public void onOrderInfo(final OrderInfoBean orderInfoBean) {
        //第一步将这些支付成功的商品，从购物车中删除。因为提交订单后，服务端会将购买的商品从它购物车数据中删除
        CacheManager.getInstance().removeSelectedProducts();
//服务端已经成功下订单
        //使用支付宝完成支付功能
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(ShopcarActivity.this);
                Map<String,String> resultMap = payTask.payV2(orderInfoBean.getOrderInfo(), true);
                if (resultMap.get("resultStatus").equals("9000")) {//返回值是9000时代表支付成功
                    handler.sendEmptyMessage(1);
                } else {
                    handler.sendEmptyMessage(2);
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1: {
                    showMessage("支付成功");
                    savePayMessage("支付成功");
                    //第二步跳转的主页面，并且显示HomeFragment
                    ARouter.getInstance().build("/main/MainActivity").withInt("index", BottomBar.HOME_INDEX).navigation();
                    break;
                }
                case 2: {
                    showMessage("支付失败");
                    savePayMessage("支付失败");
                    //第二步跳转的主页面，并且显示HomeFragment
                    ARouter.getInstance().build("/main/MainActivity").withInt("index", BottomBar.HOME_INDEX).navigation();
                    break;
                }
                default:
                    break;
            }
        }
    };

    private void savePayMessage(String message) {
        final ShopcarMessage shopcarMessage = new ShopcarMessage();
        shopcarMessage.setTime(System.currentTimeMillis());
        shopcarMessage.setBody(message);
        shopcarMessage.setIsRead(false);
        shopcarMessage.setTitle("支付消息:");
        shopcarMessage.setType(MessageManager.PAY_TYPE);
        MessageManager.getInstance().addMessage(shopcarMessage, new MessageManager.IMessageListener() {
            @Override
            public void onResult(boolean isSuccess, List<ShopcarMessage> shopcarMessageList) {
                Toast.makeText(ShopcarActivity.this,"存储成功", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(shopcarMessage);
            }
        });
    }

    @Override
    public void showLoaing() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }

    @Override
    public void showEmpty() {

    }


    @Override
    protected void destroy() {
        super.destroy();
        CacheManager.getInstance().unSetShopcarDataChangerListener(iShopcarDataChangeListener);
    }

    @Override
    public void onClick(View v) {
        //如果删除的话
        if (v.getId() == R.id.deleteBtn) {
            List<ShopcarBean> deleteShopcarBeanList = CacheManager.getInstance().getDeleteShopcarBeanList();
            if (deleteShopcarBeanList.size()>0) {
                httpPresenter.deleteProducts(deleteShopcarBeanList);
            } else {
                showMessage("当前没有要删除的数据");
            }
        } else if (v.getId() == R.id.payBtn) {
            //第一步检查购物车上商品在仓库中是否都有库存
            httpPresenter.checkInventory(CacheManager.getInstance().getSelectedProductBeanList());
        }
    }
}
