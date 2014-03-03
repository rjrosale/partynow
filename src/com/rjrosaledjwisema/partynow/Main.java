package com.rjrosaledjwisema.partynow;

import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import com.rjrosaledjwisema.partynow.R;

public class Main extends FragmentActivity {
    
    FragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;
    int Number = 0;

      
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        
        mAdapter = new FragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (TitlePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        
    }
    
      
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
}
