package com.rjrosaledjwisema.partynow;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class Settings extends Activity {

	private TimePicker time;
	private EditText proximity;
	private Button submit;
	private ParseUser user;
	private String timeString;
	private int proximityInt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_layout);
		
		user = ParseUser.getCurrentUser();
		Toast.makeText(this, user.getUsername(), Toast.LENGTH_SHORT).show();
		time = (TimePicker) findViewById(R.id.party_time);
		proximity = (EditText) findViewById(R.id.proximity);
		submit = (Button) findViewById(R.id.submit_button);
		initButtonListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void initButtonListeners() {
		submit.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				timeString = time.getCurrentHour() + ":" + time.getCurrentMinute();
				proximityInt = Integer.parseInt(proximity.getText().toString());
				user.put("time", timeString);
				user.put("proximity", proximityInt);
		
				user.saveInBackground(new SaveCallback() {

					@Override
					public void done(ParseException e) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "Successfully saved", Toast.LENGTH_SHORT).show();

						Intent i = new Intent(Settings.this, Main.class);
						startActivity(i);
						finish();
					}
				});
			}
		});
	}
}
