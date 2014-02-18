package com.rjrosaledjwisema.partynow;

public class User {
	private String userName;
	private String userPassword;
	
	public User(String user, String password) {
		this.userName = user;
		this.userPassword = password;
	}
	
	public void setUser(String user) {
		this.userName = user;
	}
	
	public String getUser() {
		return this.userName;
	}
	
	public void setPassword(String password) {
		this.userPassword = password;
	}
	
	public String getPassword() {
		return this.userPassword;
	}
}
