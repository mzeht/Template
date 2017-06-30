package com.wpmac.androidnougatframework.ui.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xrecyclerview.XRecyclerView;
import com.wpmac.androidnougatframework.R;
import com.wpmac.androidnougatframework.adapter.EmptyAdapter;
import com.wpmac.androidnougatframework.base.BaseLoadFragment;
import com.wpmac.androidnougatframework.bean.AndroidBean;
import com.wpmac.androidnougatframework.constants.Constants;
import com.wpmac.androidnougatframework.http.cache.ACache;
import com.wpmac.androidnougatframework.model.EverydayModel;
import com.wpmac.androidnougatframework.retrofit.base.ApiManagment;
import com.wpmac.androidnougatframework.retrofit.po.FrontpageBean;
import com.wpmac.androidnougatframework.rxbus.RxBus;
import com.wpmac.androidnougatframework.rxbus.RxBusBaseMessage;
import com.wpmac.androidnougatframework.rxbus.RxCodeConstants;
import com.wpmac.androidnougatframework.utils.CommonUtils;
import com.wpmac.androidnougatframework.utils.DebugUtil;
import com.wpmac.androidnougatframework.utils.GlideImageLoader;
import com.wpmac.androidnougatframework.utils.PerfectClickListener;
import com.wpmac.androidnougatframework.utils.SPUtils;
import com.wpmac.androidnougatframework.utils.TimeUtil;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * 每日推荐
 * 更新逻辑：判断是否是第二天(相对于2016-11-26)
 * 是：判断是否是大于12：30
 * *****     |是：刷新当天数据
 * *****     |否：使用缓存：|无：请求前一天数据,直到请求到数据为止
 * **********             |有：使用缓存
 * 否：使用缓存 ： |无：请求今天数据
 * **********    |有：使用缓存
 */
public class EverydayFragment extends BaseLoadFragment {

    private static final String TAG = "EverydayFragment";
    private ACache maCache;
    private ArrayList<List<AndroidBean>> mLists;
    private ArrayList<String> mBannerImages;
    private EverydayModel mEverydayModel;
    private HeaderItemEverydayBinding mHeaderBinding=new HeaderItemEverydayBinding();
    private IncludeEverydayBinding IncludeEverydayBinding=new IncludeEverydayBinding();
    private FooterItemEverydayBinding mFooterBinding;
    private View mHeaderView;
    private View mFooterView;
    private EverydayAdapter mEverydayAdapter;
    private boolean mIsPrepared = false;
    private boolean mIsFirst = true;
    // 是否是上一天的请求
    private boolean isOldDayRequest;
    private RotateAnimation animation;
    // 记录请求的日期
    String year = getTodayTime().get(0);
    String month = getTodayTime().get(1);
    String day = getTodayTime().get(2);

    @BindView(R.id.ll_loading)
    LinearLayout llLoading;

    @BindView(R.id.iv_loading)
    ImageView ivLoading;

    @BindView(R.id.xrv_everyday)
    XRecyclerView xrvEveryday;


    class HeaderItemEverydayBinding{

        @BindView(R.id.banner)
        Banner banner;

        @BindView(R.id.include_everyday)
        RelativeLayout includeEveryday;

    }

    class IncludeEverydayBinding {
        @BindView(R.id.tv_daily_text)
        TextView tvDailyText;

        @BindView(R.id.ib_xiandu)
        ImageButton ibXiandu;

        @BindView(R.id.ib_movie_hot)
        ImageButton ibMovieHot;

    }

    class FooterItemEverydayBinding{

    }

    @Override
    public int setContent() {
        return R.layout.fragment_everyday;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        showLoading();
        showContentView();
        ButterKnife.bind(this, bindingView);

        llLoading.setVisibility(View.VISIBLE);
        animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(3000);//设置动画持续时间
        animation.setInterpolator(new LinearInterpolator());//不停顿
        animation.setRepeatCount(10);
        ivLoading.setAnimation(animation);
        animation.startNow();
        mBannerImages = (ArrayList<String>) maCache.getAsObject(Constants.BANNER_PIC);
        DebugUtil.error("----mBannerImages: " + (mBannerImages == null));
        DebugUtil.error("----mLists: " + (mLists == null));
        mHeaderView = LayoutInflater.from(getActivity()).inflate(R.layout.header_item_everyday, null);
//        mHeaderBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.header_item_everyday, null, false);
        // 设置本地数据点击事件等
        ButterKnife.bind(mHeaderBinding, mHeaderView);
        ButterKnife.bind(IncludeEverydayBinding, mHeaderBinding.includeEveryday);
        initLocalSetting();
        initRecyclerView();

        mIsPrepared = true;
        /**
         * 因为启动时先走loadData()再走onActivityCreated，

         maCache = ACache.get(getContext());
         mEverydayModel = new EverydayModel();
         * 所以此处要额外调用load(),不然最初不会加载内容
         */
        loadData();
    }

    /**
     * 获取当天日期
     */
    private ArrayList<String> getTodayTime() {
        String data = TimeUtil.getData();
        String[] split = data.split("-");
        String year = split[0];
        String month = split[1];
        String day = split[2];
        ArrayList<String> list = new ArrayList<>();
        list.add(year);
        list.add(month);
        list.add(day);
        return list;
    }

    @Override
    protected void loadData() {
        // 显示时轮播图滚动
        if (mHeaderBinding != null && mHeaderBinding.banner != null) {
            mHeaderBinding.banner.startAutoPlay();
            mHeaderBinding.banner.setDelayTime(4000);
        }

        if (!mIsVisible || !mIsPrepared) {
            return;
        }

        String oneData = SPUtils.getString("everyday_data", "2016-11-26");
        if (!oneData.equals(TimeUtil.getData())) {// 是第二天
            if (TimeUtil.isRightTime()) {//大于12：30,请求

                isOldDayRequest = false;
                mEverydayModel.setData(getTodayTime().get(0), getTodayTime().get(1), getTodayTime().get(2));
                showRotaLoading(true);
                loadBannerPicture();
                showContentData();
            } else {// 小于，取缓存没有请求前一天

                ArrayList<String> lastTime = TimeUtil.getLastTime(getTodayTime().get(0), getTodayTime().get(1), getTodayTime().get(2));
                mEverydayModel.setData(lastTime.get(0), lastTime.get(1), lastTime.get(2));
                year = lastTime.get(0);
                month = lastTime.get(1);
                day = lastTime.get(2);

                isOldDayRequest = true;// 是昨天
                getACacheData();
            }
        } else {// 当天，取缓存没有请求当天

            isOldDayRequest = false;
            getACacheData();
        }
    }

    private void initLocalSetting() {
        mEverydayModel.setData(getTodayTime().get(0), getTodayTime().get(1), getTodayTime().get(2));
//        DebugUtil.error("" + year + month + day);
        // 显示日期,去掉第一位的"0"
        IncludeEverydayBinding.tvDailyText.setText(getTodayTime().get(2).indexOf("0") == 0 ?
                getTodayTime().get(2).replace("0", "") : getTodayTime().get(2));
        IncludeEverydayBinding.ibXiandu.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
//                WebViewActivity.loadUrl(v.getContext(), "https://gank.io/xiandu", "加载中...");
            }
        });
        IncludeEverydayBinding.ibMovieHot.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                RxBus.getDefault().post(RxCodeConstants.JUMP_TYPE_TO_ONE, new RxBusBaseMessage());
            }
        });
    }

    /**
     * 取缓存
     */
    private void getACacheData() {
        if (!mIsFirst) {
            return;
        }

        if (mBannerImages != null && mBannerImages.size() > 0) {
            mHeaderBinding.banner.setImages(mBannerImages).setImageLoader(new GlideImageLoader()).start();
        } else {
            loadBannerPicture();
        }
        mLists = (ArrayList<List<AndroidBean>>) maCache.getAsObject(Constants.EVERYDAY_CONTENT);
        if (mLists != null && mLists.size() > 0) {
            setAdapter(mLists);
        } else {
            showRotaLoading(true);
            showContentData();
        }
    }


    /**
     * 加载正文内容
     */
    private void showContentData() {


        ApiManagment.getInstance().getTingApi().getFrontpage()
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<FrontpageBean>() {

                    @Override
                    public void onError(Throwable e) {
//                        listener.loadFailed();
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(FrontpageBean frontpageBean) {

                        frontpageBean.getResult().getFocus();

//                        listener.loadSuccess(frontpageBean);
                    }
                });
//        mEverydayModel.showRecyclerViewData(new RequestImpl() {
//            @Override
//            public void loadSuccess(Object object) {
//                if (mLists != null) {
//                    mLists.clear();
//                }
//                mLists = (ArrayList<List<AndroidBean>>) object;
//                if (mLists.size() > 0 && mLists.get(0).size() > 0) {
//                    setAdapter(mLists);
//                } else {
//                    requestBeforeData();
//                }
//            }
//
//            @Override
//            public void loadFailed() {
//                if (mLists != null && mLists.size() > 0) {
//                    return;
//                }
//                showError();
//            }
//
//            @Override
//            public void addSubscription(Subscription subscription) {
//                EverydayFragment.this.addSubscription(subscription);
//            }
//        });
    }

    /**
     * 没请求到数据就取缓存，没缓存一直请求前一天数据
     */
    private void requestBeforeData() {
        mLists = (ArrayList<List<AndroidBean>>) maCache.getAsObject(Constants.EVERYDAY_CONTENT);
        if (mLists != null && mLists.size() > 0) {
            setAdapter(mLists);
        } else {
            // 一直请求，知道请求到数据为止
            ArrayList<String> lastTime = TimeUtil.getLastTime(year, month, day);
            mEverydayModel.setData(lastTime.get(0), lastTime.get(1), lastTime.get(2));
            year = lastTime.get(0);
            month = lastTime.get(1);
            day = lastTime.get(2);
            showContentData();
        }
    }

    /**
     * 无数据返回时，暂时去掉
     */
    private void setEmptyAdapter() {
        showRotaLoading(false);

        EmptyAdapter emptyAdapter = new EmptyAdapter();
        ArrayList<String> list = new ArrayList<>();
        list.add(CommonUtils.getString(R.string.string_everyday_empty));
        emptyAdapter.addAll(list);
        xrvEveryday.setAdapter(emptyAdapter);

        // 保存请求的日期
        SPUtils.putString("everyday_data", TimeUtil.getData());

        mIsFirst = false;
    }

    private void initRecyclerView() {
        xrvEveryday.setPullRefreshEnabled(false);
        xrvEveryday.setLoadingMoreEnabled(false);
        if (mHeaderView == null) {
            mHeaderView = mHeaderView.getRootView();
            xrvEveryday.addHeaderView(mHeaderView);
        }
        if (mFooterView == null) {

            mFooterView =  LayoutInflater.from(getActivity()).inflate(R.layout.footer_item_everyday, null);
            xrvEveryday.addFootView(mFooterView, true);
            xrvEveryday.noMoreLoading();
        }
        xrvEveryday.setLayoutManager(new LinearLayoutManager(getContext()));
        // 需加，不然滑动不流畅
        xrvEveryday.setNestedScrollingEnabled(false);
        xrvEveryday.setHasFixedSize(false);
        xrvEveryday.setItemAnimator(new DefaultItemAnimator());
    }

    private void setAdapter(ArrayList<List<AndroidBean>> lists) {
        showRotaLoading(false);
        if (mEverydayAdapter == null) {
            mEverydayAdapter = new EverydayAdapter();
        } else {
            mEverydayAdapter.clear();
        }
        mEverydayAdapter.addAll(lists);
//        DebugUtil.error("----111111 ");
//        xrvEveryday.setAdapter(mEverydayAdapter);
//        mEverydayAdapter.notifyDataSetChanged();
//        DebugUtil.error("----222222 ");
        maCache.remove(Constants.EVERYDAY_CONTENT);
        // 缓存三天，这样就可以取到缓存了！
        maCache.put(Constants.EVERYDAY_CONTENT, lists, 259200);

        if (isOldDayRequest) {
            ArrayList<String> lastTime = TimeUtil.getLastTime(getTodayTime().get(0), getTodayTime().get(1), getTodayTime().get(2));
            SPUtils.putString("everyday_data", lastTime.get(0) + "-" + lastTime.get(1) + "-" + lastTime.get(2));
        } else {
            // 保存请求的日期
            SPUtils.putString("everyday_data", TimeUtil.getData());
        }
        mIsFirst = false;

        xrvEveryday.setAdapter(mEverydayAdapter);
        mEverydayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onInvisible() {
        // 不可见时轮播图停止滚动
        if (mHeaderBinding != null && mHeaderBinding.banner != null) {
            mHeaderBinding.banner.stopAutoPlay();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // 失去焦点，否则RecyclerView第一个item会回到顶部
        xrvEveryday.setFocusable(false);
        DebugUtil.error("-----EverydayFragment----onResume()");
        // 开始图片请求
        Glide.with(getActivity()).resumeRequests();
    }

    @Override
    public void onPause() {
        super.onPause();
        DebugUtil.error("-----EverydayFragment----onPause()");
        // 停止全部图片请求 跟随着Activity
        Glide.with(getActivity()).pauseRequests();

    }

    private void loadBannerPicture() {
//        mEverydayModel.showBanncerPage(new RequestImpl() {
//            @Override
//            public void loadSuccess(Object object) {
//                if (mBannerImages == null) {
//                    mBannerImages = new ArrayList<String>();
//                } else {
//                    mBannerImages.clear();
//                }
//                FrontpageBean bean = (FrontpageBean) object;
//                if (bean != null && bean.getResult() != null && bean.getResult().getFocus() != null && bean.getResult().getFocus().getResult() != null) {
//                    final List<FrontpageBean.ResultBeanXXXXXXXXXXXXXX.FocusBean.ResultBeanX> result = bean.getResult().getFocus().getResult();
//                    if (result != null && result.size() > 0) {
//                        for (int i = 0; i < result.size(); i++) {
//                            //获取所有图片
//                            mBannerImages.add(result.get(i).getRandpic());
//                        }
//                        mHeaderBinding.banner.setImages(mBannerImages).setImageLoader(new GlideImageLoader()).start();
//                        mHeaderBinding.banner.setOnBannerClickListener(new OnBannerClickListener() {
//                            @Override
//                            public void OnBannerClick(int position) {
//                                position = position - 1;
//                                // 链接没有做缓存，如果轮播图使用的缓存则点击图片无效
//                                if (result.get(position) != null && result.get(position).getCode() != null
//                                        && result.get(position).getCode().startsWith("http")) {
//                                    WebViewActivity.loadUrl(getContext(), result.get(position).getCode(), "加载中...");
//                                }
//                            }
//                        });
//                        maCache.remove(Constants.BANNER_PIC);
//                        maCache.put(Constants.BANNER_PIC, mBannerImages, 30000);
//                    }
//                }
//            }
//
//            @Override
//            public void loadFailed() {
//
//            }
//
//            @Override
//            public void addSubscription(Subscription subscription) {
//                EverydayFragment.this.addSubscription(subscription);
//            }
//        });
    }

    private void showRotaLoading(boolean isLoading) {
        if (isLoading) {
            llLoading.setVisibility(View.VISIBLE);
            xrvEveryday.setVisibility(View.GONE);
            animation.startNow();
        } else {
            llLoading.setVisibility(View.GONE);
            xrvEveryday.setVisibility(View.VISIBLE);
            animation.cancel();
        }
    }

    @Override
    protected void onRefresh() {
        showContentView();
        showRotaLoading(true);
        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DebugUtil.error("--EverydayFragment   ----onDestroy");
    }

}
