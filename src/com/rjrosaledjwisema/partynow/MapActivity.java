package com.rjrosaledjwisema.partynow;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class MapActivity extends FragmentActivity {

	private GoogleMap mMap;
	private String address = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Bundle intentbundle;
		String address;
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		setUpMapIfNeeded();
		
		intentbundle = this.getIntent().getExtras();
		if(intentbundle != null) {
			address = intentbundle.getString("addr");
			if(address != null) {
				this.address = address;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_screen, menu);
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		return true;
	}

	private void setUpMapIfNeeded() {
	    // Do a null check to confirm that we have not already instantiated the map.
	    if (mMap == null) {
	        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
	                            .getMap();
	        // Check if we were successful in obtaining the map.
	        if (mMap != null) {
	            // The Map is verified. It is now safe to manipulate the map.

	        }
	    }
	}
	
}
