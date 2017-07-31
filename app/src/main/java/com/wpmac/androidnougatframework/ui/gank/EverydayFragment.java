package com.wpmac.androidnougatframework.ui.gank;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.bumptech.glide.Glide;
import com.wpmac.androidnougatframework.R;
import com.wpmac.androidnougatframework.base.BaseLoadFragment;
import com.wpmac.androidnougatframework.bean.AndroidBean;
import com.wpmac.androidnougatframework.constants.Constants;
import com.wpmac.androidnougatframework.databinding.FooterItemEverydayBinding;
import com.wpmac.androidnougatframework.databinding.FragmentEverydayBinding;
import com.wpmac.androidnougatframework.databinding.HeaderItemEverydayBinding;
import com.wpmac.androidnougatframework.http.cache.ACache;
import com.wpmac.androidnougatframework.model.EverydayModel;
import com.wpmac.androidnougatframework.multitype.EveryDayOneViewBinder;
import com.wpmac.androidnougatframework.multitype.EveryDayTwoViewBinder;
import com.wpmac.androidnougatframework.retrofit.base.ApiManagment;
import com.wpmac.androidnougatframework.retrofit.po.GankIoDayBean;
import com.wpmac.androidnougatframework.rxbus.RxBus;
import com.wpmac.androidnougatframework.rxbus.RxBusBaseMessage;
import com.wpmac.androidnougatframework.rxbus.RxCodeConstants;
import com.wpmac.androidnougatframework.rxutils.RxObservableUtils;
import com.wpmac.androidnougatframework.utils.DebugUtil;
import com.wpmac.androidnougatframework.utils.PerfectClickListener;
import com.wpmac.androidnougatframework.utils.SPUtils;
import com.wpmac.androidnougatframework.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;
import me.drakeet.multitype.ClassLinker;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;


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
public class EverydayFragment extends BaseLoadFragment<FragmentEverydayBinding> {

    private static final String TAG = "EverydayFragment";
    private ACache maCache;
    private ArrayList<List<AndroidBean>> mLists;
    private ArrayList<String> mBannerImages;
    private EverydayModel mEverydayModel;
    private HeaderItemEverydayBinding mHeaderBinding;//=new HeaderItemEverydayBinding();
    private FooterItemEverydayBinding mFooterBinding;
    private View mHeaderView;
    private View mFooterView;
//    private EverydayAdapter mEverydayAdapter;
    private boolean mIsPrepared = false;
    private boolean mIsFirst = true;
    // 是否是上一天的请求
    private boolean isOldDayRequest;
    private RotateAnimation animation;
    // 记录请求的日期
    String year = getTodayTime().get(0);
    String month = getTodayTime().get(1);
    String day = getTodayTime().get(2);


    private Items mItems = new Items();
    private MultiTypeAdapter mMultiTypeAdapter = new MultiTypeAdapter();

    @Override
    public int setContent() {
        return R.layout.fragment_everyday;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        bindingView.llLoading.setVisibility(View.VISIBLE);
        animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(3000);//设置动画持续时间
        animation.setInterpolator(new LinearInterpolator());//不停顿
        animation.setRepeatCount(10);
        bindingView.ivLoading.setAnimation(animation);
        animation.startNow();

        maCache = ACache.get(getContext());
        mEverydayModel = new EverydayModel();
        mBannerImages = (ArrayList<String>) maCache.getAsObject(Constants.BANNER_PIC);
        DebugUtil.error("----mBannerImages: " + (mBannerImages == null));
        DebugUtil.error("----mLists: " + (mLists == null));

        mHeaderBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.header_item_everyday, null, false);
        // 设置本地数据点击事件等
        initLocalSetting();
        initRecyclerView();

        mIsPrepared = true;
        /**
         * 因为启动时先走loadData()再走onActivityCreated，
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
        ApiManagment.getInstance().getGankApi().getGankIoDay(TimeUtil.getTodayTime().get(0), TimeUtil.getTodayTime().get(1), TimeUtil.getTodayTime().get(2))
                .compose(RxObservableUtils.applySchedulers())
                .compose(this.bindToLifecycle())
                .subscribe(new DisposableObserver<GankIoDayBean>() {
                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull GankIoDayBean gankIoDayBean) {
//                        L.d(gankIoDayBean.toString());
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initLocalSetting() {
//        mEverydayModel.setData(getTodayTime().get(0), getTodayTime().get(1), getTodayTime().get(2));
//        DebugUtil.error("" + year + month + day);
        // 显示日期,去掉第一位的"0"
        mHeaderBinding.includeEveryday.tvDailyText.setText(getTodayTime().get(2).indexOf("0") == 0 ?
                getTodayTime().get(2).replace("0", "") : getTodayTime().get(2));
        mHeaderBinding.includeEveryday.ibXiandu.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
//                WebViewActivity.loadUrl(v.getContext(), "https://gank.io/xiandu", "加载中...");
            }
        });
        mHeaderBinding.includeEveryday.ibMovieHot.setOnClickListener(new PerfectClickListener() {
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

    }


    /**
     * 加载正文内容
     */
    private void showContentData() {




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



    private void initRecyclerView() {



        bindingView.xrvEveryday.setPullRefreshEnabled(false);
        bindingView.xrvEveryday.setLoadingMoreEnabled(false);
        if (mHeaderView == null) {
            mHeaderView = mHeaderBinding.getRoot();
            bindingView.xrvEveryday.addHeaderView(mHeaderView);
        }
        if (mFooterView == null) {

            mFooterView =  LayoutInflater.from(getActivity()).inflate(R.layout.footer_item_everyday, null);
            bindingView.xrvEveryday.addFootView(mFooterView, true);
            bindingView.xrvEveryday.noMoreLoading();
        }
        bindingView.xrvEveryday.setLayoutManager(new LinearLayoutManager(getContext()));
        // 需加，不然滑动不流畅
        bindingView.xrvEveryday.setNestedScrollingEnabled(false);
        bindingView.xrvEveryday.setHasFixedSize(false);
        bindingView.xrvEveryday.setItemAnimator(new DefaultItemAnimator());
    }

    private void setAdapter(ArrayList<List<AndroidBean>> lists) {
        showRotaLoading(false);
        if (mMultiTypeAdapter == null) {
            mMultiTypeAdapter = new MultiTypeAdapter();
        } else {

        }
        mMultiTypeAdapter.setItems(mItems);
        mMultiTypeAdapter.register(AndroidBean.class).
                to(new EveryDayOneViewBinder(), new EveryDayTwoViewBinder())
                .withClassLinker(new ClassLinker<AndroidBean>() {
                    @Override
                    public Class<? extends ItemViewBinder<AndroidBean, ?>> index(@NonNull AndroidBean androidBean) {
                        return null;
                    }
                });

        mItems.addAll(lists);
//        mEverydayAdapter.addAll(lists);
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

        bindingView.xrvEveryday.setAdapter(mMultiTypeAdapter);
        mMultiTypeAdapter.notifyDataSetChanged();
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
        bindingView.xrvEveryday.setFocusable(false);
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
            bindingView.xrvEveryday.setVisibility(View.GONE);
            animation.startNow();
        } else {
            bindingView.xrvEveryday.setVisibility(View.VISIBLE);
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
