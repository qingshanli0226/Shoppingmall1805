package mvp.presenter;

import mvp.moudel.IMoudel;
import mvp.view.IView;

public class BasePresenter <M extends IMoudel,V extends IView> implements IPresenter {
   protected  M imoudel;
   protected  V iview;

    public BasePresenter(M imoudel, V iview) {
        this.imoudel = imoudel;
        this.iview = iview;
    }

    @Override
    public void destroy() {
            if (imoudel!=null){
                imoudel.destroy();
                imoudel=null;
            }
            System.gc();
    }
}
