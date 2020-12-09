package com.example.net.bean;

import java.util.List;

public class ShopcarBean {


        /**
         * productId : 2691
         * productName : 【漫踪】原创 宫崎骏 龙猫 可爱雪地靴动漫保暖鞋周边冬季毛绒鞋子
         * productNum : 1
         * url : /1447232577216.jpg
         * productPrice : 199.00
         * productSelected : true
         */

        private String productId;
        private String productName;
        private String productNum;
        private String url;
        private String productPrice;
        private boolean productSelected;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductNum() {
            return productNum;
        }

        public void setProductNum(String productNum) {
            this.productNum = productNum;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(String productPrice) {
            this.productPrice = productPrice;
        }

        public boolean isProductSelected() {
            return productSelected;
        }

        public void setProductSelected(boolean productSelected) {
            this.productSelected = productSelected;
        }
}
