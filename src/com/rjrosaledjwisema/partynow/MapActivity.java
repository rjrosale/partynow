package com.rjrosaledjwisema.partynow;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MapActivity extends Activity {

	private GoogleMap mMap;
	private String address = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Bundle intentbundle;
		String address;
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
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
		getMenuInflater().inflate(R.menu.map, menu);
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		return true;
	}

}
