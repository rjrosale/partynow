package com.rjrosaledjwisema.partynow;

<<<<<<< HEAD
import android.content.Intent;
=======
import java.io.IOException;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
>>>>>>> 17a5d268be47ef88222a510b3a9debcffd4a1948
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class EventDetails extends SherlockActivity {
<<<<<<< HEAD
	private TextView eventname, username, eventaddr, eventdate, eventtime;
	private Button attendEvent, seeAttendees, viewHostProfile;
	private ParseUser user;
	private String eventID, otherUser;
	private Bundle bundle;
=======
	private TextView eventName, hostName, eventAddress, eventDate, eventTime;
	private Button attend, seeAttendees;
>>>>>>> 17a5d268be47ef88222a510b3a9debcffd4a1948

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_details);
<<<<<<< HEAD
		bundle = this.getIntent().getExtras();
		username = (TextView)findViewById(R.id.event_details_hostNameText);
		eventname = (TextView)findViewById(R.id.event_details_eventNameText);
		eventaddr = (TextView)findViewById(R.id.event_details_eventAddressText);
		eventdate = (TextView)findViewById(R.id.event_details_eventDateText);
		eventtime = (TextView)findViewById(R.id.event_details_eventTimeText);
		attendEvent = (Button)findViewById(R.id.event_details_attendButton);
		seeAttendees = (Button)findViewById(R.id.event_details_viewAttendees);
		viewHostProfile = (Button)findViewById(R.id.event_details_viewHostProfile);
=======

		eventName = (TextView)findViewById(R.id.event_details_eventNameText);
		
		 Bundle intentbundle = this.getIntent().getExtras();
			if(intentbundle != null) {
				String event = intentbundle.getString("eventName");
				if(event != null) {
					eventName.setText(event);
				}
			}
		Log.d("djwisema", "Event name is " + eventName.getText());
		hostName = (TextView)findViewById(R.id.event_details_hostNameText);
		eventAddress = (TextView)findViewById(R.id.event_details_eventAddressText);
		eventDate = (TextView)findViewById(R.id.event_details_eventDateText);
		eventTime= (TextView)findViewById(R.id.event_details_eventTimeText);
		attend = (Button)findViewById(R.id.event_details_attendButton);
		seeAttendees = (Button)findViewById(R.id.event_details_viewAttendees);

>>>>>>> 17a5d268be47ef88222a510b3a9debcffd4a1948
		initLayout();
	}

	private void initLayout() {
<<<<<<< HEAD
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
=======
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("EventDetails");
		query.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> objects, ParseException e) {
		        if (e == null) {
		        	for (ParseObject obj : objects) {
		        		Log.d("djwisema", "Event name is " + obj.getString("event_name"));
		        		if (eventName.getText().equals(obj.getString("event_name"))) {
		        			hostName.setText(obj.getString("event_poster"));
		        			eventAddress.setText(obj.getString("event_address"));
		        			eventDate.setText(obj.getString("event_date"));
		        			eventTime.setText(obj.getString("event_hour") + ":" +
		        					obj.getString("event_minute"));
		        		}
		        	}
		        } else {
		            Log.d("score", "Error: " + e.getMessage());
		        }
		    }

		});
		
		attend.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				ParseUser.getCurrentUser().put("events_attending", eventName.getText());
				Toast.makeText(EventDetails.this, "You are now attending " + 
				eventName.getText(), Toast.LENGTH_SHORT).show();
			}
		});
	}
>>>>>>> 17a5d268be47ef88222a510b3a9debcffd4a1948
}
