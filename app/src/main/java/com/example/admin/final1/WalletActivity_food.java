package com.example.admin.final1;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class WalletActivity_food extends AppCompatActivity {
    private Button add;
    private Button edit;
    private Button delete;
    private Button end;
    private EditText edname;
    private EditText edprice;
    private EditText edtime;
    private com.example.chaosmid.lifeapp_account.mydb_food db = null;
    private Cursor cursor;
    private long myid = 1;
    private ListView listview;
　

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_food);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("記帳簿");

        add = (Button) findViewById(R.id.btn_add);
        edit = (Button) findViewById(R.id.btn_edit);
        delete = (Button) findViewById(R.id.btn_delete);
        end = (Button) findViewById(R.id.btn_end);
        edname = (EditText) findViewById(R.id.edname);
        edprice = (EditText) findViewById(R.id.edprice);
        edtime = (EditText) findViewById(R.id.edtime);
        listview = (ListView) findViewById(R.id.listview);
        add.setOnClickListener(listener);
        edit.setOnClickListener(listener);
        delete.setOnClickListener(listener);
        end.setOnClickListener(listener);
        listview.setOnItemClickListener(ilistener);

        db = new com.example.chaosmid.lifeapp_account.mydb_food(this);
        db.open();
        cursor = db.getALL();
        UpdateAdapter(cursor);
    }

    private void UpdateAdapter(Cursor cursor) {

        if (cursor != null && cursor.getCount() >= 0) {

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_2, cursor, new String[]{
                    "time", "name"}, new int[]{android.R.id.text1,
                    android.R.id.text2});
            listview.setAdapter(adapter);

        }
    }

    private AdapterView.OnItemClickListener ilistener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            myid = arg3;
            showdata(myid);
        }

        private void showdata(long myid) {
            Cursor cursor = db.getsearchid(myid);
            edname.setText(cursor.getString(1));
            edprice.setText(cursor.getString(2));
            edtime.setText(cursor.getString(3));
            Log.d("mylog", "serch：" + cursor.getString(0));
            Log.d("mylog", "serch：" + cursor.getString(1));
            Log.d("mylog", "serch：" + cursor.getString(2));
            Log.d("mylog", "serch：" + cursor.getString(3));
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.btn_add: {
                    String price = edprice.getText().toString();
                    String name = edname.getText().toString();
                    String time = edtime.getText().toString();

                    if(!name.equals("")) {
                        if (db.append(name, price, time) > 0) {
                            cursor = db.getALL();
                            UpdateAdapter(cursor);
                            clearedit();
                        }
                        break;
                    } else {
                        Toast.makeText(WalletActivity_food.this, "請輸入你的標題！",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                case R.id.btn_edit: {
                    String price = edprice.getText().toString();
                    String name = edname.getText().toString();
                    String time = edtime.getText().toString();

                    if (db.updata(myid, name, price, time)) {
                        cursor = db.getALL();
                        UpdateAdapter(cursor);
                        clearedit();
                    }
                }
                break;

                case R.id.btn_delete:
                    if (db.delete(myid)) {
                        cursor = db.getALL();
                        UpdateAdapter(cursor);
                        clearedit();
                    }
                    break;

                case R.id.btn_end:
                    finish();
            }
        }

        private void clearedit() {
            edname.setText("");
            edprice.setText("");
            edtime.setText("");
        }
    };
}
