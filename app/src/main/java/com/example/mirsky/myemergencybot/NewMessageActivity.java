package com.example.mirsky.myemergencybot;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewMessageActivity extends AppCompatActivity {

    Button btnCreate;
    EditText eTxtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);

        btnCreate = findViewById(R.id.btnCreateMessage);
        eTxtMessage = findViewById(R.id.eTxtMessage);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(NewMessageActivity.this);
                dbh.addNewMessage(eTxtMessage.getText().toString());
            }
        });

    }

}
