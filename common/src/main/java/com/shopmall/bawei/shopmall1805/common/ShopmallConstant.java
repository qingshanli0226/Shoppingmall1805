package com.shopmall.bawei.shopmall1805.common;

public class ShopmallConstant {

    public static final int READ_TIME = 50;//网络读取时间
    public static final int WRITE_TIME = 50;//网路写入时间
    public static final int CONNECT_TIME = 50;//连接时间
    public static String BASE_URL = "http://49.233.0.68:8080/";
    //public static String BASE_URL = "http://192.168.3.4:8080/";

    public static String BASE_RESOURCE_URL = BASE_URL+"atguigu";
    public static String BASE_RESOURCE_IMAGE_URL = BASE_URL+"atguigu/img";


    public static final String JSCON_ERROR_CODE = "10000";
    public static final String JSON_ERROR_MESSAGE = "服务端范湖数据解析错误";

    public static final String HTTP_ERROR_CODE = "20000";
    public static final String HTTP_ERROR_MESSAGE = "网络错误";

    public static final String SECURITY_ERROR_CODE = "30000";
    public static final String SECURITY_ERROR_MESSAGE = "权限错误";

    public static final String USER_NOT_REGISTER_ERROR = "1001";

    public static final String SOCKET_TIMEOUT_ERROR_CODE = "40000";
    public static final String SOCKET_TIMEOUT_ERROR_MESSAGE = "连接超时错误";

    public static final String PLAYER_VIDEO_URL = "videoUrl";
    public static final String PLAYER_VIDEO_LIST = "videoList";
    public static final String PLAYER_VIDEO_POSITION = "position";


    public static final String spName = "shopmall";
    public static final String tokenName = "token";

    public static final String LOGIN_ACTION = "com.bawei.shopmall.LOGIN_ACTION";

    public static final int TO_LOGIN_FROM_SHOPCAR_FRAGMTNT = 0;
    public static final int TO_LOGIN_FROM_GOODS_DETAIL_ADD_SHOPCAR = 1;
    public static final int TO_LOGIN_FROM_GOODS_DETAIL_SHOPCAR_PIC = 2;
    public static final int TO_LOGIN_FROM_MINE_FRAGMENT = 3;
    public static final String TO_LOGIN_KEY = "toLogin";

    public static final String LOGIN_ACTIVITY_PATH = "/user/LoginActivity";
    public static final String SHOP_CAR_ACTIVITY_PATH = "/shopcar/ShopCarActivity";

    public static final String FU_SHI = "服饰";
    public static final String YOU_XI = "游戏";
    public static final String DONG_MAN = "动漫";
    public static final String ZHAUNG_BAN = "装扮";
    public static final String GU_FENG = "古风";
    public static final String MAN_ZHAN = "漫展票务";
    public static final String WEN_JU = "文具";
    public static final String LING_SHI = "零食";
    public static final String SOU_SHI = "首饰";
    public static final String GENG_DUO = "更多";

    public static final String PAY_NAME="名字";
    public static final String PAY_PHONE="电话";
    public static final String PAY_ADDRESS="地址";
    public static final String PAY_NO_ADDITION="未填写";
    public static final String PAY_ALL_PRICE="总价";


}
