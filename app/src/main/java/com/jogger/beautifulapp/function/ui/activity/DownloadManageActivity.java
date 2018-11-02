package com.jogger.beautifulapp.function.ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jogger.beautifulapp.BuildConfig;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseActivity;
import com.jogger.beautifulapp.base.recyclerview.MyLinearLayoutManager;
import com.jogger.beautifulapp.entity.DownloadInfo;
import com.jogger.beautifulapp.function.adapter.DownloadManageAdapter;
import com.jogger.beautifulapp.function.contract.DownloadManageContract;
import com.jogger.beautifulapp.function.presenter.DownloadManagePresenter;
import com.jogger.beautifulapp.function.service.DownloadService;
import com.jogger.beautifulapp.function.ui.dialog.DownloadHintDialog;
import com.jogger.beautifulapp.util.L;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DownloadManageActivity extends BaseActivity<DownloadManagePresenter> implements DownloadManageContract.View, DownloadManageAdapter.OnDownloadManageListener,
        BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener,
        DownloadService.OnHttpDownloadListener {
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.tv_no_content)
    TextView tvNoContent;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    private DownloadManageAdapter mAdapter;
    private boolean mIsLoading;
    private DownloadInfo mDownloadInfo;
    private ServiceConnection mServiceConnection;
    private DownloadService.MyBinder mBinder;

    @Override
    protected DownloadManagePresenter createPresenter() {
        return new DownloadManagePresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_download_manage;
    }

    @Override
    protected void init() {
        mAdapter = new DownloadManageAdapter(null);
        mAdapter.setOnDownloadManageListener(this);
        rvContent.setLayoutManager(new MyLinearLayoutManager(this));
        rvContent.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mBinder = (DownloadService.MyBinder) iBinder;
                mBinder.setOnHttpDownloadListener(DownloadManageActivity.this);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
        mPresenter.getDownloadDatas();
    }


    @OnClick({R.id.tv_title, R.id.tv_cancel, R.id.tv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title:
                finish();
                break;
            case R.id.tv_cancel:
                mAdapter.cancelSelected();
                break;
            case R.id.tv_delete:
                mAdapter.deleteSelected();
                break;
        }
    }

    @Override
    public void hasSelected(boolean has) {
        llContainer.setVisibility(has ? View.VISIBLE : View.GONE);
    }

    @Override
    public void deleteCompleted() {
        llContainer.setVisibility(View.GONE);
        if (mAdapter.getData().isEmpty()) {
            rvContent.setVisibility(View.GONE);
            tvNoContent.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mDownloadInfo = (DownloadInfo) adapter.getItem(position);
        showDialog();
    }

    private void showDialog() {
        if (mDownloadInfo == null) {
            L.e("----downloadInfo is null");
            return;
        }
        DownloadHintDialog downloadHintDialog = new DownloadHintDialog();
        switch (mDownloadInfo.getState()) {
            case DOWNLOADING:
            case START:
                downloadHintDialog.setTitle("正在下载");
                downloadHintDialog.setContent("你想要暂停下载该文件吗？");
                downloadHintDialog.setConfirmText("暂停");
                break;
            case PAUSE:
                downloadHintDialog.setTitle("已加入队列");
                downloadHintDialog.setContent("你想要继续下载该文件吗？");
                downloadHintDialog.setConfirmText("继续");
                break;
            case ERROR:
            case STOP:
                downloadHintDialog.setTitle("下载失败");
                downloadHintDialog.setContent("你想要重新尝试下载该文件？");
                downloadHintDialog.setConfirmText("重试");
                break;
            case FINISH:
                //启动安装
                File file = new File(mDownloadInfo.getSavePath());
                if (file.exists()) {
                    installApk();
                    return;
                } else {
                    downloadHintDialog.setTitle("下载失败");
                    downloadHintDialog.setContent("未找到已下载的文件");
                }
                break;
        }
        downloadHintDialog.setOnDownloadHintDialogListener(new DownloadHintDialog.OnDownloadHintDialogListener() {
            @Override
            public void onConfirm() {
                switch (mDownloadInfo.getState()) {
                    case DOWNLOADING:
                    case START:
                        mPresenter.pauseDownload();
                        break;
                    case PAUSE:
                        mPresenter.startDownload();
                        break;
                    case ERROR:
                    case STOP:
                    case FINISH:
                        mPresenter.reDownload();
                        break;
                }

            }
        });
        downloadHintDialog.show(getSupportFragmentManager(), DownloadHintDialog.class.getSimpleName());
    }


    @Override
    public DownloadInfo getDownloadInfo() {
        return mDownloadInfo;
    }

    @Override
    public void getDownloadDatasSuccess(List<DownloadInfo> downloadInfos) {
        if (downloadInfos != null && !downloadInfos.isEmpty()) {
            tvNoContent.setVisibility(View.GONE);
            rvContent.setVisibility(View.VISIBLE);
            mAdapter.setNewData(downloadInfos);
        } else {
            tvNoContent.setVisibility(View.VISIBLE);
            rvContent.setVisibility(View.GONE);
        }
    }

    @Override
    public void getMoreDatasSuccess(List<DownloadInfo> downloadInfos) {
        mAdapter.addData(downloadInfos);
        mAdapter.loadMoreComplete();
        mIsLoading = false;
    }

    @Override
    public void onLoadMoreRequested() {
        if (mIsLoading)
            return;
        mIsLoading = true;
        rvContent.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mPresenter.isHasNext())
                    mPresenter.getMoreDatas();
                else mAdapter.loadMoreEnd();
            }
        }, 100);
    }

    @Override
    public void onCompleted(DownloadInfo downloadInfo) {
        mAdapter.updateDownloadState(downloadInfo);
    }

    @Override
    public void onStart(DownloadInfo downloadInfo) {
        mAdapter.updateDownloadState(downloadInfo);
    }

    @Override
    public void updateProgress(DownloadInfo downloadInfo, long readLength, long countLength) {
        mAdapter.updateDownloadState(downloadInfo);
    }

    @Override
    public void onStop(DownloadInfo downloadInfo) {
        mAdapter.updateDownloadState(downloadInfo);
    }

    @Override
    public void onPause(DownloadInfo downloadInfo) {
        mAdapter.updateDownloadState(downloadInfo);
    }

    @Override
    public void onError(DownloadInfo downloadInfo) {
        mAdapter.updateDownloadState(downloadInfo);
    }

    private void installApk() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        File file = new File(mDownloadInfo.getSavePath());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileProvider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }
}
