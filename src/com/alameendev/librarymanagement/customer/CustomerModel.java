package com.alameendev.librarymanagement.customer;

import java.util.List;

import com.alameendev.librarymanagement.db.DB_LABELS;
import com.alameendev.librarymanagement.db.LibraryDatabase;
import com.alameendev.librarymanagement.model.Customer;

public class CustomerModel {
	private CustomerView customerView;

	public CustomerModel(CustomerView customerView) {
		this.customerView = customerView;
		if (LibraryDatabase.getInstance().retrieveCustomer() != null) {
			LibraryDatabase.getInstance().initDb(DB_LABELS.CUSTOMER);
		}
	}

	// Adding the customer object to the database.
	public void addCustomer(String name, String phone, String emailId, String address) {
		LibraryDatabase db = LibraryDatabase.getInstance();
		Customer customer = new Customer.Builder().address(address).emailId(emailId).membership(true).name(name)
				.phone(phone).customerId(db.uniqueId().getCustomerId()).build();
		db.addCustomer(customer);
		customerView.showAlert("Customer added successfully!\n");
	}
	public void addCustomer(Customer customer) {
		
	}

	// Deleting the customer from the database.
	public void deleteCustomer(int id) {
		if (LibraryDatabase.getInstance().getAllCutomers().size() == 0) {
			customerView.showAlert("There is no customer to delete!\n");
			return;
		}
		LibraryDatabase.getInstance().deleteCustomer(id);
		LibraryDatabase.getInstance().saveCustomer();
	}

	// Retrieving all customers from the database.
	public List<Customer> getCustomerList() {
		return LibraryDatabase.getInstance().getAllCutomers();
	}
}
