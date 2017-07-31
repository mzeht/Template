package com.wpmac.androidnougatframework;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.wpmac.androidnougatframework.base.BaseActivity;
import com.wpmac.androidnougatframework.constants.ConstantsImageUrl;
import com.wpmac.androidnougatframework.databinding.ActivityMainBinding;
import com.wpmac.androidnougatframework.databinding.NavHeaderMainBinding;
import com.wpmac.androidnougatframework.ui.gank.GankFragment;
import com.wpmac.androidnougatframework.utils.CommonUtils;
import com.wpmac.androidnougatframework.utils.ImgLoadUtil;
import com.wpmac.androidnougatframework.utils.PerfectClickListener;
import com.wpmac.androidnougatframework.utils.SPUtils;
import com.wpmac.androidnougatframework.view.MyFragmentPagerAdapter;
import com.wpmac.androidnougatframework.view.statusbar.StatusBarUtil;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements  ViewPager.OnPageChangeListener, View.OnClickListener {

//    @BindView(R.id.view_status)
//    View viewStatus;
//    @BindView(R.id.iv_title_gank)
//     ImageView llTitleGank;
//    @BindView(R.id.iv_title_one)
//     ImageView llTitleOne;
//    @BindView(R.id.iv_title_dou)
//     ImageView llTitleDou;
//    @BindView(R.id.drawer_layout)
//    DrawerLayout drawerLayout;
//    @BindView(R.id.nav_view)
//    NavigationView navView;
//    @BindView(R.id.ll_title_menu)
//    FrameLayout titleMenu;
//    @BindView(R.id.vp_content)
//    ViewPager vpContent;

    //头部布局
//    class HeadviewBind{
//        @BindView(R.id.day_night_switch)
//        SwitchCompat dayNightSwitch;
//        @BindView(R.id.ll_nav_exit)
//        LinearLayout llNavExit;
//        @BindView(R.id.iv_avatar)
//        ImageView ivAvatar;
//        @BindView(R.id.ll_nav_homepage)
//        LinearLayout llNavHomepage;
//        @BindView(R.id.ll_nav_scan_download)
//        LinearLayout llNavScanDownload;
//        @BindView(R.id.ll_nav_deedback)
//        LinearLayout llNavDeedback;
//        @BindView(R.id.ll_nav_about)
//        LinearLayout llNavAbout;
//        @BindView(R.id.ll_nav_login)
//        LinearLayout llNavLogin;
//
//    }

//    //头部绑定
//    HeadviewBind headViewBind=new HeadviewBind();



    private FrameLayout llTitleMenu;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private NavigationView navView;
    private DrawerLayout drawerLayout;
    private ViewPager vpContent;

    // 一定需要对应的bean
    private ActivityMainBinding mBinding;
    private ImageView llTitleGank;
    private ImageView llTitleOne;
    private ImageView llTitleDou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        ButterKnife.bind(this);
        initStatusView();
        initId();
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(MainActivity.this, drawerLayout,
                CommonUtils.getColor(R.color.colorTheme));
        initContentFragment();
        initDrawerLayout();
        initListener();

    }

    private void initListener() {
        llTitleMenu.setOnClickListener(this);
    }


    private void initId() {
        drawerLayout = mBinding.drawerLayout;
        navView = mBinding.navView;
        fab = mBinding.include.fab;
        toolbar = mBinding.include.toolbar;
        llTitleMenu = mBinding.include.llTitleMenu;
        vpContent = mBinding.include.vpContent;
        fab.setVisibility(View.GONE);

        llTitleGank = mBinding.include.ivTitleGank;
        llTitleOne = mBinding.include.ivTitleOne;
        llTitleDou = mBinding.include.ivTitleDou;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ll_title_menu:// 开启菜单
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }

    }


    NavHeaderMainBinding bind;
    private void initDrawerLayout() {
        mBinding.navView.inflateHeaderView(R.layout.nav_header_main);
        View headerView = navView.getHeaderView(0);
//        ButterKnife.bind(headViewBind, headerView);
        bind = DataBindingUtil.bind(headerView);
        bind.dayNightSwitch.setChecked(SPUtils.getNightMode());
        ImgLoadUtil.displayCircle(bind.ivAvatar, ConstantsImageUrl.IC_AVATAR);
        bind.llNavExit.setOnClickListener(this);
        bind.ivAvatar.setOnClickListener(this);
        bind.llNavHomepage.setOnClickListener(listener);
        bind.llNavScanDownload.setOnClickListener(listener);
        bind.llNavDeedback.setOnClickListener(listener);
        bind.llNavAbout.setOnClickListener(listener);
        bind.llNavLogin.setOnClickListener(listener);
    }

    private void initContentFragment() {

        ArrayList<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(new GankFragment());
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList);
        vpContent.setAdapter(adapter);
        vpContent.addOnPageChangeListener(this);
        llTitleGank.setSelected(false);
        vpContent.setCurrentItem(0);
    }

    private PerfectClickListener listener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {

        }
    };

    private void initStatusView() {
        ViewGroup.LayoutParams layoutParams =  mBinding.include.viewStatus.getLayoutParams();
        layoutParams.height = StatusBarUtil.getStatusBarHeight(this);
        mBinding.include.viewStatus.setLayoutParams(layoutParams);
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
