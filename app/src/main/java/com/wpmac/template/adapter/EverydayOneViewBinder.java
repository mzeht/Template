package com.wpmac.template.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wpmac.template.R;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2017/8/8.
 */
public class EverydayOneViewBinder extends ItemViewBinder<EverydayOne, EverydayOneViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_everyday_one, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull EverydayOne everydayOne) {


    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private View ll_one_photo_line;
        private ImageView iv_one_photo;
        private TextView tv_one_photo_title;

        ViewHolder(View itemView) {
            super(itemView);
            this.ll_one_photo_line = itemView.findViewById(R.id.ll_one_photo_line);
            this.iv_one_photo = (ImageView) itemView.findViewById(R.id.iv_one_photo);
            this.tv_one_photo_title = (TextView) itemView.findViewById(R.id.tv_one_photo_title);
        }
    }
}
