package com.alextim.diskarchive;

public class LoggingBean {
	public LoggingBean(String userName) {
		this.userName = userName;
	}
	
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String toString() {
		return String.format("User name: %s", new Object[]{this.userName});
	}
}
