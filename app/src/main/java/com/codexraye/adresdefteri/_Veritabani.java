package com.codexraye.adresdefteri;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class _Veritabani {

    private static final String DATABASE_NAME = "REHBER";
    private static final String TABLE_NAME="rehber";
    private static final int DATABASE_VERSION = 1;

    public static final String KEY="id";
    public static final String ADISOYADI="adisoyadi";
    public static final String TELEFON="telefon";
    public static final String ADRES="adres";

    private final Context contextim;
    private VeritaniHelper veritaniHelper;
    private SQLiteDatabase veritabanim;

    public _Veritabani(Context c) {
        this.contextim=c;
    }

    public void baglantiyiKapat(){
        veritaniHelper.close();
    }
    public _Veritabani baglantiyiAc(){
        veritaniHelper = new VeritaniHelper(contextim);
        veritabanim=veritaniHelper.getWritableDatabase();
        return this;

    }

    public void bilgiyiKaydet(String adisoyadi, String telefonu, String adresi) {
        ContentValues cv = new ContentValues();
        cv.put(ADISOYADI,adisoyadi);
        cv.put(TELEFON,telefonu);
        cv.put(ADRES,adresi);
        veritabanim.insert(TABLE_NAME,null,cv);
    }

    private static class VeritaniHelper extends SQLiteOpenHelper{

        public VeritaniHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS "+
                    TABLE_NAME + " (" +KEY +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ADISOYADI+" TEXT," +TELEFON+" TEXT, "+ADRES+" TEXT "+
                    ")";
            db.execSQL(CREATE_TABLE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
        }
    }





}

