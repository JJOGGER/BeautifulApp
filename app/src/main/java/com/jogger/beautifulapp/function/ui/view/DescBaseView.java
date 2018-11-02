package com.jogger.beautifulapp.function.ui.view;

import com.jogger.beautifulapp.base.BaseView;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.RecentAppData;

/**
 * Created by jogger on 2018/11/1.
 */
public interface DescBaseView extends BaseView {
    void getDesc1DatasSuccess(AppInfo appInfo);

    void getDesc2DatasSuccess(RecentAppData recentAppData);
}
