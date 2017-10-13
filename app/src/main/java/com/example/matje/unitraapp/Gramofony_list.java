package com.example.matje.unitraapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class Gramofony_list extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DatabaseGramofon handler;
    private ArrayList <Specyfikacja2Parametry>searchResultstmp = new ArrayList<Specyfikacja2Parametry>();
    private SharedPreferences preferencesID;
    private SharedPreferences preferencesDB;
    DataGramofon data;
    final DatabaseGramofon db = new DatabaseGramofon(this);
    String mLines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gramofony_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        File database=getApplicationContext().getDatabasePath("gf.db");
        handler = new DatabaseGramofon(this);

        if (!database.exists()) {
            Log.i("DatabaseGF", "Not Found");

            mLines = readTxt();
            String[] separated = mLines.split("\n");
            for (int i = 0; i < separated.length; i++) {
                data = new DataGramofon();
                String[] separated1 = separated[i].split(";");
                data.setProducent(separated1[0]);
                data.setTyp(separated1[1]);
                data.setNazwa(separated1[2]);
                data.setLampy(separated1[3]);
                data.setRok_produkcji(separated1[4]);
                data.setZakres_obrotow(separated1[5]);
                data.setRodzaj(separated1[6]);
                data.setWymiary(separated1[7]);
                data.setZdjecie1(separated1[8]);
                data.setZdjecie2(separated1[9]);
                data.setZdjecie3(separated1[10]);
                data.setSchemat(separated1[11]);
                handler.addGF(data);
            }
        } else {
            Log.i("DatabaseGF", "Found");
        }
        final ArrayList<Specyfikacja2Parametry> searchResults = GetSearchResults();


        MyCustomBaseAdapter adapter=new MyCustomBaseAdapter(this,R.id.listaGF ,searchResults);
        //adapter.notifyDataSetChanged();
        final ListView listView = (ListView) findViewById(R.id.listaGF);



        adapter.sort(new Comparator<Specyfikacja2Parametry>() {
            @Override
            public int compare(Specyfikacja2Parametry lhs, Specyfikacja2Parametry rhs) {

                return (lhs.getNazwa()).compareTo(rhs.getNazwa());
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {
                int itemPosition     = position;
                Specyfikacja2Parametry name = (Specyfikacja2Parametry) parent.getItemAtPosition(itemPosition);
                Log.i("Wartosc listy",name.getNazwa());

                Log.i("ID bazy danych", String.valueOf(handler.getID(name.getNazwa())));
                preferencesID = getSharedPreferences("ID", Activity.MODE_PRIVATE);
                SharedPreferences.Editor preferencesEditor = preferencesID.edit();
                preferencesEditor.putString("ID", String.valueOf(handler.getID(name.getNazwa())));
                preferencesEditor.commit();

                preferencesDB = getSharedPreferences("DB", Activity.MODE_PRIVATE);
                SharedPreferences.Editor preferencesEditorDB = preferencesDB.edit();
                preferencesEditorDB.putString("DB","gf");
                preferencesEditorDB.commit();


                final ProgressDialog dialog = new ProgressDialog(Gramofony_list.this);
                dialog.setMessage("Wczytuje specyfikację...");
                dialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Gramofony_list.this, Tabs.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                }).start();


            }
        });

        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);


        final EditText findtext=(EditText)findViewById(R.id.find);

        findtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //String text = findtext.getText().toString().toLowerCase(Locale.getDefault());
                MyCustomBaseAdapter adapter=null;
                // searchResultstmp=new ArrayList<Specyfikacja2Parametry>();
                //text = text.toLowerCase(Locale.getDefault());
                List<Specyfikacja2Parametry> gotowa =new ArrayList<>();

                searchResultstmp.clear();
                gotowa.clear();

                if (editable.length() == 0) {
                    adapter=new MyCustomBaseAdapter(Gramofony_list.this,R.id.listaRO ,searchResults);
                } else {
                    for (int i= 0;i<searchResults.size();i++) {
                        Specyfikacja2Parametry wp = searchResults.get(i);
                        if ((wp.getNazwa().toLowerCase(Locale.getDefault()).contains(editable)) || (wp.getModel().toLowerCase(Locale.getDefault()).contains(editable))) {
                            gotowa.add(wp);

                            Log.i("Wyszukiwarka", wp.getNazwa());
                            //adapter=new MyCustomBaseAdapter(Kolumny_list.this,R.id.listaZG ,searchResultstmp);
                        }

                    }
                    searchResultstmp=new ArrayList<Specyfikacja2Parametry>(gotowa);

                }
                //searchResultstmp=new ArrayList<Specyfikacja2Parametry>(new LinkedHashSet<Specyfikacja2Parametry>(searchResultstmp));
                if(adapter==null)
                    adapter=new MyCustomBaseAdapter(Gramofony_list.this,R.id.listaGF ,searchResultstmp);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);


            }
        });

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gramofony_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private String readTxt(){

        InputStream inputStream = getResources().openRawResource(R.raw.gfbaza);
        System.out.println(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString();
    }


    private ArrayList<Specyfikacja2Parametry> GetSearchResults(){
        ArrayList<Specyfikacja2Parametry> results = new ArrayList<Specyfikacja2Parametry>();


        List<DataGramofon> Lista = handler.getAll();
        String[] ListaLV = new String[Lista.size()];
        for (int i = 0; i < Lista.size(); i++) {
            String a= Lista.get(i).getNazwa();
            String b= Lista.get(i).getProducent();
            Specyfikacja2Parametry srl = new Specyfikacja2Parametry();
            srl.setNazwa(a);
            srl.setModel(b);
            results.add(srl);
        }

        return results;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            final ProgressDialog dialog = new ProgressDialog(Gramofony_list.this);
            dialog.setMessage("Wczytuje listę...");
            dialog.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Gramofony_list.this, Glowna.class);
                    startActivity(intent);
                    dialog.dismiss();
                }
            }).start();
        }
        return super.onKeyDown(keyCode, event);
    }

}

