package com.wpmac.template.bean;

import com.wpmac.template.retrofit.base.ParamNames;

import java.util.List;

import lombok.Data;

/**
 * Created by Administrator on 2017/8/9.
 */

@Data
public class GankBean {


    /**
     * category : ["App","Android","iOS","休息视频","拓展资源","前端","福利"]
     * error : false
     * results : {"Android":[{"_id":"597af9c1421aa90ca3bb6b91","createdAt":"2017-07-28T16:45:53.174Z","desc":"Android开发中人脸识别(静态)","publishedAt":"2017-08-09T13:49:27.260Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/d054760c2477","used":true,"who":"drawthink"},{"_id":"597dce34421aa90ca209c51b","createdAt":"2017-07-30T20:16:52.80Z","desc":"一个极简但是强大的VR本地播放器，基于IJKPlayer、MD360Player4Android，并使用DataBinding","images":["http://img.gank.io/ea71986c-4e0f-4b21-97a5-06dc311fff0b"],"publishedAt":"2017-08-09T13:49:27.260Z","source":"web","type":"Android","url":"https://github.com/wheat7/VRPlayer","used":true,"who":"麦田哥"},{"_id":"59898c0f421aa90c9203d483","createdAt":"2017-08-08T18:01:51.839Z","desc":"Android 高级混淆和代码保护技术","images":["http://img.gank.io/edbfd9aa-493e-4be9-8fb9-b148025ae658"],"publishedAt":"2017-08-09T13:49:27.260Z","source":"web","type":"Android","url":"http://drakeet.me/android-advanced-proguard-and-security/","used":true,"who":"drakeet"}],"App":[{"_id":"59798512421aa90ca209c4f9","createdAt":"2017-07-27T14:15:46.258Z","desc":"安卓音频截取及转换","images":["http://img.gank.io/5fd6fa1e-c5b4-44fc-bfa6-9cb367fa1c39"],"publishedAt":"2017-08-09T13:49:27.260Z","source":"chrome","type":"App","url":"https://github.com/google/ringdroid","used":true,"who":"jp1017"}],"iOS":[{"_id":"597c5540421aa90ca209c510","createdAt":"2017-07-29T17:28:32.705Z","desc":"一款完成度较高的记录跑步App。：）","images":["http://img.gank.io/fe1d3102-cbe9-4ef2-9def-12a41a8d7146"],"publishedAt":"2017-08-09T13:49:27.260Z","source":"web","type":"iOS","url":"https://github.com/moshuqi/YSRun","used":true,"who":"moshuqi"},{"_id":"598a803f421aa97de5c7ca44","createdAt":"2017-08-09T11:23:43.237Z","desc":"潘多拉播放器，做的超漂亮~","images":["http://img.gank.io/6bee277d-2aa4-42b5-9fa2-82654dd45bde"],"publishedAt":"2017-08-09T13:49:27.260Z","source":"chrome","type":"iOS","url":"https://github.com/AppliKeySolutions/PandoraPlayer","used":true,"who":"代码家"}],"休息视频":[{"_id":"59833082421aa97de5c7ca1d","createdAt":"2017-08-03T22:17:38.40Z","desc":"走进大众工厂，看一张铁皮是怎么变成汽车的（看条）","publishedAt":"2017-08-09T13:49:27.260Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av12897095/","used":true,"who":"LHF"}],"前端":[{"_id":"598920be421aa90ca3bb6bf1","createdAt":"2017-08-08T10:23:58.251Z","desc":"React 入门实例教程","images":["http://img.gank.io/1193c4bc-da28-41cc-9031-fd359f70341a"],"publishedAt":"2017-08-09T13:49:27.260Z","source":"api","type":"前端","url":"http://www.ruanyifeng.com/blog/2015/03/react.html","used":true,"who":"prtens"},{"_id":"5989d38d421aa90ca209c578","createdAt":"2017-08-08T23:06:53.664Z","desc":"[Vue.js]构建一个预渲染SEO友好的应用示例 [译]","publishedAt":"2017-08-09T13:49:27.260Z","source":"web","type":"前端","url":"https://jeneser.github.io/blog/2017/08/07/vue-seo-demo/","used":true,"who":"yazhe wang"}],"拓展资源":[{"_id":"598402c5421aa90c9203d471","createdAt":"2017-08-04T13:14:45.270Z","desc":"基于 GitHub Comment 实现的论坛评论系统。","publishedAt":"2017-08-09T13:49:27.260Z","source":"chrome","type":"拓展资源","url":"https://github.com/gitalk/gitalk","used":true,"who":"代码家"}],"福利":[{"_id":"598a5478421aa90ca3bb6bfc","createdAt":"2017-08-09T08:16:56.373Z","desc":"8-9","publishedAt":"2017-08-09T13:49:27.260Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fid5poqfznj20u011imzm.jpg","used":true,"who":"daimajia"}]}
     */
    @ParamNames("error")
    private boolean error;
    @ParamNames("results")
    private ResultsBean results;
    @ParamNames("category")
    private List<String> category;


    @Data
    public static class ResultsBean {
        @ParamNames("Android")
        private ContentBean Android;

        @ParamNames("iOS")
        private ContentBean ios;

        @ParamNames("前端")
        private ContentBean front;

        @ParamNames("App")
        private ContentBean app;

        @ParamNames("休息视频")
        private ContentBean restMovie;

        @ParamNames("拓展资源")
        private ContentBean resource;

        @ParamNames("瞎推荐")
        private ContentBean recommend;

        @ParamNames("福利")
        private ContentBean welfare;


        @Data
        public static class ContentBean{
            private List<SimpleBean> mSimpleBeanList;

            @Data
            public static class SimpleBean {
                /**
                 * _id : 597af9c1421aa90ca3bb6b91
                 * createdAt : 2017-07-28T16:45:53.174Z
                 * desc : Android开发中人脸识别(静态)
                 * publishedAt : 2017-08-09T13:49:27.260Z
                 * source : web
                 * type : Android
                 * url : http://www.jianshu.com/p/d054760c2477
                 * used : true
                 * who : drawthink
                 * images : ["http://img.gank.io/ea71986c-4e0f-4b21-97a5-06dc311fff0b"]
                 */

                private String _id;
                private String createdAt;
                private String desc;
                private String publishedAt;
                private String source;
                private String type;
                private String url;
                private boolean used;
                private String who;
                private List<String> images;
                private String imageUrl;


            }
        }



    }
}
