package com.example.mirsky.myemergencybot;

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

            }
        });

        msgLinLayout = findViewById(R.id.msgLinLayout);

        fillMessage();

    }

    private void fillMessage() {
        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TBL_MESSAGE,null,null, null,
                null, null, null);

        TextView txtMessage = new TextView(this);

        int messageTextIndex = cursor.getColumnIndex("text");

        while (cursor.moveToNext()) {
            String messageText = cursor.getString(messageTextIndex);

            txtMessage.setText(messageText);
            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            msgLinLayout.addView(txtMessage,lParams);
        }
        cursor.close();
    }

}
