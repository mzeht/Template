package com.wpmac.template.base;

import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wpmac.template.R;
import com.wpmac.template.utils.CommonUtils;
import com.wpmac.template.utils.PerfectClickListener;
import com.wpmac.template.view.statusbar.StatusBarUtil;

/**
 * Created by wpmac on 2017/1/6.
 */

public abstract class BaseLoadActivity extends BaseActivity {


    protected View base;
    protected View content;
    private LinearLayout llProgressBar;
    private View refresh;
    private AnimationDrawable mAnimationDrawable;
    private Toolbar mToolbar;

    protected <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }




    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        base = getLayoutInflater().from(this).inflate(R.layout.activity_base, null, false);
        content = getLayoutInflater().from(this).inflate(layoutResID, null, false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        content.getRootView().setLayoutParams(params);
        RelativeLayout mContainer = (RelativeLayout) base.getRootView().findViewById(R.id.container);
        mContainer.addView(content);
        getWindow().setContentView(base);
        StatusBarUtil.setColor(this, CommonUtils.getColor(R.color.colorTheme), 0);
        llProgressBar = getView(R.id.ll_progress_bar);
        refresh = getView(R.id.ll_error_refresh);
        ImageView img = getView(R.id.img_progress);
        // 加载动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }

        setToolBar();
        // 点击加载失败布局
        refresh.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showLoading();
                onRefresh();
            }
        });
        content.setVisibility(View.GONE);
    }

    /**
     * 设置titlebar
     */
    protected void setToolBar() {
        mToolbar = getView(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }




    protected void showLoading() {
        if (llProgressBar.getVisibility() != View.VISIBLE) {
            llProgressBar.setVisibility(View.VISIBLE);
        }
        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (content.getVisibility() != View.GONE) {
            content.setVisibility(View.GONE);
        }
        if (refresh.getVisibility() != View.GONE) {
            refresh.setVisibility(View.GONE);
        }
    }

    protected void showContentView() {
        if (llProgressBar.getVisibility() != View.GONE) {
            llProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (refresh.getVisibility() != View.GONE) {
            refresh.setVisibility(View.GONE);
        }
        if (content.getVisibility() != View.VISIBLE) {
            content.setVisibility(View.VISIBLE);
        }
    }

    protected void showError() {
        if (llProgressBar.getVisibility() != View.GONE) {
            llProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (refresh.getVisibility() != View.VISIBLE) {
            refresh.setVisibility(View.VISIBLE);
        }
        if (content.getVisibility() != View.GONE) {
            content.setVisibility(View.GONE);
        }
    }

    /**
     * 失败后点击刷新
     */
    protected void onRefresh() {

    }


}
