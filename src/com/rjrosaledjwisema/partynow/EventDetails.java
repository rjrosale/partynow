package com.rjrosaledjwisema.partynow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.parse.ParseUser;

public class EventDetails extends SherlockActivity {
	private TextView eventname, username, eventaddr, eventdate, eventtime;
	private Button attendEvent, seeAttendees, viewHostProfile;
	private ParseUser user;
	private String eventID, otherUser;
	private Bundle bundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_details);
		bundle = this.getIntent().getExtras();
		username = (TextView)findViewById(R.id.event_details_hostNameText);
		eventname = (TextView)findViewById(R.id.event_details_eventNameText);
		eventaddr = (TextView)findViewById(R.id.event_details_eventAddressText);
		eventdate = (TextView)findViewById(R.id.event_details_eventDateText);
		eventtime = (TextView)findViewById(R.id.event_details_eventTimeText);
		attendEvent = (Button)findViewById(R.id.event_details_attendButton);
		seeAttendees = (Button)findViewById(R.id.event_details_viewAttendees);
		viewHostProfile = (Button)findViewById(R.id.event_details_viewHostProfile);
		initLayout();
	}

	private void initLayout() {
		user = ParseUser.getCurrentUser();
		if (bundle != null) {
			username.setText(bundle.getString("eventhost"));
			eventname.setText(bundle.getString("eventname"));
			eventaddr.setText(bundle.getString("eventaddress"));
			eventtime.setText(bundle.getString("eventtime"));
			eventdate.setText(bundle.getString("eventdate"));
			eventID = bundle.getString("objectID");
			otherUser = bundle.getString("eventhost");
		}
		attendEvent.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				user.add("events_attending", eventID);
				user.saveInBackground();
				Toast.makeText(EventDetails.this, "You are attending event # " + eventID, Toast.LENGTH_SHORT).show();
			}
		});
		seeAttendees.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				
			}
		});
		viewHostProfile.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent i = new Intent(EventDetails.this, OtherProfile.class);
				i.putExtra("username", otherUser);
				startActivity(i);
			}
		});
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main, menu);

		return true;
	}
}
