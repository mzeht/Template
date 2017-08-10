package com.wpmac.template.ui.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wpmac.template.R;
import com.wpmac.template.base.BaseLoadFragment;
import com.wpmac.template.databinding.FragmentTestBinding;

/**
 * Created by Administrator on 2017/8/9.
 */

public class TestFragment extends BaseLoadFragment<FragmentTestBinding> {

    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    @Override
    public int setContent() {
        return R.layout.fragment_test;
    }

    public static TestFragment newInstance() {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        showContentView();
        isPrepared = true;



    }

    @Override
    protected void loadData() {
        if(!isPrepared || !mIsVisible) {
            return;
        }
//        ApiManagment.getInstance()
//                .getTingApi().getFrontpage()
//                .compose(RxObservableUtils.applySchedulers())
//                .compose(this.bindToLifecycle())
//                .subscribe(new DisposableObserver<FrontpageBean>() {
//                    @Override
//                    public void onNext(@NonNull FrontpageBean frontpageBean) {
//                        showContentView();
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

            showContentView();


    }
}
