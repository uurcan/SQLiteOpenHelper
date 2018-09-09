package com.example.celebi.sqliteopenhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler {

    myDBHelper myHelper;
    public MyDBHandler(Context context)
    {
        myHelper = new myDBHelper(context);
    }

    public long insertData(String name,String password){
        SQLiteDatabase db = myHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(myDBHelper.COL_USERNAME,name);
        cv.put(myDBHelper.COL_PASSWORD,password);
        long id = db.insert(myDBHelper.TABLE_NAME,null,cv);
        return id;
    }

    public int delete(String userName)
    {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        String[] value = {userName};
        int count  = db.delete(myDBHelper.TABLE_NAME,myDBHelper.COL_USERNAME + " = ?",value);
        return count;
    }

    public int updateName(String oldName,String newName)
    {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(myDBHelper.COL_USERNAME,newName);

        String[] newValue = {oldName};
        int result = db.update(myDBHelper.TABLE_NAME,cv,myDBHelper.COL_USERNAME+"= ?",newValue);
        return result;
    }

    public String getData()
    {
        SQLiteDatabase db = myHelper.getWritableDatabase();

        String[] columns = {myDBHelper.COL_ID,myDBHelper.COL_USERNAME,myDBHelper.COL_PASSWORD};

        Cursor cursor = db.query(myDBHelper.TABLE_NAME,columns,
                null,null,null,null,null);
        StringBuffer buffer = new StringBuffer();

        while(cursor.moveToNext())
        {
            int colId = cursor.getInt(cursor.getColumnIndex(myDBHelper.COL_ID));
            String colUsername = cursor.getString(cursor.getColumnIndex(myDBHelper.COL_USERNAME));
            String colPassword = cursor.getString(cursor.getColumnIndex(myDBHelper.COL_PASSWORD));

            buffer.append(colId + " " + colUsername + " " + colPassword + "\n");
        }
        return buffer.toString();
    }
    static class myDBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "myDatabase.db";
    private static final int DATABASE_VERS = 1;

    private static final String TABLE_NAME = "dbTable";
    private static final String COL_ID = "_id";
    private static final String COL_USERNAME = "username";
    private static final String COL_PASSWORD = "password";

    private static final String CREATE_TABLE = "CREATE TABLE " + "TABLE_NAME" + " (" +
        COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_USERNAME + "TEXT, " + COL_PASSWORD + "TEXT " + ");";

    private  static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;

    private  Context context;
    public myDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERS);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        }
        catch (Exception e)
        {
            Message.message(context,""+e);
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
        catch (Exception e)
        {
            Message.message(context,""+e);
            }
        }
    }
}
