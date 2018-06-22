package com.example.admin.final1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class mydb_food extends Activity {

    public SQLiteDatabase db = null;
    private final static String DATABASE_NAME = "db1.db";
    private final static String TABLE_NAME = "table02";
    private final static String _ID = "_id";
    private final static String NAME = "name";
    private final static String PRICE = "price";
    private final static String TIME = "time";
    private final static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " (" + _ID + " INTEGER PRIMARY KEY," + NAME + " TEXT," + PRICE
            + " TEXT," + TIME + " TEXT)";

    private Context mCtx = null;
    public mydb_food(Context ctx) {
        this.mCtx = ctx;
    }
    public void open() throws SQLException {
        db = mCtx.openOrCreateDatabase(DATABASE_NAME, 0, null);
        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
        }
    }　

    public void close() throws SQLException {
        db.close();
    }

    public Cursor getALL() {
        Cursor mCursor = db
                .query(TABLE_NAME, new String[] { _ID, NAME, PRICE, TIME }, null,
                        null, null, null, null);
        while (mCursor.moveToNext()){
            Log.d("mylog","getall："+ mCursor.getString(0));
            Log.d("mylog","getall："+ mCursor.getString(1));
            Log.d("mylog","getall："+ mCursor.getString(2));
            Log.d("mylog","getall："+ mCursor.getString(3));
        }
        return mCursor;
    }

    public Cursor getsearchid(long rowid) throws SQLException {
        Cursor mCursor = db.query(TABLE_NAME,
                new String[] { _ID, NAME, PRICE, TIME }, _ID + "=" + rowid, null,
                null, null, null, null);
        if (mCursor != null && mCursor.getCount() > 0) {
            mCursor.moveToNext();
        }
        return mCursor;
    }

    public long append(String name, String price, String time) {
        ContentValues args = new ContentValues();
        args.put(NAME, name);
        args.put(PRICE, price);
        args.put(TIME, time);
        Log.d("mylog","add："+ name);
        Log.d("mylog","add："+ price);
        Log.d("mylog","add："+ time);
        return db.insert(TABLE_NAME, null, args);
    }

    public boolean delete(long rowid) {
        Log.d("mylog","del："+ rowid);
        return db.delete(TABLE_NAME, _ID + "=" + rowid, null) > 0;
    }

    public boolean updata(long rowid, String name, String price, String time) {
        ContentValues args = new ContentValues();
        args.put(NAME, name);
        args.put(PRICE, price);
        args.put(TIME, time);
        Log.d("mylog","updata："+ rowid);
        Log.d("mylog","updata："+ name);
        Log.d("mylog","updata："+ price);
        Log.d("mylog","updata："+ time);
        return db.update(TABLE_NAME, args, _ID + "=" + rowid, null) > 0;
    }
}
