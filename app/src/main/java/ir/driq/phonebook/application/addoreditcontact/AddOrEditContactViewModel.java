package ir.driq.phonebook.application.addoreditcontact;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import ir.driq.phonebook.business.database.DatabaseHelper;
import ir.driq.phonebook.business.model.Contact;

/**
 * Add contact viewModel
 **/

public class AddOrEditContactViewModel extends AndroidViewModel {

    private DatabaseHelper databaseHelper;

    public AddOrEditContactViewModel(@NonNull Application application) {
        super(application);
        databaseHelper = new DatabaseHelper(application.getApplicationContext());
    }

    void addContact(Contact contact) {
        databaseHelper.addContact(contact);
    }

    void updateContact(Contact contact) {
        databaseHelper.editContact(contact);
    }
}
