package com.alameendev.librarymanagement.librarysetup;

import com.alameendev.librarymanagement.db.DB_LABELS;
import com.alameendev.librarymanagement.db.LibraryDatabase;
import com.alameendev.librarymanagement.model.Library;

//Access modifier for this LibrarySetupModel class should be default.
//So that outside of the package this class cannot be accessed.
class LibrarySetupModel {

	// This variable should be private.
	// so that outside of this class cannot access this variable.
	private LibrarySetupView librarySetupView;
	private Library library;

	// Access modifier for this LibrarySetupModel constructor should be default.
	// So that outside of the package this constructor cannot be accessed.
	LibrarySetupModel(LibrarySetupView librarySetupView) {
		this.librarySetupView = librarySetupView;
	}

	public void startSetup() {
		if (LibraryDatabase.getInstance().getLibrary() == null
					&& LibraryDatabase.getInstance().retrieveLibrary() == null) {
				librarySetupView.setUpLibrary();
				librarySetupView.showAlert("Library Setup Completed!\n");
				librarySetupView.onSetupComplete();
			} else {
				LibraryDatabase.getInstance().initDb(DB_LABELS.LIBRARY);
				librarySetupView.onSetupComplete();
			}
	}

	public void updateLibrary(String libraryName, String phoneNo, String emailId, String address) {
		Library library = new Library.Builder().libraryName(libraryName).phoneNo(phoneNo).emailId(emailId)
				.address(address).build();
		LibraryDatabase.getInstance().updateLibrary(library);
		LibraryDatabase.getInstance().saveLibrary();
		librarySetupView.showAlert("Library information updated successfully!");
	}

	public Library getLibraryInformation() {
		return LibraryDatabase.getInstance().getLibrary();
	}
}
