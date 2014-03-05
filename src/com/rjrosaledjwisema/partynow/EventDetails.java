package com.rjrosaledjwisema.partynow;

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

public class EventDetails extends SherlockActivity {
	private TextView username, fullname, vicinity;
	private Button addComment, addFriend;
	private ImageView picture;
	private ListView listView;
	private ParseUser user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_details);

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
		user = ParseUser.getCurrentUser();
		username.setText(user.getString("username"));
		fullname.setText(user.getString("fullName"));
		vicinity.setText(user.getString("vicinity"));


		addFriend.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				user.add("friends_list", username);
				Toast.makeText(EventDetails.this, username + " has been added as a friend.", Toast.LENGTH_SHORT).show();
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
