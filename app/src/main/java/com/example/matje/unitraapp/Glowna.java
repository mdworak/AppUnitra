package com.example.matje.unitraapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RawRes;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.util.Comparator;

public class Glowna extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glowna);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        String[] Lista={"Radioodbiorniki","Gramofony","Zestawy Głośnikowe","Magnetofony","Radio-magnetofony","Radiola","Wzmacniacze","Inne"};
        final ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.itemlistviewkategori, Lista);

        final ListView listView = (ListView)findViewById(R.id.listaKategorii);
        /*adapter.sort(new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {

                return (lhs).compareTo(rhs);
            }
        });
        */
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition     = position;
                final ProgressDialog dialog;
                switch(itemPosition) {
                    case 0:
                        dialog = new ProgressDialog(Glowna.this);
                        dialog.setMessage("Wczytuje listę...");
                        dialog.show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(Glowna.this, Radiodobiorniki_list.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        }).start();
                        break;
                    case 1:
                        dialog = new ProgressDialog(Glowna.this);
                        dialog.setMessage("Wczytuje listę...");
                        dialog.show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(Glowna.this, Gramofony_list.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        }).start();
                        break;
                    case 2:
                        dialog = new ProgressDialog(Glowna.this);
                        dialog.setMessage("Wczytuje listę...");
                        dialog.show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(Glowna.this, Kolumny_list.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        }).start();
                        break;
                    case 3:
                        dialog = new ProgressDialog(Glowna.this);
                        dialog.setMessage("Wczytuje listę...");
                        dialog.show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(Glowna.this, Magnetofony_list.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        }).start();
                        break;
                    case 4:
                        dialog = new ProgressDialog(Glowna.this);
                        dialog.setMessage("Wczytuje listę...");
                        dialog.show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(Glowna.this, Radio_magnetofon_list.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        }).start();
                        break;
                    case 5:
                        dialog = new ProgressDialog(Glowna.this);
                        dialog.setMessage("Wczytuje listę...");
                        dialog.show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(Glowna.this, Radiola_list.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        }).start();
                        break;
                    case 7:
                        dialog = new ProgressDialog(Glowna.this);
                        dialog.setMessage("Wczytuje listę...");
                        dialog.show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(Glowna.this,Inne_list.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        }).start();
                        break;
                    case 6:
                        dialog = new ProgressDialog(Glowna.this);
                        dialog.setMessage("Wczytuje listę...");
                        dialog.show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(Glowna.this,Wzmacniacz_list.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        }).start();
                        break;
                    default:
                        break;

                }





            }
        });




        Button btest = (Button)findViewById(R.id.test);
        btest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Glowna.this, Tabs.class);
                startActivity(intent);
            }
        });

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
        getMenuInflater().inflate(R.menu.glowna, menu);
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
        final ProgressDialog dialog;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_kolekcja) {


        } else if (id == R.id.nav_compare) {

        } else if (id == R.id.nav_katalog) {
            dialog = new ProgressDialog(Glowna.this);
            dialog.setMessage("Wczytuje katalogi...");
            dialog.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Glowna.this,Katalogi_list.class);
                    startActivity(intent);
                    dialog.dismiss();
                }
            }).start();




        } /*else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
