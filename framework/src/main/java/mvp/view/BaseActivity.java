package mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import mvp.presenter.IPresenter;

public abstract  class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity,IView {

    protected  P ipresenter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ipresenter!=null){
            ipresenter.destroy();
            ipresenter=null;
        }
        System.gc();
    }
}
