package com.jogger.beautifulapp.function.ui.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.control.DownloadControl;
import com.jogger.beautifulapp.entity.DownloadUrl;
import com.jogger.beautifulapp.util.L;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jogger on 2018/10/10.
 */
public class DownloadDialog extends BaseDialogFragment {
    @BindView(R.id.ibtn_googleplay)
    ImageButton ibtnGooglePlay;
    @BindView(R.id.ibtn_wandoujia)
    ImageButton ibtnWanDouJia;
    @BindView(R.id.ibtn_direct)
    ImageButton ibtnDirect;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private List<DownloadUrl> mDownloadUrls;

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_download;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void init(View view) {
        super.init(view);
        Bundle arguments = getArguments();
        if (arguments == null) return;
        mDownloadUrls = (List<DownloadUrl>) arguments.getSerializable(Constant.DOWNLOAD_URLS);
        String size = arguments.getString(Constant.SIZE);
        L.e("-----mDownloadUrls:" + mDownloadUrls);
        if (mDownloadUrls == null || mDownloadUrls.isEmpty()) {
            return;
        }
        if (!TextUtils.isEmpty(size)) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText("应用大小：" + size);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
        for (int i = 0; i < mDownloadUrls.size(); i++) {
            DownloadUrl downloadUrl = mDownloadUrls.get(i);
            if (Constant.CHANNEL_GOOGLE.equals(downloadUrl.getChannel())) {
                ibtnGooglePlay.setVisibility(View.VISIBLE);
            } else if (Constant.CHANNEL_DIRECT.equals(downloadUrl.getChannel())) {
                ibtnDirect.setVisibility(View.VISIBLE);
            } else if (Constant.CHANNEL_WANDOUJIA.equals(downloadUrl.getChannel())) {
                ibtnWanDouJia.setVisibility(View.VISIBLE);
            }
        }
    }

    @OnClick({R.id.ibtn_googleplay, R.id.ibtn_wandoujia, R.id.ibtn_direct})
    public void onClick(View v) {
        if (mDownloadUrls == null) {
            return;
        }
        String currentDownload = null;
        switch (v.getId()) {
            case R.id.ibtn_googleplay:
                currentDownload = Constant.CHANNEL_GOOGLE;
                break;
            case R.id.ibtn_wandoujia:
                currentDownload = Constant.CHANNEL_WANDOUJIA;
                break;
            case R.id.ibtn_direct:
                currentDownload = Constant.CHANNEL_DIRECT;
                break;
        }
        DownloadUrl downloadUrl = findDownloadUrlByChannel(currentDownload);
        if (downloadUrl == null) return;
        DownloadControl.startDownload(downloadUrl);
        dismiss();
    }

    private DownloadUrl findDownloadUrlByChannel(String currentDownload) {
        for (int i = 0; i < mDownloadUrls.size(); i++) {
            DownloadUrl downloadUrl = mDownloadUrls.get(i);
            if (currentDownload.equals(downloadUrl.getChannel())) {
                return downloadUrl;
            }
        }
        return null;
    }
}
