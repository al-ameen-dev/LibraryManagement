package com.alameendev.librarymanagement.customer;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.alameendev.librarymanagement.login.LoginView;
import com.alameendev.librarymanagement.model.Customer;
import com.alameendev.librarymanagement.validationutils.Validation;

public class CustomerView {

	private CustomerModel customerModel;
	private LoginView loginView;

	public CustomerView() {
		customerModel = new CustomerModel(this);
		loginView = new LoginView();
	}

	// Manage Customers Menu.
	public void showManageCustomerMenu() {
		Scanner scanner = new Scanner(System.in);
		exit: while (true) {
			System.out.printf("%nManage Customers Menu   : 1 %-5s| 2 %-5s| 3 %-5s| 4 %-5s| 5 %-5s%n", "Add Customers",
					"Show Customers", "Delete Customer", "Logout", "Go Back");
			System.out.print("Enter the choice:");
			try {
				int choice = scanner.nextInt();
				switch (choice) {
				case 1:
					this.addCustomer();
					break;
				case 2:
					this.showCustomers();
					break;
				case 3:
					this.deleteCustomer();
					break;
				case 4:
					loginView.logout();
					return;
				case 5:
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

	// Add customer view.
	public void addCustomer() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the customer name:");
		scanner.nextLine();
		String name = scanner.nextLine();
		if(!Validation.validate().name(name)) {
			showAlert("Invalid Username format!\n");
			return;
		}
		System.out.printf("Enter the Phone number:");
		String phone = scanner.next();
		if(!Validation.validate().phone(phone)) {
			showAlert("Invalid phone number!\n");
			return;
		}
		System.out.printf("Enter the email Id:");
		String emailId = scanner.next();
		if(!Validation.validate().email(emailId)) {
			showAlert("Invalid email format!\n");
			return;
		}
		scanner.nextLine();
		System.out.printf("Enter the address:");
		String address = scanner.nextLine();
		if(address.length() < 4) {
			showAlert("Enter a Valid Address!\n");
			return;
		}
		customerModel.addCustomer(name,phone,emailId,address);
	}

	// Show customer list view.
	public void showCustomers() {
		List<Customer> customerList = customerModel.getCustomerList();
		showCustomerTable(customerList);
	}

	// Helper function to format and print the customer list.
	public void showCustomerTable(List<Customer> customerList) {
		if (customerList.size() == 0) {
			showAlert("Currently there is no customers in the library.\n");
		} else {
			System.out.printf("%n%-15s|%-15s|%-15s|%-15s|%-15s|%-15s%n", "Customer Id", "Customer Name", "Email Id",
					"Phone", "Address", "Membership");
			for (Customer customer : customerList) {
				System.out.printf("%n%-15d|%-15s|%-15s|%-15s|%-15s|%-15b%n", customer.getCustomerId(),
						customer.getName(), customer.getEmailId(), customer.getPhone(), customer.getAddress(),
						customer.isMembership());
			}
		}
	}

	// Delete view to get the customer id.
	public void deleteCustomer() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the customer Id:");
		try {
			int id = scanner.nextInt();
			customerModel.deleteCustomer(id);
		}catch(InputMismatchException e) {
			showAlert("Enter a number input!\n");
		}
	}

    public void showAlert(String alert) {
		System.out.println(alert);
    }

}
