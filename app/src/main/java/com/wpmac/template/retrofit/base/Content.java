package com.wpmac.template.retrofit.base;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author: wpmac  Date: 2017/3/24 Time: 上午2:47
 * @email: mzeht8303@gamil.com
 */
public class Content<T>  implements Serializable {


    private static final long serialVersionUID = 8736925200488789924L;


    @SerializedName("count")
    private Integer count;

    @SerializedName("content")
    private List<T> content;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
