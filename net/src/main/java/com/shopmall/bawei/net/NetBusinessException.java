package com.shopmall.bawei.net;

public class NetBusinessException extends Exception {
    private String errorCode;
    private String errorMessage;

    public NetBusinessException(String code, String message) {
        super(message);
        this.setErrorCode(code);
        this.setErrorMessage(message);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
