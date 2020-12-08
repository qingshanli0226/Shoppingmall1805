package framework.mvpc;

import android.util.Log;

import framework.Contact;

public
class JsonRepository extends Contact.centerUserRepostory {
    @Override
    protected void createModel() {
        model = new jsonModel();
    }

    @Override
    public void getshopcal(int count) {
        model.getshopcal(count);
    }

    @Override
    public void loginAndRegister(int count, String username, String password) {
        model.loginAndRegister(count,username,password);
    }

    @Override
    public void shcarShop(int count) {
        model.shcarShop(count);
    }

}
