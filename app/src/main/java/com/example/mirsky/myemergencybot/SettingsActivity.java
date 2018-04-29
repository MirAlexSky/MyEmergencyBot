package com.example.mirsky.myemergencybot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    ImageButton iBtnBack;
    TextView txtContacts, txtMessage, txtButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        iBtnBack = findViewById(R.id.iBtnBack);

        txtContacts = findViewById(R.id.txtContacts);
        txtMessage = findViewById(R.id.txtMessage);
        txtButton = findViewById(R.id.txtButton);

        txtContacts.setOnClickListener(new txtClickListener());
        txtMessage.setOnClickListener(new txtClickListener());
        txtButton.setOnClickListener(new txtClickListener());

        iBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    class txtClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.txtContacts:
                    startActivity(new Intent(SettingsActivity.this, ContactsActivity.class));
                    break;
                case R.id.txtMessage:
                    justMessage("Эта часть приложения ещё доробатывается");
                    break;
                case R.id.txtButton:
                    justMessage("Эта часть приложения ещё доробатывается");
                    //startActivity(new Intent(SettingsActivity.this, ButtonSettingsActivity.class));
                    break;
            }
        }
    }

    void justMessage(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

}
