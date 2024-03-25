package com.alameendev.librarymanagement.model;

import java.io.Serializable;
import java.util.Date;

public class IssuedBooks implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long issuedBooksId;
	private long customerId;
	private long bookId;
	private Date issuedDate;
	private Date dueDate;

	private IssuedBooks() {
	}

	private IssuedBooks(Builder builder) {
		this.issuedBooksId = builder.issuedBooksId;
		this.bookId = builder.bookId;
		this.customerId = builder.customerId;
		this.issuedDate = builder.issuedDate;
		this.dueDate = builder.dueDate;
	}

	public long getIssuedBooksId() {
		return issuedBooksId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public long getBookId() {
		return bookId;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setIssuedBooksId(long issuedBooksId) {
		this.issuedBooksId = issuedBooksId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public static class Builder {
		public long issuedBooksId;
		private long customerId;
		private long bookId;
		private Date issuedDate;
		private Date dueDate;

		public Builder issuedBooksId(long issuedBooksId) {
			this.issuedBooksId = issuedBooksId;
			return this;
		}

		public Builder customerId(long customerId) {
			this.customerId = customerId;
			return this;
		}

		public Builder bookid(long bookId) {
			this.bookId = bookId;
			return this;
		}

		public Builder issuedDate(Date issuedDate) {
			this.issuedDate = issuedDate;
			return this;
		}

		public Builder dueDate(Date dueDate) {
			this.dueDate = dueDate;
			return this;
		}

		public IssuedBooks build() {
			return new IssuedBooks(this);
		}
	}

	@Override
	public String toString() {
		return "{Issued Book +" + issuedBooksId + " customer id " + customerId + " book id " + bookId + "}";
	}
}
