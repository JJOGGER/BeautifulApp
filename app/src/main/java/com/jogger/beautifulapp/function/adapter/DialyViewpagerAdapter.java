package com.jogger.beautifulapp.function.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jogger.beautifulapp.constant.Constant;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.function.ui.fragment.DialiyPageFragment;

import java.util.List;


public class DialyViewpagerAdapter extends FragmentPagerAdapter {
    private List<AppInfo> mAppInfos;

    public DialyViewpagerAdapter(FragmentManager fm, List<AppInfo> appInfos) {
        super(fm);
        mAppInfos = appInfos;
    }

    @Override
    public Fragment getItem(int position) {
        DialiyPageFragment dialyFragment = new DialiyPageFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.APP_INFO, mAppInfos.get(position));
        dialyFragment.setArguments(bundle);
        return dialyFragment;
    }

    @Override
    public int getCount() {
        return mAppInfos.size();
    }
}
