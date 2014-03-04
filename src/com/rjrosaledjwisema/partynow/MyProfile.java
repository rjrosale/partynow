package com.rjrosaledjwisema.partynow;

import java.util.List;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyProfile extends Activity {
	private Button editProfile;
	private TextView userName, fullName, address;
	private String userID = null, username = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_my);
		
		Bundle intentbundle = this.getIntent().getExtras();
		if(intentbundle != null) {
			String id = intentbundle.getString("userID");
			if(id != null) {
				userID = id;
			}
		}
		
		editProfile = (Button) findViewById(R.id.profile_my_editprofile);
		userName = (TextView) findViewById(R.id.profile_my_username);
		fullName = (TextView) findViewById(R.id.profile_my_fullname);
		address = (TextView) findViewById(R.id.profile_my_address);
		
		initButtonListeners();
		initLayout();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void initLayout() {
		if (userID != null) {
			ParseUser currentUser = ParseUser.getCurrentUser();
			if (currentUser != null) {
			  username = currentUser.getString("userName");
			} else {
			  
			}
			if (username != null) {
				ParseQuery<ParseObject> query = ParseQuery.getQuery("Profile");
				query.findInBackground(new FindCallback<ParseObject>() {
				    public void done(List<ParseObject> objects, ParseException e) {
				        if (e == null) {
				        	for (ParseObject obj : objects) {
				        		if (username.equals(obj.getString("userName"))){
					        		userName.setText(obj.getString("userName"));
					        		fullName.setText(obj.getString("fullName"));
					        		address.setText(obj.getString("Address"));
				        		}
				        	}
				        } else {
				        	Toast.makeText(MyProfile.this, "User profile not set up", Toast.LENGTH_SHORT).show();
				        }
				    }
				});
			}
		}
	}
	
	private void initButtonListeners() {
		editProfile.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MyProfile.this, EditMyProfile.class);
				MyProfile.this.startActivity(intent);
			}
		});
	}
}
