package com.wpmac.androidnougatframework.bean;


import com.wpmac.androidnougatframework.retrofit.base.ParamNames;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * Created by jingbin on 2016/11/30.
 * 首页item bean
 */
@Data
@ToString
public class AndroidBean implements Serializable {

    // 存储单独设置的值，用来显示title
    @ParamNames("type_title")
    private String type_title;
    // 随机图URL
    @ParamNames("image_url")
    private String image_url;

    @ParamNames("_id")
    private String _id;
    @ParamNames("createdAt")
    private String createdAt;
    @ParamNames("desc")
    private String desc;
    @ParamNames("publishedAt")
    private String publishedAt;
    @ParamNames("type")
    private String type;
    @ParamNames("url")
    private String url;
    @ParamNames("used")
    private boolean used;
    @ParamNames("who")
    private String who;

    @ParamNames("source")
    private String source;
    @ParamNames("images")
    private List<String> images;

}
