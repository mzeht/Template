package com.wpmac.androidnougatframework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by Administrator on 2017/7/5.
 */

public abstract class BaseFragment extends RxFragment {

    protected View bindingView;

    protected boolean mIsVisible = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ll = inflater.from(container.getContext()).inflate(setContent(), null);
        return ll;
    }

    /**
     * 布局
     */
    public abstract int setContent();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    protected void onInvisible() {
    }

    protected void onVisible() {
        loadData();
    }


    protected void loadData() {
    }

    protected void showContentView() {

    }

    /**
     * 加载失败后点击后的操作
     */
    protected void onRefresh() {

    }

}
