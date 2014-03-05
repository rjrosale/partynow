package com.rjrosaledjwisema.partynow;

import java.io.IOException;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
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
	private TextView eventName, hostName, eventAddress, eventDate, eventTime;
	private Button attend, seeAttendees;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_details);

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

		initLayout();
	}

	private void initLayout() {
		
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
}
