package com.rjrosaledjwisema.partynow;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class EventsAttendingActivity extends SherlockActivity {
	private ListView listLayout;
	private EventListAdapter eventAdapter;
	private ArrayList<Event> listEvents = new ArrayList<Event>();
	private View listView;


		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.events_posted);
			eventAdapter = new EventListAdapter(this, listEvents);
			initLayout();
		}


	protected void initLayout() {

		listLayout = (ListView)findViewById(R.id.events_posted_listview);
		listLayout.setAdapter(eventAdapter);
		Log.d("lFDSJFOSJFPDSFPDS", "I got here one");
		ParseQuery<ParseObject> query = ParseQuery.getQuery("EventDetails");
		Log.d("lFDSJFOSJFPDSFPDS", "QUERY NOW");
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> objects, ParseException e) {
				if (e == null) {
					Log.d("lFDSJFOSJFPDSFPDS", "E == NULL IT'S FINE");
					for (ParseObject obj : objects) {
						Log.d("lFDSJFOSJFPDSFPDS", obj.getString("event_poster") + " vs " + ParseUser.getCurrentUser().getUsername());
						if (obj.getString("event_name").equals(ParseUser.getCurrentUser().get("events_attending"))) {
							String event_name = obj.getString("event_name");
							String event_address = obj.getString("event_address");
							String event_date = obj.getString("event_date");
							String event_time = "" + obj.getInt("event_hour") + ":" + obj.getInt("event_minute");
							Event event = new Event(event_name, event_address, event_time, event_date);
							event.setObjectId(obj.getObjectId());
							listEvents.add(event);
							Log.d("lFDSJFOSJFPDSFPDS", "ADDED TO LIST VIEW");
							eventAdapter.notifyDataSetChanged();
						}
					}
				} else {
					Log.d("lFDSJFOSJFPDSFPDS", "ERROR ERROR ERROR");
				}
			}

		});
	}
}