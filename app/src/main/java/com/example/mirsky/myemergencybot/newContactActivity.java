package com.example.mirsky.myemergencybot;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class newContactActivity extends AppCompatActivity implements View.OnClickListener{

    EditText eTxtPhoneNumb, eTxtName, eTxtMail;
    Button btnCreate;
    DBHelper dbh;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        eTxtPhoneNumb = findViewById(R.id.eTxtPhoneNum);
        eTxtName = findViewById(R.id.eTxtName);
        eTxtMail = findViewById(R.id.eTxtMail);
        btnCreate = findViewById(R.id.btnCreat);

        btnCreate.setOnClickListener(this);
        eTxtName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (eTxtName.getText().toString().equals("Имя")) {
                    eTxtName.setText(null);
                }
                return false;
            }
        });
        // DBHelper declaration
        dbh = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        dbh.createNewContact(eTxtName.getText().toString(), eTxtPhoneNumb.getText().toString(),
                eTxtMail.getText().toString());

        Toast.makeText(this, "Новый контакт успешно добвален", Toast.LENGTH_SHORT)
                .show();

        eTxtPhoneNumb.setText(null);
        eTxtName.setText(null);
        eTxtMail.setText(null);
    }

}
