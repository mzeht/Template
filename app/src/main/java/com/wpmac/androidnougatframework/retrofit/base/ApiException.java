package com.wpmac.androidnougatframework.retrofit.base;


import com.wpmac.androidnougatframework.constants.Constants;

/**
 * @author: wpmac  Date: 2017/5/31 Time: 下午5:07
 * @email: mzeht8303@gamil.com
 */
public class ApiException  extends RuntimeException {
    private int mErrorCode;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(int errorCode) {
        mErrorCode = errorCode;
    }

    public ApiException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.mErrorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * 判断是否是token失效
     *
     * @return 失效返回true, 否则返回false;
     */
    public boolean isTokenExpried() {
        return mErrorCode == Constants.TOKEN_EXPRIED;
    }
}

