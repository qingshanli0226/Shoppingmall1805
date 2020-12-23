package com.shopmall.bawei.order.sendorder;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.base.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.net.bean.FindForSendBean;
import com.shopmall.bawei.order.R;

import java.util.List;

@Route(path = "/order/SendOrderActivity")
public class SendOrderActivity extends BaseActivity {
    private RecyclerView rvSendorder;
    private SendOrderAdapter adapter;

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        ARouter.getInstance().build("/main/MainActivity").navigation();
    }

    @Override
    protected void initData() {
        List<FindForSendBean.ResultBean> findForSendList = CacheManager.getInstance().getFindForSendList();
        adapter.updataData(findForSendList);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_send_order;
    }

    @Override
    protected void initView() {
        rvSendorder = (RecyclerView) findViewById(R.id.rv_sendorder);
        rvSendorder.setLayoutManager(new LinearLayoutManager(this));
        adapter=new SendOrderAdapter();
        rvSendorder.setAdapter(adapter);
    }
}