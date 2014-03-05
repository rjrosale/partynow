package com.rjrosaledjwisema.partynow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.parse.ParseUser;

public class OtherProfile extends SherlockActivity {
	private TextView username, fullname, vicinity;
	private Button addComment, addFriend;
	private ImageView picture;
	private ListView listView;
	private ParseUser user;
	private Bundle bundle;
	private String otherUser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_friend);
		
		bundle = getIntent().getExtras();
		username = (TextView)findViewById(R.id.profile_friends_username);
		fullname = (TextView)findViewById(R.id.profile_friends_fullname);
		vicinity = (TextView)findViewById(R.id.profile_friends_address);
		picture = (ImageView)findViewById(R.id.profile_friends_picture);
		addComment = (Button)findViewById(R.id.profile_friends_addreview);
		addFriend = (Button)findViewById(R.id.profile_friends_addfriend);
		listView = (ListView)findViewById(R.id.profile_friends_commentlistview);

		initLayout();
	}

	private void initLayout() {
		// Need to get a query. Currently only a copy of MyProfile
		user = ParseUser.getCurrentUser();
		username.setText(bundle.getString("username"));
		fullname.setText(user.getString("fullName"));
		vicinity.setText(user.getString("vicinity"));
		otherUser = bundle.getString("username");
		// Check otherUser's string name if your list contains it. Or we can use objectID here (pass in from intent)
		addFriend.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				user.add("friends_list", otherUser);
				user.saveInBackground();
				Toast.makeText(OtherProfile.this, otherUser + " has been added as a friend.", Toast.LENGTH_SHORT).show();
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
