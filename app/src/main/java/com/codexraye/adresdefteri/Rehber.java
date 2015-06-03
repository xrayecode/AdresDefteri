package com.codexraye.adresdefteri;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by akkaraman on 1.6.2015.
 */
public class Rehber {
    public static Context defaulContext;

    private static final String TABLE_NAME = "REHBER";
    private static final String[] COLUMNS = new String[] { "ID","ADISOYAD","TELEFON","ADRES"};
    public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS "	+ TABLE_NAME;
    public static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + " (" + COLUMNS[0] + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                                                                   + COLUMNS[1] + " TEXT, "
                                                                                                   + COLUMNS[2] + " TEXT, "
                                                                                                   + COLUMNS[3]	+ " TEXT)";
    public String id="";
    public String adisoyadi="";
    public String telefon="";
    public String adresi="";

    private static SQLiteDatabase dataBase;

    public static SQLiteDatabase getDB() {
        if (dataBase == null) {
            dataBase = (new Veritabani(defaulContext)).getWritableDatabase();
        }
        return dataBase;
    }

    /* INSERT START*/
    public void insert(ContentValues cv) {
        SQLiteDatabase db = this.getDB();
        db.beginTransaction();
        try {
            db.insert(TABLE_NAME, null, cv);
            db.setTransactionSuccessful();
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }


    public ContentValues insertContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMNS[1],adisoyadi);
        contentValues.put(COLUMNS[2],telefon);
        contentValues.put(COLUMNS[3],adresi);
        return contentValues;
    }

    /* UPDATE START*/
    public void update(ContentValues cv) {
        SQLiteDatabase db = this.getDB();
        db.beginTransaction();
        try {
            db.update(TABLE_NAME, cv, where(), null);
            db.setTransactionSuccessful();
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

    }

    public ContentValues updateContentValues() {
        ContentValues contentValues = new ContentValues();
        if (!adisoyadi.equals("")) contentValues.put(COLUMNS[1],adisoyadi);
        if (!telefon.equals("")) contentValues.put(COLUMNS[2],telefon);
        if (!adresi.equals("")) contentValues.put(COLUMNS[3],adresi);
        return contentValues;
    }

    /* START READ*/
    public Cursor readOne(){
        SQLiteDatabase db = this.getDB();
        try {
            Cursor cursor = db.query(TABLE_NAME, columns(), where(), null, null, null, null);
            if (cursor.moveToNext()) {
                return cursor;
            }
            return null;
        } finally {
        }
    }

    public Cursor readAll() {
        SQLiteDatabase db = this.getDB();
        try {
            Cursor cursor = db.query(TABLE_NAME, columns(), null, null, null, null, null);
            if (cursor.moveToNext()) {
                return cursor;
            }
            return null;
        } finally {
        }

    }


    /* WHERE */
    private String where() {
        return COLUMNS[0] + " = " + this.id;
    }

    /* COLUMNS*/
    private String[] columns() {
        return COLUMNS;
    }

    /* ORDER BY */
    private String orderBy() {
        return COLUMNS[0];
    }

    // GETTER SETTER
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAdisoyadi() {
        return adisoyadi;
    }
    public void setAdisoyadi(String adisoyadi) {
        this.adisoyadi = adisoyadi;
    }
    public String getTelefon() {
        return telefon;
    }
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    public String getAdresi() {
        return adresi;
    }
    public void setAdresi(String adresi) {
        this.adresi = adresi;
    }

}
