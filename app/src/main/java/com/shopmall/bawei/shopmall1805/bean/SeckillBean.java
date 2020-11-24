package com.shopmall.bawei.shopmall1805.bean;

import java.util.List;

public class SeckillBean {

    /**
     * end_time : 1479052800
     * list : [{"cover_price":"20.00","figure":"/1478489000522.png","name":"尚硅谷购物节特供优惠券  满600-120优惠券","origin_price":"20.00","product_id":"7100"},{"cover_price":"10.00","figure":"/1478489035167.png","name":"尚硅谷购物节特供优惠券  满300-80优惠券","origin_price":"10.00","product_id":"7101"},{"cover_price":"5.00","figure":"/1478489878735.png","name":"尚硅谷购物节特供优惠券  满160-40优惠券","origin_price":"5.00","product_id":"7102"},{"cover_price":"49.00","figure":"/1475045805488.jpg","name":"【古风原创】 自动直柄伞 晴雨伞 【云鹤游】包邮  新增折叠伞","origin_price":"69.00","product_id":"9593"},{"cover_price":"5.00","figure":"/1478678511949.png","name":"尚硅谷购物节特供优惠券  满60-20优惠券","origin_price":"5.00","product_id":"10536"},{"cover_price":"49.00","figure":"/1438680345318.jpg","name":"【古风原创】 自动直柄伞 晴雨伞 【青竹词】包邮  新增折叠伞","origin_price":"59.00","product_id":"555"}]
     * start_time : 1478772000
     */

    private String end_time;
    private String start_time;
    private List<ListBean> list;

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * cover_price : 20.00
         * figure : /1478489000522.png
         * name : 尚硅谷购物节特供优惠券  满600-120优惠券
         * origin_price : 20.00
         * product_id : 7100
         */

        private String cover_price;
        private String figure;
        private String name;
        private String origin_price;
        private String product_id;

        public String getCover_price() {
            return cover_price;
        }

        public void setCover_price(String cover_price) {
            this.cover_price = cover_price;
        }

        public String getFigure() {
            return figure;
        }

        public void setFigure(String figure) {
            this.figure = figure;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrigin_price() {
            return origin_price;
        }

        public void setOrigin_price(String origin_price) {
            this.origin_price = origin_price;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }
    }
}
