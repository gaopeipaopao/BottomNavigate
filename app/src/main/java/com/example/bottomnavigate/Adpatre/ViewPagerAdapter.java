package com.example.bottomnavigate.Adpatre;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 泡泡 on 2017/12/2.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String[]  titles=new String[]{"推荐","视频","图片","段子"};
    private List<Fragment> mFragment=new ArrayList<>();


    public ViewPagerAdapter(List<Fragment> mfragment, FragmentManager fm) {
        super(fm);
        this.mFragment=mfragment;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
