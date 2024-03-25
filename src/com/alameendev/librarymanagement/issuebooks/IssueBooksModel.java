package com.alameendev.librarymanagement.issuebooks;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.alameendev.librarymanagement.db.DB_LABELS;
import com.alameendev.librarymanagement.db.LibraryDatabase;
import com.alameendev.librarymanagement.model.IssuedBooks;

public class IssueBooksModel {

	private IssueBooksView issueBooksView;

	IssueBooksModel(IssueBooksView issueBooksView) {
		this.issueBooksView = issueBooksView;
		if (LibraryDatabase.getInstance().retrieveIssuedBooks() != null) {
			LibraryDatabase.getInstance().initDb(DB_LABELS.ISSUED_BOOKS);
		}
	}

	public boolean checkCustomer(long customerId) {
		return LibraryDatabase.getInstance().isCustomerPresent(customerId);
	}

	public boolean checkBook(long bookId) {
		return LibraryDatabase.getInstance().isBookPresent(bookId);
	}

	public void addIssuedBook(long customerId, long bookId, String dueDate) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LibraryDatabase db = LibraryDatabase.getInstance();
		Date parsedDueDate, issuedDate;
		try {
			LocalDateTime userLocalTime = LocalDateTime.parse(dueDate, dateTimeFormatter);
			LocalDateTime issuedUserLocalDateTime = LocalDateTime.now();
			issuedDate = toDate(issuedUserLocalDateTime);
			parsedDueDate = toDate(userLocalTime);
			IssuedBooks issuedBook = new IssuedBooks.Builder().customerId(customerId).bookid(bookId)
					.issuedDate(issuedDate).dueDate(parsedDueDate).issuedBooksId(db.uniqueId().getIssuedBooksId())
					.build();
			db.addIssuedBook(issuedBook);
			issueBooksView.showAlert("Book issued successfully for the customer with id '" + customerId + "'");
		} catch (Exception e) {
			issueBooksView.showAlert("Invalid date and time format,Please enter in specified format!\n");
		}
	}

	public Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public LocalDateTime toLocalDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public List<IssuedBooks> getIssuedBooksList() {
		if (LibraryDatabase.getInstance().getAllIssuedBooks().size() == 0) {
			issueBooksView.showAlert("Currently no books issued!\n");
		}
		return LibraryDatabase.getInstance().getAllIssuedBooks();
	}

	public List<IssuedBooks> getOverDueBooksList() {
		LocalDateTime localDateTime = LocalDateTime.now();
		Date now = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		if (LibraryDatabase.getInstance().getOverDueBooks(now).size() == 0) {
			issueBooksView.showAlert("The library doesnt have any overduebooks!\n");
		}
		return LibraryDatabase.getInstance().getOverDueBooks(now);
	}

	public List<IssuedBooks> getOverDueBooksListForCustomert(long customerId) {
		LocalDateTime localDateTime = LocalDateTime.now();
		Date now = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		if (LibraryDatabase.getInstance().getOverDueBooksForCustomer(customerId, now).size() == 0) {
			issueBooksView.showAlert("The user doesnt have any overdue books!\n");
		}
		return LibraryDatabase.getInstance().getOverDueBooksForCustomer(customerId, now);
	}

	public void deleteIssuedBook(long customerId, long bookId) {
		LibraryDatabase db = LibraryDatabase.getInstance();
		if (db.getAllIssuedBooks().size() == 0) {
			issueBooksView.showAlert("There is no Books issued to reclaim!\n");
			return;
		}
		String result = db.deleteIssuedBook(customerId, bookId);
		issueBooksView.showAlert(result);
//		db.getBook(bookId).increaseAvailableCount();
//		db.saveBook();
//		db.saveIssuedBooks();
	}

	public List<IssuedBooks> getIssuedBooksForCustomer(long customerId) {
		return LibraryDatabase.getInstance().getAllIssuedBooksForCustomer(customerId);
	}

	public int bookAvailableCount(long bookid) {
		return LibraryDatabase.getInstance().getBook(bookid).getAvailableCount();
	}

}
