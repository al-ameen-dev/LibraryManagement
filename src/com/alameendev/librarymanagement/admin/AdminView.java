package com.alameendev.librarymanagement.admin;

import java.util.Scanner;

import com.alameendev.librarymanagement.login.LoginView;
import com.alameendev.librarymanagement.model.Admin;
import com.alameendev.librarymanagement.validationutils.Validation;

public class AdminView {

	private AdminModel adminModel;
	private LoginView loginView;

	public AdminView() {
		adminModel = new AdminModel(this);
		this.loginView = new LoginView();
	}

	// Admin menu.
	public void showManageAdminMenu() {
		Scanner scanner = new Scanner(System.in);
		exit: while (true) {
			System.out.printf("%nAdmin Setup Menu   : 1 %-5s| 2 %-5s| 3 %-5s| 4 %-5s%n", "Edit admin Info",
					"Show Admin Info", "Logout", "Go Back");
			System.out.print("Enter the choice:");
			try {
				int choice = scanner.nextInt();
				switch (choice) {
				case 1:
					this.editAdmin();
					break;
				case 2:
					this.showAdminInfo();
					break;
				case 3:
					showAlert("Loged out Successfully\n");
					loginView.init();
					return;
				case 4:
					break exit;
				default:
					showAlert("Enter a valid option!\n");
					break;
				}
			} catch (Exception e) {
				showAlert("Enter a number input!\n");
				scanner.next();
			}
		}
	}

	private void showAdminInfo() {
		Admin admin = adminModel.getAdminInfo();
		System.out.printf("Admin Name:%s%nAdmin Phone:%s%nAdmin Email:%s%nadmin Address:%s", admin.getName(),
				admin.getPhoneNo(), admin.getEmailId(), admin.getAddress());
	}

	public void editAdmin() {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("Enter admin name:");
		scanner.nextLine();
		String adminName = scanner.nextLine();
		if (!Validation.validate().name(adminName)) {
			showAlert("Enter a valid username!");
			return;
		}
		System.out.print("Enter the pasword:");
		String password = scanner.next();
		if (password.length() < 6) {
			showAlert("Password must be greater than 6 characters!\n");
			return;
		}
		System.out.print("Enter the phone no:");
		String phoneNo = scanner.next();
		if (!Validation.validate().phone(phoneNo)) {
			showAlert("Enter a valid phone number!\n");
			return;
		}
		System.out.print("Enter the email:");
		String emailId = scanner.next();
		scanner.nextLine();
		if (!Validation.validate().email(emailId)) {
			showAlert("Enter a valid email format!\n");
			return;
		}
		System.out.print("Enter the admin address:");
		String address = scanner.nextLine();
		if (address.length() < 6) {
			showAlert("Enter a valid address!\n");
			return;
		}
		adminModel.updateAdmin(adminName, password, phoneNo, emailId, address);
	}

	public void showAlert(String alert) {
		System.out.print(alert);
	}
}
