package com.rjrosaledjwisema.partynow;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class EventDetails extends Activity {
	private TextView eventName, hostName, eventAddress, eventDate, eventTime;
	private Button attend, seeAttendees;

	private String[] drawerListViewItems;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle actionBarDrawerToggle; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_details);

		// get list items from strings.xml
        drawerListViewItems = getResources().getStringArray(R.array.items);
       
        // get ListView defined in activity_main.xml
        drawerListView = (ListView) findViewById(R.id.left_drawer);
     
        
        // Set the adapter for the list view
        drawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_listitem, drawerListViewItems));
 
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
		
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
		
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
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent intent;
			// TODO Auto-generated method stub
			if ("Home".equals(((TextView) arg1).getText())) {
				intent = new Intent(EventDetails.this, Main.class);
				EventDetails.this.startActivity(intent);
            }  else if ("Profile".equals(((TextView) arg1).getText())) {
            	intent = new Intent(EventDetails.this, MyProfile.class);
            	EventDetails.this.startActivity(intent);
            } else if ("Friends".equals(((TextView) arg1).getText())) {
            	intent = new Intent(EventDetails.this, Friends.class);
				EventDetails.this.startActivity(intent);
            } else if ("Settings".equals(((TextView) arg1).getText())) {
            	intent = new Intent(EventDetails.this, Settings.class);
				EventDetails.this.startActivity(intent);
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
}
