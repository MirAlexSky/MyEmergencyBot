package com.example.mirsky.myemergencybot;

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

    }

    class bdHelper extends SQLiteOpenHelper {

        final static String DATABASE_NAME = "MyDB1";
        final static int DATABASE_VERSION = 1;

        public bdHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //db.execSQL("CREATE TABLE " +
            //        " ");
            // TODO Сделать бд
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
