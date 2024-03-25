package com.alameendev.librarymanagement.managebook;

import java.util.Scanner;

import com.alameendev.librarymanagement.login.LoginView;
import com.alameendev.librarymanagement.model.Book;

import java.util.InputMismatchException;
import java.util.List;

public class ManageBookView {

	private ManageBookModel manageBookModel;
	private LoginView loginView;
//	private Scanner scanner = Input.in().getScanner();

	public ManageBookView() {
		manageBookModel = new ManageBookModel(this);
		loginView = new LoginView();
	}

	// Menu for Managing Books.
	public void showManageBookMenu() {
		//Scanner scanner = Input.in().getScanner();
		Scanner scanner = new Scanner(System.in);
		exit: while (true) {
			System.out.printf("%nManage Books Menu   : 1 %-5s| 2 %-5s| 3 %-5s| 4 %-5s| 5 %-5s| 6 %-5s| 7 %-5s%n",
					"Add Book", "Show Books", "Search Books", "Update stocks", "Delete Book", "Logout", "Go Back");
			System.out.print("Enter the choice:");
			try {
				int choice = scanner.nextInt();
				switch (choice) {
				case 1:
					this.addBook();
					break;
				case 2:
					this.listBooks();
					break;
				case 3:
					this.searchBooksByTitle();
					break;
				case 4:
					this.stockUpdate();
					break;
				case 5:
					this.deleteBookById();
					break;
				case 6:
					loginView.logout();
					return;
				case 7:
					break exit;
				default:
					showAlert("Enter a valid option!\n");
					break;
				}
			}catch(InputMismatchException e) {
				showAlert("Enter a number input!\n");
				scanner.next();
			}
		}
	}

	private void stockUpdate() {
		//Scanner scanner = Input.in().getScanner();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the book Id:");
		try {
			int bookid = scanner.nextInt();
			System.out.print("Enter the new books count:");
			int newBooks = scanner.nextInt();
			manageBookModel.updateStock(bookid, newBooks);
		}catch(InputMismatchException e) {
			showAlert("Enter a number input!");
		}
	}

	// This method is used to get the book information from the user.
	public void addBook() {
		//Scanner scanner = Input.in().getScanner();
		Scanner scanner = new Scanner(System.in);
		System.out.println("---Enter the books to the library---\n");
		System.out.print("Enter the Bookname:");
		scanner.nextLine();
		String name = scanner.nextLine();
		System.out.print("Enter the author:");
		String author = scanner.nextLine();
		System.out.print("Enter the publication:");
		String publication = scanner.nextLine();
		int edition = 0;
		showAlert("Enter the edition:");
		try {
			edition = scanner.nextInt();
		}catch(InputMismatchException e) {
			showAlert("Enter a number input!\n");
			return;
		}
		System.out.print("Enter the genre:");
		String genre = scanner.next();
		System.out.print("Enter the no of Books available:");
		try {
			int availableCount = scanner.nextInt();
			showAlert("Enter the volume:");
			int volume = scanner.nextInt();
			manageBookModel.updateBookList(name, author, publication, edition, genre, availableCount, volume);
		}catch(InputMismatchException e) {
			showAlert("Enter a number input!\n");
		}
	}

	// This method will get the book id from the user.
	public void deleteBookById() {
		//Scanner scanner = Input.in().getScanner();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the id of the book to delete:");
		int id = scanner.nextInt();
		manageBookModel.deleteBookFromTheList(id);
	}

	// Search the books by title.
	public void searchBooksByTitle() {
		//Scanner scanner = Input.in().getScanner();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the title to search:");
		String title = scanner.nextLine();
		List<Book> bookList = manageBookModel.getBooksByTitle(title);
		this.showBookTable(bookList);

	}

	// This method print book in a formatted table
	public void showBookTable(List<Book> bookList) {
		if (bookList.size() == 0) {
			showAlert("Currently there is no book in the library. Add some books");
		} else {
			System.out.printf("%n%-5s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s%n", "Id", "Book Name", "Author",
					"Publication", "Edition", "Genre", "Volume", "Books available");
			for (Book book : bookList) {
				System.out.printf("%n%-5d|%-15s|%-15s|%-15s|%-15s|%-15S|%-15d|%-15d%n", book.getId(), book.getName(),
						book.getAuthor(), book.getPublication(), book.getEdition(), book.getGenre(), book.getVolume(),
						book.getAvailableCount());
			}
		}
	}

	// This method list the books.
	public void listBooks() {
		List<Book> bookList = manageBookModel.getAllBooks();
		this.showBookTable(bookList);
	}

	public void showAlert(String alert) {
		System.out.print(alert);
	}
}
