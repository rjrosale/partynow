package com.rjrosaledjwisema.partynow;

import com.viewpagerindicator.IconPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter 
implements IconPagerAdapter{

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public int getIconResId(int index) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Fragment getItem(int position) 
    {
        // TODO Auto-generated method stub
        Fragment fragment = null;
        switch(position){
        case 0:
            return fragment;
        case 1:
        	fragment = new PostActivity();
            break;
        case 2:
        	fragment = new MapActivity();
        	break;
        }
        return fragment;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 3;
    }    
 
    @Override
    public CharSequence getPageTitle(int position){
        String title = "";
        switch(position){
        case 0:
            title = "My Events";
            break;
        case 1:
            title = "Post an Event";
            break;
        case 2:
            title = "Find an Event";
            break;
        }
        return title;
    }

}
