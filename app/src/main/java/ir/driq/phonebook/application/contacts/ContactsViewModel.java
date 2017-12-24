package ir.driq.phonebook.application.contacts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import ir.driq.phonebook.business.database.DatabaseHelper;
import ir.driq.phonebook.business.model.Contact;

/**
 * Contact list viewModel
 */

public class ContactsViewModel extends AndroidViewModel {

    private LiveData<List<Contact>> contacts;
    private DatabaseHelper databaseHelper;

    public ContactsViewModel(@NonNull Application application) {
        super(application);
        databaseHelper = new DatabaseHelper(application.getApplicationContext());
    }

    LiveData<List<Contact>> getContacts() {
        if (contacts == null) {
            loadContacts();
        }
        return contacts;
    }

    private void loadContacts() {
        contacts = databaseHelper.getContacts();
    }
}
