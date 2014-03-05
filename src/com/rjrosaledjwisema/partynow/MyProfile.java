package com.rjrosaledjwisema.partynow;


import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import java.util.List;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class MyProfile extends SherlockActivity {
	private TextView username, fullname, vicinity;
	private Button editProfile;
	private ImageView picture;
	private ListView listView;
	private ParseUser user;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_my);

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
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main, menu);

		return true;
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
