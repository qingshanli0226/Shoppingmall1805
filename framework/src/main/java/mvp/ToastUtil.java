package mvp;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    Context context;

    public ToastUtil(Context context) {
        this.context = context;
    }

    public static void toast( Context context,String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}