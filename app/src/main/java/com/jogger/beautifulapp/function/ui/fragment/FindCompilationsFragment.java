package com.jogger.beautifulapp.function.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.base.recyclerview.MyGridLayoutManager;
import com.jogger.beautifulapp.base.recyclerview.SpaceItemDecoration;
import com.jogger.beautifulapp.entity.AppCompilationsData;
import com.jogger.beautifulapp.function.adapter.FindCompilationsAdapter;
import com.jogger.beautifulapp.function.contract.FindCompilationsContract;
import com.jogger.beautifulapp.function.presenter.FindCompilationsPresenter;
import com.jogger.beautifulapp.util.SizeUtil;

import butterknife.BindView;

/**
 * Created by Jogger on 2018/6/14.发现合辑
 */

public class FindCompilationsFragment extends BaseFragment<FindCompilationsPresenter> implements
        FindCompilationsContract.View {
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    private FindCompilationsAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_item;
    }

    @Override
    public void init() {
        rvContent.setLayoutManager(new MyGridLayoutManager(mActivity, 2));
        rvContent.addItemDecoration(new SpaceItemDecoration(SizeUtil.dp2px(5)));
        mPresenter.getFindCompilationsDatas(1, 20);
        mAdapter = new FindCompilationsAdapter(null);
        rvContent.setAdapter(mAdapter);
    }

    @Override
    protected FindCompilationsPresenter createPresenter() {
        return new FindCompilationsPresenter();
    }

    @Override
    public void loadDatas(AppCompilationsData appData) {
        mAdapter.setNewData(appData.getAlbums());
    }
}
