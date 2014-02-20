package com.rjrosaledjwisema.partynow;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EventView extends LinearLayout {
	private TextView name, address, date, time;
	private Event m_Event;
	
	public EventView(Context context, Event event) {
		super(context);
		// TODO
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.event_view, this, true);
		
		name = (TextView)findViewById(R.id.eventNameText);
		address = (TextView)findViewById(R.id.eventAddressText);
		date = (TextView)findViewById(R.id.eventDateText);
		time = (TextView)findViewById(R.id.eventTimeText);
		
		setEvent(event);
		requestLayout();
	}

	/**
	 * Mutator method for changing the Joke object this View displays. This View
	 * will be updated to display the correct contents of the new Joke.
	 * 
	 * @param joke
	 *            The Joke object which this View will display.
	 */
	public void setEvent(Event event) {
		// TODO
		this.m_Event = event;
		this.name.setText(event.getName());
		this.date.setText(event.getDate());
		this.address.setText(event.getAddress());
		this.time.setText(event.getTime());
		requestLayout();
	}
}
