package com.wpmac.androidnougatframework.ui.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.wpmac.androidnougatframework.R;
import com.wpmac.androidnougatframework.base.BaseLoadFragment;
import com.wpmac.androidnougatframework.rxbus.RxBus;
import com.wpmac.androidnougatframework.rxbus.RxCodeConstants;
import com.wpmac.androidnougatframework.view.MyFragmentPagerAdapter;

import org.reactivestreams.Subscription;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/6/30.
 */

public class GankFragment extends BaseLoadFragment {
    private ArrayList<String> mTitleList = new ArrayList<>(4);
    private ArrayList<Fragment> mFragments = new ArrayList<>(4);
    @BindView(R.id.vp_gank)
    ViewPager vpGank;
    @BindView(R.id.tab_gank)
    TabLayout tabGank;



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showLoading();
        initFragmentList();
        ButterKnife.bind(this, bindingView);
        /**
         * 注意使用的是：getChildFragmentManager，
         * 这样setOffscreenPageLimit()就可以添加上，保留相邻3个实例，切换时不会卡
         * 但会内存溢出，在显示时加载数据
         */
        MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), mFragments, mTitleList);
        vpGank.setAdapter(myAdapter);
        // 左右预加载页面的个数
        vpGank.setOffscreenPageLimit(3);
        myAdapter.notifyDataSetChanged();
        tabGank.setTabMode(TabLayout.MODE_FIXED);
        tabGank.setupWithViewPager(vpGank);
        showContentView();
        // item点击跳转
        initRxBus();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_gank;
    }

    private void initFragmentList() {
        mTitleList.add("每日推荐");
        mTitleList.add("福利");
        mTitleList.add("干货订制");
        mTitleList.add("大安卓");
        mFragments.add(new EverydayFragment());
        mFragments.add(new EverydayFragment());
        mFragments.add(new EverydayFragment());
        mFragments.add(new EverydayFragment());
//        mFragments.add(new WelfareFragment());
//        mFragments.add(new CustomFragment());
//        mFragments.add(AndroidFragment.newInstance("Android"));
    }

    /**
     * 每日推荐点击"更多"跳转
     */
    private void initRxBus() {
        Subscription subscription = (Subscription) RxBus.getDefault().toObservable(RxCodeConstants.JUMP_TYPE, Integer.class)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        if (integer == 0) {
                            vpGank.setCurrentItem(3);
                        } else if (integer == 1) {
                            vpGank.setCurrentItem(1);
                        } else if (integer == 2) {
                            vpGank.setCurrentItem(2);
                        }
                    }


                });
    }


}
