package com.joinable.phatsprints.joinable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int tabCount;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.tabCount = NumOfTabs;
    }

    @Override
    public Fragment getItem(int pos) {
        switch(pos) {
            case 0:
                return new CreateEvent();
            case 1:
                return new Notifications();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
