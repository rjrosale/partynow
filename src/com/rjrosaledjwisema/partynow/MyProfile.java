package com.rjrosaledjwisema.partynow;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

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

}
