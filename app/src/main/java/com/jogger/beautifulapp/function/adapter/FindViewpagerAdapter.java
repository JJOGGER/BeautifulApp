package com.jogger.beautifulapp.function.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.function.ui.fragment.FindChoiceFragment;
import com.jogger.beautifulapp.function.ui.fragment.FindRecentFragment;
import com.jogger.beautifulapp.util.Util;

/**
 * Created by Jogger on 2018/6/12.
 */

public class FindViewpagerAdapter extends FragmentPagerAdapter {

    private final String[] mTabs;

    public FindViewpagerAdapter(FragmentManager fm) {
        super(fm);
        mTabs = Util.getApp().getResources().getStringArray(R.array.find_tab);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FindChoiceFragment();
            case 1:
                return new FindRecentFragment();
            case 2:
            case 3:
            case 4:
                return new FindChoiceFragment();
            default:
                return new FindChoiceFragment();
        }
    }

    @Override
    public int getCount() {
        return mTabs.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs[position];
    }
}
