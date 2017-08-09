package com.wpmac.template.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wpmac.template.MainActivity;
import com.wpmac.template.R;
import com.wpmac.template.base.BaseActivity;
import com.wpmac.template.constants.ConstantsImageUrl;
import com.wpmac.template.utils.CommonUtils;
import com.wpmac.template.utils.PerfectClickListener;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransitionActivity extends BaseActivity {

    @BindView(R.id.iv_defult_pic)
    ImageView ivDefultPic;

    @BindView(R.id.iv_pic)
    ImageView ivPic;

    @BindView(R.id.tv_jump)
    TextView tvJump;

    private boolean isIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        ButterKnife.bind(this);

        int i = new Random().nextInt(ConstantsImageUrl.TRANSITION_URLS.length);
        // 先显示默认图
        ivDefultPic.setImageDrawable(CommonUtils.getDrawable(R.drawable.img_transition_default));
        Glide.with(this)
                .load(ConstantsImageUrl.TRANSITION_URLS[i])
                .placeholder(R.drawable.img_transition_default)
                .error(R.drawable.img_transition_default)
                .into(ivPic);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ivDefultPic.setVisibility(View.GONE);
            }
        }, 1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toMainActivity();
            }
        }, 3500);

//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.transition_anim);
//        animation.setAnimationListener(animationListener);
//        mBinding.ivPic.startAnimation(animation);

        tvJump.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                toMainActivity();
//                animationEnd();
            }
        });
    }

    private void toMainActivity() {

            if (isIn) {
                return;
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
            finish();
            isIn = true;
    }
}
