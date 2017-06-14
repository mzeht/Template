package com.wpmac.androidnougatframework.base;

import android.app.Application;
import android.content.Context;

import com.wpmac.androidnougatframework.db.DBManager;
import com.wpmac.androidnougatframework.utils.Utils;

/**
 * Created by wpmac on 2017/1/6.
 */

public class BaseApplication extends Application {

    private static Context mContext;

    private static BaseApplication sApplication;

    public static BaseApplication getInstance() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        sApplication = this;
        BasePreference.initialize(mContext);
        DBManager.initialize(mContext);
        Utils.init(mContext);

    }

    /**
     * @return
     * 全局的上下文
     */
    public static Context getmContext() {
        return mContext;
    }


}
