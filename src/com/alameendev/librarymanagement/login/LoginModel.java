package com.alameendev.librarymanagement.login;

import com.alameendev.librarymanagement.db.DB_LABELS;
import com.alameendev.librarymanagement.db.LibraryDatabase;

public class LoginModel {

	private LoginView loginView;

	LoginModel(LoginView loginView) {
		this.loginView = loginView;
		
		if(LibraryDatabase.getInstance().getAdmin() == null && LibraryDatabase.getInstance().retrieveAdmin()==null){
			LibraryDatabase.getInstance().createAdmin("zsgs", "admin");
		}else {
			LibraryDatabase.getInstance().initDb(DB_LABELS.ADMIN);
		}
	}

	public void validateUser(String userName, String password) {
		if (isValidUserName(userName)) {
			if (isValidPassword(password)) {
				loginView.onSuccess();
			} else {
				loginView.showAlert("Invalid password!\n");
				loginView.init();
			}
		}else {
			loginView.showAlert("invalid User Name!\n");
			loginView.init();
		}
	}


	// this method should be private because this method used only with in this
	// class.
	private boolean isValidUserName(String userName) {
		return userName.equals(LibraryDatabase.getInstance().getAdmin().getName());
	}

	// this method should be private because this method used only with in this
	// class.
	private boolean isValidPassword(String password) {
		return password.equals(LibraryDatabase.getInstance().getAdmin().getPassword());
	}
}
