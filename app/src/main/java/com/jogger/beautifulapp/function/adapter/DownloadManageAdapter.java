package com.jogger.beautifulapp.function.adapter;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.db.DBManager;
import com.jogger.beautifulapp.entity.DownloadInfo;
import com.jogger.beautifulapp.http.download.DownloadState;
import com.jogger.beautifulapp.util.L;
import com.jogger.beautifulapp.util.Util;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jogger on 2018/10/19.
 */
public class DownloadManageAdapter extends BaseQuickAdapter<DownloadInfo, BaseViewHolder> {
    private OnDownloadManageListener mListener;
    private boolean mCancelSelected;
    private SimpleDateFormat mDayFormat;
    private SimpleDateFormat mHMFormat;
    private Date mDate;
    private Handler mHandler;


    @SuppressWarnings("all")
    public void deleteSelected() {
        mHandler = new Handler(Looper.getMainLooper());
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Iterator<DownloadInfo> iterator = mData.iterator(); iterator.hasNext(); ) {
                    DownloadInfo next = iterator.next();
                    if (next.isSelected()) {
                        File file = new File(next.getSavePath());
                        if (file.exists())
                            file.delete();
                        DBManager.getDaoSession().delete(next);
                        iterator.remove();
                    }
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mContext == null) return;
                        cancelSelected();
                        if (mListener != null)
                            mListener.deleteCompleted();
                    }
                });
            }
        }).start();
    }

    /**
     * 更新
     */
    public void updateDownloadState(DownloadInfo downloadInfo) {
        for (int i = 0; i < mData.size(); i++) {
            DownloadInfo info = mData.get(i);
            if (info.getId() == downloadInfo.getId()) {
                setData(i, downloadInfo);
                break;
            }
        }
    }

    public interface OnDownloadManageListener {
        void hasSelected(boolean has);

        void deleteCompleted();
    }

    public void setOnDownloadManageListener(OnDownloadManageListener listener) {
        mListener = listener;
    }

    public void cancelSelected() {
        mCancelSelected = true;
        notifyDataSetChanged();
    }

    @Override
    public void setNewData(@Nullable List<DownloadInfo> data) {
        super.setNewData(data);
    }

    public DownloadManageAdapter(@Nullable List<DownloadInfo> data) {
        super(R.layout.rv_download_manage_item_item, data);
        mDayFormat = new SimpleDateFormat("yyyy/MM/dd", Util.getApp().getResources().getConfiguration().locale);
        mHMFormat = new SimpleDateFormat("HH:mm", Util.getApp().getResources().getConfiguration().locale);
        mDate = new Date();
    }

    @Override
    protected void convert(final BaseViewHolder helper, final DownloadInfo item) {
        helper.setText(R.id.tv_title, item.getTitle() + "");
        if (item.getState() == DownloadState.START) {
            helper.setText(R.id.tv_state, "开始下载");
        } else if (item.getState() == DownloadState.DOWNLOADING) {
            helper.setText(R.id.tv_state, "正在下载");
        } else if (item.getState() == DownloadState.PAUSE) {
            helper.setText(R.id.tv_state, "暂停");
        } else if (item.getState() == DownloadState.STOP) {
            helper.setText(R.id.tv_state, "已停止");
        } else if (item.getState() == DownloadState.ERROR) {
            helper.setText(R.id.tv_state, "下载失败");
        } else {
            helper.setText(R.id.tv_state, "已完成");
        }
        boolean showPro = item.getState() != DownloadState.FINISH && item.getState() != DownloadState.STOP && item.getState() != DownloadState.ERROR;
        helper.getView(R.id.pb_progress).setVisibility(showPro ? View.VISIBLE : View.GONE);
        if (showPro) {
            helper.setProgress(R.id.pb_progress, (int) item.getReadLength(), (int) item.getCountLength());
        }
        float size = item.getCountLength() / 1024f / 1024f;
        if (size > 1) {
            DecimalFormat fnum = new DecimalFormat("##0.00");
            String sizeF = fnum.format(size);
            helper.setText(R.id.tv_size, sizeF + "MB");
        } else {
            helper.setText(R.id.tv_size, item.getCountLength() / 1024 + "KB");
        }
        mDate.setTime(item.getDownloadDate());
        String date = mDayFormat.format(mDate);
        mDate.setTime(System.currentTimeMillis());
        String todayDate = mDayFormat.format(mDate);
        if (date.equals(todayDate)) {
            String hm = mHMFormat.format(mDate);
            helper.setText(R.id.tv_date, hm);
        } else {
            helper.setText(R.id.tv_date, date);
        }
        helper.setOnCheckedChangeListener(R.id.cb_check, new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                item.setSelected(checked);
                L.e("--------------checked:" + checked);
                boolean hasSelected = false;
                for (int i = 0; i < mData.size(); i++) {
                    if (mData.get(i).isSelected()) {
                        hasSelected = true;
                        break;
                    }
                }
                if (mListener != null)
                    mListener.hasSelected(hasSelected);
            }
        });
        if (mCancelSelected) {
            helper.setChecked(R.id.cb_check, false);
        } else {
            helper.setChecked(R.id.cb_check, item.getSelected());
        }
    }

}
