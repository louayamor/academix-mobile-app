package itbsgl.louayamor.academix.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import itbsgl.louayamor.academix.model.Contact;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4; // Incremented to trigger upgrade
    private static final String DATABASE_NAME = "AcademixDB";

    private static final String TABLE_USERS = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_PHONENUMBER = "phonenumber";
    private static final String KEY_TIMESTAMP = "timestamp";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_USERNAME + " TEXT,"
            + KEY_PASSWORD + " TEXT"
            + ")";

    private static final String CREATE_TABLE_CONTACTS = "CREATE TABLE " + TABLE_CONTACTS + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_USERNAME + " TEXT, "
            + KEY_PHONENUMBER + " TEXT, "
            + KEY_TIMESTAMP + " TIMESTAMP DEFAULT ( datetime ('now' ,'localtime')))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }


    public void addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, username);
        values.put(KEY_PASSWORD, password);

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_USERNAME, contact.getUsername());
        values.put(KEY_PHONENUMBER, contact.getNum());
        values.put(KEY_TIMESTAMP, System.currentTimeMillis()); // Ensure a valid timestamp is added

        long id = db.insert(TABLE_CONTACTS, null, values);
        contact.setId((int) id);

        db.close();
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);

        if (cursor.moveToFirst()) {
            do {
                String username = cursor.getString(1);
                String phone = cursor.getString(2);
                contactList.add(new Contact(username, phone));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return contactList;
    }

    public void deleteContact(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_USERNAME + " = ?", new String[]{username});
        db.close();
    }

    public void updateContact(int id, String newUsername, String newPhone) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, newUsername);
        values.put(KEY_PHONENUMBER, newPhone);

        db.update(TABLE_CONTACTS, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Contact> searchContacts(String query) {
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String searchQuery = "SELECT * FROM " + TABLE_CONTACTS + " WHERE "
                + KEY_USERNAME + " LIKE ? OR "
                + KEY_PHONENUMBER + " LIKE ?";

        Cursor cursor = db.rawQuery(searchQuery, new String[]{"%" + query + "%", "%" + query + "%"});

        if (cursor.moveToFirst()) {
            do {
                int usernameIndex = cursor.getColumnIndex(KEY_USERNAME);
                int phoneIndex = cursor.getColumnIndex(KEY_PHONENUMBER);

                if (usernameIndex >= 0 && phoneIndex >= 0) {
                    String username = cursor.getString(usernameIndex);
                    String phone = cursor.getString(phoneIndex);
                    contactList.add(new Contact(username, phone));
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return contactList;
    }

    public List<Contact> getAllContactsSortedByDate() {
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_CONTACTS + " ORDER BY " + KEY_TIMESTAMP + " DESC";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(KEY_ID);
                int usernameIndex = cursor.getColumnIndex(KEY_USERNAME);
                int phoneIndex = cursor.getColumnIndex(KEY_PHONENUMBER);
                int timestampIndex = cursor.getColumnIndex(KEY_TIMESTAMP);

                if (usernameIndex >= 0 && phoneIndex >= 0 && timestampIndex >= 0) {
                    int id = cursor.getInt(idIndex);
                    String username = cursor.getString(usernameIndex);
                    String phone = cursor.getString(phoneIndex);
                    String timestamp = cursor.getString(timestampIndex);
                    contactList.add(new Contact(id, username, phone, timestamp));
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return contactList;
    }


    public void clearContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM contacts");
        db.close();
    }

    public void insertRandomContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (int i = 1; i <= 15; i++) {
                String username = "Louay" + i;
                String phone = "+12345678" + String.format("%02d", i);
                ContentValues values = new ContentValues();
                values.put(KEY_USERNAME, username);
                values.put(KEY_PHONENUMBER, phone);
                db.insert(TABLE_CONTACTS, null, values);
            }

            for (int i = 1; i <= 15; i++) {
                String username = "Amor" + i;
                String phone = "+98765432" + String.format("%02d", i);
                ContentValues values = new ContentValues();
                values.put(KEY_USERNAME, username);
                values.put(KEY_PHONENUMBER, phone);
                db.insert(TABLE_CONTACTS, null, values);
            }

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS,
                new String[]{KEY_ID},
                KEY_USERNAME + "=? AND " + KEY_PASSWORD + "=?",
                new String[]{username, password},
                null, null, null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        return cursorCount > 0;
    }
}
