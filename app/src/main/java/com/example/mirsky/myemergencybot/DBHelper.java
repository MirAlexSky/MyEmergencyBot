package com.example.mirsky.myemergencybot;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

        static final String DBNAME = "MainDB";
        static final String TBL_CONTACT = "contact";
        static final String TBL_MESSAGE = "message";
        static final int DBVERSION = 1;

        public DBHelper(Context context) {
            super(context, DBNAME, null, DBVERSION);
        }

        @Override
        public void onCreate (SQLiteDatabase db) {
            db.execSQL( " CREATE TABLE if not exists `contact` (" +
                    "name varchar (255)," +
                    "phone varchar (30) primary key," +
                    "mail varchar (255)" +
                    ") " );
            db.execSQL( " CREATE TABLE if not exists `message` (" +
                    "_ID int (10) primary key," +
                    "text varchar (255)" +
                    ") " );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        void createNewContact(String name, String phone, String mail) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put( "name", name );
            values.put( "phone", phone );
            values.put( "mail", mail );
            db.insert("contact", null, values);
        }

        void addNewMessage(String message) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put( "text", message );
            db.insert("message", null, values);
        }
}
