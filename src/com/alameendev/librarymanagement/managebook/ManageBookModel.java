package com.alameendev.librarymanagement.managebook;

import java.util.List;

import com.alameendev.librarymanagement.db.DB_LABELS;
import com.alameendev.librarymanagement.db.LibraryDatabase;
import com.alameendev.librarymanagement.model.Book;

public class ManageBookModel {

	private ManageBookView manageBookView;

	public ManageBookModel(ManageBookView manageBookView) {
		this.manageBookView = manageBookView;
		if (LibraryDatabase.getInstance().retrieveBook() != null) {
			LibraryDatabase.getInstance().initDb(DB_LABELS.BOOKS);
		}
	}

	// Add books to the booklist.
	public void updateBookList(String name, String author, String publication, int edition, String genre,
			int availableCount, int volume) {
		LibraryDatabase db = LibraryDatabase.getInstance();
		Book book = new Book.Builder().name(name).author(author).publication(publication).edition(edition).genre(genre)
				.volume(volume).availableCount(availableCount).id(db.uniqueId().getBookId()).build();
		db.addBook(book);
		manageBookView.showAlert("Book added successfully!\n");
	}

	// Return the list of the books available.
	public List<Book> getAllBooks() {
		return LibraryDatabase.getInstance().listBooks();
	}

	// Delete the books from the book list.
	public void deleteBookFromTheList(int id) {
		if (LibraryDatabase.getInstance().listBooks().size() == 0) {
			manageBookView.showAlert("There is no book in the library to delete");
			return;
		}
		if (LibraryDatabase.getInstance().deleteBook(id)) {
			manageBookView.showAlert("Book with id '" + id + "' deleted successfully!");
			LibraryDatabase.getInstance().saveBook();
		} else {
			manageBookView.showAlert("There is no book in id '" + id+"'");
		}

	}

	// Stock update.
	public void updateStock(int bookId, int newBooks) {
		if (!LibraryDatabase.getInstance().isBookPresent(bookId)) {
			manageBookView.showAlert("There is no book in the Id " + bookId);
			return;
		}
		LibraryDatabase.getInstance().getBook(bookId).updateStock(newBooks);
		LibraryDatabase.getInstance().saveBook();
		manageBookView.showAlert("Stocks updated successfully!");
	}

	// Searching books by title
	public List<Book> getBooksByTitle(String title) {
		return LibraryDatabase.getInstance().listBooks().stream().filter(book -> book.getName().startsWith(title))
				.toList();
	}

}