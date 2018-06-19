package com.jogger.beautifulapp.function.ui.activity;

import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.function.contract.ChoiceDescContract;
import com.jogger.beautifulapp.function.presenter.ChoiceDescPresenter;
import com.jogger.beautifulapp.swipelayoutlib.app.SwipeBackActivity;
import com.jogger.beautifulapp.util.DescTextUtil;
import com.jogger.beautifulapp.util.L;
import com.jogger.beautifulapp.widget.DescImageView;
import com.jogger.beautifulapp.widget.DescScrollView;

import butterknife.BindView;

public class FindChoiceDescActivity extends SwipeBackActivity<ChoiceDescPresenter> implements
        ChoiceDescContract.View, DescScrollView.OnScrollListener {
    @BindView(R.id.nsv_content)
    DescScrollView nsvContent;
    @BindView(R.id.fl_top_container)
    FrameLayout flTopContainer;
    @BindView(R.id.iv_title)
    DescImageView ivTitle;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_sub_title)
    TextView tvSubTitle;
    @BindView(R.id.ibtn_collection)
    ImageButton ibtnCollection;
    @BindView(R.id.ibtn_share)
    ImageButton ibtnShare;
    @BindView(R.id.ibtn_download)
    ImageButton ibtnDownload;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.ibtn_collection_roll)
    ImageButton ibtnCollectionRoll;
    @BindView(R.id.ibtn_share_roll)
    ImageButton ibtnShareRoll;
    @BindView(R.id.ibtn_download_roll)
    ImageButton ibtnDownloadRoll;
    @BindView(R.id.tv_collection)
    TextView tvCollection;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_download)
    TextView tvDownload;
    private int mShowY;//设置隐藏动画的按钮所处位置
    private int mOffsetY;//偏移动画距离

    @Override
    public int getLayoutId() {
        return R.layout.activity_find_choice_desc;
    }

    @Override
    protected ChoiceDescPresenter createPresenter() {
        return new ChoiceDescPresenter();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        int[] postion = new int[2];
        ibtnCollectionRoll.getLocationOnScreen(postion);
        //隐藏位置：scrollview中按钮y-头部位置bottom+头部位置高度一半
        mShowY = postion[1] - flTopContainer.getBottom() + flTopContainer
                .getMeasuredHeight() / 2;
    }

    @Override
    protected void init() {
        super.init();
        int id = getIntent().getIntExtra(Constant.ID, 0);
        mPresenter.getChoiceDescData(id);
        ivTitle.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver
                .OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ivTitle.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                nsvContent.setEnlarge(ivTitle);
            }
        });
        nsvContent.setOnScrollListener(this);
//        nsvContent.setOnScrollListener(mShowY + ibtn_back.getMeasuredHeight(), mShowY,
//                new MyScrollView.OnScrollListener() {
//                    @Override
//                    public void showAppBtn() {
//                        ibtn_app.animate().translationYBy(y).y(mY).setDuration(500)
//                                .setInterpolator(new BounceInterpolator()).start();
//                    }
//
//                    @Override
//                    public void hideAppBtn() {
//                        ibtn_app.animate().translationYBy(mY).y(y).setDuration(500)
//                                .setInterpolator(new BounceInterpolator()).start();
//                    }
//
//                    @Override
//                    public void upShow() {
//                        ll_btn_container.setVisibility(View.INVISIBLE);
//                        ibtn_fav.setVisibility(View.VISIBLE);
//                        ibtn_share.setVisibility(View.VISIBLE);
//                        ibtn_down.setVisibility(View.VISIBLE);
//                    }
//
//                    @Override
//                    public void downShow() {
//                        //滑动到与回退按钮底部
//                        ibtn_fav.setVisibility(View.INVISIBLE);
//                        ibtn_share.setVisibility(View.INVISIBLE);
//                        ibtn_down.setVisibility(View.INVISIBLE);
//                        ll_btn_container.setVisibility(View.VISIBLE);
//                    }
//                });

    }

    @Override
    public void getChoiceDescDataSuccess(AppInfo appInfo) {
        L.e("------app:" + appInfo);
        Glide.with(this)
                .load(appInfo.getCover_image())
                .into(ivTitle);
        Glide.with(this)
                .load(appInfo.getIcon_image())
                .into(ivIcon);
        tvTitle.setText(appInfo.getTitle());
        tvSubTitle.setText(appInfo.getSub_title());
        tvDesc.setMovementMethod(LinkMovementMethod.getInstance());
        new DescTextUtil().setDrawableText(appInfo.getContent(), tvDesc);
    }

    @Override
    public void onScrollY(int scrollY) {
        if (scrollY >= mShowY) {
            L.e("----------此处隐藏动画" + scrollY);
//            int offsetX = ibtnCollection.getLeft() - ibtnCollectionRoll
//                    .getLeft();
//            int offsetY = ibtnCollection.getMeasuredHeight() / 2;
//            ibtnCollectionRoll.setTranslationX(offsetX);
//            tvCollection.setTranslationX(offsetX);
//            ibtnCollectionRoll.setTranslationY(offsetY);
//            tvCollection.setTranslationY(offsetY);
//            ibtnShareRoll.setTranslationY(offsetY);
            ibtnCollectionRoll.setVisibility(View.INVISIBLE);
            ibtnShareRoll.setVisibility(View.INVISIBLE);
            ibtnDownloadRoll.setVisibility(View.INVISIBLE);
            tvCollection.setVisibility(View.INVISIBLE);
            tvShare.setVisibility(View.INVISIBLE);
            tvDownload.setVisibility(View.INVISIBLE);
            ibtnCollection.setVisibility(View.VISIBLE);
            ibtnShare.setVisibility(View.VISIBLE);
            ibtnDownload.setVisibility(View.VISIBLE);
        } else if (scrollY < mShowY) {
            if (ibtnCollection.getVisibility() == View.VISIBLE) {
                ibtnCollectionRoll.setVisibility(View.VISIBLE);
                ibtnShareRoll.setVisibility(View.VISIBLE);
                ibtnDownloadRoll.setVisibility(View.VISIBLE);
                tvCollection.setVisibility(View.VISIBLE);
                tvShare.setVisibility(View.VISIBLE);
                tvDownload.setVisibility(View.VISIBLE);
                ibtnCollection.setVisibility(View.INVISIBLE);
                ibtnShare.setVisibility(View.INVISIBLE);
                ibtnDownload.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void scollUp(int scrollUpY) {

    }
}
