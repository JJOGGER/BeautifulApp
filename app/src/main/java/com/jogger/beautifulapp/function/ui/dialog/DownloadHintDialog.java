package com.jogger.beautifulapp.function.ui.dialog;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jogger.beautifulapp.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jogger on 2018/10/24.
 */
public class DownloadHintDialog extends BaseDialogFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    private OnDownloadHintDialogListener mListener;
    private String mTitle;
    private String mContent;
    private String mCancelText;
    private String mConfirmText;

    public interface OnDownloadHintDialogListener {
        void onConfirm();
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public void setCancelText(String cancelText) {
        mCancelText = cancelText;
    }

    public void setConfirmText(String confirmText) {
        mConfirmText = confirmText;
    }

    public void setOnDownloadHintDialogListener(OnDownloadHintDialogListener listener) {
        mListener = listener;
    }

    @Override
    protected void init(View view) {
        if (mContent != null)
            tvContent.setText(mContent);
        if (mTitle != null)
            tvTitle.setText(mTitle);
        if (!TextUtils.isEmpty(mCancelText))
            tvCancel.setText(mCancelText);
        if (!TextUtils.isEmpty(mConfirmText))
            tvConfirm.setText(mConfirmText);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_download_hint;
    }

    @OnClick({R.id.tv_cancel, R.id.tv_confirm})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                break;
            case R.id.tv_confirm:
                if (mListener != null)
                    mListener.onConfirm();
                break;
        }
        dismiss();
    }
}
