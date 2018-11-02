package com.jogger.beautifulapp.function.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.base.recyclerview.MyLinearLayoutManager;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.AppSocialArticleData;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.entity.SocialArticle;
import com.jogger.beautifulapp.function.adapter.RankAdapter;
import com.jogger.beautifulapp.function.contract.RankContract;
import com.jogger.beautifulapp.function.presenter.RankPresenter;
import com.jogger.beautifulapp.function.ui.activity.MainActivity;
import com.jogger.beautifulapp.function.ui.activity.RecentDescActivity;

import butterknife.BindView;

/**
 * Created by Jogger on 2018/6/10.应用排行
 */

public class RankFragment extends BaseFragment<RankPresenter> implements RankContract.View,BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    private RankAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_rank;
    }

    @Override
    protected RankPresenter createPresenter() {
        return new RankPresenter();
    }

    @Override
    public void init() {
        mAdapter = new RankAdapter(null);
        rvContent.setLayoutManager(new MyLinearLayoutManager(mActivity));
        rvContent.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mPresenter.getRankDatas(1, 50);
    }

    @Override
    public void onResume() {
        super.onResume();
        setBgColor();
    }

    @Override
    public void getRankDatasSuccess(AppSocialArticleData appSocialArticleData) {
        mAdapter.setNewData(appSocialArticleData.getApps());
    }

    @Override
    public void getRecentDescDataSuccess(RecentAppData appRecentData) {
        startNewActivity(RecentDescActivity.class, Constant.APP_DATA, appRecentData);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && mIsViewCreated) {
            setBgColor();
        }
    }

    private void setBgColor() {
        int color = getResources().getColor(R.color.colorFind);
        rlTitle.setBackgroundColor(color);
        ((MainActivity) mActivity).getBaseContainer().setBackgroundColor(color);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        SocialArticle socialArticle = (SocialArticle) adapter.getItem(position);
        if (socialArticle == null) return;
        mPresenter.getRecentDescData(socialArticle.getArticle_id());
    }
}
