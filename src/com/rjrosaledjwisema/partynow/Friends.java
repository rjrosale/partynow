package com.rjrosaledjwisema.partynow;

<<<<<<< HEAD
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;

import android.os.Bundle;
=======
import java.util.Arrays;
import java.util.LinkedList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
>>>>>>> 17a5d268be47ef88222a510b3a9debcffd4a1948

public class Friends extends SherlockActivity {

	private String[] drawerListViewItems;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle actionBarDrawerToggle; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_friends);
		
		drawerListViewItems = getResources().getStringArray(R.array.items);
        LinkedList<String> items = new LinkedList<String>(Arrays.asList(drawerListViewItems));
        items.remove(2);
        // get ListView defined in activity_Friends.xml
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
	}

<<<<<<< HEAD
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
=======
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent intent;
			// TODO Auto-generated method stub
			if ("Home".equals(((TextView) arg1).getText())) {
				intent = new Intent(Friends.this, Main.class);
				Friends.this.startActivity(intent);
            }  else if ("Profile".equals(((TextView) arg1).getText())) {
            	intent = new Intent(Friends.this, MyProfile.class);
				Friends.this.startActivity(intent);
            } else if ("Settings".equals(((TextView) arg1).getText())) {
            	intent = new Intent(Friends.this, Settings.class);
				Friends.this.startActivity(intent);
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
>>>>>>> 17a5d268be47ef88222a510b3a9debcffd4a1948

}
