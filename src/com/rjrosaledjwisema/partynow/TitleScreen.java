package com.rjrosaledjwisema.partynow;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseAnalytics;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import android.widget.Toast;

public class TitleScreen extends Activity {

	private EditText event_name, event_address;
	private Button database_send;
	private RadioButton public_rb, private_rb;
	DatePicker datepicker;
	TimePicker timepicker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title_screen);
		Parse.initialize(this, "q4gB8a9Fr30V0T0Rq7Cak26YpSEJeGnD4ShknpGn", "Su7l9n816K3fI1hILnErpWjOBwxfWSUAC7XRvpnZ");
		event_name = (EditText)findViewById(R.id.editname);
		event_address = (EditText)findViewById(R.id.editaddress);
		database_send = (Button)findViewById(R.id.send_database_button);
		public_rb = (RadioButton)findViewById(R.id.publicrb);
		private_rb = (RadioButton)findViewById(R.id.privaterb);
		datepicker = (DatePicker)findViewById(R.id.datePicker1);
		timepicker = (TimePicker)findViewById(R.id.timePicker1);
		
		event_name.setText("");
		event_address.setText("");
		
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
				Intent local;
				String datestr = "" + datepicker.getMonth() + "/" + datepicker.getDayOfMonth() + "/" + datepicker.getYear();
				
				if (event_name.getText().toString() != "" && event_address.getText().toString() != "") {
					ParseObject eventDetails = new ParseObject("EventDetails");
					eventDetails.put("event_name", event_name.getText().toString());
					eventDetails.put("event_address", event_address.getText().toString());
					eventDetails.put("event_hour", ((Integer)(timepicker.getCurrentHour())).intValue());
					eventDetails.put("event_minute", ((Integer)(timepicker.getCurrentMinute())).intValue());
					eventDetails.put("event_date", datestr);

					if (public_rb.isChecked())
						eventDetails.put("pub_priv", true);
					else
						eventDetails.put("pub_priv", false);
					
					eventDetails.saveInBackground();
					Toast.makeText(getBaseContext(), "Uploaded to database!", Toast.LENGTH_SHORT).show(); 
					
					local = new Intent(TitleScreen.this, MapActivity.class);
					local.putExtra("address", event_address.getText().toString());
					TitleScreen.this.startActivity(local);
					
				} else {
					Toast.makeText(getBaseContext(), "Fill in blank text fields!", Toast.LENGTH_SHORT).show(); 
				}
			}
		});
	}

}
