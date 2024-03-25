package com.alameendev.librarymanagement.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.alameendev.librarymanagement.model.Admin;
import com.alameendev.librarymanagement.model.Book;
import com.alameendev.librarymanagement.model.Credentials;
import com.alameendev.librarymanagement.model.Customer;
import com.alameendev.librarymanagement.model.IssuedBooks;
import com.alameendev.librarymanagement.model.Library;
import com.alameendev.librarymanagement.model.UniqueId;
import com.alameendev.librarymanagement.serializer.JsonSerializer;
import com.fasterxml.jackson.core.type.TypeReference;

public class LibraryDatabase {

	private static LibraryDatabase libraryDatabase;

	private Library library;
	private Admin admin;
	private UniqueId uniqueId;
	private List<Book> bookList = new ArrayList<>();
	private List<Credentials> credentialsList = new ArrayList<>();
	private List<Customer> customerList = new ArrayList<>();
	private List<IssuedBooks> issuedBooksList = new ArrayList<>();

	private final String UNIQUE_ID_FILE_NAME = "uniqueid.librarymanagement";
	private final String ADMIN_FILE_NAME = "admin.librarymanagement";
	private final String BOOK_FILE_NAME = "book.librarymanagement";
	private final String CUSTOMER_FILE_NAME = "customer.librarymanagement";
	private final String ISSUED_BOOK_FILE_NAME = "issuedbook.librarymanagement";
	private final String LIBRARY_FILE_NAME = "library.librarymanagement";

	private LibraryDatabase() {
		if (JsonSerializer.serialize().retrieveFromJson(UNIQUE_ID_FILE_NAME, UniqueId.class) != null) {
			uniqueId = JsonSerializer.serialize().retrieveFromJson(UNIQUE_ID_FILE_NAME, UniqueId.class);
		} else {
			uniqueId = UniqueId.id();
		}
	}

	// Singleton pattern to get the LibraryDatabase instance.
	public static LibraryDatabase getInstance() {
		if (libraryDatabase == null) {
			try {
				libraryDatabase = new LibraryDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return libraryDatabase;
	}

	// A helper method to retrive the data that stored in file by a DB_LABELS enum.
	public void initDb(DB_LABELS label) {
		switch (label) {
		case ADMIN:
			if (retrieveAdmin() != null) {
				this.admin = retrieveAdmin();
			}
			break;
		case LIBRARY:
			if (retrieveLibrary() != null) {
				this.library = retrieveLibrary();
			}
			break;
		case BOOKS:
			if (retrieveBook() != null) {
				this.bookList = retrieveBook();
			}
			break;
		case CUSTOMER:
			if (retrieveCustomer() != null) {
				this.customerList = retrieveCustomer();
			}
			break;
		case ISSUED_BOOKS:
			if (retrieveIssuedBooks() != null) {
				this.issuedBooksList = retrieveIssuedBooks();
			}
			break;
		default:
			break;
		}

	}

	public UniqueId uniqueId() {
		return uniqueId;
	}

	// Updating the library with new library.
	public void updateLibrary(Library library) {
		this.library = library;
	}

	// Retrievning the library object.
	public Library getLibrary() {
		return library;
	}

	// Retrieve the book object based on a specific id.
	public Book getBook(long bookId) {
		for (Book book : bookList) {
			if (book.getId() == bookId) {
				return book;
			}
		}
		return null;
	}

	// Helper function used to serialize the UniqueId object and write it to the
	// disk.
	public void saveUniqueId() {
		JsonSerializer.serialize().saveToJson(uniqueId, UNIQUE_ID_FILE_NAME);
	}

	// Helper function used to serialize the Library object and write it to the
	// disk.
	public void saveLibrary() {
		JsonSerializer.serialize().saveToJson(library, LIBRARY_FILE_NAME);
	}

	// Helper function used to serialize the Admin object and write it to the disk.
	public void saveAdmin() {
		JsonSerializer.serialize().saveToJson(admin, ADMIN_FILE_NAME);
	}

	// Helper function used to serialize the Book object and write it to the disk.
	public void saveBook() {
		JsonSerializer.serialize().saveToJson(bookList, BOOK_FILE_NAME);
	}

	// Helper function used to serialize the IssuedBooks object and write it to the
	// disk.
	public void saveIssuedBooks() {
		JsonSerializer.serialize().saveToJson(issuedBooksList, ISSUED_BOOK_FILE_NAME);
	}

	// Helper function used to serialize the Customer object and write it to the
	// disk.
	public void saveCustomer() {
		JsonSerializer.serialize().saveToJson(customerList, CUSTOMER_FILE_NAME);
	}

	// Helper function to retrieve the UniqueId object json from the file.
	public UniqueId retrieveUniqueId() {
		return JsonSerializer.serialize().retrieveFromJson(UNIQUE_ID_FILE_NAME, UniqueId.class);
	}

	// Helper function to retrieve the Admin object json from the file.
	public Admin retrieveAdmin() {
		return JsonSerializer.serialize().retrieveFromJson(ADMIN_FILE_NAME, Admin.class);
	}

	// Helper function to retrieve the Library object json from the file.
	public Library retrieveLibrary() {
		return JsonSerializer.serialize().retrieveFromJson(LIBRARY_FILE_NAME, Library.class);
	}

	// Helper function to retrieve the Book object json form the file.
	public List<Book> retrieveBook() {
		return JsonSerializer.serialize().retrieveFromJson(BOOK_FILE_NAME, new TypeReference<List<Book>>() {
		});
	}

	// Helper function to retrieve the Customer object list json from the file.
	public List<Customer> retrieveCustomer() {
		return JsonSerializer.serialize().retrieveFromJson(CUSTOMER_FILE_NAME, new TypeReference<List<Customer>>() {
		});
	}

	// Helper function to retrive the IssuedBooks object list json from the file.
	public List<IssuedBooks> retrieveIssuedBooks() {
		return JsonSerializer.serialize().retrieveFromJson(ISSUED_BOOK_FILE_NAME,
				new TypeReference<List<IssuedBooks>>() {
				});
	}

	// Adds book to the books list.
	public void addBook(Book book) {
		bookList.add(book);
		uniqueId().incrementBookid();
		saveUniqueId();
		saveBook();
	}

	// Deletes book from the books list.
	public boolean deleteBook(int id) {
		return bookList.removeIf(book -> book.getId() == id);
	}

	// List all books from the books list.
	public List<Book> listBooks() {
		return bookList;
	}

	// Adds credentials to the credentials list.
	public void addCredentials(Credentials credentials) {
		credentialsList.add(credentials);
	}

	// Delete credentials from the credentials list.
	public void deleteCredentials(int id) {
		credentialsList.removeIf(cred -> cred.getId() == id);
	}

	// Adds customer to the customer list.
	public void addCustomer(Customer customer) {
		customerList.add(customer);
		saveCustomer();
		uniqueId().incrementCustomerId();
		saveUniqueId();
	}

	// Return the customer if present.
	public boolean isCustomerPresent(long customerId) {
		Optional<Customer> customer = customerList.stream().filter(cust -> cust.getCustomerId() == customerId)
				.findFirst();
		return customer.isPresent();
	}

	// Helper method to check if the Book object is present in the bookList.
	public boolean isBookPresent(long bookId) {
		Optional<Book> book = bookList.stream().filter(bk -> bk.getId() == bookId).findFirst();
		return book.isPresent();
	}

	// Delete customer from the customer list.
	public void deleteCustomer(int id) {
		customerList.removeIf(customer -> customer.getCustomerId() == id);
	}

	// Returns the customers list.
	public List<Customer> getAllCutomers() {
		return customerList;
	}

	// Adds IssuedBooks to the issued book list.
	public void addIssuedBook(IssuedBooks issuedBook) {
		issuedBooksList.add(issuedBook);
		saveIssuedBooks();
		uniqueId().incrementIssuedBooksId();
		saveUniqueId();
		if (getBook(issuedBook.getBookId()) != null) {
			getBook(issuedBook.getBookId()).deductAvailableCount();
			saveBook();
		}
	}

	// Delete IssuedBook from the issued book list.
	public String deleteIssuedBook(long customerId, long bookId) {
		if (issuedBooksList.removeIf(issued -> issued.getCustomerId() == customerId && issued.getBookId() == bookId)) {
			if (getBook(bookId) != null) {
				getBook(bookId).increaseAvailableCount();
				saveBook();
				saveIssuedBooks();
				return "Book successfully reclaimed!";
			} else {
				return "There is no Book in the id '" + bookId + "'";
			}
		}
		return "The book id or customer id is incorrect";
	}

	// Get the IssuedBooks list.
	public List<IssuedBooks> getAllIssuedBooks() {
		return issuedBooksList;
	}

	// Get the issued books list.
	public List<IssuedBooks> getAllIssuedBooksForCustomer(long customerId) {
		return issuedBooksList.stream().filter(issued -> issued.getCustomerId() == customerId).toList();
	}

	// Get all overdue Books.
	public List<IssuedBooks> getOverDueBooks(Date dueDate) {
		return issuedBooksList.stream().filter(issued -> issued.getDueDate().before(dueDate)).toList();
	}

	// Get Over due books for specific customer.
	public List<IssuedBooks> getOverDueBooksForCustomer(long customerId, Date dueDate) {
		return issuedBooksList.stream().filter(issued -> issued.getDueDate().before(dueDate))
				.filter(issued -> issued.getCustomerId() == customerId).toList();
	}

	public Admin getAdmin() {
		return admin;
	}

	public void createAdmin(String name, String password) {
		this.admin = new Admin(name, password);
	}
}
