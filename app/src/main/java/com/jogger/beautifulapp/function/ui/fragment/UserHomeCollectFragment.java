package com.jogger.beautifulapp.function.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BaseFragment;
import com.jogger.beautifulapp.base.recyclerview.MyGridLayoutManager;
import com.jogger.beautifulapp.base.recyclerview.SpaceItemDecoration;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.AppCollectData;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.Collection;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.entity.User;
import com.jogger.beautifulapp.function.adapter.UserCollectAdapter;
import com.jogger.beautifulapp.function.contract.UserHomeCollectContract;
import com.jogger.beautifulapp.function.presenter.UserHomeCollectPresenter;
import com.jogger.beautifulapp.function.ui.activity.FindChoiceDescActivity;
import com.jogger.beautifulapp.function.ui.activity.RecentDescActivity;
import com.jogger.beautifulapp.function.ui.activity.UserHomeActivity;
import com.jogger.beautifulapp.util.SizeUtil;

import butterknife.BindView;

/**
 * Created by Jogger on 2018/6/17.
 * 用户收藏
 */

public class UserHomeCollectFragment extends BaseFragment<UserHomeCollectPresenter> implements
        UserHomeCollectContract.View, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    private UserCollectAdapter mAdapter;
    private User mUser;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_home_collect;
    }

    @Override
    public void init() {
        rvContent.setLayoutManager(new MyGridLayoutManager(mActivity, 2));
        rvContent.addItemDecoration(new SpaceItemDecoration(SizeUtil.dp2px(4)));
        mAdapter = new UserCollectAdapter(null);
        mAdapter.setOnItemClickListener(this);
        rvContent.setAdapter(mAdapter);
        mPresenter.getUserCollectDatas(1, 20);
        rvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //向上dy负值
                if (mActivity instanceof UserHomeActivity) {
                    ((UserHomeActivity) mActivity).scrollToTop(!rvContent.canScrollVertically(-1)
                            , dy);
                }
            }
        });
    }

    @Override
    protected UserHomeCollectPresenter createPresenter() {
        Bundle arguments = getArguments();
        if (arguments == null) return null;
        mUser = (User) arguments.getSerializable(Constant.USER_INFO);
        if (mUser == null) return null;
        return new UserHomeCollectPresenter(mUser.getId());
    }

    @Override
    public void getUserCollectDatasSuccess(AppCollectData appCollectData) {
        mAdapter.setNewData(appCollectData.getCollections());
//        ((UserHomeActivity)mActivity).updateCount(-1,appCollectData.getCollections());
    }

    @Override
    public void getDesc1DatasSuccess(AppInfo appInfo) {
        startNewActivity(FindChoiceDescActivity.class, Constant.APP_INFO, appInfo);
    }

    @Override
    public void getDesc2DatasSuccess(RecentAppData recentAppData) {
        startNewActivity(RecentDescActivity.class, Constant.APP_DATA, recentAppData);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Collection collection = (Collection) adapter.getItem(position);
        if (collection == null) return;
        int type = Constant.TYPE_IMG_DESC;//带图详情页
        if ("community".equals(collection.getSource())) {
            type =  Constant.TYPE_RECENT_DESC;
        }
        mPresenter.getDescDatas(collection.getId(), type);
    }
}
