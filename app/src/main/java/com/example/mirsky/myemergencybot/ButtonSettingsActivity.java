package com.example.mirsky.myemergencybot;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ButtonSettingsActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnRed;
    Button btnBlue;
    Button btnBlack;
    Button btnYelow;

    Button btnHelp2;

    SharedPreferences sPref;

    final String SET_BTN_COLOR = "setBtnColor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_settings);

    btnRed = findViewById(R.id.button5);
    btnBlue = findViewById(R.id.button4);
    btnBlack = findViewById(R.id.button3);
    btnYelow = findViewById(R.id.button2);

    btnRed.setOnClickListener(this);
    btnBlue.setOnClickListener(this);
    btnBlack.setOnClickListener(this);
    btnYelow.setOnClickListener(this);

    btnHelp2 = findViewById(R.id.btnHelp2);
    }


    @Override
    public void onClick(View v) {

        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();

        String strColor;

        switch (v.getId()) {
            case R.id.button5:
                btnHelp2.setBackground(btnRed.getBackground());
                strColor = "red";
                break;
            case R.id.button4:
                btnHelp2.setBackground(btnBlue.getBackground());
                strColor ="Blue";
                break;
            case R.id.button3:
                btnHelp2.setBackground(btnBlack.getBackground());
                strColor = "Black";
                break;
            case R.id.button2:
                btnHelp2.setBackground(btnYelow.getBackground());
                strColor = "Yellow";
                break;
            default:
                return;
        }

        ed.putString(SET_BTN_COLOR, strColor);
        ed.apply();

    }
}
