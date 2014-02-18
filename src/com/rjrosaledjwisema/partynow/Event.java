package com.rjrosaledjwisema.partynow;

public class Event {
	private String eventName;
	private String eventAddress;
	private String eventTime;
	
	public Event(String eventName, String eventAddress, String eventTime) {
		this.eventName = eventName;
		this.eventAddress = eventAddress;
		this.eventTime = eventTime;
	}
	
	public String getName() {
		return this.eventName;
	}
	
	public void setName(String name) {
		this.eventName = name;
	}
	
	public String getAddress() {
		return this.eventAddress;
	}
	
	public void setAddress(String address) {
		this.eventAddress = address;
	}
	
	public String getTime() {
		return this.eventTime;
	}
	
	public void setTime(String time) {
		this.eventTime = time;
	}
}
