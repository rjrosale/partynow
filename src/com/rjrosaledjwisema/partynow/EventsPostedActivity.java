package com.rjrosaledjwisema.partynow;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class EventsPostedActivity extends SherlockActivity {
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
						if (obj.getString("event_poster").equals(ParseUser.getCurrentUser().getUsername())) {
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
					//		        	initLayoutListener();
				} else {
					Log.d("lFDSJFOSJFPDSFPDS", "ERROR ERROR ERROR");
				}
			}

		});
	}
}
//	private void initLayoutListener() {
//		listLayout.setOnItemClickListener(new ListView.OnItemClickListener() {
//		    // Called when the user long-clicks on someView
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				// TODO Auto-generated method stub
//				String newAddr = listEvents.get(arg2).getAddress();
//				String newName = listEvents.get(arg2).getName();
//				try {
//					List<Address> addr = geocoder.getFromLocationName(newAddr, 1);
//					if(addr.size() > 0) {
//			    	    double latitude = addr.get(0).getLatitude();
//			    	    double longitude = addr.get(0).getLongitude();
//			    	    
//			    	    if (marker != null)
//			    	    	marker.remove();
//			    	    marker = mMap.addMarker(new MarkerOptions()
//			            .position(new LatLng(latitude, longitude))
//			            .title(newName));
//			       	    CameraUpdate myLoc = CameraUpdateFactory.newCameraPosition(
//			    	            new CameraPosition.Builder().target(new LatLng(latitude,
//			    	                   longitude)).zoom(7).build());
//			    	    mMap.moveCamera(myLoc);
//			    	}
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//		});
//	}
//}
