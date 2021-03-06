package com.wpmac.template.base;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.wpmac.template.utils.Utils;

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
//        DBManager.initialize(mContext);
        Utils.init(mContext);
        Stetho.initializeWithDefaults(this);

    }

    /**
     * @return
     * 全局的上下文
     */
    public static Context getmContext() {
        return mContext;
    }


}
