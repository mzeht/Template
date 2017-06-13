package com.wpmac.androidnougatframework;

/**
 * @author: wpmac  Date: 2017/3/27 Time: 下午3:18
 * @email: mzeht8303@gamil.com
 */
public class HelloJni {

    static {
        //加载.so类库，加载的名称去掉lib
        System.loadLibrary("hello");
    }

    public native String sayHello();
}
