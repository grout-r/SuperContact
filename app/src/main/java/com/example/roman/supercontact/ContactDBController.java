package com.example.roman.supercontact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Roman on 28/02/2017.
 */

public class ContactDBController {

    ContactDBController(Context context)
    {
        tdb = new MyDBOpenHelper(context, "prod.db", null, 1);
        sdb = tdb.getWritableDatabase();
    }

    private boolean doContactExist(Contact contact)
    {
        Log.e("ctrl", "->"  + contact.last_name);
        String table_name = "contacts";
        String[] columns = {"ID", "FIRST_NAME", "LAST_NAME"};
        String where_clause = "FIRST_NAME=? COLLATE NOCASE AND LAST_NAME=? COLLATE NOCASE";
        String[] where_args = {contact.first_name, contact.last_name};
        Cursor c = sdb.query(table_name, columns, where_clause, where_args, null, null, null);
        if (c.getCount() > 0 && !contact.inserted)
        {
            return true;
        }
        c.moveToFirst();
        for(int i = 0; i < c.getCount(); i++) {
           if (c.getInt(0) != contact.id)
           {
               return true;
           }
            c.moveToNext();
        }
        c.close();
        return false;
    }

    boolean addContact(Contact new_contact)
    {
        if (doContactExist(new_contact))
        {
            return false;
        }
        ContentValues cv = new ContentValues();
        cv.put("FIRST_NAME", new_contact.first_name);
        cv.put("LAST_NAME", new_contact.last_name);
        cv.put("HOME_PHONE", new_contact.mobile_phone);
        cv.put("MOBILE_PHONE", new_contact.mobile_phone);
        cv.put("MAIL", new_contact.mail);
        sdb.insert("contacts", null, cv);
        return true;
    }

    void deleteContact(Contact contact)
    {
        String table_name = "contacts";
        String where_clause = "ID = ?";
        String []where_args = {String.valueOf(contact.id)};
        sdb.delete(table_name, where_clause, where_args);
    }

    boolean updateContact(Contact contact)
    {
        if (doContactExist(contact))
        {
            return false;
        }
        String table_name = "contacts";
        String where_clause = "ID = ?";
        String []where_args = {String.valueOf(contact.id)};
        ContentValues cv = new ContentValues();
        cv.put("FIRST_NAME", contact.first_name);
        cv.put("LAST_NAME", contact.last_name);
        cv.put("HOME_PHONE", contact.home_phone);
        cv.put("MOBILE_PHONE", contact.mobile_phone);
        cv.put("MAIL", contact.mail);
        sdb.update(table_name, cv, where_clause, where_args);
        return true;
    }

    ArrayList<Contact> getAllContacts()
    {
        ArrayList<Contact> retVal = new ArrayList<>();
        String table_name = "contacts";
        String[] columns = {"ID", "FIRST_NAME", "LAST_NAME", "HOME_PHONE","MOBILE_PHONE", "MAIL"};
        Cursor c = sdb.query(table_name, columns, null, null, null, null, null);
        c.moveToFirst();
        for(int i = 0; i < c.getCount(); i++)
        {
            retVal.add(new Contact(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5)));
            c.moveToNext();
        }
        c.close();
        return retVal;
    }

    private MyDBOpenHelper tdb;
    private SQLiteDatabase sdb;

}
