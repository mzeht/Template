package com.wpmac.androidnougatframework.retrofit.base;

import com.google.gson.annotations.SerializedName;
import com.wpmac.androidnougatframework.constants.Constants;

/**
 * @author: wpmac  Date: 2017/5/31 Time: 下午5:05
 * @email: mzeht8303@gamil.com
 */
public class HttpStatus {
    @SerializedName("resultCode")
    private int mCode;
    @SerializedName("resultMsg")
    private String mMessage;

    public int getCode() {
        return mCode;
    }

    public String getMessage() {
        return mMessage;
    }

    /**
     * API是否请求失败
     *
     * @return 失败返回true, 成功返回false
     */
    public boolean isSuccess() {
        return mCode == Constants.RESP_CODE_SUCCESS;
    }
}
