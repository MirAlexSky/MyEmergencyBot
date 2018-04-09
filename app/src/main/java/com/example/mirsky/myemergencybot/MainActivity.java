package com.example.mirsky.myemergencybot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final double APP_VERSION = 1.0;

    TextView txtV;
    Button btnHelp;
    ImageButton iBtnSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHelp = findViewById(R.id.btnHelp);
        iBtnSet = findViewById(R.id.iBtnSet);
        txtV = findViewById(R.id.txtV);

        txtV.setText(String.valueOf(APP_VERSION));

        btnHelp.setOnClickListener(new btnClickListener());
        iBtnSet.setOnClickListener(new btnClickListener());

        // Set buttonHelp color from settings
        SharedPreferences sPref = getPreferences(MODE_PRIVATE);
        String strColor = sPref.getString("setBtnColor", "non");
        switch (strColor) {
            case "red" :
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
