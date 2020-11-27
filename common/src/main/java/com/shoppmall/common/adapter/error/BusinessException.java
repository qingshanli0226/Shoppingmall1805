package com.shoppmall.common.adapter.error;

public class BusinessException extends Exception {
    private ErrorBean errorBean;
    public BusinessException(ErrorBean errorBean){this.setErrorBean(errorBean);}

    private void setErrorBean(ErrorBean errorBean) {
        this.errorBean=errorBean;
    }
    public ErrorBean getErrorBean(){return  errorBean;}
}
