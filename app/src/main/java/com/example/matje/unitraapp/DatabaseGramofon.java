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

public class DatabaseGramofon extends SQLiteOpenHelper {
    public DatabaseGramofon(Context context) {
        super(context, "gf.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table gf(" +
                        "id integer primary key autoincrement," +
                        "nazwa text," +
                        "producent text," +
                        "typ text," +
                        "lampy text," +
                        "rok_produkcji text," +
                        "zakres_obrotow text," +
                        "wymiary text," +
                        "rodzaj text,"+
                        "schemat text,"+
                        "zdjecie1 text," +
                        "zdjecie2 text," +
                        "zdjecie3 text);" +
                        "");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public void addGF(DataGramofon data){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nazwa", data.getNazwa());
        values.put("producent", data.getProducent());
        values.put("typ", data.getTyp());
        values.put("lampy", data.getLampy());
        values.put("rok_produkcji", data.getRok_produkcji());
        values.put("zakres_obrotow", data.getZakres_obrotow());
        values.put("wymiary", data.getWymiary());
        values.put("rodzaj", data.getRodzaj());
        values.put("schemat", data.getSchemat());
        values.put("zdjecie1", data.getZdjecie1());
        values.put("zdjecie2", data.getZdjecie2());
        values.put("zdjecie3", data.getZdjecie3());

        db.insertOrThrow("gf",null, values);
    }

    public List<DataGramofon> getAll(){
        List<DataGramofon> contacts = new LinkedList<DataGramofon>();
        String[] columns={"nazwa","producent","typ","lampy","rok_produkcji","zakres_obrotow","wymiary","rodzaj"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =db.query("gf",columns,null,null,null,null,null,null);
        while(cursor.moveToNext()){
            DataGramofon data = new DataGramofon();
            data.setNazwa(cursor.getString(0));
            data.setProducent(cursor.getString(1));
            data.setTyp(cursor.getString(2));
            data.setLampy(cursor.getString(3));
            data.setRok_produkcji(cursor.getString(4));
            data.setZakres_obrotow(cursor.getString(5));
            data.setWymiary(cursor.getString(6));
            data.setRodzaj(cursor.getString(7));
            contacts.add(data);
        }
        return contacts;
    }

    public List<DataGramofon> getNazwa(){
        List<DataGramofon> contacts = new LinkedList<DataGramofon>();
        String[] columns={"nazwa"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =db.query("gf",columns,null,null,null,null,null,null);
        while(cursor.moveToNext()){
            DataGramofon data = new DataGramofon();
            data.setNazwa(cursor.getString(0));
            contacts.add(data);
        }
        return contacts;
    }


    public String getNazwa(String id){
        SQLiteDatabase db = getReadableDatabase();
        String[] columns={"nazwa"};
        String args[]={id+""};
        Cursor cursor=db.query("gf",columns,"id=?",args,null,null,null,null);
        String model=null;
        if(cursor!=null){
            cursor.moveToFirst();
            model = cursor.getString(0);
        }
        return model;
    }



    public List<DataGramofon> getDane(String id){
        List<DataGramofon> contacts = new LinkedList<DataGramofon>();
        String[] columns={"nazwa","producent","typ","lampy","rok_produkcji","zakres_obrotow","wymiary","rodzaj"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT nazwa,producent,typ,lampy,rok_produkcji,zakres_obrotow,wymiary,rodzaj  FROM  gf WHERE id='"+id+"'" , null);

        while(cursor.moveToNext()) {
            DataGramofon data = new DataGramofon();
            data.setNazwa(cursor.getString(0));
            data.setProducent(cursor.getString(1));
            data.setTyp(cursor.getString(2));
            data.setLampy(cursor.getString(3));
            data.setRok_produkcji(cursor.getString(4));
            data.setZakres_obrotow(cursor.getString(5));
            data.setWymiary(cursor.getString(6));
            data.setRodzaj(cursor.getString(7));
            contacts.add(data);
        }
        return contacts;
    }

    public int getID(String nazwa) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor mCursor = db.rawQuery(
                "SELECT id  FROM  gf WHERE nazwa='"+nazwa+"'" , null);

        mCursor.moveToFirst();
        return mCursor.getInt(0);
    }

    public String[] getZdjecia(String id){
        String [] photos = new String[3];
        String[] columns={"zdjecie1","zdjecie2","zdjecie3"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT zdjecie1,zdjecie2,zdjecie3  FROM  gf WHERE id='"+id+"'" , null);
        cursor.moveToFirst();
        photos[0]=cursor.getString(0);
        photos[1]=cursor.getString(1);
        photos[2]=cursor.getString(2);
        return photos;
    }



}

