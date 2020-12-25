package view.presenter;

import framework.Contact;
import framework.IUserDataReturn;
import framework.JsonDataCallBace;
import mode.ShopcarBean;
import view.repostory.UserReposotry;

public
class UserPresenter extends Contact.CenterUserPresenter {

    public UserPresenter(Contact.ICenterUserIview iCenterUserIview) {
        super(iCenterUserIview);
    }

    @Override
    public void onLogOut(IUserDataReturn iUserDataReturn) {
        Repostory.onLogOut(iUserDataReturn);
    }


    @Override
    protected void createRepostory() {
        Repostory  = new UserReposotry();
    }
}
