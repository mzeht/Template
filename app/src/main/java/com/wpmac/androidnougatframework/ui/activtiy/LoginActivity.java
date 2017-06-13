package com.wpmac.androidnougatframework.ui.activtiy;

import android.os.Bundle;

import com.wpmac.androidnougatframework.R;
import com.wpmac.androidnougatframework.base.BaseActivity;
import com.wpmac.androidnougatframework.debug.L;
import com.wpmac.androidnougatframework.retrofit.base.ApiManager;
import com.wpmac.androidnougatframework.retrofit.po.LoginPo;
import com.wpmac.androidnougatframework.rxutils.BaseDisposableObserver;
import com.wpmac.androidnougatframework.rxutils.RxObservableUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.hehe)
    void hehe(){
        ApiManager.getSmartApi().login("name", "ps")
                .compose(RxObservableUtils.applySchedulers())
                .subscribe(new BaseDisposableObserver<LoginPo>(getActivity()) {
                    @Override
                    public void onSuccess(LoginPo loginPo) {
                        L.d(loginPo.toString());
                    }
                });
    }


}
