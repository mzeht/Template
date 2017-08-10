package com.wpmac.template.ui.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.example.xrecyclerview.XRecyclerView;
import com.wpmac.template.R;
import com.wpmac.template.base.BaseLoadFragment;
import com.wpmac.template.databinding.FragmentAndroidBinding;
import com.wpmac.template.debug.L;
import com.wpmac.template.http.cache.ACache;
import com.wpmac.template.model.GankOtherModel;
import com.wpmac.template.multitype.AndroidViewBinder;
import com.wpmac.template.retrofit.base.ApiManagment;
import com.wpmac.template.retrofit.po.GankIoDataBean;
import com.wpmac.template.rxutils.RxObservableUtils;
import com.wpmac.template.utils.DebugUtil;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;


/**
 * 大安卓 fragment
 */
public class AndroidFragment extends BaseLoadFragment<FragmentAndroidBinding> {

    private static final String TAG = "AndroidFragment";
    private static final String TYPE = "mType";
    private String mType = "Android";
    private int mPage = 1;
    private boolean mIsPrepared;
    private boolean mIsFirst = true;
    //    private AndroidAdapter mAndroidAdapter;
    private MultiTypeAdapter mMultiTypeAdapter = new MultiTypeAdapter();
    private Items mItems = new Items();
    private ACache mACache;
    private GankIoDataBean mAndroidBean;
    private GankOtherModel mModel;

    public static AndroidFragment newInstance(String type) {
        AndroidFragment fragment = new AndroidFragment();
        Bundle args = new Bundle();
        args.putString(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getString(TYPE);
        }
    }

    @Override
    public int setContent() {
        return R.layout.fragment_android;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        L.d("onActivityCreated");

//        mACache = ACache.get(getContext());
//        mModel = new GankOtherModel();
//        mAndroidBean = (GankIoDataBean) mACache.getAsObject(Constants.GANK_ANDROID);
//        mAndroidAdapter = new AndroidAdapter();


        bindingView.xrvAndroid.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                loadAndroidData();
            }

            @Override
            public void onLoadMore() {
                mPage++;
                loadAndroidData();
            }
        });

        // 准备就绪
        mIsPrepared = true;
    }

    @Override
    protected void loadData() {
        L.d("loadData");
        if (!mIsPrepared || !mIsVisible || !mIsFirst) {
            return;
        }


//        if (mAndroidBean != null
//                && mAndroidBean.getResults() != null
//                && mAndroidBean.getResults().size() > 0) {
//            showContentView();
////            mAndroidBean = (GankIoDataBean) mACache.getAsObject(Constants.GANK_ANDROID);
//            setAdapter(mAndroidBean);
//        } else {
//            loadAndroidData();
//        }
        setAdapter();
        loadAndroidData();
    }

    private void loadAndroidData() {
        L.d("loadAndroidData");

        ApiManagment.getInstance().getGankApi().getGankIoData(mType, mPage, ApiManagment.per_page_more)
                .compose(RxObservableUtils.applySchedulers())
                .compose(this.bindToLifecycle())
                .subscribe(new DisposableObserver<GankIoDataBean>() {
                    @Override
                    public void onNext(@NonNull GankIoDataBean gankIoDataBean) {
                        showContentView();
                        if (mPage == 1) {
                            if (gankIoDataBean != null && gankIoDataBean.getResults() != null && gankIoDataBean.getResults().size() > 0) {
                                mItems.clear();
                                mItems.addAll(gankIoDataBean.getResults());
                                mMultiTypeAdapter.notifyDataSetChanged();
                                bindingView.xrvAndroid.refreshComplete();

//                                mACache.remove(Constants.GANK_ANDROID);
                                // 缓存50分钟
//                                mACache.put(Constants.GANK_ANDROID, gankIoDataBean, 30000);
                            }
                        } else {
                            if (gankIoDataBean != null && gankIoDataBean.getResults() != null && gankIoDataBean.getResults().size() > 0) {
                                bindingView.xrvAndroid.refreshComplete();
                                mItems.addAll(gankIoDataBean.getResults());
                                mMultiTypeAdapter.notifyDataSetChanged();
                            } else {
                                bindingView.xrvAndroid.noMoreLoading();
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        bindingView.xrvAndroid.refreshComplete();
                        // 注意：这里不能写成 mPage == 1，否则会一直显示错误页面
                        if (mMultiTypeAdapter.getItemCount() == 0) {
                            showError();
                        }
                        if (mPage > 1) {
                            mPage--;
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 设置adapter
     */
    private void setAdapter() {
//        mAndroidAdapter.clear();
//        mAndroidAdapter.addAll(mAndroidBean.getResults());
        mMultiTypeAdapter.register(GankIoDataBean.ResultBean.class, new AndroidViewBinder(true));
        mMultiTypeAdapter.setItems(mItems);
        bindingView.xrvAndroid.setLayoutManager(new LinearLayoutManager(getActivity()));
        bindingView.xrvAndroid.setAdapter(mMultiTypeAdapter);
//        mMultiTypeAdapter.notifyDataSetChanged();
//        bindingView.xrvAndroid.refreshComplete();

        mIsFirst = false;
    }

    /**
     * 加载失败后点击后的操作
     */
    @Override
    protected void onRefresh() {
        loadAndroidData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DebugUtil.error(TAG + "   ----onDestroy");
    }

    @Override
    public void onResume() {
        super.onResume();
        DebugUtil.error(TAG + "   ----onResume");
    }
}
