package com.jogger.beautifulapp.function.presenter;

import android.graphics.Canvas;
import android.view.animation.Interpolator;

import com.jeremyfeinstein.slidingmenu.SlidingMenu;
import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.function.contract.MainContract;
import com.jogger.beautifulapp.function.model.MainModel;

/**
 * Created by Jogger on 2018/6/7.
 * 主页
 */

public class MainPresenter extends BasePresenter<MainContract.View, MainContract.Model>
        implements MainContract.Presenter {
    /**
     * 初始化侧滑菜单
     */
    @Override
    public void initSlidingMenuAnim() {
        final Interpolator interpolator = new Interpolator() {
            @Override
            public float getInterpolation(float t) {
                t -= 1.0f;
                return t * t * t + 1.0f;
            }
        };
        SlidingMenu.CanvasTransformer canvasTransformer = new SlidingMenu.CanvasTransformer() {
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen) {
                float scale = (float) (percentOpen * 0.25 + 0.75);
                canvas.scale(scale, scale, canvas.getWidth() / 2, canvas.getHeight() / 2);
                canvas.translate(0, (1 - interpolator.getInterpolation(percentOpen)));
            }

        };
        mView.initSlidingMenuAnim(canvasTransformer);
    }

    @Override
    public MainContract.Model attachModel() {
        return new MainModel();
    }
}
