package com.rjrosaledjwisema.partynow;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;

public class MapActivity extends FragmentActivity 
implements OnMyLocationChangeListener {

	private GoogleMap mMap;
	private LatLng myLocation;
	private String address = null;
	private ListView listLayout;
	private EventListAdapter eventAdapter;
	private ArrayList<Event> listEvents = new ArrayList<Event>();
	private Marker marker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		eventAdapter = new EventListAdapter(this, listEvents);
		initLayout();
	}
	
	protected void initLayout() {
		setContentView(R.layout.activity_map);
		
		listLayout = (ListView) findViewById(R.id.eventListViewGroup);
		listLayout.setAdapter(eventAdapter);
		
		Bundle intentbundle = this.getIntent().getExtras();
		if(intentbundle != null) {
			String addr = intentbundle.getString("address");
			if(addr != null) {
				this.address = addr;
			}
		}
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
		        		// Make these work with the listview!
		        		listEvents.add(event);
		        		eventAdapter.notifyDataSetChanged();
		        	}
		        	initLayoutListener();
		        } else {
		            Log.d("score", "Error: " + e.getMessage());
		        }
		    }

		});
		setUpMapIfNeeded();
	}
	
	private void initLayoutListener() {
		listLayout.setOnItemClickListener(new ListView.OnItemClickListener() {
		    // Called when the user long-clicks on someView

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String newAddr = listEvents.get(arg2).getAddress();
				Geocoder geocoder = new Geocoder(MapActivity.this); 
				try {
					List<Address> addr = geocoder.getFromLocationName(newAddr, 1);
					if(addr.size() > 0) {
			    	    double latitude = addr.get(0).getLatitude();
			    	    double longitude = addr.get(0).getLongitude();
			    	    
			    	    marker.remove();
			    	    marker = mMap.addMarker(new MarkerOptions()
			            .position(new LatLng(latitude, longitude))
			            .title("Your Event!"));
			    	}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_screen, menu);
		return true;
	}

	private void setUpMapIfNeeded() {
	    // Do a null check to confirm that we have not already instantiated the map.
		
	    if (mMap == null) {
	    	mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	
	        // Check if we were successful in obtaining the map.
	        if (mMap != null) {
	            // The Map is verified. It is now safe to manipulate the map.
	        	mMap.getUiSettings().setAllGesturesEnabled(true);
	        	mMap.getUiSettings().setMyLocationButtonEnabled(true);
	        	mMap.setMyLocationEnabled(true);
	        	mMap.setOnMyLocationChangeListener(this);
	            pinEventAddress();
	        }
	    }
	}
	
	private void pinEventAddress() {
		Geocoder geocoder = new Geocoder(this); 
    	List<Address> addresses = null;
    	
    	try {
			addresses = geocoder.getFromLocationName(this.address, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(addresses.size() > 0) {
    	    double latitude = addresses.get(0).getLatitude();
    	    double longitude = addresses.get(0).getLongitude();
    	    marker = mMap.addMarker(new MarkerOptions()
            .position(new LatLng(latitude, longitude))
            .title("Your Event!"));
    	}
	}
	
	@Override
	public void onMyLocationChange(Location location) {
		// TODO Auto-generated method stub
	
		CameraUpdate myLoc = CameraUpdateFactory.newCameraPosition(
	            new CameraPosition.Builder().target(new LatLng(location.getLatitude(),
	                   location.getLongitude())).zoom(15).build());
	    mMap.moveCamera(myLoc);
	    mMap.setOnMyLocationChangeListener(null);
	}
	
}
