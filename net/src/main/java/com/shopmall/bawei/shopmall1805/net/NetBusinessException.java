package com.shopmall.bawei.shopmall1805.net;

public class NetBusinessException extends Exception{
    private String errorCold;
    private String errorMsg;
    public NetBusinessException(String code, String errorMsg) {
        this.setErrorCode(code);
        this.setErrorMessage(errorMsg);
    }
    public String getErrorCode() {
        return errorCold;
    }
    public void setErrorCode(String errorCode) {
        this.errorCold = errorCode;
    }

    public String getErrorMessage() {
        return errorMsg;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMsg = errorMessage;
    }
}
