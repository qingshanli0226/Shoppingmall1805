package com.shopmall.bawei.net.mode;

public class OrderInfoBean {

    private String orderInfo;
    private String outTradeNo;

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @Override
    public String toString() {
        return "OrderInfoBean{" +
                "orderInfo='" + orderInfo + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                '}';
    }
}
