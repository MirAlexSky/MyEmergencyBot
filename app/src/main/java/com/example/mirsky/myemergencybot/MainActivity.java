package com.example.mirsky.myemergencybot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final double APP_VERSION = 1.0;
    static final String TAG = "Check";

    TextView txtV;
    Button btnHelp;
    ImageButton iBtnSet;
    LinearLayout linerContacts;
    LinearLayout linerMessage;

    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHelp = findViewById(R.id.btnHelp);
        iBtnSet = findViewById(R.id.iBtnSet);
        txtV = findViewById(R.id.txtV);
        linerContacts = findViewById(R.id.linLayoutContacts);
        linerMessage = findViewById(R.id.linLayoutMessage);
        recycler = findViewById(R.id.rv);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(recycler.getContext(), DividerItemDecoration.VERTICAL);
        recycler.setLayoutManager(lm);
        recycler.addItemDecoration(itemDecoration);

        // Fill Contacts
        fillContacts();
        // Fill Message
        fillMessage();

        txtV.setText(String.valueOf(APP_VERSION));

        btnHelp.setOnClickListener(new btnClickListener());
        iBtnSet.setOnClickListener(new btnClickListener());

        //loadSettings();
    }

    @Override
    protected void onResume() {
        super.onResume();



        //fillContacts();
        //fillMessage();
        //fillMessage();
        //fillContacts();

        //loadSettings();
    }


    private void fillMessage() {

        Cursor cursor = createCursor(DBHelper.TBL_MESSAGE);
        int tableNum = cursor.getColumnIndex("text");

        RVAdapter adapter = new RVAdapter(cursor,tableNum);
        recycler.setAdapter(adapter);

//        if (null != adapter) {
//
//        } else {
//            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//
//            TextView errTxt = new TextView(this);
//            errTxt.setText("Что-то пошло не так, изените");
//            listViewMessage.addView(errTxt, lParams);
//        }

    }

    private Cursor createCursor( String table) {

        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cursor = db.query(table,null,null, null,
                null, null, null);

        return cursor;
    }

    private void fillContacts() {

//        ArrayList<String> contactsList = new ArrayList<>();
//
//        fillArrayFromCursor(contactsList, DBHelper.TBL_CONTACT, "name");
//
//        try {
//        adapterContacts = new ArrayAdapter<>(this, R.layout.textview, contactsList);
//        }
//        catch (Exception e) {
//            justMessage("Адаптер не создан, видимо что-то не так с макетом TextView");
//        }
//
//        if (null != adapterContacts) {
//        listViewContacts.setAdapter(adapterContacts);
//        } else {
//            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//
//            TextView errTxt = new TextView(this);
//            errTxt.setText("Что-то пошло не так, изените");
//            listViewContacts.addView(errTxt, lParams);
//            //TODO Размер шрифта
//        }
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
