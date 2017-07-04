package com.wpmac.androidnougatframework.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wpmac.androidnougatframework.R;
import com.wpmac.androidnougatframework.bean.AndroidBean;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2017/7/4.
 */
public class EveryDayThreeViewBinder extends ItemViewBinder<AndroidBean, EveryDayThreeViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_everyday_three, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AndroidBean everyDayThree) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
