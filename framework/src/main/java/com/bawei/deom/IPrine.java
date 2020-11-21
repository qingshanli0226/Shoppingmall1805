package com.bawei.deom;

public interface IPrine <PView extends IView> {
    void attach(PView pView);
    void onDestroy();
}
