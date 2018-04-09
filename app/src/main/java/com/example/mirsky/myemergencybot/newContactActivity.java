package com.example.mirsky.myemergencybot;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class newContactActivity extends AppCompatActivity implements View.OnClickListener{

    EditText eTxtPhoneNumb, eTxtName, eTxtNote;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        eTxtPhoneNumb = findViewById(R.id.eTxtPhoneNum);
        eTxtName = findViewById(R.id.eTxtName);
        eTxtNote = findViewById(R.id.eTxtNote);
        btnCreate = findViewById(R.id.btnCreat);

        btnCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        dbHelper dbh = new dbHelper(this);
        SQLiteDatabase db = dbh.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", eTxtName.getText().toString() );
        db.insert("contact", null, values);

    }

    class dbHelper extends SQLiteOpenHelper {

        static final String DBNAME = "MainDB";
        static final int DBVERSION = 1;

        public dbHelper(Context context) {
            super(context, DBNAME, null, DBVERSION);
        }

        @Override
        public void onCreate (SQLiteDatabase db) {
            db.execSQL( " CREATE TABLE if not exists `contact` (" +
                    "_ID int (10) primary key," +
                    "name varchar (255)," +
                    "phone varchar (30)," +
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
    }
}
