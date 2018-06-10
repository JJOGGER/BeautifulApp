package com.jogger.beautifulapp.widget.rhythm;

/**
 * 钢琴布局监听器
 */
public abstract interface IRhythmItemListener {
    public abstract void onRhythmItemChanged(int paramInt);

    public abstract void onSelected(int paramInt);

    public abstract void onStartSwipe();
}