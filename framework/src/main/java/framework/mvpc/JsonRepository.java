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
    public void getHomeur() {
        Log.i("====","这是repostory层");
        model.gethomeurl();
    }

    @Override
    public void getshopcal(int count) {
        model.getshopcal(count);
    }

    @Override
    public void getBaiocal() {
        model.getBiaoCal();
    }
}
