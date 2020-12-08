package framework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import framework.mvpc.JsonPresenter;


public abstract

class BaseActivity<P extends JsonPresenter> extends AppCompatActivity implements  Contact.CenterUserIview {
    protected  P jsonPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutId());
        initData();
        createPresenter();
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
