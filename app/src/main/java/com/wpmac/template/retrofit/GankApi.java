package com.wpmac.template.retrofit;


import com.wpmac.template.bean.GankBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author: wpmac  Date: 2017/3/24 Time: 上午2:33
 * @email: mzeht8303@gamil.com
 */
public interface GankApi {

//    /**
//     * 登录
//     *
//     * @return
//     */
//    @POST("doLogin")
//    Observable<ApiResult<LoginPo>> login(@Field("name") String name,@Field("password")String password);
//
//
//    @FormUrlEncoded
//    @POST("demo")
//    Observable<ApiResult<LoginPo>> loginByForm(@FieldMap Map<String,String> map);


    /**
     * 每日数据： http://gank.io/api/day/年/月/日
     * eg:http://gank.io/api/day/2015/08/06
     */
    @GET("day/{year}/{month}/{day}")
    Observable<GankBean> getGankIoDay(@Path("year") String year, @Path("month") String month, @Path("day") String day);

}
