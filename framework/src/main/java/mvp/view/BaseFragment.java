package mvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import mvp.presenter.IPresenter;

public abstract class BaseFragment <P extends IPresenter> extends Fragment implements IFragment,IView {
   protected P ipresenter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return view=inflater.inflate(bandlayout(),container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return view.findViewById(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (ipresenter!=null){
            ipresenter.destroy();
            ipresenter=null;
        }
        System.gc();
    }
}
