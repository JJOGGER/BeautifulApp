package com.jogger.beautifulapp.function.ui.activity;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseActivity;
import com.jogger.beautifulapp.base.IPresenter;

public class WelcomeActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected IPresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startNewActivity(MainActivity.class);
                finish();
            }
        }, 500);
    }


    @Override
    public boolean isFullScreen() {
        return true;
    }
}
