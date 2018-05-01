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

    private void fillContacts() {
        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cursor = db.query("contact",null,null, null,
                null, null, null);

        TextView txtContact = new TextView(this);

        int contactNameIndex = cursor.getColumnIndex("name");

        Log.i(TAG, "contactNameIndex = " + contactNameIndex);
        while (cursor.moveToNext()) {
            String contactName = cursor.getString(contactNameIndex);
            Log.i(TAG, "contactName = " + contactName);

            txtContact.setText(contactName);
            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linerContacts.addView(txtContact,lParams);
        }
        cursor.close();
    }

}
