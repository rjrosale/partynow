package com.rjrosaledjwisema.partynow;

import java.util.Arrays;

import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.rjrosaledjwisema.partynow.R;

public class Main extends FragmentActivity implements LocationListener, GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener {
    
	private String[] drawerListViewItems;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle actionBarDrawerToggle; 
    private FragmentAdapter mAdapter;
    private ViewPager mPager;
    private PageIndicator mIndicator;
    private int Number = 0;

    private String userID = null;
    private LocationClient mLocationClient;
    private Location currLocation;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        
        Bundle intentbundle = this.getIntent().getExtras();
		if(intentbundle != null) {
			String id = intentbundle.getString("userID");
			if(id != null) {
				userID = id;
			}
		}
		mLocationClient = new LocationClient(this, this, this);
        // get list items from strings.xml
        drawerListViewItems = getResources().getStringArray(R.array.items);
        // get ListView defined in activity_main.xml
        drawerListView = (ListView) findViewById(R.id.left_drawer);
 
        // Set the adapter for the list view
        drawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_listitem, Arrays.copyOfRange(drawerListViewItems, 
                		1, drawerListViewItems.length)));
 
        // App Icon 
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
 
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                R.drawable.ic_launcher,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
                );
 
        // Set actionBarDrawerToggle as the DrawerListener
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
 
        getActionBar().setDisplayHomeAsUpEnabled(true); 
        /*LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);                                    
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        Log.d("djwisema", "HERE");
        currLocation = mLocationClient.getLastLocation();*/
        
        mAdapter = new FragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (TitlePageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
    }
    
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent intent;
			// TODO Auto-generated method stub
			/*if ("Home".equals(((TextView) arg1).getText())) {
				intent = getIntent();
				intent.putExtra("userID", userID);
				overridePendingTransition(0, 0);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				finish();
				overridePendingTransition(0, 0);
				startActivity(intent);
            }*/ if ("Profile".equals(((TextView) arg1).getText())) {
            	intent = new Intent(Main.this, MyProfile.class);
            	intent.putExtra("userID", userID);
				Main.this.startActivity(intent);
            } else if ("Friends".equals(((TextView) arg1).getText())) {
            	intent = new Intent(Main.this, Friends.class);
            	intent.putExtra("userID", userID);
				Main.this.startActivity(intent);
            } else if ("Settings".equals(((TextView) arg1).getText())) {
            	intent = new Intent(Main.this, Settings.class);
            	intent.putExtra("userID", userID);
				Main.this.startActivity(intent);
            }
            drawerLayout.closeDrawer(drawerListView);
		}
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
 
         // call ActionBarDrawerToggle.onOptionsItemSelected(), if it returns true
        // then it has handled the app icon touch event
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
 
    public void onLocationChanged(Location location) 
    {
            if(location != null)
            {
                    currLocation = location;
                    
            }
    }
    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
        
    }
    
    public Location getCurLoc() {
    	return this.currLocation;
    }
    
 // Global constants
    /*
     * Define a request code to send to Google Play services
     * This code is returned in Activity.onActivityResult
     */
    private final static int
            CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    
    // Define a DialogFragment that displays the error dialog
    public static class ErrorDialogFragment extends DialogFragment {
        // Global field to contain the error dialog
        private Dialog mDialog;
        // Default constructor. Sets the dialog field to null
        public ErrorDialogFragment() {
            super();
            mDialog = null;
        }
        // Set the dialog to display
        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }
        // Return a Dialog to the DialogFragment.
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return mDialog;
        }
    }
    
    /*
     * Handle results returned to the FragmentActivity
     * by Google Play services
     */
    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        // Decide what to do based on the original request code
        switch (requestCode) {
            
            case CONNECTION_FAILURE_RESOLUTION_REQUEST :
            /*
             * If the result code is Activity.RESULT_OK, try
             * to connect again
             */
                switch (resultCode) {
                    case Activity.RESULT_OK :
                    /*
                     * Try the request again
                     */
                   
                    break;
                }
           
        }
     }
    
    private boolean servicesConnected() {
        // Check that Google Play services is available
        int resultCode =
                GooglePlayServicesUtil.
                        isGooglePlayServicesAvailable(this);
        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            // In debug mode, log the status
            Log.d("Location Updates",
                    "Google Play services is available.");
            // Continue
            return true;
        // Google Play services was not available for some reason
        } else {
            // Get the error code
            return false;
        }
    }
    
    @Override
    public void onConnected(Bundle dataBundle) {
        // Display the connection status
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();

    }
   
    /*
     * Called by Location Services if the connection to the
     * location client drops because of an error.
     */
    @Override
    public void onDisconnected() {
        // Display the connection status
        Toast.makeText(this, "Disconnected. Please re-connect.",
                Toast.LENGTH_SHORT).show();
    }
    
    /*
     * Called by Location Services if the attempt to
     * Location Services fails.
     */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(
                        this,
                        CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            
        }
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        // Connect the client.
        mLocationClient.connect();
    }
   
    /*
     * Called when the Activity is no longer visible.
     */
    @Override
    protected void onStop() {
        // Disconnecting the client invalidates it.
        mLocationClient.disconnect();
        super.onStop();
    }
}