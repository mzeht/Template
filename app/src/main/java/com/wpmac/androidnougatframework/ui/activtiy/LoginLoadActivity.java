package com.wpmac.androidnougatframework.ui.activtiy;

import android.os.Bundle;

import com.wpmac.androidnougatframework.R;
import com.wpmac.androidnougatframework.base.BaseLoadActivity;
import com.wpmac.androidnougatframework.utils.T;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginLoadActivity extends BaseLoadActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        showContentView();
    }

    @OnClick(R.id.hehe)
    void hehe(){
        T.showToastShort(getActivity(), "hehe");
    }


}
