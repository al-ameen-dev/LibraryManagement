package com.alameendev.librarymanagement.homepage;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.alameendev.librarymanagement.admin.AdminView;
import com.alameendev.librarymanagement.customer.CustomerView;
import com.alameendev.librarymanagement.db.LibraryDatabase;
import com.alameendev.librarymanagement.issuebooks.IssueBooksView;
import com.alameendev.librarymanagement.librarysetup.LibrarySetupView;
import com.alameendev.librarymanagement.login.LoginView;
import com.alameendev.librarymanagement.managebook.ManageBookView;
import com.alameendev.librarymanagement.model.Admin;

public class HomePageView {

	private ManageBookView manageBookView;
	private CustomerView customerView;
	private LoginView loginView;
	private IssueBooksView issueBooksView;
	private LibrarySetupView librarySetupView;
	private AdminView adminView;

	public HomePageView() {
		this.manageBookView = new ManageBookView();
		this.customerView = new CustomerView();
		this.loginView = new LoginView();
		this.issueBooksView = new IssueBooksView();
		this.librarySetupView = new LibrarySetupView();
		this.adminView = new AdminView();
	}

	public void init() {
		this.showHomePage();
	}

	public void showHomePage() {

		Scanner scanner = new Scanner(System.in);
		exit: while (true) {
			System.out.printf("%nMenu   : 1 %-5s| 2 %-5s| 3 %-5s| 4 %-5s| 5 %-5s| 8 %-5s| 9 %-5s%n", "Manage Books",
					"Manage Customer", "Manage Issued Books", "Library Setup", "Admin Setup", "Logout", "Exit");
			System.out.print("Enter the choice:");
			try {
				int choice = scanner.nextInt();
				switch (choice) {
				case 1:
					manageBookView.showManageBookMenu();
					break;
				case 2:
					customerView.showManageCustomerMenu();
					break;
				case 3:
					issueBooksView.showManageIssuedBookMenu();
					break;
				case 4:
					librarySetupView.showManageLibrarySetupMenu();
					break;
				case 5:
					adminView.showManageAdminMenu();
					break;
				case 8:
					loginView.logout();
					return;
				case 9:
					showAlert("Thank you, Visit again!\n");
					break exit;
				case 10:
					showAlert("Saving the files!\n");
					LibraryDatabase.getInstance().saveAdmin();
					// LibraryDatabase.getInstance().saveToJson();
					break;
				case 11:
					showAlert("Retrieving files!\n");
					Admin admin = LibraryDatabase.getInstance().retrieveAdmin();
					System.out.println(admin);
					break;
				case 12:
//					LibraryDatabase.getInstance().saveBookListToDisk();
//					System.out.println("Book list saved");
//					break;
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

	public void showAlert(String alert) {
		System.out.println(alert);
	}
}
