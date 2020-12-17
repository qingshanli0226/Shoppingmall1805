package framework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.shopmall.bawei.framework.R;

import framework.Mvp.Presenter;
import framework.mvpc.JsonPresenter;
import view.ToolBar;


public abstract

class BaseActivity<P extends Presenter> extends AppCompatActivity implements  Contact.CenterUserIview ,ToolBar.IToolBarClickListner{
    protected  P jsonPresenter;
    protected ToolBar tooBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutId());
        createPresenter();
        initData();
        tooBar = (ToolBar) findViewById(R.id.tooBar);
        tooBar.setToolBarClickListner(this);
        OnClickListener();


    }

    protected abstract void createPresenter();


    protected abstract void OnClickListener();

    protected abstract void initData();

    protected abstract int getlayoutId();
    public void SetToastView(String settype, String msg){
        Toast.makeText(this, settype+"--->"+msg, Toast.LENGTH_SHORT).show();
    }

    public void setLog(String settype, String setmsg){
        Log.i(settype,settype+"--->"+setmsg);
    }



}
