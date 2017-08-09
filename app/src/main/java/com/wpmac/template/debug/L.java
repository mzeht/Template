package com.wpmac.template.debug;

import com.orhanobut.logger.Logger;


/**
 * Log统一管理类
 *
 *
 *
 */
public class L {
	public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
	private static final String TAG = "mzeht";

	// 下面四个是默认tag的函数
	public static void i(String msg) {
		if (isDebug){
			Logger.t(TAG).i(msg);
//			Log.i(TAG, msg);
		}

	}

	public static void d(String msg) {
		if (isDebug){
			Logger.t(TAG).d( msg);
//			Log.d(TAG, msg);
		}

	}

	public static void e(String msg) {
		if (isDebug){
			Logger.t(TAG).e( msg);
//			Log.e(TAG, msg);
		}

	}

	public static void v(String msg) {
		if (isDebug){
//			Log.v(TAG, msg);
			Logger.t(TAG).v(msg);
		}

	}

    public static void j(String msg) {
        if (isDebug){
            Logger.t(TAG).json(msg);
//			Log.i(TAG, msg);
        }

    }

	// 下面是传入自定义tag的函数
	public static void i(String tag, String msg) {
		if (isDebug){
//			Log.i(TAG, msg);
			Logger.t(TAG).i(msg);
		}

	}

	public static void d(String tag, String msg) {
		if (isDebug){
			Logger.t(TAG).d( msg);
		}

	}

	public static void e(String tag, String msg) {
		if (isDebug){
//			Log.e(TAG, msg);
			Logger.t(TAG).e( msg);
		}

	}

	public static void v(String tag, String msg) {
		if (isDebug){
//			Log.v(TAG, msg);
			Logger.t(TAG).e( msg);
		}
			
	}
}
