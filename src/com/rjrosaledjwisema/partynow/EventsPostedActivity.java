package com.rjrosaledjwisema.partynow;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class EventsPostedActivity extends SherlockFragmentActivity {
	private ListView listLayout;
	private EventListAdapter eventAdapter;
	private ArrayList<Event> listEvents = new ArrayList<Event>();
	private View listView;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listView = inflater.inflate(R.layout.events_posted, container, false);
		eventAdapter = new EventListAdapter(this, listEvents);
		initLayout();
		return listView;
	}
	
//	public void onDestroyView() {
//	    super.onDestroyView();
//	    FragmentManager fm = getActivity().getSupportFragmentManager();
//	    Fragment fragment = (fm.findFragmentById(R.id.map));
//	    FragmentTransaction ft = fm.beginTransaction();
//	    ft.remove(fragment);
//	    ft.commit();
//	}
	
	protected void initLayout() {
		
		listLayout = (ListView) listView.findViewById(R.id.events_posted_listview);
		listLayout.setAdapter(eventAdapter);

		ParseQuery<ParseObject> query = ParseQuery.getQuery("EventDetails");
		query.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> objects, ParseException e) {
		        if (e == null) {
		        	for (ParseObject obj : objects) {
		        		String event_name = obj.getString("event_name");
		        		String event_address = obj.getString("event_address");
		        		String event_date = obj.getString("event_date");
		        		String event_time = "" + obj.getInt("event_hour") + ":" + obj.getInt("event_minute");
		        		Event event = new Event(event_name, event_address, event_time, event_date);
		        		event.setObjectId(obj.getObjectId());
		        		listEvents.add(event);
		        		eventAdapter.notifyDataSetChanged();
		        	}
//		        	initLayoutListener();
		        } else {
		            Log.d("score", "Error: " + e.getMessage());
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
