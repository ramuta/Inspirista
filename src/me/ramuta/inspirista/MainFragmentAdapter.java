package me.ramuta.inspirista;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainFragmentAdapter extends FragmentPagerAdapter {
	FragmentActivity activity;
	public static final String TEST = "test";	

	public MainFragmentAdapter(FragmentManager fm, FragmentActivity activity) {
		super(fm);
		this.activity = activity;		
	}

	@Override
	public Fragment getItem(int i) {
		Fragment fragment = new MainImagesFragment();
        Bundle args = new Bundle();
        args.putInt(MainImagesFragment.POSITION, i);
        fragment.setArguments(args);
        return fragment;
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return activity.getString(R.string.title_section1).toUpperCase();
            case 1: return activity.getString(R.string.title_section2).toUpperCase();
            case 2: return activity.getString(R.string.title_section3).toUpperCase();
        }
        return null;
    }
}
