package view.repostory;

import framework.Contact;
import framework.IUserDataReturn;
import view.model.UserModel;

public
class UserReposotry extends Contact.CenterUserReposotry {

    @Override
    protected void createModel() {
        model = new UserModel();
    }
    @Override
    public void onLogOut(IUserDataReturn iUserDataReturn) {
        model.onLogOut(iUserDataReturn);
    }
}
