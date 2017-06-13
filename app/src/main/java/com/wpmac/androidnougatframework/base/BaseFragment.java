package com.wpmac.androidnougatframework.base;


import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * @author: wpmac  Date: 2017/5/22 Time: 上午1:35
 * @email: mzeht8303@gamil.com
 */
public class BaseFragment extends RxFragment {

    private boolean isAddeded;

    public void setIsAddeded(boolean arg0){
        isAddeded = arg0;
    }

    public boolean isAddeded() {
        return isAddeded || super.isAdded();
    }

}
