package com.rjrosaledjwisema.partynow;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
 
public class MainDrawer extends Activity {
 
    private String[] drawerListViewItems;
    private ListView drawerListView;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
 
        // get list items from strings.xml
        drawerListViewItems = getResources().getStringArray(R.array.items);
 
        // get ListView defined in activity_main.xml
        drawerListView = (ListView) findViewById(R.id.left_drawer);
 
                // Set the adapter for the list view
        drawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_listitem, drawerListViewItems));
 
    }
 
}
