package com.jogger.beautifulapp.function.ui;

import com.jeremyfeinstein.slidingmenu.SlidingMenu;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseActivity;
import com.jogger.beautifulapp.function.contract.MainContract;
import com.jogger.beautifulapp.function.presenter.MainPresenter;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.sm_menu)
    SlidingMenu smMenu;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void createPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    protected void init() {
        super.init();
        mPresenter.initSlidingMenuAnim();
    }

    @Override
    public void initSlidingMenuAnim(SlidingMenu.CanvasTransformer transformer) {
        smMenu.setBehindCanvasTransformer(transformer);
    }
}
