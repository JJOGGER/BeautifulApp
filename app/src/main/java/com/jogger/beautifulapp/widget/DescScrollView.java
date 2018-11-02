package com.jogger.beautifulapp.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

/**
 * Created by Jogger on 2018/6/18.
 */

public class DescScrollView extends NestedScrollView implements OnTouchListener {
    boolean isShow = true;//判断圆按钮是否显示
    boolean isOver = false;//判断按钮是否已经和回退按钮同级（BCD处于显示状态）
    private int mStartY = -1;
    private int mLastY = -1;
    private int mScrollY;//记录滑动的Y
    private int mOldScrollY;//标记上一次的y
    private int mFirstPosition;
    private OnScrollListener mListener;
    private int mUpShowY;//上滑到此处显示控件A，隐藏B,C,D
    private int mDownShowY;//下滑到此处显示BCD,隐藏A
    private boolean mIsEnlarge = false;//是否放大图片
    private DescImageView mImageView;//要放大操作的图片
    private boolean mIsLarging;//是否正在放大状态
    private boolean mIsTransing;
    private int mMinWidth;
    private int mMinHeight;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return onTouchEvent(motionEvent);
    }


    public interface OnScrollListener {
        void onScrollY(int scrollY);

        void scollUp(int scrollUpY);
//        void showAppBtn();//移入按钮
//
//        void hideAppBtn();//移出按钮
//
//        void upShow();
//
//        void downShow();


    }

    public DescScrollView(Context context) {
        this(context, null);
    }

    public DescScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DescScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
    }

    public void setOnScrollListener(OnScrollListener listener) {
        mListener = listener;
//        mUpShowY = upShowY;
//        mDownShowY = downShowY;
    }

    @Override
    protected void onScrollChanged(int scrollX, int scrollY, int
            oldScrollX, int oldScrollY) {
        super.onScrollChanged(scrollX, scrollY, oldScrollX, oldScrollY);
        mScrollY = scrollY;
        mOldScrollY = oldScrollY;
        if (mListener != null)
            mListener.onScrollY(mScrollY);
//        L.e("-------scrollY:" + scrollY + ",oldScrollY" + oldScrollY);
        if (mScrollY < mMinHeight) {//处理平移效果
            mIsTransing = true;
            transAnim((mScrollY) / 2);
        } else {
            mIsTransing = false;
            if (mImageView != null)
                mImageView.setTran(false);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (mStartY == -1) {
                    mStartY = (int) ev.getY();
                }
                if (mLastY == -1) {
                    mLastY = (int) ev.getY();
                }
                int moveY = (int) ev.getY();
//                if (mOldScrollY > mScrollY) {
//                    //下滑
//                    L.e("---------movey:"+(moveY-mStartY));
//                }
//                if (mStartY-mStartMoveY>50){
//                    mStartY = mStartMoveY + 50;
//                }else {
//                    mStartY = mStartMoveY + 50;
//                }
//                if (mScrollY == 0 && (mStartMoveY - mStartY) > 50) {
//                    //下滑
//                    mListener.scollDown(mStartMoveY - mStartY);
//                } else if (mScrollY > mOldScrollY) {
//                    //上滑
//                    mListener.scollUp(mStartMoveY - mStartY);
//                } else {
//                    L.e("------------else");
//                }
//                mStartY = moveY - 50;
//                if (mStartY - moveY > 50) {
                //向上移动
//                    if (isShow) {
//                        if (mListener != null) {
//                            mListener.showAppBtn();
//                        }
//                        isShow = false;
//                    }
//                    mStartY = moveY + 50;
//                    if (!isOver && scrollY <= mUpShowY) {
//                        if (scrollY >= mDownShowY) {
//                            if (mListener != null) {
//                                mListener.upShow();
//                            }
//                            isOver = true;
//                        }
//                    }
//                } else {
                //下滑
//                    L.e("------------下滑");
//                    if (!isShow) {
//                        if (mListener != null) {
//                            mListener.hideAppBtn();
//                        }
//                        isShow = true;
//                    }
//                    mStartY = moveY + 50;
//                    if (isOver) {
//                        if (scrollY <= mDownShowY) {
//                            //滑动到与回退按钮底部
//                            if (mListener != null) {
//                                mListener.downShow();
//                            }
//                            isOver = false;
//                        }
//                    }
//                }

                //处理放大缩小
                if (!mIsEnlarge || mLastY == moveY) break;
//                if (mScrollY - mOldScrollY > 10 && mScrollY < mMinHeight) {
//                    //上滑
//                    transAnim((mOldScrollY)/2);
//                } else if (mScrollY - mOldScrollY < -10 && mScrollY < mMinHeight) {
//                    transAnim((mScrollY)/2);
//                }
                if (mScrollY == 0 && moveY - mLastY > 0) {
                    //下滑
//                    if (!mIsTransing) {
                    if (!mIsLarging) {
                        if (getScrollY() == 0) {
                            mFirstPosition = (int) ev.getY();
                        } else {
                            break;
                        }
                    }
                    //放大
                    mIsLarging = true;
                    largeAnim(moveY - mFirstPosition);
                    mLastY = moveY;
                    return true;
//                    }
                } else {
                    mLastY = moveY;
//                    if (mStartY - moveY > 50) {
//                        if (!mIsTransing) {
//                            mFirstPosition = (int) ev.getY();
//                        }
//                        mIsTransing = true;
//                        L.e("---------moveY:" + moveY + ",mStartY:" + mStartY + "--mScrollY:" +
//                                mScrollY);
//                        if (mScrollY <= mMinHeight) {
//                            //处理缩放
//                            L.e("---------b" + (moveY - mFirstPosition));
//                            transAnim(-1);
//                        }
//                    }
                    break;
                }
            case MotionEvent.ACTION_UP:
                if (mIsEnlarge) {
                    reStore();
                    mStartY = -1;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void largeAnim(int moveY) {
        mImageView.setTran(false);
        ViewGroup.LayoutParams layoutParams = mImageView.getLayoutParams();
        layoutParams.height = mMinHeight + moveY / 3;//设置高度
        layoutParams.width = mMinWidth + (moveY / 3) * mMinWidth / mMinHeight;
//        if (layoutParams.width < mMinWidth) {
//            layoutParams.width = mMinWidth;
//        }

        if (layoutParams.height < mMinHeight) {
            layoutParams.height = mMinHeight;
        }
        if (layoutParams.height > getContext().getResources().getDisplayMetrics().heightPixels) {//设置界限
            layoutParams.height = getContext().getResources().getDisplayMetrics().heightPixels;
        }
        mImageView.setLayoutParams(layoutParams);
        mImageView.setTranslationX(-((moveY / 3) * mMinWidth / mMinHeight) / 2);
//        mImageView.setScaleX(layoutParams.height / (float) mMinHeight);//等比设置宽度
    }

    private void transAnim(int moveY) {
        MarginLayoutParams layoutParams = (MarginLayoutParams)
                mImageView.getLayoutParams();
        mImageView.setOffsetY(moveY);
//        mImageView.setLayoutParams(layoutParams);
//        layoutParams.height = mMinHeight + moveY / 3;//设置高度
//        layoutParams.width = mMinWidth + moveY / 3;
//        if (layoutParams.width < mMinWidth) {
//            layoutParams.width = mMinWidth;
//        }
//        if (layoutParams.height < mMinHeight) {
//            layoutParams.height = mMinHeight;
//        }
//        if (layoutParams.height > getContext().getResources().getDisplayMetrics().heightPixels)
//        {//设置界限
//            layoutParams.height = getContext().getResources().getDisplayMetrics().heightPixels;
//        }
//        mImageView.setLayoutParams(layoutParams);
    }

    /**
     * 重置控件
     */
    private void reStore() {
        final ViewGroup.LayoutParams layoutParams = mImageView.getLayoutParams();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(layoutParams.height, mMinHeight);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int h = (int) animation.getAnimatedValue();
                if (h < mMinHeight) {
                    h = mMinHeight;
                }
                layoutParams.height = h;
                layoutParams.width = (int) (h * mMinWidth / (float) mMinHeight);
                mImageView.setTranslationX(-(layoutParams.width - mMinWidth) / 2f);
                mImageView.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.start();
        mIsLarging = false;
    }

    public void setEnlarge(DescImageView imageView) {
        mIsEnlarge = true;
        mImageView = imageView;
        imageView.setOnTouchListener(this);
        mMinWidth = mImageView.getMeasuredWidth();
        mMinHeight = mImageView.getMeasuredHeight();
    }
}
