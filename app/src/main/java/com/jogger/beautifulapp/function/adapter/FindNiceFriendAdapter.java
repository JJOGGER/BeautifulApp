package com.jogger.beautifulapp.function.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.recyclerview.DividerLinearItemDecoration;
import com.jogger.beautifulapp.base.recyclerview.UnScrollLinearLayoutManager;
import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.User;
import com.jogger.beautifulapp.entity.UsersRank;
import com.jogger.beautifulapp.function.ui.activity.UserHomeActivity;

import java.util.List;


public class FindNiceFriendAdapter extends BaseQuickAdapter<UsersRank, BaseViewHolder> implements
        FindNiceFriendItemAdapter.OnItemClickListener {
    public FindNiceFriendAdapter(@Nullable List<UsersRank> data) {
        super(R.layout.rv_find_nice_friend_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UsersRank item) {
        helper.setText(R.id.tv_top, item.getName());
        FindNiceFriendItemAdapter adapter = new FindNiceFriendItemAdapter(item.getUsers());
        RecyclerView recyclerView = helper.getView(R.id.rv_content);
        UnScrollLinearLayoutManager layoutManager = new UnScrollLinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerLinearItemDecoration(mContext));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        User user = (User) adapter.getItem(position);
        if (user == null) return;
        Intent intent=new Intent(mContext,UserHomeActivity.class);
        intent.putExtra(Constant.USER_INFO,user);
        mContext.startActivity(intent);
    }
}
