package mvp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.common2.LoginBean;

import java.util.HashMap;

import http.MyHttp;
import http.ShopmallObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserService extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String token = ShopUserManager.getInstance().getToken();
        Log.i("lhj", "onStartCommand: "+token);
        if (token==null){

            Toast.makeText(this, "空", Toast.LENGTH_SHORT).show();
            return START_STICKY;
        }else {
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        MyHttp.getShopmallApiService().autologin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopmallObserver<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        ShopUserManager.getInstance().saveLoginBean(loginBean);
                        Toast.makeText(UserService.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {

                    }
                });

        }

        return START_NOT_STICKY;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
