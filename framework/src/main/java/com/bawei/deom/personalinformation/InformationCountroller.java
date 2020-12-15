package com.bawei.deom.personalinformation;

import com.bawei.deom.BaseAroute;
import com.bawei.deom.IView;

import java.util.HashMap;

import bean.PhoneBean;

public class InformationCountroller {
    public  interface InformationView extends IView {
        void onupdateAddressView(PhoneBean phoneBean);
        void onupdatePhoneView(PhoneBean phoneBean);
    }
    public abstract static class InformatioShow extends BaseAroute<InformationView> {
             public abstract void UpdateAddressShow(String address);
             public abstract void UpdatePhoneShow(String phone);
    }
}
