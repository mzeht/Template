package com.wpmac.androidnougatframework.retrofit.base;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author: wpmac  Date: 2017/3/24 Time: 上午2:43
 * @email: mzeht8303@gamil.com
 */
public class ApiPagesResult<T> implements Serializable {
    private static final long serialVersionUID = -8421200997970330114L;
    @SerializedName("resultCode")
    private String resultCode ;
    @SerializedName("resultMsg")
    private String resultMsg;
    @SerializedName("content")
    private Content<T> content;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Content<T> getContent() {
        return content;
    }

    public void setContent(Content<T> content) {
        this.content = content;
    }
}
