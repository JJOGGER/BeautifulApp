
package com.jogger.beautifulapp.swipelayoutlib.app;

import android.os.Bundle;
import android.view.View;

import com.jogger.beautifulapp.base.BaseActivity;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.swipelayoutlib.SwipeBackLayout;
import com.jogger.beautifulapp.swipelayoutlib.Utils;


public abstract class SwipeBackActivity<T extends IPresenter> extends BaseActivity implements
        SwipeBackActivityBase, BaseView {
    private SwipeBackActivityHelper mHelper;
    protected T mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    protected void init() {
        mPresenter = (T) super.mPresenter;
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mHelper != null)
            mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }
}
