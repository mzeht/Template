package com.wpmac.template.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * @author: wpmac  Date: 2017/3/24 Time: 下午6:16
 * @email: mzeht8303@gamil.com
 */
public class AlertDialogWidget {

    public static AlertDialog createSimple(String title,String message,Context context) {

        AlertDialog.Builder  builder = new AlertDialog.Builder(context);
        AlertDialog  alert = builder
                .setTitle("系统提示：")
                .setMessage("没有找到记录")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create();
        return alert;
    }
}
