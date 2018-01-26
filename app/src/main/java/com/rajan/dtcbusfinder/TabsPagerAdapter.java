package com.rajan.dtcbusfinder;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ayush goel on 21/10/2015.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new ViewRouteFragment();
            case 1:
                return new ViewBusFragment();
            case 2:
                return new ViewTrainsFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
