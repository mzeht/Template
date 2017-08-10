package com.wpmac.template.retrofit;


import com.wpmac.template.bean.GankBean;
import com.wpmac.template.retrofit.po.GankIoDataBean;

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


    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * eg: http://gank.io/api/data/Android/10/1
     */
    @GET("data/{type}/{pre_page}/{page}")
    Observable<GankIoDataBean> getGankIoData(@Path("type") String id, @Path("page") int page, @Path("pre_page") int pre_page);

}
