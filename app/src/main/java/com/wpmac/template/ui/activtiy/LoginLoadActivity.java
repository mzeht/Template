package com.wpmac.template.ui.activtiy;

import android.os.Bundle;
import android.os.Handler;

import com.wpmac.template.R;
import com.wpmac.template.base.BaseLoadActivity;
import com.wpmac.template.utils.T;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginLoadActivity extends BaseLoadActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showContentView();
            }
        }, 1000);


    }

    @OnClick(R.id.hehe)
    void hehe(){
        T.showToastShort(getActivity(), "hehe");
    }


}
