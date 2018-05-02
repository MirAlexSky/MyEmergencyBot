package com.example.mirsky.myemergencybot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final double APP_VERSION = 1.0;
    static final String TAG = "Check";

    TextView txtV;
    Button btnHelp;
    ImageButton iBtnSet;
    LinearLayout linerContacts;
    LinearLayout linerMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHelp = findViewById(R.id.btnHelp);
        iBtnSet = findViewById(R.id.iBtnSet);
        txtV = findViewById(R.id.txtV);
        linerContacts = findViewById(R.id.linLayoutContacts);
        linerMessage = findViewById(R.id.linLayoutMessage);

        // Fill linerContacts
        fillContacts();
        // Fill linerMessage
        fillMessage();

        txtV.setText(String.valueOf(APP_VERSION));

        btnHelp.setOnClickListener(new btnClickListener());
        iBtnSet.setOnClickListener(new btnClickListener());

        //loadSettings();

    }

    @Override
    protected void onResume() {
        super.onResume();

        linerContacts.removeAllViews();
        linerMessage.removeAllViews();
        fillContacts();
        fillMessage();
        //loadSettings();
    }


    private void fillMessage() {

        String messageText;
        TextView txtMessage;

        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TBL_MESSAGE,null,null, null,
                null, null, null);

        int messageTextIndex = cursor.getColumnIndex("text");

        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        while (cursor.moveToNext()) {
            messageText = cursor.getString(messageTextIndex);
            txtMessage = new TextView(this);
            txtMessage.setText(messageText);
            linerMessage.addView(txtMessage,lParams);
        }
        cursor.close();

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

    private void loadSettings() {

        //Set Help Button's Color

        SharedPreferences settings = getSharedPreferences("MySet", MODE_PRIVATE);
        String ColorOfButton = settings.getString(ButtonSettingsActivity.SET_BTN_COLOR, "null");
        switch (ColorOfButton) {
            case "Red" :
                btnHelp.setBackgroundColor(13730176);
                break;
            case "Blue" :
                btnHelp.setBackgroundColor(6579455);
                break;
            case "Black" :
                btnHelp.setBackgroundColor(0);
                break;
            case "Yelow" :
                btnHelp.setBackgroundColor(16770565);
                break;
        }
    }

    class btnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btnHelp:
                    justMessage("Эта часть приложения ещё дорабатывается");
                    break;
                case R.id.iBtnSet:
                    Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    void justMessage(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

}
