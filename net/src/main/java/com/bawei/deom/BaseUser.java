package com.bawei.deom;

public class BaseUser {
    public static String BASE_URL=" http://49.233.0.68:8080";
    //请求数据的基本url
    public static final String BASE_JSON="/atguigu/json/";
    //请求图片的Url
    public static final String BASE_IMAGE = "/atguigu/img";

    //小裙子
    public static final String SKIRT_URL = BASE_JSON + "SKIRT_URL.json";
    //上衣
    public static final String JACKET_URL = BASE_JSON + "JACKET_URL.json";
    //下装(裤子)
    public static final String PANTS_URL = BASE_JSON + "PANTS_URL.json";
    //外套
    public static final String OVERCOAT_URL = BASE_JSON + "OVERCOAT_URL.json";
    //配件
    public static final String ACCESSORY_URL = BASE_JSON + "ACCESSORY_URL.json";
    //包包
    public static final String BAG_URL = BASE_JSON + "BAG_URL.json";
    //装扮
    public static final String DRESS_UP_URL = BASE_JSON + "DRESS_UP_URL.json";
    //居家宅品
    public static final String HOME_PRODUCTS_URL = BASE_JSON + "HOME_PRODUCTS_URL.json";
    //办公文具
    public static final String STATIONERY_URL = BASE_JSON + "STATIONERY_URL.json";
    //数码周边
    public static final String DIGIT_URL = BASE_JSON +  "DIGIT_URL.json";
    //游戏专区
    public static final String GAME_URL = BASE_JSON + "GAME_URL.json";

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
}
