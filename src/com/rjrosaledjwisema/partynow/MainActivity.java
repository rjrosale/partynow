package com.rjrosaledjwisema.partynow;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.parse.Parse;
import com.parse.ParseObject;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	private Button post, find;
	private FragmentAdapter mAdapter;
	private ViewPager mPager;
	private PageIndicator mIndicator;
	private int Number = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

		mAdapter = new FragmentAdapter(getSupportFragmentManager());

		mPager = (ViewPager)findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);

		mIndicator = (TitlePageIndicator)findViewById(R.id.indicator);
		mIndicator.setViewPager(mPager);


		post = (Button)findViewById(R.id.postEvent);
		find = (Button)findViewById(R.id.findEvent);

		initButtonListeners();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	private void initButtonListeners() {
		post.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				MainActivity.this.startActivity(new Intent(MainActivity.this, PostActivity.class));
			}
		});

		find.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				MainActivity.this.startActivity(new Intent(MainActivity.this, MapActivity.class));
			}
		});
	}
}
