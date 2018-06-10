package com.jogger.beautifulapp.function.contract;


import com.jeremyfeinstein.slidingmenu.SlidingMenu;
import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;

/**
 * 主页
 */
public interface MainContract {
    interface Model extends BaseModel {
    }

    interface View extends BaseView {
        void initSlidingMenuAnim(SlidingMenu.CanvasTransformer transformer);
    }

    interface Presenter extends IPresenter<View, Model> {
        void initSlidingMenuAnim();
    }
}
