package com.wpmac.androidnougatframework.retrofit;


import com.wpmac.androidnougatframework.retrofit.base.ApiResult;
import com.wpmac.androidnougatframework.retrofit.po.LoginPo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author: wpmac  Date: 2017/3/24 Time: 上午2:33
 * @email: mzeht8303@gamil.com
 */
public interface MzehtApi {

    /**
     * 登录
     *
     * @return
     */
    @POST("doLogin")
    Observable<ApiResult<LoginPo>> login(@Field("name") String name,@Field("password")String password);


    @FormUrlEncoded
    @POST("demo")
    Observable<ApiResult<LoginPo>> loginByForm(@FieldMap Map<String,String> map);

}
