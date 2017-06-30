package com.wpmac.androidnougatframework.adapter;

import android.view.ViewGroup;

import com.wpmac.androidnougatframework.base.BaseRecyclerViewAdapter;
import com.wpmac.androidnougatframework.base.BaseRecyclerViewHolder;


/**
 * Created by jingbin on 2016/12/24.
 */

public class EmptyAdapter extends BaseRecyclerViewAdapter<String> {

    @Override
    public void convert(int position, BaseRecyclerViewHolder holder, String bean) {

    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }
}
