package com.jogger.beautifulapp.base.recyclerview.refresh;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jogger on 2018/6/22.
 */

public abstract class RefreshViewCreator {
    /**
     * 获取下拉刷新的View
     *
     * @param context 上下文
     * @param parent  RecyclerView
     */
    public abstract View getRefreshView(Context context, ViewGroup parent);

    /**
     * 正在下拉
     * @param currentDragHeight   当前拖动的高度
     * @param refreshViewHeight  总的刷新高度
     * @param currentRefreshStatus 当前状态
     */
    public abstract void onPull(int currentDragHeight, int refreshViewHeight, int currentRefreshStatus);

    /**
     * 正在刷新中
     */
    public abstract void onRefreshing();

    /**
     * 停止刷新
     */
    public abstract void onStopRefresh();
}
