package com.rjrosaledjwisema.partynow;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.ActionMode.Callback;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
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
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;

public class MapActivity extends Fragment 
implements OnMyLocationChangeListener {

	private GoogleMap mMap;
	private LatLng myLocation;
	private String address = null, name = null;
	private ListView listLayout;
	private EventListAdapter eventAdapter;
	private ArrayList<Event> listEvents = new ArrayList<Event>();
	private ArrayList<Marker> markers;
	private View mapView;
	private Location currLoc;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*if (mapView != null) {
		    ViewGroup parent = (ViewGroup) mapView.getParent();
		    if (parent != null)
		        parent.removeView(mapView);
		}
		try {
		    mapView = inflater.inflate(R.layout.activity_map, container, false);
		} catch (InflateException e) {
		    /* map is already there, just return view as it is 
			if (mapView != null)
				return mapView;
			else
				mapView = inflater.inflate(R.layout.activity_map, container, false);
		}*/
		mapView = inflater.inflate(R.layout.activity_map, container, false);
		eventAdapter = new EventListAdapter(getActivity(), listEvents);
		
		initLayout();
		return mapView;
	}
	
	public void onDestroyView() {
	    super.onDestroyView();
	    FragmentManager fm = getActivity().getSupportFragmentManager();
	    Fragment fragment = (fm.findFragmentById(R.id.map));
	    FragmentTransaction ft = fm.beginTransaction();
	    ft.remove(fragment);
	    ft.commit();
	}
	
	protected void initLayout() {
		
		
		listLayout = (ListView) mapView.findViewById(R.id.eventListViewGroup);
		listLayout.setAdapter(eventAdapter);
		
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("EventDetails");
		query.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> objects, ParseException e) {
		        if (e == null) {
		        	mMap.clear();
		        	for (ParseObject obj : objects) {
		        		String event_name = obj.getString("event_name");
		        		String event_address = obj.getString("event_address");
		        		String event_date = obj.getString("event_date");
		        		String event_time = "" + obj.getInt("event_hour") + ":" + obj.getInt("event_minute");
		        		Event event = new Event(event_name, event_address, event_time, event_date);
		        		event.setObjectId(obj.getObjectId());
		        		// Make these work with the listview!
		        		listEvents.add(event);
		        		eventAdapter.notifyDataSetChanged();
		        		
		        		Geocoder geocoder = new Geocoder(getActivity()); 
						try {
							List<Address> addr = geocoder.getFromLocationName(event_address, 1);
							Location party = new Location("Party");
							
							if(addr.size() > 0) {
					    	    double latitude = addr.get(0).getLatitude();
					    	    double longitude = addr.get(0).getLongitude();
					    	    
					    	    party.setLatitude(latitude);
					    	    party.setLongitude(longitude);
					    	    
					    	    //	setCurLoc();
					    	    //	Double distance = (double) currLoc.distanceTo(party);
						    	  //  Double miles = (distance) / 1.609344f;
						    	   // if (miles > 50) {
							    	   mMap.addMarker(new MarkerOptions()
							            .position(new LatLng(latitude, longitude))
							            .title(event_name));
						    	    //}
					    	    
					    	}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
				String newName = listEvents.get(arg2).getName();
				Geocoder geocoder = new Geocoder(getActivity()); 
				try {
					List<Address> addr = geocoder.getFromLocationName(newAddr, 1);
					if(addr.size() > 0) {
			    	    double latitude = addr.get(0).getLatitude();
			    	    double longitude = addr.get(0).getLongitude();
			    	    
			       	    CameraUpdate myLoc = CameraUpdateFactory.newCameraPosition(
			    	            new CameraPosition.Builder().target(new LatLng(latitude,
			    	                   longitude)).zoom(7).build());
			    	    mMap.moveCamera(myLoc);
			    	}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		listLayout.setOnItemLongClickListener(new OnItemLongClickListener() {
		    // Called when the user long-clicks on someView

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				
		        Event event = listEvents.get(position);
		        Intent intent = new Intent(getActivity(), EventDetails.class);
		        intent.putExtra("eventName", event.getName());
		        getActivity().startActivity(intent);
		        return true;
		  }
		});
	}
	
	private void setUpMapIfNeeded() {
	    // Do a null check to confirm that we have not already instantiated the map.
		
		
		
	    if (mMap == null) {
	    	mMap = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	
	        // Check if we were successful in obtaining the map.
	        if (mMap != null) {
	            // The Map is verified. It is now safe to manipulate the map.
	        	mMap.getUiSettings().setAllGesturesEnabled(true);
	        	mMap.getUiSettings().setMyLocationButtonEnabled(true);
	        	mMap.setMyLocationEnabled(true);
	        	mMap.setOnMyLocationChangeListener(this);
	        }
	    }
	}
	
	public void setCurLoc() {
		currLoc = ((Main) getActivity()).getCurLoc();
	}
	
	@Override
	public void onMyLocationChange(Location location) {
		// TODO Auto-generated method stub
		currLoc = location;
		
		CameraUpdate myLoc = CameraUpdateFactory.newCameraPosition(
	            new CameraPosition.Builder().target(new LatLng(location.getLatitude(),
	                   location.getLongitude())).zoom(15).build());
	    mMap.moveCamera(myLoc);
	    mMap.setOnMyLocationChangeListener(null);
	}
	
}
