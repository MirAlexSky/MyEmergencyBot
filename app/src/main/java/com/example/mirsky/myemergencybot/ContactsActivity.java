package com.example.mirsky.myemergencybot;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactsActivity extends AppCompatActivity {

    static final String TAG = "Check";

    FloatingActionButton fActBtn;
    LinearLayout linerContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        fActBtn = findViewById(R.id.fActButton);
        fActBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactsActivity.this, newContactActivity.class));
            }
        });

        linerContacts = findViewById(R.id.linLayout);

        fillContacts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        linerContacts.removeAllViews();
        fillContacts();
    }

    private void fillContacts() {
        String contactName;
        TextView txtContact;

        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cursor = db.query("contact",null,null, null,
                null, null, null);

        int contactNameIndex = cursor.getColumnIndex("name");

        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        while (cursor.moveToNext()) {
            contactName = cursor.getString(contactNameIndex);

            txtContact = new TextView(this);

            txtContact.setText(contactName);
            linerContacts.addView(txtContact,lParams);
        }
        cursor.close();
    }

}
