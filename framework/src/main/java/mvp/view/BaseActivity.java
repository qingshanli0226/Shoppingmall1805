package mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract  class BaseActivity extends AppCompatActivity {
    private String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        create();
    }

    protected void create(){

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    protected  abstract  void initView();
    protected abstract int getLayoutId();

    protected void printLog(String message){
        Log.d(TAG,message);
    }


    protected void launchActivity(Class launcActivityClass, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtras(bundle);
        intent.setClass(this, launcActivityClass);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
        printLog("onDestroy..............");
    }

    protected void destroy() {

    }


    @Override
    protected void onPause() {
        super.onPause();
        pause();
    }

    public void pause() {
        printLog("onPause..................");

    }


    @Override
    protected void onResume() {
        super.onResume();
        resume();
    }

    public void resume() {

        printLog("onResume..................");
    }





}
