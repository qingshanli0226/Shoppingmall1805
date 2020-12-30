package com.bawei.shopmall.find;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.common.view.ErrorBean;
import com.bawei.common.view.MyToolBar;
import com.bawei.framework.BaseFragment;
import com.bawei.framework.BasePresenter;
import com.bawei.framework.IView;
import com.bawei.framework.MessageManager;
import com.bawei.framework.RefreshLayout;
import com.bawei.framework.greendao.MessageBean;
import com.bawei.shopmall.message.MessageAdapter;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends BaseFragment<BasePresenter, IView> implements IView, MyToolBar.IToolBarClickListner, RefreshLayout.IRefreshListener {

    private MessageAdapter messageAdapter;
    private RecyclerView findRecycler;
    private RefreshLayout refreshLayout;

    @Override
    protected int layoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView() {
        findRecycler = findViewById(R.id.find_recycler);
        findRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        messageAdapter = new MessageAdapter();
        findRecycler.setAdapter(messageAdapter);
        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.addRefreshListener(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        refeshData(3);
    }

    private void refeshData(int type) {
        if (type == 1) {
            MessageManager.getInstance().refresh();
            MessageManager.getInstance().queryMessage(new MessageManager.IMessageListener() {
                @Override
                public void onResult(boolean isSuccess, List<MessageBean> messageBeanList) {
                    messageAdapter.updateData(messageBeanList);
                }
            });
        } else if (type == 2) {
            MessageManager.getInstance().loadShowNum();
            MessageManager.getInstance().queryMessage(new MessageManager.IMessageListener() {
                @Override
                public void onResult(boolean isSuccess, List<MessageBean> messageBeanList) {
                    messageAdapter.updateData(messageBeanList);
                }
            });
        } else {

        }
    }

    @Override
    protected void initData() {
        MessageManager.getInstance().queryMessage(new MessageManager.IMessageListener() {

            @Override
            public void onResult(boolean isSuccess, List<MessageBean> messageBeanList) {
                messageAdapter.updateData(messageBeanList);
            }
        });


        findRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void showLoaDing() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void onRefresh() {
        refeshData(0);
    }

    @Override
    public void onLoadMore() {
        refeshData(1);
    }

    @Override
    public void onRefreshComplete() {

    }
}
