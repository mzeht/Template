package com.wpmac.androidnougatframework.retrofit.base;


import com.wpmac.androidnougatframework.retrofit.MzehtApi;

/**
 * @author: wpmac  Date: 2017/3/24 Time: 上午2:36
 * @email: mzeht8303@gamil.com
 */
public class ApiManager {

    protected static final Object monitor = new Object();
    static MzehtApi sMzehtApi = null;

    public static MzehtApi getSmartApi() {
        synchronized (monitor) {
            if (sMzehtApi == null) {
                sMzehtApi = new ApiRetrofit().getSmartApi();
            }
            return sMzehtApi;
        }
    }

    /**
     * 清除实例，为了刷新服务器地址
     */
    public static void clear(){

        sMzehtApi = null;

    }
}
