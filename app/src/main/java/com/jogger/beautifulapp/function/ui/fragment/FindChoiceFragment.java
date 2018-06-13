package com.jogger.beautifulapp.function.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.base.recyclerview.MyLinearLayoutManager;
import com.jogger.beautifulapp.entity.FindChoiceData;
import com.jogger.beautifulapp.entity.MediaArticle;
import com.jogger.beautifulapp.function.adapter.FindChoiceAdapter;
import com.jogger.beautifulapp.function.contract.FindChoiceContract;
import com.jogger.beautifulapp.function.presenter.FindChoicePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Jogger on 2018/6/12.
 * 发现精选页
 */

public class FindChoiceFragment extends BaseFragment<FindChoicePresenter> implements
        FindChoiceContract.View {
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    private FindChoiceAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_item;
    }

    @Override
    protected void createPresenter() {
        mPresenter = new FindChoicePresenter();
    }

    @Override
    public void init() {
        rvContent.setLayoutManager(new MyLinearLayoutManager(mActivity));
        mAdapter = new FindChoiceAdapter(null);
        rvContent.setAdapter(mAdapter);
        mPresenter.getFindChoiceDatas(String.valueOf(0));
    }

    @Override
    public void loadDatas(FindChoiceData findChoiceData) {
        List<MediaArticle> mediaArticles = new ArrayList<>();
        for (int i = 0; i < findChoiceData.getContent().size(); i++) {
            mediaArticles.add(findChoiceData.getContent().get(i).getApps().get(0));
        }
        mAdapter.setNewData(mediaArticles);
    }
}
