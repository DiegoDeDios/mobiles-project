package com.example.proyectofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper implements BaseColumns {

    public SQLiteDatabase db;

    private static final String USERTABLE = "Users";
    private static final String COLUMNNAME = "name";
    private static final String COLUMNFATHER = "lastnamefather";
    private static final String COLUMNMOTHER = "lastnamemother";
    private static final String COLUMNMAIL ="email";
    private static final String COLUMNSCHOOL = "schoolgrade";
    private static final String COLUMNAGE = "age";
    private static final String COLUMNPHONE = "phone";

    private static final String CREDENTIALTABLE = "Credentials";
    private static final String COLUMNPASSWORD = "password";

    private static final String DATABASENAME = "mydatabase.db";

    private static final String CREATETABLEUSERS = "CREATE TABLE " +USERTABLE+
            "("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COLUMNNAME+" TEXT NOT NULL, "+
            COLUMNFATHER+" TEXT NOT NULL, "+
            COLUMNMOTHER+" TEXT, "+
            COLUMNAGE+" INTEGER NOT NULL, "+
            COLUMNMAIL+" TEXT NOT NULL, "+
            COLUMNSCHOOL+" TEXT NOT NULL, "+
            COLUMNPHONE+" TEXT NOT NULL);";

    private static final String CREATETABLECREDENTIALS = "CREATE TABLE " +CREDENTIALTABLE+
            "("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COLUMNPASSWORD+" TEXT NOT NULL);";

    public DBHelper(Context context){super(context, DATABASENAME, null, 2);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(CREATETABLEUSERS);
        db.execSQL(CREATETABLECREDENTIALS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERTABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CREDENTIALTABLE);
        onCreate(db);
    }

    public void insertUser(Users user){
        db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMNNAME, user.name);
        cv.put(COLUMNFATHER, user.fatherLastName);
        cv.put(COLUMNMOTHER, user.motherLastName);
        cv.put(COLUMNAGE, user.age);
        cv.put(COLUMNMAIL, user.email);
        cv.put(COLUMNSCHOOL, user.schoolGrade);
        cv.put(COLUMNPHONE, user.phone);

        db.insert(USERTABLE, null, cv);
    }

    public void insertUserCredential(String credential){
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMNPASSWORD, credential);

        db.insert(CREDENTIALTABLE, null, cv);
    }

    //TODO: Hacer un get para checar si el usuario está registrado

    public String getUserName(String email, String password){
        String userName = "";
        db = getReadableDatabase();
        String queryPLates = "SELECT u.name FROM Users u INNER JOIN Credentials c ON u._ID = c._ID WHERE u.email = ? AND c.password = ?";
        Cursor c = db.rawQuery(queryPLates, new String[]{email, password});

        if(c.moveToFirst()){
            do{
                Users u = new Users();
                u.setName(c.getString(c.getColumnIndex(COLUMNNAME)));

                userName = u.name;
            }while(c.moveToNext());
        }
        c.close();
        return userName;
    }

    public String getExistingMail(String email){
        String userName = "";
        db = getReadableDatabase();
        String queryPLates = "SELECT name FROM Users u WHERE u.email = ?";
        Cursor c = db.rawQuery(queryPLates, new String[]{email});

        if(c.moveToFirst()){
            do{
                Users u = new Users();
                u.setName(c.getString(c.getColumnIndex(COLUMNNAME)));

                userName = u.name;
            }while(c.moveToNext());
        }
        c.close();
        return userName;
    }
}
