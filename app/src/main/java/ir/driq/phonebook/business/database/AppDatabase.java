package ir.driq.phonebook.business.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ir.driq.phonebook.business.model.Contact;

/**
 * Room database
 */

@Database(entities = Contact.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "database.db";
    private static AppDatabase database;

    static AppDatabase getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return database;
    }

    public abstract ContactDao getContactDao();
}
