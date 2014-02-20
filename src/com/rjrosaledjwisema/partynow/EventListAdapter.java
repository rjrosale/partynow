package com.rjrosaledjwisema.partynow;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EventListAdapter extends BaseAdapter {
	
		/** The application Context in which this JokeListAdapter is being used. */
		private Context m_context;

		/** The data set to which this JokeListAdapter is bound. */
		private List<Event> eventList;

		/**
		 * Parameterized constructor that takes in the application Context in which
		 * it is being used and the Collection of Joke objects to which it is bound.
		 * m_nSelectedPosition will be initialized to Adapter.NO_SELECTION.
		 * 
		 * @param context
		 *            The application Context in which this JokeListAdapter is being
		 *            used.
		 * 
		 * @param jokeList
		 *            The Collection of Joke objects to which this JokeListAdapter
		 *            is bound.
		 */
		public EventListAdapter(Context context, List<Event> eventList) {
			//TODO
			m_context = context;
			this.eventList = eventList;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return eventList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return eventList.get(arg0);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			EventView eventView;

			if (convertView == null) {
				eventView = new EventView(m_context, eventList.get(position));
			} else {
				eventView = (EventView) convertView;
				TextView name = (TextView) eventView.findViewById(R.id.eventNameText);
				TextView address = (TextView) eventView.findViewById(R.id.eventAddressText);
				TextView date = (TextView) eventView.findViewById(R.id.eventDateText);
				TextView time = (TextView) eventView.findViewById(R.id.eventTimeText);
			}

			eventView.setEvent(eventList.get(position));
			
			return eventView;
		}
}
