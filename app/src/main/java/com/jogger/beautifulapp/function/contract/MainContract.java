package com.jogger.beautifulapp.function.contract;


import com.jeremyfeinstein.slidingmenu.SlidingMenu;
import com.jogger.beautifulapp.base.BaseModel;
import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.base.IPresenter;
import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

/**
 * 主页
 */
public interface MainContract {
    interface Model extends BaseModel {
        void getRandomDatas(OnHttpRequestListener<AppRecentData> listener);
    }

    interface View extends BaseView {
        void initSlidingMenuAnim(SlidingMenu.CanvasTransformer transformer);

        void getRandomDatasSuccess(Object obje);
    }

    interface Presenter extends IPresenter<View, Model> {
        void initSlidingMenuAnim();

        void getRandomDatas();
    }
}
