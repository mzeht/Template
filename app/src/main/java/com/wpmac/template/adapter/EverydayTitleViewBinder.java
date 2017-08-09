package com.wpmac.template.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wpmac.template.R;
import com.wpmac.template.bean.AndroidBean;
import com.wpmac.template.rxbus.RxBus;
import com.wpmac.template.rxbus.RxCodeConstants;
import com.wpmac.template.utils.CommonUtils;

import java.util.List;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2017/8/8.
 */
public class EverydayTitleViewBinder extends ItemViewBinder<List<AndroidBean>, EverydayTitleViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_everyday_title, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull List<AndroidBean> object) {

        int index = 0;
        String title = object.get(0).getType_title();
        holder.tvTitleType.setText(title);
        if ("Android".equals(title)) {
            holder.ivTitleType.setImageDrawable(CommonUtils.getDrawable(R.drawable.home_title_android));
            index = 0;
        } else if ("福利".equals(title)) {
            holder.ivTitleType.setImageDrawable(CommonUtils.getDrawable(R.drawable.home_title_meizi));
            index = 1;
        } else if ("IOS".equals(title)) {
            holder.ivTitleType.setImageDrawable(CommonUtils.getDrawable(R.drawable.home_title_ios));
            index = 2;
        } else if ("休息视频".equals(title)) {
            holder.ivTitleType.setImageDrawable(CommonUtils.getDrawable(R.drawable.home_title_movie));
            index = 2;
        } else if ("拓展资源".equals(title)) {
            holder.ivTitleType.setImageDrawable(CommonUtils.getDrawable(R.drawable.home_title_source));
            index = 2;
        } else if ("瞎推荐".equals(title)) {
            holder.ivTitleType.setImageDrawable(CommonUtils.getDrawable(R.drawable.home_title_xia));
            index = 2;
        } else if ("前端".equals(title)) {
            holder.ivTitleType.setImageDrawable(CommonUtils.getDrawable(R.drawable.home_title_qian));
            index = 2;
        } else if ("App".equals(title)) {
            holder.ivTitleType.setImageDrawable(CommonUtils.getDrawable(R.drawable.home_title_app));
            index = 2;
        }

//        if (position != 0) {
            holder.viewLine.setVisibility(View.VISIBLE);
//        } else {
            holder.viewLine.setVisibility(View.GONE);
//        }

        final int finalIndex = index;
        holder.llTitleMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getDefault().post(RxCodeConstants.JUMP_TYPE, finalIndex);
            }
        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivTitleType;
        private TextView tvTitleType;
        private View viewLine;
        private LinearLayout llTitleMore;

        ViewHolder(View itemView) {
            super(itemView);
            this.ivTitleType = (ImageView) itemView.findViewById(R.id.iv_title_type);
            this.tvTitleType = (TextView) itemView.findViewById(R.id.tv_title_type);
            this.viewLine = (View) itemView.findViewById(R.id.view_line);
            this.llTitleMore = (LinearLayout) itemView.findViewById(R.id.ll_title_more);
        }
    }
}
