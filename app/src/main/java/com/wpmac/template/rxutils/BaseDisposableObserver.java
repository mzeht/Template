package com.wpmac.template.rxutils;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wpmac.template.debug.L;
import com.wpmac.template.retrofit.base.ApiException;
import com.wpmac.template.retrofit.base.ApiResult;
import com.wpmac.template.utils.NetworkUtils;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * @author: wpmac  Date: 2017/5/31 Time: 下午2:15
 * @email: mzeht8303@gamil.com
 */
public abstract class BaseDisposableObserver<T> extends DisposableObserver<ApiResult<T>> {


    private final int RESPONSE_CODE_OK = 1;       //自定义的业务逻辑，成功
    private final int RESPONSE_CODE_FAILED = 2;  //返回数据失败,严重的错误
    private Context mContext;
    private static Gson gson = new Gson();
    private int errorCode;
    private String errorMsg = "未知的错误！";

    public BaseDisposableObserver(Context context) {
        this.mContext = context;
    }

    @Override
    protected void onStart() {
        if (!NetworkUtils.isConnected()) {
            errorCode = RESPONSE_CODE_FAILED;
            errorMsg = "没有网络，请检查网络连接";
            onFailure(errorCode,errorMsg);
        }
    }

    public abstract void onSuccess(T t);


    @Override
    public void onNext(@NonNull ApiResult<T> response) {
        if (response.getResultCode() == RESPONSE_CODE_OK) {
            onSuccess(response.getObject());
        } else {
            onFailure(response.getResultCode(), response.getResultMsg());
        }

    }


    @Override
    public void onError(@NonNull Throwable t) {
        L.d(t.getMessage());

        if (t instanceof HttpException) {
            HttpException httpException = (HttpException) t;
            errorCode = httpException.code();
            errorMsg = httpException.getMessage();
            getErrorMsg(httpException);
        } else if (t instanceof ApiException) {
            ApiException exception = (ApiException) t;
            errorCode = exception.getErrorCode();
            errorMsg = exception.getErrorMessage();
        } else if (t instanceof SocketTimeoutException) {  //VPN open
            errorCode = RESPONSE_CODE_FAILED;
            errorMsg = "服务器响应超时";
        } else if (t instanceof ConnectException) {
            errorCode = RESPONSE_CODE_FAILED;
            errorMsg = "网络连接异常，请检查网络";
        } else if (t instanceof RuntimeException) {
            errorCode = RESPONSE_CODE_FAILED;
            errorMsg = "运行时错误";
        } else if (t instanceof UnknownHostException) {
            errorCode = RESPONSE_CODE_FAILED;
            errorMsg = "无法解析主机，请检查网络连接";
        } else if (t instanceof UnknownServiceException) {
            errorCode = RESPONSE_CODE_FAILED;
            errorMsg = "未知的服务器错误";
        } else if (t instanceof IOException) {  //飞行模式等
            errorCode = RESPONSE_CODE_FAILED;
            errorMsg = "没有网络，请检查网络连接";
        }
        onFailure(errorCode, errorMsg);


    }


    @CallSuper
    public void onFailure(int code, String message) {
        switch (code) {

            case 4:
                //退回到登录页面
//                Intent intent = new Intent();
//                intent.setClass(mContext, LoginActivity.class);
//                mContext.startActivity(intent);
                break;
        }
        Toast.makeText(mContext, message + "   code=" + code, Toast.LENGTH_SHORT).show();
    }

    private void getErrorMsg(HttpException httpException) {
    }

    @Override
    public void onComplete() {

    }
}
