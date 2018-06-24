package com.jogger.beautifulapp.base.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jogger.beautifulapp.util.SizeUtil;

/**
 * Created by Jogger on 2018/6/14.间隔
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        int position = parent.getChildLayoutPosition(view);
        //rcyclerview有头布局
        int headerLayoutCount = ((BaseQuickAdapter) parent.getAdapter()).getHeaderLayoutCount();
        if (position == headerLayoutCount || position == headerLayoutCount + 1) {
            outRect.top = SizeUtil.dp2px(12);
        }
        if (position % 2 == headerLayoutCount) {//第一列数据设置right
            outRect.right = space;
        }
    }
}
