import bean.LoginBean;
import bean.RegisterBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface INetWorkApiService {

    @POST("login")
    Observable<LoginBean> login();

    @POST("register")
    Observable<RegisterBean> register();
}
