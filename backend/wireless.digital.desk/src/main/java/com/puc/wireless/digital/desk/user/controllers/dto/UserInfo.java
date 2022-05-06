package com.puc.wireless.digital.desk.user.controllers.dto;

public class UserInfo {

	private String username;
	
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public UserInfo(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UserInfo [username=" + username + "]";
	}
	
}
