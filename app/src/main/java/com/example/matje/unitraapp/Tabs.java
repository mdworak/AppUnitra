package com.example.matje.unitraapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.matje.unitraapp.R.id.container;

public class Tabs extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SharedPreferences preferencesDB;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabs, menu);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:

                    Tab1 tab1 = new Tab1 ();
                    return tab1;
                case 1:
                    Tab2 tab2 = new Tab2();
                    return tab2;
                case 2:
                    Tab3 tab3 = new Tab3();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Zdjęcia";
                case 1:
                    return "Specyfikacja";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            final ProgressDialog dialog = new ProgressDialog(Tabs.this);
            dialog.setMessage("Wczytuje listę...");
            dialog.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    preferencesDB = getSharedPreferences("DB", Activity.MODE_PRIVATE);
                    final String mes = preferencesDB.getString("DB", "");
                    Log.i("Tab1",mes);
                    if(mes.equals("zg")) {
                        Intent intent = new Intent(Tabs.this, Kolumny_list.class);
                        startActivity(intent);
                        dialog.dismiss();

                    }
                    else if(mes.equals("ro")){
                        Intent intent = new Intent(Tabs.this, Radiodobiorniki_list.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                    else if(mes.equals("gf")){
                        Intent intent = new Intent(Tabs.this, Gramofony_list.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                    else if(mes.equals("mg")){
                        Intent intent = new Intent(Tabs.this, Magnetofony_list.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                    else if(mes.equals("rm")){
                        Intent intent = new Intent(Tabs.this, Radio_magnetofon_list.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                    else if(mes.equals("rd")){
                        Intent intent = new Intent(Tabs.this, Radiola_list.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                    else if(mes.equals("inne")){
                        Intent intent = new Intent(Tabs.this, Inne_list.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                    else if(mes.equals("wz")){
                        Intent intent = new Intent(Tabs.this, Wzmacniacz_list.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                }
            }).start();
        }
        return super.onKeyDown(keyCode, event);
    }

}
