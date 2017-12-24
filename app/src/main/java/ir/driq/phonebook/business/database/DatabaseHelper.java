package ir.driq.phonebook.business.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

import ir.driq.phonebook.business.model.Contact;

/**
 * Database helper
 */

public class DatabaseHelper {

    private ContactDao contactDao;

    public DatabaseHelper(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        contactDao = database.getContactDao();
    }

    public LiveData<List<Contact>> getContacts() {
        return contactDao.getAll();
    }

    public LiveData<Contact> getContact(int id) {
        return contactDao.get(id);
    }

    public void addContact(Contact contact) {
        contactDao.insert(contact);
    }

    public void addContacts(List<Contact> contacts) {
        contactDao.insert(contacts);
    }

    public void delete(Contact contact) {
        contactDao.delete(contact);
    }

    public void editContact(Contact contact) {
        contactDao.update(contact);
    }
}
