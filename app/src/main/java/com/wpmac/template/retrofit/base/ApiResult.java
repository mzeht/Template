package com.wpmac.template.retrofit.base;

import java.io.Serializable;

/**
 * @author: wpmac  Date: 2017/3/24 Time: 上午2:41
 * @email: mzeht8303@gamil.com
 */
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = 517161603122400300L;
    private int resultCode ;
    private String resultMsg;
    private T object;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "resultCode=" + resultCode +
                ", resultMsg='" + resultMsg + '\'' +
                '}';
    }
}
