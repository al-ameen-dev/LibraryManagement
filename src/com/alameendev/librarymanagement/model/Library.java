package com.alameendev.librarymanagement.model;

import java.io.Serializable;

public class Library implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String libraryName;
	private long libraryId;
	private String phoneNo;
	private String emailId;
	private String address;

	private Library() {

	}

	private Library(Builder builder) {
		this.libraryName = builder.libraryName;
		this.libraryId = builder.libraryId;
		this.phoneNo = builder.phoneNo;
		this.emailId = builder.emailId;
		this.address = builder.address;
	}

	public String getLibraryName() {
		return libraryName;
	}

	public long getLibraryId() {
		return libraryId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public void setLibraryId(long libraryId) {
		this.libraryId = libraryId;
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

	public static class Builder {
		private String libraryName;
		private long libraryId;
		private String phoneNo;
		private String emailId;
		private String address;

		public Builder libraryName(String libraryName) {
			this.libraryName = libraryName;
			return this;
		}

		public Builder libraryId(long libraryId) {
			this.libraryId = libraryId;
			return this;
		}

		public Builder phoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
			return this;
		}

		public Builder emailId(String emailId) {
			this.emailId = emailId;
			return this;
		}

		public Builder address(String address) {
			this.address = address;
			return this;
		}

		public Library build() {
			return new Library(this);
		}
	}

	@Override
	public String toString() {

		return "Library { " + libraryId + " - " + libraryName + " - " + phoneNo + " - " + emailId + " - " + address
				+ " }";
	}

}
