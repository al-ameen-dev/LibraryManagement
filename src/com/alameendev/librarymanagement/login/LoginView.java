package com.alameendev.librarymanagement.login;

import java.util.Scanner;

import com.alameendev.librarymanagement.LibraryManagement;
import com.alameendev.librarymanagement.db.LibraryDatabase;
import com.alameendev.librarymanagement.librarysetup.LibrarySetupView;

public class LoginView {

	private LoginModel loginModel;

	public LoginView() {
		loginModel = new LoginModel(this);
	}

	public void init() {
		LibraryManagement lm = LibraryManagement.getInstance();
		String welcomeString = lm.getAppname() + " -----\nv ----- " + lm.getAppVersion();
		String welcomeMessage = String.format("%n%s%n", welcomeString);
		showAlert(welcomeMessage);
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nPlease login to proceed.\n");
		System.out.print("\nEnter the user name:");
		String userName = scanner.next();
		System.out.print("Enter the password:");
		String password = scanner.next();
		loginModel.validateUser(userName, password);
	}

	public void logout() {
		showAlert("Loged Out Successfully!\n");
		init();
	}

	public void onSuccess() {
		LibraryManagement lm = LibraryManagement.getInstance();
		System.out.flush();
		String loginString = "Login successful....";
		String welcomeString = lm.getAppname() + " -v " + lm.getAppVersion();
		String userString = " Welcome " + LibraryDatabase.getInstance().getAdmin().getName();
		String welcomeMessage = String.format("%n%s %n%s %n%s %n", loginString, welcomeString, userString);
		showAlert(welcomeMessage);
		LibrarySetupView librarySetupView = new LibrarySetupView();
		librarySetupView.init();
	}

	public void showAlert(String alertText) {
		System.out.print(alertText);
	}

}
