package view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shopmall.bawei.pay.R;

import framework.BaseActivity;
import view.loadinPage.ErrorBean;

public class PayActivity extends BaseActivity implements ToolBar.IToolBarClickListner, View.OnClickListener {
    private TextView payText;
    private RecyclerView payRv;
    private LinearLayout layoutPay;
    private TextView payMoney;
    private Button payButton;
    @Override
    protected void createPresenter() {

    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void initData() {
        payText = (TextView) findViewById(R.id.payText);
        payRv = (RecyclerView) findViewById(R.id.payRv);
        layoutPay = (LinearLayout) findViewById(R.id.layoutPay);
        payMoney = (TextView) findViewById(R.id.payMoney);
        payButton = (Button) findViewById(R.id.payButton);
        findViewById(R.id.payButton).setOnClickListener(this);
        tooBar = findViewById(R.id.tooBar);
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.payButton) {
            Toast.makeText(this, "点击支付按钮事件", Toast.LENGTH_SHORT).show();
        }
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

    }
}