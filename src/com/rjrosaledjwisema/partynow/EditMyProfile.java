package com.rjrosaledjwisema.partynow;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditMyProfile extends Activity {

	private Button saveChanges;
	private EditText userName, fullName, address;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_edit);
		saveChanges = (Button) findViewById(R.id.profile_edit_savechanges);
		userName = (EditText) findViewById(R.id.profile_edit_username);
		fullName = (EditText) findViewById(R.id.profile_my_fullname);
		address = (EditText) findViewById(R.id.profile_my_address);
		initButtonListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_my_profile, menu);
		return true;
	}
	
	private void initButtonListeners() {
		saveChanges.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				String username = userName.getText().toString();
				String fullname = fullName.getText().toString();
				String addr = address.getText().toString();
				
				if (!username.isEmpty() && !fullName.getText().toString().isEmpty()
						&& !addr.isEmpty()) {
					ParseObject profile = new ParseObject("Profile");
					profile.put("userName", username);
					profile.put("fullName", fullname);
					profile.put("Address", addr);
		
					profile.saveInBackground();
					
					Toast.makeText(EditMyProfile.this, "Uploaded to database!", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(EditMyProfile.this, MyProfile.class);
					EditMyProfile.this.startActivity(intent);
				} else {
					Toast.makeText(EditMyProfile.this, "Fill in all fields!!", Toast.LENGTH_SHORT).show(); 
				}
			}	
				
				
		});
	}	

}
