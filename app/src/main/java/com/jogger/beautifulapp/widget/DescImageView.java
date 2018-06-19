package com.jogger.beautifulapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;


public class DescImageView extends AppCompatImageView {

    private int mOffsetY;

    public boolean isTran() {
        return mIsTran;
    }

    public void setTran(boolean tran) {
        mIsTran = tran;
    }

    private boolean mIsTran = false;

    public DescImageView(Context context) {
        this(context, null);
    }

    public DescImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DescImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOffsetY(int offsetY) {
        if (getDrawable() == null) {
            return;
        }
        mOffsetY = offsetY;
        mIsTran = true;
        invalidate();
    }

    public int getOffsetY() {
        return mOffsetY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!mIsTran) {
            super.onDraw(canvas);
            return;
        }
        Drawable drawable = getDrawable();
        if (drawable != null) {
            int w = drawable.getIntrinsicWidth();
            int h = drawable.getIntrinsicHeight();
            drawable.setBounds(0, 0,w, h);
            canvas.save();
            canvas.translate(0, getOffsetY());
            super.onDraw(canvas);
            canvas.restore();
        }

    }
}
