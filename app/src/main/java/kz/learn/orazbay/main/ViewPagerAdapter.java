package kz.learn.orazbay.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by orazbay on 11/9/17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Fragment [] fragments;
    public ViewPagerAdapter(FragmentManager fm,Fragment [] fragments) {
        super(fm);
        this.fragments=fragments;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
