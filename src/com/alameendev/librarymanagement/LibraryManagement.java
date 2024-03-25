package com.alameendev.librarymanagement;

import java.io.IOException;

import com.alameendev.librarymanagement.login.LoginView;
import com.fasterxml.jackson.core.JsonProcessingException;

public class LibraryManagement {

	// This variable should be private so that other classes cannot access this
	// variable.
	// Also it should be static so that only one instance will be created.
	private static LibraryManagement libraryManagement;

	private String appName = "Library Management System";

	private String appVersion = "0.0.1";

	// default constructor should be private so that we cannot create an instance
	// from other class.
	private LibraryManagement() {

	}

	// Creating a single instance of this application class.
	// So that we access the application info(appName, appVersion) from any where in
	// the application.

	public static LibraryManagement getInstance() {
		if (libraryManagement == null) {
			libraryManagement = new LibraryManagement();
		}
		return libraryManagement;
	}

	private void create() throws JsonProcessingException, IOException {
		LoginView loginView = new LoginView();
		loginView.init();
	}

	public String getAppname() {
		return appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public static void main(String[] args) throws Exception {
		//Application created and starter from here.
		LibraryManagement.getInstance().create();

	}
}
