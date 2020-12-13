package mvp.view;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.shopmall.bawei.framework.R;

public abstract class BaseMVPActivity<T extends IPresenter,V extends IView> extends BaseActivity {
   protected T ihttpPresenter;
    protected ProgressBar loadingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        ihttpPresenter.attachView((V)this);
        loadingBar = findViewById(R.id.loadingBar);
        initData();

    }
    protected abstract void initPresenter();
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ihttpPresenter.datachView();
    }
}
