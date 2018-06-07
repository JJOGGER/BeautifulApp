package com.jogger.beautifulapp.function.ui;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void init() {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startNewActivity(MainActivity.class);
            }
        }, 500);
    }

    @Override
    public boolean isFullScreen() {
        return true;
    }
}
