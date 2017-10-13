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

public class DatabaseZG extends SQLiteOpenHelper {
    public DatabaseZG(Context context) {
        super(context, "zg.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table zg(" +
                        "id integer primary key autoincrement," +
                        "nazwa text," +
                        "model text," +
                        "moc_znamionowa text," +
                        "moc_maksymalna text," +
                        "efektywnosc text," +
                        "impedancja text," +
                        "pasmo_przenoszenia text," +
                        "konstrukcja text," +
                        "wymiary text," +
                        "waga text," +
                        "niskotonowy text," +
                        "sredniotonowy text," +
                        "wysokotonowy text," +
                        "zdjecie1 text," +
                        "zdjecie2 text," +
                        "zdjecie3 text);" +
                        "");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void addZg(DataZG data){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nazwa", data.getNazwa());
        values.put("model", data.getModel());
        values.put("moc_znamionowa", data.getMoc_znamionowa());
        values.put("moc_maksymalna", data.getMoc_maksymalna());
        values.put("efektywnosc", data.getEfektywnosc());
        values.put("impedancja", data.getImpedancja());
        values.put("pasmo_przenoszenia", data.getPasmo_przenoszenia());
        values.put("konstrukcja", data.getKonstrukcja());
        values.put("wymiary", data.getWymiary());
        values.put("waga", data.getWaga());
        values.put("niskotonowy", data.getNiskotonowy());
        values.put("sredniotonowy", data.getSredniotonowy());
        values.put("wysokotonowy", data.getWysokotonowy());
        values.put("zdjecie1", data.getZdjecie1());
        values.put("zdjecie2", data.getZdjecie2());
        values.put("zdjecie3", data.getZdjecie3());
        db.insertOrThrow("zg",null, values);
    }

    public List<DataZG> getAll(){
        List<DataZG> contacts = new LinkedList<DataZG>();
        String[] columns={"nazwa","model","moc_znamionowa","moc_maksymalna","efektywnosc","impedancja","pasmo_przenoszenia","konstrukcja","wymiary","waga","niskotonowy","sredniotonowy","wysokotonowy"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =db.query("zg",columns,null,null,null,null,null,null);
        while(cursor.moveToNext()){
            DataZG data = new DataZG();
            data.setNazwa(cursor.getString(0));
            data.setModel(cursor.getString(1));
            data.setMoc_znamionowa(cursor.getString(2));
            data.setMoc_maksymalna(cursor.getString(3));
            data.setEfektywnosc(cursor.getString(4));
            data.setImpedancja(cursor.getString(5));
            data.setPasmo_przenoszenia(cursor.getString(6));
            data.setKonstrukcja(cursor.getString(7));
            data.setWymiary(cursor.getString(8));
            data.setWaga(cursor.getString(9));
            data.setNiskotonowy(cursor.getString(10));
            data.setSredniotonowy(cursor.getString(11));
            data.setWysokotonowy(cursor.getString(12));
            contacts.add(data);
        }
        return contacts;
    }

    public List<DataZG> getNazwa(){
        List<DataZG> contacts = new LinkedList<DataZG>();
        String[] columns={"nazwa"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =db.query("zg",columns,null,null,null,null,null,null);
        while(cursor.moveToNext()){
            DataZG data = new DataZG();
            data.setNazwa(cursor.getString(0));
            contacts.add(data);
        }
        return contacts;
    }

    public String[] getZdjecia(String id){
        String [] photos = new String[3];
        String[] columns={"zdjecie1","zdjecie2","zdjecie3"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT zdjecie1,zdjecie2,zdjecie3  FROM  zg WHERE id='"+id+"'" , null);
        cursor.moveToFirst();
        photos[0]=cursor.getString(0);
        photos[1]=cursor.getString(1);
        photos[2]=cursor.getString(2);
        return photos;
    }

    public String getNazwa(String id){
        SQLiteDatabase db = getReadableDatabase();
        String[] columns={"nazwa"};
        String args[]={id+""};
        Cursor cursor=db.query("zg",columns,"id=?",args,null,null,null,null);
        String model=null;
        if(cursor!=null){
            cursor.moveToFirst();
            model = cursor.getString(0);
        }
        return model;
    }



    public List<DataZG> getDane(String id){
        List<DataZG> contacts = new LinkedList<DataZG>();
        String[] columns={"nazwa","model","moc_znamionowa","moc_maksymalna","efektywnosc","impedancja","pasmo_przenoszenia","konstrukcja","wymiary","waga","niskotonowy","sredniotonowy","wysokotonowy"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT nazwa,model,moc_znamionowa,moc_maksymalna,efektywnosc,impedancja,pasmo_przenoszenia,konstrukcja,wymiary,waga,niskotonowy,sredniotonowy,wysokotonowy  FROM  zg WHERE id='"+id+"'" , null);

        while(cursor.moveToNext()) {
            DataZG data = new DataZG();
            data.setNazwa(cursor.getString(0));
            data.setModel(cursor.getString(1));
            data.setMoc_znamionowa(cursor.getString(2));
            data.setMoc_maksymalna(cursor.getString(3));
            data.setEfektywnosc(cursor.getString(4));
            data.setImpedancja(cursor.getString(5));
            data.setPasmo_przenoszenia(cursor.getString(6));
            data.setKonstrukcja(cursor.getString(7));
            data.setWymiary(cursor.getString(8));
            data.setWaga(cursor.getString(9));
            data.setNiskotonowy(cursor.getString(10));
            data.setSredniotonowy(cursor.getString(11));
            data.setWysokotonowy(cursor.getString(12));

            contacts.add(data);
        }
        return contacts;
    }



    public void deleteKey(String numer){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("contact","numer" + "=" + numer, null);
    }

    public int getIDFromNazwa(String nazwa) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor mCursor = db.rawQuery(
                "SELECT id  FROM  zg WHERE nazwa='"+nazwa+"'" , null);

        mCursor.moveToFirst();
        return mCursor.getInt(0);
    }
    public int getIDFromModel(String nazwa) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor mCursor = db.rawQuery(
                "SELECT id  FROM  zg WHERE model='"+nazwa+"'" , null);

        mCursor.moveToFirst();
        return mCursor.getInt(0);
    }



}

