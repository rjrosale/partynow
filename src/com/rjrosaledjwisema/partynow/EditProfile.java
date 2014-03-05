package com.rjrosaledjwisema.partynow;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class EditProfile extends SherlockActivity {
	private EditText fullName, phoneNumber, vicinity;
	private Button saveChanges;
	private String name, phone, city;
	private ParseUser user;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_edit);
		
		user = ParseUser.getCurrentUser();
		Toast.makeText(this, user.getUsername(), Toast.LENGTH_SHORT).show();
		fullName = (EditText)findViewById(R.id.profile_edit_fullname);
		phoneNumber = (EditText)findViewById(R.id.profile_edit_phone);
		vicinity = (EditText)findViewById(R.id.profile_edit_address);
		saveChanges = (Button)findViewById(R.id.profile_edit_savechanges);
		initButtonListeners();
	}
	
	private void initButtonListeners() {
		saveChanges.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				name = fullName.getText().toString();
				phone = phoneNumber.getText().toString();
				city = vicinity.getText().toString();
				user.put("fullName", name);
				user.put("phone_number", phone);
				user.put("vicinity", city);
				user.add("friends_list", "dan");
				user.add("events_attending", "objectIDHERE");
//				user.put("events_attending", new ArrayList<Event>());
//				Log.d("REGEREGREGEREG", "added arraylistevents to parse");
//				user.put("friend_list", new ArrayList<String>());
//				Log.d("REGEREGREGEREG", "added arraylistfriends to parse");
				user.saveInBackground(new SaveCallback() {

					@Override
					public void done(ParseException e) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "Successfully saved", Toast.LENGTH_SHORT).show();

						Intent i = new Intent(EditProfile.this, MyProfile.class);
						startActivity(i);
						finish();
					}
				});
			}
		});
	}
}
