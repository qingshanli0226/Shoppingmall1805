package com.shopmall.bawei.shopmall1805.common;

public class BusinessException extends Exception {
    private ErrorBean errorBean;

    public BusinessException(ErrorBean errorBean) {
        this.errorBean = errorBean;
    }

    public ErrorBean getErrorBean() {
        return errorBean;
    }

    public void setErrorBean(ErrorBean errorBean) {
        this.errorBean = errorBean;
    }
}
