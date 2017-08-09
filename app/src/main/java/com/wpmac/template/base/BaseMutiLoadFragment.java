package com.wpmac.template.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.wpmac.template.R;

/**
 * Created by Administrator on 2017/7/5.
 */

public abstract class BaseMutiLoadFragment extends RxFragment {

    protected View bindingView;

    protected boolean mIsVisible = false;

    // 内容布局
    protected RelativeLayout mContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ll = inflater.from(container.getContext()).inflate(R.layout.fragment_base_muti_load, null);
        mContainer = (RelativeLayout) ll.findViewById(R.id.container);
        bindingView =inflater.from(container.getContext()).inflate(setContent(), null);
        mContainer.addView(bindingView);
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
