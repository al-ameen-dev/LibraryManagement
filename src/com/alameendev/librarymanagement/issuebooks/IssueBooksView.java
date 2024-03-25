package com.alameendev.librarymanagement.issuebooks;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.alameendev.librarymanagement.db.LibraryDatabase;
import com.alameendev.librarymanagement.login.LoginView;
import com.alameendev.librarymanagement.model.IssuedBooks;

public class IssueBooksView {

	private IssueBooksModel issueBooksModel;
	private LoginView loginView;

	public IssueBooksView() {
		this.issueBooksModel = new IssueBooksModel(this);
		loginView = new LoginView();
	}

	// Menu for Managing Issued Books.
	public void showManageIssuedBookMenu() {
		Scanner scanner = new Scanner(System.in);
		exit: while (true) {
			System.out.printf(
					"%nManage Books Menu   : 1 %-5s| 2 %-5s| 3 %-5s| 4 %-5s| 5 %-5s| 6 %-5s| 7 %-5s| 8 %-5s%n",
					"Issue A Book", "Show Issued Books", "Customer Books", "Show Overdue Books",
					"Overdue Books For Customer", "Reclaim Book", "Logout", "Go Back");
			System.out.print("Enter the choice:");
			try {
				int choice = scanner.nextInt();
				switch (choice) {
				case 1:
					this.addIssuedBook();
					break;
				case 2:
					this.listIssuedBooks();
					break;
				case 3:
					this.listIssuedBooksForCustomer();
					break;
				case 4:
					this.listOverDueBooks();
					break;
				case 5:
					this.listOverDueBooksForcustomer();
					break;
				case 6:
					this.deleteIssuedBook();
					break;
				case 7:
					loginView.logout();
					return;
				case 8:
					break exit;
				default:
					showAlert("Enter a valid option!\n");
					break;
				}
			} catch (InputMismatchException e) {
				showAlert("Enter a number input!");
				scanner.next();
			}

		}
	}

	private void deleteIssuedBook() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the Customer Id:");
		try {
			long customerId = scanner.nextInt();
			showAlert("Enter the book Id:");
			long bookId = scanner.nextInt();
			issueBooksModel.deleteIssuedBook(customerId, bookId);
		} catch (InputMismatchException e) {
			showAlert("Enter a number input!\n");
		}
	}

	public void addIssuedBook() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the Customer Id:");
		try {
			long customerId = scanner.nextInt();
			if (!issueBooksModel.checkCustomer(customerId)) {
				showAlert("There is no customer in Id " + customerId + "\n");
				return;
			}
			System.out.print("Enter the book id:");
			long bookId = scanner.nextInt();
			if (!issueBooksModel.checkBook(bookId)) {
				showAlert("There is no book in Id " + bookId + "\n");
				return;
			}
//			if (LibraryDatabase.getInstance().getBook(bookId).getAvailableCount() == 0) {
			if(issueBooksModel.bookAvailableCount(bookId) == 0) {
				showAlert("Currently the book with id " + bookId + " not available!\n");
				return;
			}
			scanner.nextLine();
			System.out.print("Enter the due date and time(YYYY-MM-DD HH:MM:SS):");
			String dueDate = scanner.nextLine();
			issueBooksModel.addIssuedBook(customerId, bookId, dueDate);
		} catch (InputMismatchException e) {
			showAlert("Enter a number input!");
			return;
		}
	}

	public void listIssuedBooks() {
		showIssuedBooksTable(issueBooksModel.getIssuedBooksList());
	}

	public void listOverDueBooks() {
		showIssuedBooksTable(issueBooksModel.getOverDueBooksList());
	}

	public void listOverDueBooksForcustomer() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the customer Id:");
		try {
			long customerId = scanner.nextInt();
			showIssuedBooksTable(issueBooksModel.getOverDueBooksListForCustomert(customerId));
		} catch (InputMismatchException e) {
			showAlert("Enter a number input!\n");
		}
	}

	public void listIssuedBooksForCustomer() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the customer Id:");
		try {
			long customerId = scanner.nextInt();
			showIssuedBooksTable(issueBooksModel.getIssuedBooksForCustomer(customerId));
		} catch (InputMismatchException e) {
			showAlert("Enter a number input!");
		}
	}

	public void showIssuedBooksTable(List<IssuedBooks> issuedBooksList) {
		if (issuedBooksList != null && issuedBooksList.size() > 0) {
			System.out.printf("%n%-5s|%-15s|%-15s|%-25s|%-15s%n", "Id", "Customer Id", "Book Id", "Issued Date",
					"Due Date");
			for (IssuedBooks issued : issuedBooksList) {
				LocalDateTime dueDate = issueBooksModel.toLocalDateTime(issued.getDueDate());
				LocalDateTime issuedDate = issueBooksModel.toLocalDateTime(issued.getIssuedDate());
				showAlert(String.format("%n%-5d|%-15d|%-15d|%tY %tb %ta %te,  %tT %-15tp|%tY %tb %ta %te, %tT %-20tp%n",
						issued.getIssuedBooksId(), issued.getCustomerId(), issued.getBookId(), issuedDate, issuedDate,
						issuedDate, issuedDate, issuedDate, issuedDate, issuedDate, dueDate, dueDate, dueDate, dueDate,
						dueDate));
			}

			// System.out.println("Currently there is no books issued in the library!");
		} 
	}

	public void showAlert(String alert) {
		System.out.println(alert);
	}
}
