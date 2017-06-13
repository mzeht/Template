package com.wpmac.androidnougatframework.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wpmac.androidnougatframework.R;


/**
 * Created by wpmac on 16/1/18.
 */
public class DialogWidget {

    public static Dialog createDialog(Context mContext)
    {
        Dialog  dialog = new Dialog(mContext, R.style.dialog);
        int marginPx = dpToPx(mContext.getResources(), 80);
        ViewGroup.LayoutParams pMLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_default, null);
        String str = "请稍后...";
        ((TextView)mView.findViewById(R.id.tv_dialog_message)).setText(str);
        dialog.setCanceledOnTouchOutside(false);
        dialog.addContentView(mView, pMLayoutParams);
        return dialog;

    }

    public static int dpToPx(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                res.getDisplayMetrics());
    }
}
