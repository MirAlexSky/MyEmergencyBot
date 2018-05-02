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

public class MessageActivity extends AppCompatActivity {

    static final String TAG = "Check";
    LinearLayout msgLinLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.newMessageFub);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNewMessage = new Intent(MessageActivity.this, NewMessageActivity.class);
                startActivity(intentNewMessage);
            }
        });

        msgLinLayout = findViewById(R.id.msgLinLayout);

        fillMessage();

    }

    @Override
    protected void onResume() {
        super.onResume();

        msgLinLayout.removeAllViews();
        fillMessage();
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
            msgLinLayout.addView(txtMessage,lParams);
        }

        cursor.close();
    }

}
