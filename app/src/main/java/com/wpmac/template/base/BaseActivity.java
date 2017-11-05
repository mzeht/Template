package com.wpmac.template.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.wpmac.premission.XPermissionUtils;
import com.wpmac.template.debug.L;
import com.wpmac.template.manager.AppManager;

/**
 * @author: wpmac  Date: 2017/6/14 Time: 下午1:14
 * @email: mzeht8303@gamil.com
 */
public class BaseActivity extends RxAppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        AppManager.getAppManager().addActivity(this);


    }

    /**
     * 获取当前Activity
     **/
    protected Activity getActivity() {
        return this;
    }

    /**
     * 获取当前Context
     **/
    protected Context getContext() {
        return this;
    }

    /**
     * 短暂显示Toast提示(来自res)
     **/
    protected void showShortToast(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    protected void showShortToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    protected void showLongToast(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    protected void showLongToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();


    }


    /**
     * Debug输出Log日志
     **/
    protected void showLogDebug(String tag, String msg) {
        L.d(tag, msg);
    }

    /**
     * Error输出Log日志
     **/
    protected void showLogError(String tag, String msg) {
        L.e(tag, msg);
    }


    /**
     * 权限
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        XPermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

//	/** 带有右进右出动画的退出 **/
//	public void finish() {
//		super.finish();
//		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
//	}

    /**
     * 默认退出
     **/
    protected void defaultFinish() {
        super.finish();
    }

    /**
     * 隐藏软键盘
     */
    protected void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                imm.hideSoftInputFromWindow(getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //获取屏幕宽度
    public int getDisplayMetricsWidth() {
        int i = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        int j = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        return Math.min(i, j);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    protected void exitSys() {
        AppManager.getAppManager().AppExit(getApplicationContext());
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

//        ToastWidget.createDialog(getApplicationContext(),event.getKeyCode()+"").show();
        return super.dispatchKeyEvent(event);
    }
}
