package com.alameendev.librarymanagement.librarysetup;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.alameendev.librarymanagement.homepage.HomePageView;
import com.alameendev.librarymanagement.login.LoginView;
import com.alameendev.librarymanagement.model.Library;
import com.alameendev.librarymanagement.validationutils.Validation;

public class LibrarySetupView {

	// This variable should be private.
	// so that outside of this class cannot access this variable.
	private LibrarySetupModel librarySetupModel;
	private LoginView loginView;

	// This Constructor should be public.
	// so that all classes can create instance of this class.
	public LibrarySetupView() {
		librarySetupModel = new LibrarySetupModel(this);
		loginView = new LoginView();
	}

	public void init() {
		librarySetupModel.startSetup();

	}

	public void setUpLibrary() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the library name:");
		scanner.nextLine();
		String libraryName = scanner.nextLine();
		if (libraryName.length() < 4) {
			showAlert("Enter a valid name!\n");
			return;
		}
		System.out.print("Enter the phone no:");
		String phoneNo = scanner.nextLine();
		if (!Validation.validate().phone(phoneNo)) {
			showAlert("Enter a valid phone no!\n");
			return;
		}
		System.out.print("Enter the email id:");
		String emailId = scanner.nextLine();
		if (!Validation.validate().email(emailId)) {
			showAlert("Enter a valid email id!\n");
			return;
		}
		System.out.print("Enter the address:");
		String address = scanner.nextLine();
		if (address.length() < 5) {
			showAlert("Enter a valid address!\n");
			return;
		}
		librarySetupModel.updateLibrary(libraryName, phoneNo, emailId, address);
	}

	public void onSetupComplete() {
		new HomePageView().init();
	}

	public void showLibraryInformation() {
		Library library = librarySetupModel.getLibraryInformation();
		System.out.printf("Library Name:%s%nLibrary Phone:%s%nLibrary Email:%s%nLibrary Address:%s",
				library.getLibraryName(), library.getPhoneNo(), library.getEmailId(), library.getAddress());
	}

	public void initiateSetup() {
		setUpLibrary();
	}

	// Library menu.
	public void showManageLibrarySetupMenu() {
		Scanner scanner = new Scanner(System.in);
		exit: while (true) {
			try {
				System.out.printf("%nLibrary Setup Menu   : 1 %-5s| 2 %-5s| 3 %-5s| 4 %-5s%n", "Edit Library Info",
						"Show Library Info", "Logout", "Go Back");
				System.out.print("Enter the choice:");
				int choice = scanner.nextInt();
				switch (choice) {
				case 1:
					this.setUpLibrary();
					break;
				case 2:
					this.showLibraryInformation();
					break;
				case 3:
					loginView.logout();
					return;
				case 4:
					break exit;
				default:
					showAlert("Enter a valid option!\n");
					break;
				}
			} catch (InputMismatchException e) {
				scanner.next();
				showAlert("Enter a number input!\n");
			}
		}

	}

	public void showAlert(String alert) {
		System.out.println(alert);
	}
}
