package com.jogger.beautifulapp.function.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseDescActivity;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.entity.Tag;
import com.jogger.beautifulapp.entity.User;
import com.jogger.beautifulapp.function.contract.RecentDescContract;
import com.jogger.beautifulapp.function.presenter.RecentDescPresenter;
import com.jogger.beautifulapp.function.ui.dialog.DownloadDialog;
import com.jogger.beautifulapp.util.DescTextUtil;
import com.jogger.beautifulapp.util.SizeUtil;
import com.jogger.beautifulapp.widget.CircleImageView;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;

public class RecentDescActivity extends BaseDescActivity<RecentDescPresenter> implements
        RecentDescContract.View {
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
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.ll_img_container)
    LinearLayout llImgContainer;
    private DownloadDialog mDownloadDialog;
    private RecentAppData mAppData;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recent_desc;
    }

    @Override
    protected RecentDescPresenter createPresenter() {
        return new RecentDescPresenter();
    }


    @Override
    protected void init() {
        super.init();
        mAppData = (RecentAppData) getIntent().getSerializableExtra(Constant.APP_DATA);
        Glide.with(this)
                .load(mAppData.getIcon_image())
                .into(ivIcon);
        Glide.with(this)
                .load(mAppData.getAuthor_avatar_url())
                .into(ivUserIcon);
        tvTitle.setText(mAppData.getTitle());
        tvSubTitle.setText(mAppData.getSub_title());
        tvDesc.setMovementMethod(LinkMovementMethod.getInstance());
        if (mAppData.getTags() != null)
            for (int i = 0; i < mAppData.getTags().size(); i++) {
                fblTag.addView(createTextView(mAppData.getTags().get(i)));
            }
        new DescTextUtil().setDrawableText(mAppData.getDescription(), tvDesc);
        if (mAppData.getAll_images() != null)
            for (int i = 0; i < mAppData.getAll_images().size(); i++) {
                ImageView imageView = new ImageView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                        (LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                                .WRAP_CONTENT);
                layoutParams.bottomMargin = SizeUtil.dp2px(16);
                imageView.setLayoutParams(layoutParams);
                Glide.with(this)
                        .load(mAppData.getAll_images().get(i))
                        .centerCrop()
                        .into(imageView);
                llImgContainer.addView(imageView);
            }

    }

    @Override
    protected void download() {
        if (mAppData.getDownload_urls() == null || mAppData.getDownload_urls().isEmpty()) return;
        if (mDownloadDialog == null)
            mDownloadDialog = new DownloadDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.DOWNLOAD_URLS, (Serializable) mAppData.getDownload_urls());
        bundle.putLong(Constant.DOWNLOAD_ID, mAppData.getId());
        for (int i = 0; i < mAppData.getDownload_urls().size(); i++) {
            mAppData.getDownload_urls().get(i).setName(mAppData.getTitle());
            mAppData.getDownload_urls().get(i).setId(mAppData.getId());
        }
        mDownloadDialog.setArguments(bundle);
        mDownloadDialog.show(getSupportFragmentManager(), DownloadDialog.class.getSimpleName());
    }

    @OnClick({R.id.iv_user_icon})
    public void onDescClick(View v) {
        switch (v.getId()) {
            case R.id.iv_user_icon:
                User user=new User(mAppData);
                startNewActivity(UserHomeActivity.class, Constant.USER_INFO, user);
                break;
        }
    }

    private View createTextView(Tag tag) {
        final CardView cardView = new CardView(this);
        FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(
                FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT);
        cardView.setRadius(SizeUtil.dp2px(12));
        TextView textView = new TextView(this);
        params.leftMargin = SizeUtil.dp2px(1);
        params.topMargin = SizeUtil.dp2px(8);
        params.bottomMargin = SizeUtil.dp2px(1);
        params.rightMargin = SizeUtil.dp2px(8);
        cardView.setLayoutParams(params);
        textView.setPadding(SizeUtil.dp2px(24), 0, SizeUtil.dp2px(24), 0);
        textView.setTextColor(Color.BLACK);
        textView.setText(tag.getTitle());
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, SizeUtil.dp2px(16));
        cardView.addView(textView);
        cardView.setTag(tag);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewActivity(TagsMoreActivity.class,Constant.ID,((Tag) cardView.getTag()).getId());
            }
        });
        return cardView;
    }

}
