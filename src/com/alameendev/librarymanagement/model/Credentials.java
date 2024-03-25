package com.alameendev.librarymanagement.model;

public class Credentials {

	private static int uniqueId;
	private int id;
	private String userName;
	private String password;

	static {
		uniqueId = 1;
	}
	public Credentials(Builder builder) {
		this.id = uniqueId++;
		this.userName = builder.userName;
		this.password = builder.password;
	}
	
	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public static class Builder {
		private String userName;
		private String password;

		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}
		
		public Credentials build() {
			return new Credentials(this);
		}

	}

}
