package com.rjrosaledjwisema.partynow;


import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.google.android.gms.location.LocationClient;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
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
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;

public class MyProfile extends Activity {
	private TextView username, fullname, vicinity;
	private Button editProfile;
	private ImageView picture;
	private ListView listView;
	private ParseUser user;
	
	private String[] drawerListViewItems;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle actionBarDrawerToggle; 

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_my);

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
        
		username = (TextView)findViewById(R.id.profile_my_username);
		fullname = (TextView)findViewById(R.id.profile_my_fullname);
		vicinity = (TextView)findViewById(R.id.profile_my_address);
		picture = (ImageView)findViewById(R.id.profile_my_picture);
		editProfile = (Button)findViewById(R.id.profile_my_editprofile);
		listView = (ListView)findViewById(R.id.profile_my_commentlistview);
//		Bundle intentbundle = this.getIntent().getExtras();
//		if(intentbundle != null) {
//			String id = intentbundle.getString("userID");
//			if(id != null) {
//				userID = id;
//			}
//		}
//		
//		editProfile = (Button) findViewById(R.id.profile_my_editprofile);
//		userName = (TextView) findViewById(R.id.profile_my_username);
//		fullName = (TextView) findViewById(R.id.profile_my_fullname);
//		address = (TextView) findViewById(R.id.profile_my_address);
//		
//		initButtonListeners();
		initLayout();
	}

	 private class DrawerItemClickListener implements ListView.OnItemClickListener {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent;
				// TODO Auto-generated method stub
				if ("Home".equals(((TextView) arg1).getText())) {
					intent = new Intent(MyProfile.this, Main.class);
					MyProfile.this.startActivity(intent);
	            }  else if ("Friends".equals(((TextView) arg1).getText())) {
	            	intent = new Intent(MyProfile.this, Friends.class);
					MyProfile.this.startActivity(intent);
	            } else if ("Settings".equals(((TextView) arg1).getText())) {
	            	intent = new Intent(MyProfile.this, Settings.class);
					MyProfile.this.startActivity(intent);
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
	
	private void initLayout() {
		user = ParseUser.getCurrentUser();
		username.setText(user.getString("username"));
		fullname.setText(user.getString("fullName"));
		vicinity.setText(user.getString("vicinity"));


		editProfile.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent i = new Intent(MyProfile.this, EditProfile.class); 
				MyProfile.this.startActivity(i);
				finish();
			}
		});
	}

//	private void initLayout() {
//		if (userID != null) {
//			ParseUser currentUser = ParseUser.getCurrentUser();
//			if (currentUser != null) {
//			  username = currentUser.getString("userName");
//			} else {
//			  
//			}
//			if (username != null) {
//				ParseQuery<ParseObject> query = ParseQuery.getQuery("Profile");
//				query.findInBackground(new FindCallback<ParseObject>() {
//				    public void done(List<ParseObject> objects, ParseException e) {
//				        if (e == null) {
//				        	for (ParseObject obj : objects) {
//				        		if (username.equals(obj.getString("userName"))){
//					        		userName.setText(obj.getString("userName"));
//					        		fullName.setText(obj.getString("fullName"));
//					        		address.setText(obj.getString("Address"));
//				        		}
//				        	}
//				        } else {
//				        	Toast.makeText(MyProfile.this, "User profile not set up", Toast.LENGTH_SHORT).show();
//				        }
//				    }
//				});
//			}
//		}
//	}
//	
//	private void initButtonListeners() {
//		editProfile.setOnClickListener(new OnClickListener(){
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(MyProfile.this, EditMyProfile.class);
//				MyProfile.this.startActivity(intent);
//			}
//		});
//	}
}
