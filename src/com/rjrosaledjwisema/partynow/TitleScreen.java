package com.rjrosaledjwisema.partynow;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseAnalytics;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class TitleScreen extends Activity {

	private RelativeLayout layout;
	private EditText event_name, event_address, event_time;
	private Button database_send;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title_screen);
		Parse.initialize(this, "q4gB8a9Fr30V0T0Rq7Cak26YpSEJeGnD4ShknpGn", "Su7l9n816K3fI1hILnErpWjOBwxfWSUAC7XRvpnZ");
		event_name = (EditText)findViewById(R.id.editname);
		event_address = (EditText)findViewById(R.id.editaddress);
		event_time = (EditText)findViewById(R.id.edittime);
		database_send = (Button)findViewById(R.id.send_database_button);
		initButtonListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.title_screen, menu);
		return true;
	}
	
	private void initButtonListener() {
		database_send.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				//Implement code to add a new joke here...
//				String s = m_vwJokeEditText.getText().toString();
//				if (s != null && !s.isEmpty())
//					addJoke(new Joke(s, m_strAuthorName));
//				m_vwJokeEditText.setText("");
				
				ParseObject eventDetails = new ParseObject("EventDetails");
				eventDetails.put("event_name", event_name.getText().toString());
				eventDetails.put("address", event_address.getText().toString());
				eventDetails.put("event_time", event_time.getText().toString());
				
				ParseObject testObject = new ParseObject("TestObject");
				testObject.put("foo", "bar");
				testObject.saveInBackground();
				eventDetails.saveInBackground();

			}
		});
	}

}
