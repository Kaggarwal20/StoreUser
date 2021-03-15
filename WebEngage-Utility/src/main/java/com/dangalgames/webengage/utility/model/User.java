package com.dangalgames.webengage.utility.model;

public class User {
	
	private String userId;
	private String name;
	
	
	public User(String userId, String name) {
		super();
		this.userId = userId;
		this.name = name;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + "]";
	}
	
	
	
	
	
	

}
