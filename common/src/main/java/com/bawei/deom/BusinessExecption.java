package com.bawei.deom;

public class BusinessExecption extends Exception {
    private ErrorBen errorBean;
    public BusinessExecption(ErrorBen errorBean){
        this.setErrorBean(errorBean);
    }

    public ErrorBen getErrorBean() {
        return errorBean;
    }

    public void setErrorBean(ErrorBen errorBean) {
        this.errorBean = errorBean;
    }
}
