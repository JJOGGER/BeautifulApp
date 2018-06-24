package com.jogger.beautifulapp.function.ui.activity;

import android.graphics.Color;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.function.contract.RecentDescContract;
import com.jogger.beautifulapp.function.presenter.RecentDescPresenter;
import com.jogger.beautifulapp.swipelayoutlib.app.SwipeBackActivity;
import com.jogger.beautifulapp.util.DescTextUtil;
import com.jogger.beautifulapp.util.L;
import com.jogger.beautifulapp.util.SizeUtil;
import com.jogger.beautifulapp.widget.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;

public class RecentDescActivity extends SwipeBackActivity<RecentDescPresenter> implements
        RecentDescContract.View, NestedScrollView.OnScrollChangeListener {
    @BindView(R.id.nsv_content)
    NestedScrollView nsvContent;
    @BindView(R.id.fl_top_container)
    FrameLayout flTopContainer;
    @BindView(R.id.iv_user_icon)
    CircleImageView ivUserIcon;
    @BindView(R.id.fbl_tag)
    FlexboxLayout fblTag;
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
    @BindView(R.id.ll_img_container)
    LinearLayout llImgContainer;
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
    private int mShowY = -1;//设置隐藏动画的按钮所处位置
    private int mOffsetY;//偏移动画距离

    @Override
    public int getLayoutId() {
        return R.layout.activity_recent_desc;
    }

    @Override
    protected RecentDescPresenter createPresenter() {
        return new RecentDescPresenter();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        int[] postion = new int[2];
        L.e("-------->fblTag.getMeasuredHeight()" + fblTag.getMeasuredHeight());
        ibtnCollectionRoll.getLocationOnScreen(postion);
        //隐藏位置：scrollview中按钮y-头部位置bottom+头部位置高度一半
        if (mShowY == -1 )
            mShowY = postion[1] - flTopContainer.getBottom() + flTopContainer
                    .getMeasuredHeight() / 2 - fblTag.getMeasuredHeight();
    }

    @Override
    protected void init() {
        super.init();
        int id = getIntent().getIntExtra(Constant.ID, 0);
        nsvContent.setOnScrollChangeListener(this);
        mPresenter.getDescData(id);
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
    public void getDescDataSuccess(RecentAppData appData) {
        L.e("------app:" + appData);
        Glide.with(this)
                .load(appData.getIcon_image())
                .into(ivIcon);
        Glide.with(this)
                .load(appData.getAuthor_avatar_url())
                .into(ivUserIcon);
        tvTitle.setText(appData.getTitle());
        tvSubTitle.setText(appData.getSub_title());
        tvDesc.setMovementMethod(LinkMovementMethod.getInstance());
        if (appData.getTags() != null)
            for (int i = 0; i < appData.getTags().size(); i++) {
                fblTag.addView(createTextView(appData.getTags().get(i).getTitle()));
            }
        fblTag.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver
                .OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                fblTag.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mShowY += fblTag.getMeasuredHeight();
            }
        });
        new DescTextUtil().setDrawableText(appData.getDescription(), tvDesc);
        if (appData.getAll_images() != null)
            for (int i = 0; i < appData.getAll_images().size(); i++) {
                ImageView imageView = new ImageView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                        (LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                                .WRAP_CONTENT);
                layoutParams.bottomMargin = SizeUtil.dp2px(16);
                imageView.setLayoutParams(layoutParams);
                Glide.with(this)
                        .load(appData.getAll_images().get(i))
                        .centerCrop()
                        .into(imageView);
                llImgContainer.addView(imageView);
            }
    }


    private View createTextView(String tag) {
        CardView cardView = new CardView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, SizeUtil.dp2px(28));
        cardView.setRadius(SizeUtil.dp2px(12));
        TextView textView = new TextView(this);
        params.leftMargin = SizeUtil.dp2px(1);
        params.topMargin = SizeUtil.dp2px(8);
        params.bottomMargin = SizeUtil.dp2px(1);
        params.rightMargin = SizeUtil.dp2px(8);
        cardView.setLayoutParams(params);
        textView.setPadding(SizeUtil.dp2px(24), 0, SizeUtil.dp2px(24), 0);
        textView.setTextColor(Color.BLACK);
        textView.setText(tag);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, SizeUtil.dp2px(16));
        cardView.addView(textView);
        return cardView;
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int
            oldScrollY) {
        if (scrollY >= mShowY) {
            L.e("----------此处隐藏动画" + scrollY + "--<" + mShowY);
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

    @OnClick({R.id.ibtn_return})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtn_return:
                finish();
                break;
        }
    }
}
