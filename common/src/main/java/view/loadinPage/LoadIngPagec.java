package view.loadinPage;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shopmall.bawei.common.R;

public abstract
class LoadIngPagec  extends FrameLayout {
    private View loadingView;
    private View errorView;
    private View emptyView;
    private View successView;
    private TextView errorTv;
    public LoadIngPagec(@NonNull Context context) {
        super(context);
        init(context);
    }



    public LoadIngPagec(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadIngPagec(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init(Context context){
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        errorView = layoutInflater.inflate(R.layout.view_error,null);
        addView(errorView,params);
        errorTv = errorView.findViewById(R.id.errorTvc);

        emptyView = layoutInflater.inflate(R.layout.view_empty,null);
        addView(emptyView,params);

        successView = layoutInflater.inflate(getSuccessLayoutId(),null);
        addView(successView,params);

        loadingView = layoutInflater.inflate(R.layout.view_loading,null);
        loadingView.setBackgroundColor(Color.TRANSPARENT);//背景透明？？？
        addView(loadingView,params);

        showSuccessView();
    }


    protected abstract int getSuccessLayoutId();

    private void showLoadingpage(){
        emptyView.setVisibility(GONE);
        loadingView.setVisibility(VISIBLE);
        errorView.setVisibility(GONE);

        successView.setVisibility(GONE);
    }

    private void showErrorPage(String Errormsg){
        emptyView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        errorView.setVisibility(VISIBLE);

        successView.setVisibility(GONE);
    }

    private void showEmptyPage(){
        emptyView.setVisibility(VISIBLE);
        loadingView.setVisibility(GONE);
        errorView.setVisibility(GONE);

        successView.setVisibility(GONE);
    }
    //显示正确页面
    private void showSuccessView() {
        emptyView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        errorView.setVisibility(GONE);

        successView.setVisibility(VISIBLE);
    }

    public View getSuccessView() {
        return successView;
    }


}
