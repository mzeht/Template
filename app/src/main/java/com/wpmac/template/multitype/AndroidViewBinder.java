package com.wpmac.template.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wpmac.template.R;
import com.wpmac.template.retrofit.po.GankIoDataBean;
import com.wpmac.template.utils.ImgLoadUtil;
import com.wpmac.template.utils.TimeUtil;
import com.wpmac.template.view.webview.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2017/8/10.
 */
public class AndroidViewBinder extends ItemViewBinder<GankIoDataBean.ResultBean, AndroidViewBinder.ViewHolder> {


    private boolean isAll = false;
    public AndroidViewBinder(boolean isAll) {
        this.isAll = isAll;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_android, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull GankIoDataBean.ResultBean resultsBean) {


        if (isAll && "福利".equals(resultsBean.getType())) {
            holder.ivAllWelfare.setVisibility(View.VISIBLE);
            holder.llWelfareOther.setVisibility(View.GONE);
            ImgLoadUtil.displayEspImage(resultsBean.getUrl(), holder.ivAllWelfare, 1);
        } else {
            holder.ivAllWelfare.setVisibility(View.GONE);
            holder.llWelfareOther.setVisibility(View.VISIBLE);
        }

        if (isAll) {
            holder.tvContentType.setVisibility(View.VISIBLE);
            holder.tvContentType.setText(" · " + resultsBean.getType());
        } else {
            holder.tvContentType.setVisibility(View.GONE);

        }
//        holder.setResultsBean(object);
//        holder.executePendingBindings();
        holder.mTvAndroidDes.setText(resultsBean.getDesc());
        holder.mTvAndroidWho.setText(TextUtils.isEmpty(resultsBean.getWho())?"weizhi":resultsBean.getWho());
        holder.mTvAndroidTime.setText(TimeUtil.getTranslateTime(resultsBean.getPublishedAt()));

        // 显示gif图片会很耗内存
        if (resultsBean.getImages() != null
                && resultsBean.getImages().size() > 0
                && !TextUtils.isEmpty(resultsBean.getImages().get(0))) {
//                binding.ivAndroidPic.setVisibility(View.GONE);
            holder.ivAndroidPic.setVisibility(View.VISIBLE);
            ImgLoadUtil.displayGif(resultsBean.getImages().get(0), holder.ivAndroidPic);
        } else {
            holder.ivAndroidPic.setVisibility(View.GONE);
        }

        holder.llAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.loadUrl(v.getContext(), resultsBean.getUrl(), "加载中...");
            }
        });
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_all_welfare)
        ImageView ivAllWelfare;
        @BindView(R.id.tv_android_des)
        TextView mTvAndroidDes;
        @BindView(R.id.iv_android_pic)
        ImageView ivAndroidPic;
        @BindView(R.id.ll_welfare_other)
        LinearLayout llWelfareOther;
        @BindView(R.id.tv_android_who)
        TextView mTvAndroidWho;
        @BindView(R.id.tv_content_type)
        TextView tvContentType;
        @BindView(R.id.tv_android_time)
        TextView mTvAndroidTime;
        @BindView(R.id.ll_all)
        LinearLayout llAll;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
