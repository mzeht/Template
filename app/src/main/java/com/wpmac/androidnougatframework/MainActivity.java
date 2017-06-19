package com.wpmac.androidnougatframework;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SwitchCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wpmac.androidnougatframework.base.BaseActivity;
import com.wpmac.androidnougatframework.constants.ConstantsImageUrl;
import com.wpmac.androidnougatframework.utils.CommonUtils;
import com.wpmac.androidnougatframework.utils.ImgLoadUtil;
import com.wpmac.androidnougatframework.utils.PerfectClickListener;
import com.wpmac.androidnougatframework.utils.SPUtils;
import com.wpmac.androidnougatframework.view.statusbar.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements  ViewPager.OnPageChangeListener, View.OnClickListener {

    @BindView(R.id.view_status)
    View viewStatus;
    @BindView(R.id.iv_title_gank)
     ImageView llTitleGank;
    @BindView(R.id.iv_title_one)
     ImageView llTitleOne;
    @BindView(R.id.iv_title_dou)
     ImageView llTitleDou;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.ll_title_menu)
    FrameLayout titleMenu;

    //头部布局
    class HeadviewBind{
        @BindView(R.id.day_night_switch)
        SwitchCompat dayNightSwitch;
        @BindView(R.id.ll_nav_exit)
        LinearLayout llNavExit;
        @BindView(R.id.iv_avatar)
        ImageView ivAvatar;
        @BindView(R.id.ll_nav_homepage)
        LinearLayout llNavHomepage;
        @BindView(R.id.ll_nav_scan_download)
        LinearLayout llNavScanDownload;
        @BindView(R.id.ll_nav_deedback)
        LinearLayout llNavDeedback;
        @BindView(R.id.ll_nav_about)
        LinearLayout llNavAbout;
        @BindView(R.id.ll_nav_login)
        LinearLayout llNavLogin;

    }

    //头部绑定
    HeadviewBind headViewBind=new HeadviewBind();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initStatusView();
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(MainActivity.this, drawerLayout,
                CommonUtils.getColor(R.color.colorTheme));
        initContentFragment();
        initDrawerLayout();
        initListener();

    }

    private void initListener() {
        titleMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ll_title_menu:// 开启菜单
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }

    }


    private void initDrawerLayout() {
        navView.inflateHeaderView(R.layout.nav_header_main);
        View headerView = navView.getHeaderView(0);
        ButterKnife.bind(headViewBind, headerView);
        headViewBind.dayNightSwitch.setChecked(SPUtils.getNightMode());
        ImgLoadUtil.displayCircle(headViewBind.ivAvatar, ConstantsImageUrl.IC_AVATAR);
        headViewBind.llNavExit.setOnClickListener(this);
        headViewBind.ivAvatar.setOnClickListener(this);
        headViewBind.llNavHomepage.setOnClickListener(listener);
        headViewBind.llNavScanDownload.setOnClickListener(listener);
        headViewBind.llNavDeedback.setOnClickListener(listener);
        headViewBind.llNavAbout.setOnClickListener(listener);
        headViewBind.llNavLogin.setOnClickListener(listener);
    }

    private void initContentFragment() {

    }

    private PerfectClickListener listener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {

        }
    };

    private void initStatusView() {
        ViewGroup.LayoutParams layoutParams = viewStatus.getLayoutParams();
        layoutParams.height = StatusBarUtil.getStatusBarHeight(this);
        viewStatus.setLayoutParams(layoutParams);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        switch (position) {
            case 0:
                llTitleGank.setSelected(true);
                llTitleOne.setSelected(false);
                llTitleDou.setSelected(false);
                break;
            case 1:
                llTitleOne.setSelected(true);
                llTitleGank.setSelected(false);
                llTitleDou.setSelected(false);
                break;
            case 2:
                llTitleDou.setSelected(true);
                llTitleOne.setSelected(false);
                llTitleGank.setSelected(false);
                break;
        }

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
