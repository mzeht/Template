package com.wpmac.template.base;

/**
 * Created by Administrator on 2017/7/4.
 */

public interface AdapterDelegate<T> {
    int getLayoutId();
    void bind(RecyclerViewHolder holder, T data, int position);
}
