package com.rjrosaledjwisema.partynow;

public class Event {
	private String eventName;
	private String eventAddress;
	private String eventTime;
	private String eventDate;
	private String objectId;
	private String hostName;
	
	public Event(String eventName, String eventAddress, String eventTime, String eventDate, String hostName) {
		this.eventName = eventName;
		this.eventAddress = eventAddress;
		this.eventTime = eventTime;
		this.eventDate = eventDate;
		this.hostName = hostName;
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
	
	public String getDate() {
		return this.eventDate;
	}
	
	public void setDate(String date) {
		this.eventDate = date;
	}
	
	public String getObjectId() {
		return this.objectId;
	}
	
	public void setObjectId(String id) {
		this.objectId = id;
	}
	
	public String getHostName() {
		return this.hostName;
	}
	
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
}
