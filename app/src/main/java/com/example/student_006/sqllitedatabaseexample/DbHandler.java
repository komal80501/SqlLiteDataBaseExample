package com.example.student_006.sqllitedatabaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbHandler extends SQLiteOpenHelper{
    Context context;
    // Database Name
    private static final String DATABASENAME="VisitorsDb";
    private  static final int DATABASEVERSION=1;

    public DbHandler(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }


    public static final String TABLE_VISITORS="visitors";
    public static final String KEY_ID="visitor_id";
    public static final String KEY_VISITORS_NAME="visitors_name";
    public static final String KEY_EMAIL="visitors_email";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_VISITOR_TABLE = "CREATE TABLE " + TABLE_VISITORS +"("
                + KEY_ID+" INTEGER PRIMARY KEY ,"
                +KEY_VISITORS_NAME+" TEXT,"
                +KEY_EMAIL+" TEXT" +")";
        sqLiteDatabase.execSQL(CREATE_VISITOR_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Boolean insert_visitors(Visitors visitor)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_VISITORS_NAME, visitor.getName());
        contentValues.put(KEY_EMAIL,visitor.getEmail());
        long result=db.insert(TABLE_VISITORS,null,contentValues);
            if (result==-1)
                return false;
            else
                return true;
    }

    public Cursor getAlldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res= db.rawQuery("SELECT * FROM "+TABLE_VISITORS,null);
        return res;
    }


    public ArrayList<Visitors> getallvisitors(){
        ArrayList<Visitors> visitors= new ArrayList<Visitors>();

        String SelectrQuery="SELECT * from "+TABLE_VISITORS;

        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor= db.rawQuery(SelectrQuery,null);

        if(cursor.moveToNext())
        {
            do {

                Visitors visitors1= new Visitors();
                visitors1.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                visitors1.setName(cursor.getString(cursor.getColumnIndex(KEY_VISITORS_NAME)));
                visitors1.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));

                visitors.add(visitors1);

            }while (cursor.moveToNext());
        }
            db.close();
            return visitors;
    }

}
