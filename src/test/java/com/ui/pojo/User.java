package com.ui.pojo;

public class User {

	
	public User(String emailAddress, String password) {
		super();
		this.emailAddress = emailAddress;
		this.password = password;
	}
	
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "User [emailAddress=" + emailAddress + ", password=" + password
				+ "]";
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}



	public void setPassword(String password) {
		this.password = password;
	}

	private String emailAddress;

	private String password;
	
}
