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
import android.view.View;
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

    RecyclerView msgRecycler;
    RecyclerView contactsRecycler;

    RVAdapter adapterContacts;
    RVAdapter adapterMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHelp = findViewById(R.id.btnHelp);
        iBtnSet = findViewById(R.id.iBtnSet);
        txtV = findViewById(R.id.txtV);
        linerContacts = findViewById(R.id.linLayoutContacts);
        linerMessage = findViewById(R.id.linLayoutMessage);
        msgRecycler = findViewById(R.id.rvMsg);
        contactsRecycler = findViewById(R.id.rvContacts);

        // ini msgRecycle
        RecyclerView.LayoutManager lmMsg = new LinearLayoutManager(this);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(msgRecycler.getContext(), DividerItemDecoration.VERTICAL);
        msgRecycler.setLayoutManager(lmMsg);
        msgRecycler.addItemDecoration(itemDecoration);
        // ini contactsRecycle
        RecyclerView.LayoutManager lmContacts = new LinearLayoutManager(this);
        contactsRecycler.setLayoutManager(lmContacts);
        contactsRecycler.addItemDecoration(itemDecoration);

        // Fill Contacts
        adapterContacts = fillRecycle(DBHelper.TBL_CONTACT, "name", contactsRecycler);
        // Fill Message
        adapterMsg = fillRecycle(DBHelper.TBL_MESSAGE, "text", msgRecycler);

        txtV.setText(String.valueOf(APP_VERSION));

        btnHelp.setOnClickListener(new btnClickListener());
        iBtnSet.setOnClickListener(new btnClickListener());

        //loadSettings();
    }

    @Override
    protected void onResume() {

        // Update recycle
        updateAdapters();
        super.onResume();
    }

    private RVAdapter fillRecycle(String tbl, String columnName, RecyclerView recycler) {

        Cursor cursor = createCursor(tbl);
        int columnNum = cursor.getColumnIndex(columnName);

        RVAdapter adapter = new RVAdapter(cursor,columnNum);
        recycler.setAdapter(adapter);

        cursor.close();

        return adapter;
    }

    private Cursor createCursor( String table) {

        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getReadableDatabase();
        return db.query(table,null,null, null,
                null, null, null);
    }

    private void updateAdapters() {
        adapterMsg.setCursor(createCursor(DBHelper.TBL_MESSAGE));
        adapterContacts.setCursor(createCursor(DBHelper.TBL_CONTACT));
        adapterMsg.notifyDataSetChanged();
        adapterContacts.notifyDataSetChanged();
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
