package com.example.matje.unitraapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class Katalogi_list extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Button back;
    Button next;
    int imageIds[] = {R.drawable.katalog01, R.drawable.katalog02, R.drawable.katalog03, R.drawable.katalog04, R.drawable.katalog05};
    int count = imageIds.length;
    int currentIndex = 0;
    TextView strona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalogi_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //back = (Button)findViewById(R.id.back);
        next = (Button)findViewById(R.id.buttonNext);
        strona = (TextView)findViewById(R.id.stronaID);
        final ImageSwitcher imageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher) ;

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(Katalogi_list.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setImageResource(imageIds[0]);
                strona.setText("Strona "+1+"/"+count);

                return imageView;
            }
        });

        Animation animationOut = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        Animation animationIn = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);


        imageSwitcher.setOutAnimation(animationOut);
        imageSwitcher.setInAnimation(animationIn);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentIndex++;
                if (currentIndex == count)
                    currentIndex = 0 ;
                imageSwitcher.setImageResource(imageIds[currentIndex]);
                int tmp = currentIndex+1;
                strona.setText("Strona "+tmp+"/"+count);

               // Toast.makeText(getApplicationContext(), currentIndex+1+"/"+count,
                        //Toast.LENGTH_SHORT).show();


            }
        });

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
        getMenuInflater().inflate(R.menu.katalogi_list, menu);
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
            dialog = new ProgressDialog(Katalogi_list.this);
            dialog.setMessage("Wczytuje...");
            dialog.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Katalogi_list.this,Glowna.class);
                    startActivity(intent);
                    dialog.dismiss();
                }
            }).start();


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
}
