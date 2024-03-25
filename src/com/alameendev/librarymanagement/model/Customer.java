package com.alameendev.librarymanagement.model;

import java.io.Serializable;

public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long customerId;

	private String name;
	private String emailId;
	private String phone;
	private String address;
	private boolean membership;

	private Customer() {
	}

	private Customer(Builder builder) {
		this.name = builder.name;
		this.address = builder.address;
		this.emailId = builder.emailId;
		this.phone = builder.phone;
		this.customerId = builder.customerId;
		this.membership = builder.membership;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public boolean isMembership() {
		return membership;
	}

	public static class Builder {
		private String name;
		private long customerId;
		private String emailId;
		private String phone;
		private String address;
		private boolean membership;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder emailId(String emailId) {
			this.emailId = emailId;
			return this;
		}

		public Builder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder customerId(long customerId) {
			this.customerId = customerId;
			return this;
		}

		public Builder address(String address) {
			this.address = address;
			return this;
		}

		public Builder membership(boolean membership) {
			this.membership = membership;
			return this;
		}

		public Customer build() {
			return new Customer(this);
		}
	}

	@Override
	public String toString() {

		return "Customer { " + customerId + " - " + name + " - " + phone + " }";
	}
}
