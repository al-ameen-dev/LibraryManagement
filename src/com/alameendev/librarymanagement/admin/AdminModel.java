package com.alameendev.librarymanagement.admin;

import com.alameendev.librarymanagement.db.LibraryDatabase;
import com.alameendev.librarymanagement.model.Admin;

public class AdminModel {

    private AdminView adminView;
    public AdminModel(AdminView adminView) {
        this.adminView = adminView;
    }
    public void updateAdmin(String adminName,String password, String phoneNo, String emailId, String address) {
        LibraryDatabase database = LibraryDatabase.getInstance();
        database.getAdmin().setAddress(address);
        database.getAdmin().setName(adminName);
        database.getAdmin().setPassword(password);
        database.getAdmin().setPhoneNo(phoneNo);
        database.getAdmin().setEmailId(emailId);
        database.saveAdmin();
        adminView.showAlert("Admin Profile updated!\n");

    }

    public Admin getAdminInfo(){
        return LibraryDatabase.getInstance().getAdmin();
    }
    
}
