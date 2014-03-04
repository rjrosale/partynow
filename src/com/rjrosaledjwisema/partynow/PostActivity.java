package com.rjrosaledjwisema.partynow;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import android.widget.Toast;

public class PostActivity extends Fragment {

	private EditText event_name, event_address;
	private Button database_send;
	private RadioButton public_rb, private_rb;
	DatePicker datepicker;
	TimePicker timepicker;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View inf = inflater.inflate(R.layout.post_event, container, false);
		
		event_name = (EditText) inf.findViewById(R.id.editname);
		event_address = (EditText) inf.findViewById(R.id.editaddress);
		database_send = (Button) inf.findViewById(R.id.send_database_button);
		public_rb = (RadioButton) inf.findViewById(R.id.publicrb);
		private_rb = (RadioButton) inf.findViewById(R.id.privaterb);
		datepicker = (DatePicker) inf.findViewById(R.id.datePicker1);
		timepicker = (TimePicker) inf.findViewById(R.id.timePicker1);
		initButtonListener();
		
		return inf;
	}

	private void initButtonListener() {
		database_send.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent local;
				String datestr = "" + datepicker.getMonth() + "/" + datepicker.getDayOfMonth() + "/" + datepicker.getYear();
				String addr, name;
				
				if (!event_name.getText().toString().isEmpty() && !event_address.getText().toString().isEmpty()
						&& (public_rb.isChecked() || private_rb.isChecked())) {
					ParseObject eventDetails = new ParseObject("EventDetails");
					eventDetails.put("event_name", event_name.getText().toString());
					eventDetails.put("event_address", event_address.getText().toString());
					eventDetails.put("event_hour", ((Integer)(timepicker.getCurrentHour())).intValue());
					eventDetails.put("event_minute", ((Integer)(timepicker.getCurrentMinute())).intValue());
					eventDetails.put("event_date", datestr);
					eventDetails.put("event_poster", ParseUser.getCurrentUser().getUsername());
					if (public_rb.isChecked())
						eventDetails.put("pub_priv", true);
					else
						eventDetails.put("pub_priv", false);
					eventDetails.saveInBackground();
					
					Toast.makeText(getActivity(), "Uploaded to database!", Toast.LENGTH_SHORT).show(); 
				} else {
					Toast.makeText(getActivity(), "Fill in all fields!!", Toast.LENGTH_SHORT).show(); 
				}
//				addr = event_address.getText().toString();
//				name = event_name.getText().toString();
//				if(!addr.isEmpty()) {
//					local = new Intent(TitleScreen.this, MapActivity.class);
//					local.putExtra("address", addr);
//					local.putExtra("name", name);
//					TitleScreen.this.startActivity(local);
//				}
			}
		});
	}

}
