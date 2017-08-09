package com.wpmac.template.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

import com.wpmac.template.constants.Constants;


public class BasePreference extends Preference {
    private static BasePreference mBasePreference;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;
    private String SP_NAME = "MZEHT";
    //员工编号
    public static final String EMPLOYEE_NO = "EMPLOYEE_NO";
    //网点编号
    public static final String POINT_NO = "POINT_NO";
    //是否登录
    public static final String IS_LOGINED = "IS_LOGINED";
    //默认登录名称
    public static final String DEFALUT_NAME = "DEFALUT_NAME";
    //服务器地址
    public static final String DEFALUT_SERVER = "DEFALUT_SERVER";


    /**
     * 获取单例
     *
     * @return
     */
    public static BasePreference getInstance() {
        return mBasePreference;
    }

    /**
     * 构造函数
     *
     * @param context
     */
    private BasePreference(Context context) {
        super(context);
        mBasePreference = this;
        sp = context.getSharedPreferences(SP_NAME, 0);
        editor = sp.edit();
    }

    /**
     * 单例初始化
     *
     * @param paramContext
     */
    public static void initialize(Context paramContext) {

        if (mBasePreference == null) {
            mBasePreference = new BasePreference(paramContext);
        }

    }

    /**
     * 保存EMPLOYEE_NO
     *
     * @param
     */
    public void saveEmployeeNo(String s) {
        editor.putString(EMPLOYEE_NO, s);
        editor.commit();
    }

    /**
     * 获取EMPLOYEE_NO
     *
     * @return
     */
    public String getEmployeeNo() {
        return sp.getString(EMPLOYEE_NO, "");
    }

    /**
     * 保存POINT_NO
     *
     * @param
     */
    public void savePointNo(String s) {
        editor.putString(POINT_NO, s);
        editor.commit();
    }

    /**
     * 获取POINT_NO
     *
     * @return
     */
    public String getPointNo() {
        return sp.getString(POINT_NO, "");
    }

    /**
     * IS_LOGINED
     *
     * @param
     */
    public void saveIsLogined(boolean s) {
        editor.putBoolean(IS_LOGINED, s);
        editor.commit();
    }

    /**
     * 获取POINT_NO
     *
     * @return
     */
    public boolean getIsLogined() {
        return sp.getBoolean(IS_LOGINED, false);
    }

    /**
     * 默认登录名称
     *
     * @param
     */
    public void saveLoginName(String s) {
        editor.putString(DEFALUT_NAME, s);
        editor.commit();
    }

    /**
     * 获取登录名称
     *
     * @return
     */
    public String getLoginName() {
        return sp.getString(DEFALUT_NAME, "");
    }

    /**
     * 保存服务器地址
     *
     * @param
     */
    public void saveServerAddress(String s) {
        editor.putString(DEFALUT_SERVER, s);
        editor.commit();
        Constants.SERVER_URL = s;
    }

    /**
     * 获取服务器地址
     *
     * @return
     */
    public String getServerAddress() {
        return sp.getString(DEFALUT_SERVER, "www.baidu.com");
    }



    //------------------------------------- 通用封装-----------------------------------------//
    public static String getPrefString( String key,
                                       final String defaultValue) {

        return sp.getString(key, defaultValue);
    }

    public static void setPrefString( final String key,
                                     final String value) {
         sp.edit().putString(key, value).commit();
    }

    public static boolean getPrefBoolean( final String key,
                                         final boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public static boolean hasKey( final String key) {
        return sp.contains(key);
    }

    public static void setPrefBoolean( final String key,
                                      final boolean value) {
        sp.edit().putBoolean(key, value).commit();
    }

    public static void setPrefInt( final String key,
                                  final int value) {
        sp.edit().putInt(key, value).commit();
    }

    public static int getPrefInt( final String key,
                                 final int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public static void setPrefFloat( final String key,
                                    final float value) {
        sp.edit().putFloat(key, value).commit();
    }

    public static float getPrefFloat( final String key,
                                     final float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    public static void setSettingLong( final String key,
                                      final long value) {
        sp.edit().putLong(key, value).commit();
    }

    public static long getPrefLong( final String key,
                                   final long defaultValue) {

        return sp.getLong(key, defaultValue);
    }

    public static void clearPreference() {
        editor.clear();
        editor.commit();
    }


}
