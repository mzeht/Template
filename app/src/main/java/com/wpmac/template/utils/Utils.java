package com.wpmac.template.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;


/**
 * @author: wpmac  Date: 2017/5/31 Time: 下午2:41
 * @email: mzeht8303@gamil.com
 */
public class Utils {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }
}
