package com.wpmac.androidnougatframework.retrofit.po;


import com.wpmac.androidnougatframework.retrofit.base.ParamNames;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * Created by jingbin on 2016/11/24.
 */
@Data
@ToString
public class GankIoDataBean implements Serializable {

    @ParamNames("error")
    private boolean error;

    @ParamNames("results")
    private List<ResultBean> results;

    @Data
    @ToString
    public static class ResultBean implements Serializable {

        @ParamNames("_id")
        private String _id;
        @ParamNames("createdAt")
        private String createdAt;
        @ParamNames("desc")
        private String desc;
        @ParamNames("publishedAt")
        private String publishedAt;
        @ParamNames("source")
        private String source;
        @ParamNames("type")
        private String type;
        @ParamNames("url")
        private String url;
        @ParamNames("used")
        private boolean used;
        @ParamNames("who")
        private String who;
        @ParamNames("images")
        private List<String> images;



        public List<String> getImages() {
            return images;
        }
    }




}
