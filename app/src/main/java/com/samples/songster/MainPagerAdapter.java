package com.samples.songster;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.samples.songster.mylist.MyListFragment;
import com.samples.songster.search.SearchFragment;

/**
 * Created by chrisbraunschweiler1 on 26/10/15.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return SearchFragment.getInstance();
        } else {
            return MyListFragment.getInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
