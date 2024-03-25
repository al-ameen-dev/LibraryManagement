package com.alameendev.librarymanagement.model;

import java.io.Serializable;

public class Admin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private int id;
	private String phoneNo;
	private String emailId;
	private String address;

	private Admin() {
	};

	public Admin(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	// private Admin(Builder builder) {
	// this.name = builder.name;
	// this.id = Admin.uniqueId++;
	// this.password = builder.password;
	// this.phoneNo = builder.phoneNo;
	// this.emailId = builder.emailId;
	// this.address = builder.address;
	// }

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getAddress() {
		return address;
	}

	// public static class Builder {

	// private String name;
	// private String password;
	// private String phoneNo;
	// private String emailId;
	// private String address;

	// public Builder name(String name) {
	// this.name = name;
	// return this;
	// }

	// public Builder phoneNo(String phoneNo) {
	// this.phoneNo = phoneNo;
	// return this;
	// }

	// public Builder password(String password){
	// this.password = password;
	// return this;
	// }
	// public Builder emailId(String emailid) {
	// this.emailId = emailid;
	// return this;
	// }

	// public Builder address(String address) {
	// this.address = address;
	// return this;
	// }

	// public Admin build() {
	// return new Admin(this);
	// }
	// }

	@Override
	public String toString() {

		return "Admin { " + id + " - " + name + " - " + phoneNo + " - " + emailId + " - " + address + " }";
	}
}
