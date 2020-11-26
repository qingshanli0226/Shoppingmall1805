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
}
