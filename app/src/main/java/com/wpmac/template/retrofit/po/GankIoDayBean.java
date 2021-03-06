package com.wpmac.template.retrofit.po;


import com.wpmac.template.bean.AndroidBean;
import com.wpmac.template.retrofit.base.ParamNames;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * Created by jingbin on 2016/11/24.
 */
@Data
@ToString
public class GankIoDayBean implements Serializable {

    @ParamNames("error")
    private boolean error;
    @ParamNames("results")
    private ResultsBean results;
    @ParamNames("category")
    private List<String> category;


    @Data
    public static class ResultsBean {
        /**
         * _id : 56cc6d23421aa95caa707a69
         * createdAt : 2015-08-06T07:15:52.65Z
         * desc : 类似Link Bubble的悬浮式操作设计
         * images : ["http://img.gank.io/2f0b6c5f-6de7-4ba3-94ad-98bf721ee447"]
         * source : web
         * publishedAt : 2015-08-07T03:57:48.45Z
         * type : Android
         * url : https://github.com/recruit-lifestyle/FloatingView
         * used : true
         * who : mthli
         */

        @ParamNames("Android")
        private List<AndroidBean> Android;

        @ParamNames("iOS")
        private List<AndroidBean> ios;

        @ParamNames("前端")
        private List<AndroidBean> front;

        @ParamNames("App")
        private List<AndroidBean> app;

        @ParamNames("休息视频")
        private List<AndroidBean> restMovie;

        @ParamNames("拓展资源")
        private List<AndroidBean> resource;

        @ParamNames("瞎推荐")
        private List<AndroidBean> recommend;

        @ParamNames("福利")
        private List<AndroidBean> welfare;

    }


}
