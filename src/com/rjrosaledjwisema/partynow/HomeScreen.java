package com.rjrosaledjwisema.partynow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;

import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

public class HomeScreen extends Fragment {
	private Button post, find;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View inf = inflater.inflate(R.layout.activity_main, container, false);

		post = (Button)inf.findViewById(R.id.postEvent);
		find = (Button)inf.findViewById(R.id.findEvent);

		initButtonListeners();
		
		return inf;
	}


	private void initButtonListeners() {
		post.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				getActivity().startActivity(new Intent(getActivity(), EventsPostedActivity.class));
			}
		});

		find.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				getActivity().startActivity(new Intent(getActivity(), EventsAttendingActivity.class));
			}
		});
	}
}
