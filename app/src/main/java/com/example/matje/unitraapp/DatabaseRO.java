package com.example.matje.unitraapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Matje on 05.09.2017.
 */

public class DatabaseRO extends SQLiteOpenHelper {
    public DatabaseRO(Context context) {
        super(context, "ro.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table ro(" +
                        "id integer primary key autoincrement," +
                        "nazwa text," +
                        "producent text," +
                        "rodzaj text," +
                        "moc text," +
                        "lampy text," +
                        "rok_produkcji text," +
                        "zasilanie text," +
                        "wymiary text," +
                        "waga text," +
                        "schemat text,"+
                        "zdjecie1 text," +
                        "zdjecie2 text," +
                        "zdjecie3 text);" +
                        "");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public void addRO(DataRO data){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nazwa", data.getNazwa());
        values.put("producent", data.getProducent());
        values.put("rodzaj", data.getRodzaj());
        values.put("moc", data.getMoc());
        values.put("lampy", data.getLampy());
        values.put("rok_produkcji", data.getRok_produkcji());
        values.put("zasilanie", data.getZasilanie());
        values.put("wymiary", data.getWymiary());
        values.put("waga", data.getWaga());
        values.put("schemat", data.getSchemat());
        values.put("zdjecie1", data.getZdjecie1());
        values.put("zdjecie2", data.getZdjecie2());
        values.put("zdjecie3", data.getZdjecie3());

        db.insertOrThrow("ro",null, values);
    }

    public List<DataRO> getAll(){
        List<DataRO> contacts = new LinkedList<DataRO>();
        String[] columns={"nazwa","producent","rodzaj","moc","lampy","rok_produkcji","zasilanie","wymiary","waga"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =db.query("ro",columns,null,null,null,null,null,null);
        while(cursor.moveToNext()){
            DataRO data = new DataRO();
            data.setNazwa(cursor.getString(0));
            data.setProducent(cursor.getString(1));
            data.setRodzaj(cursor.getString(2));
            data.setMoc(cursor.getString(3));
            data.setLampy(cursor.getString(4));
            data.setRok_produkcji(cursor.getString(5));
            data.setZasilanie(cursor.getString(6));
            data.setWymiary(cursor.getString(7));
            data.setWaga(cursor.getString(8));
            contacts.add(data);
        }
        return contacts;
    }

    public List<DataRO> getNazwa(){
        List<DataRO> contacts = new LinkedList<DataRO>();
        String[] columns={"nazwa"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =db.query("ro",columns,null,null,null,null,null,null);
        while(cursor.moveToNext()){
            DataRO data = new DataRO();
            data.setNazwa(cursor.getString(0));
            contacts.add(data);
        }
        return contacts;
    }

    public String getModel(String name){
        SQLiteDatabase db = getReadableDatabase();
        String[] columns={"model"};
        String args[]={name+""};
        Cursor cursor=db.query("ro",columns,"nazwa=?",args,null,null,null,null);
        String model=null;
        if(cursor!=null){
            cursor.moveToFirst();
            model = cursor.getString(0);
        }
        return model;
    }

    public String getNazwa(String id){
        SQLiteDatabase db = getReadableDatabase();
        String[] columns={"nazwa"};
        String args[]={id+""};
        Cursor cursor=db.query("ro",columns,"id=?",args,null,null,null,null);
        String model=null;
        if(cursor!=null){
            cursor.moveToFirst();
            model = cursor.getString(0);
        }
        return model;
    }



    public List<DataRO> getDane(String id){
        List<DataRO> contacts = new LinkedList<DataRO>();
        String[] columns={"nazwa","producent","rodzaj","moc","lampy","rok_produkcji","zasilanie","wymiary","waga"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT nazwa,producent,rodzaj,moc,lampy,rok_produkcji,zasilanie,wymiary,waga  FROM  ro WHERE id='"+id+"'" , null);

        while(cursor.moveToNext()) {
            DataRO data = new DataRO();
            data.setNazwa(cursor.getString(0));
            data.setProducent(cursor.getString(1));
            data.setRodzaj(cursor.getString(2));
            data.setMoc(cursor.getString(3));
            data.setLampy(cursor.getString(4));
            data.setRok_produkcji(cursor.getString(5));
            data.setZasilanie(cursor.getString(6));
            data.setWymiary(cursor.getString(7));
            data.setWaga(cursor.getString(8));
            contacts.add(data);
        }
        return contacts;
    }

    public int getID(String nazwa) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor mCursor = db.rawQuery(
                "SELECT id  FROM  ro WHERE nazwa='"+nazwa+"'" , null);

        mCursor.moveToFirst();
        return mCursor.getInt(0);
    }

    public String[] getZdjecia(String id){
        String [] photos = new String[3];
        String[] columns={"zdjecie1","zdjecie2","zdjecie3"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT zdjecie1,zdjecie2,zdjecie3  FROM  ro WHERE id='"+id+"'" , null);
        cursor.moveToFirst();
        photos[0]=cursor.getString(0);
        photos[1]=cursor.getString(1);
        photos[2]=cursor.getString(2);
        return photos;
    }




}

