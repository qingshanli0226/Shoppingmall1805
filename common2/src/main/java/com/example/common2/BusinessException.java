package com.example.common2;


public class BusinessException extends Exception {
    private ErrorBean errorBean;
    public BusinessException(ErrorBean errorBean){
        this.setErrorBean(errorBean);
    }

    public ErrorBean getErrorBean() {
        return errorBean;
    }

    public void setErrorBean(ErrorBean errorBean) {
        this.errorBean = errorBean;
    }
}
