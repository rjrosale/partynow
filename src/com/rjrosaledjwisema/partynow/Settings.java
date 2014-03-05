package com.rjrosaledjwisema.partynow;

import java.util.Arrays;
import java.util.LinkedList;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Settings extends Activity {

	private TimePicker time;
	private EditText proximity;
	private Button submit;
	private ParseUser user;
	private String timeString;
	private int proximityInt;
	
	private String[] drawerListViewItems;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle actionBarDrawerToggle; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_layout);
		
		// get list items from strings.xml
        drawerListViewItems = getResources().getStringArray(R.array.items);
        LinkedList<String> items = new LinkedList<String>(Arrays.asList(drawerListViewItems));
        items.remove(3);
        
        // get ListView defined in activity_main.xml
        drawerListView = (ListView) findViewById(R.id.left_drawer);
 
        // Set the adapter for the list view
        drawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_listitem, items.toArray(new String[items.size()])));
 
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
		
		user = ParseUser.getCurrentUser();
		Toast.makeText(this, user.getUsername(), Toast.LENGTH_SHORT).show();
		time = (TimePicker) findViewById(R.id.party_time);
		proximity = (EditText) findViewById(R.id.proximity);
		submit = (Button) findViewById(R.id.submit_button);
		initButtonListeners();
	}

	private void initButtonListeners() {
		submit.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				timeString = time.getCurrentHour() + ":" + time.getCurrentMinute();
				proximityInt = Integer.parseInt(proximity.getText().toString());
				user.put("time", timeString);
				user.put("proximity", proximityInt);
		
				user.saveInBackground(new SaveCallback() {

					@Override
					public void done(ParseException e) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "Successfully saved", Toast.LENGTH_SHORT).show();

						Intent i = new Intent(Settings.this, Main.class);
						startActivity(i);
						finish();
					}
				});
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
					intent = new Intent(Settings.this, Main.class);
					Settings.this.startActivity(intent);
	            }  else if ("Profile".equals(((TextView) arg1).getText())) {
	            	intent = new Intent(Settings.this, MyProfile.class);
					Settings.this.startActivity(intent);
	            } else if ("Friends".equals(((TextView) arg1).getText())) {
	            	intent = new Intent(Settings.this, Friends.class);
					Settings.this.startActivity(intent);
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
