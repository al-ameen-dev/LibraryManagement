package com.alameendev.librarymanagement.model;

public class UniqueId {

	private long bookId;
	private long customerId;
	private long adminId;
	private long issuedBooksId;
	private long libraryId;

	private static UniqueId uniqueId;

	public static UniqueId id() {
		if (uniqueId == null) {
			uniqueId = new UniqueId();
		}
		return uniqueId;
	}

	private UniqueId() {
		bookId = 1;
		customerId = 1;
		adminId = 1;
		issuedBooksId = 1;
		libraryId = 1;
	}

	public void reset() {
		bookId = 1;
		customerId = 1;
		adminId = 1;
		issuedBooksId = 1;
		libraryId = 1;
	}

	public void incrementBookid() {
		bookId++;
	}

	public long getBookId() {
		return bookId;
	}

	public void incrementCustomerId() {
		customerId++;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void incrementAdminId() {
		adminId++;
	}

	public long getAdminId() {
		return adminId;
	}

	public void incrementIssuedBooksId() {
		issuedBooksId++;
	}

	public long getIssuedBooksId() {
		return issuedBooksId;
	}

	public void incrementLibraryid() {
		libraryId++;
	}

	public long getLibraryId() {
		return libraryId;
	}

}
