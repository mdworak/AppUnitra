package com.example.matje.unitraapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Matje on 31.08.2017.
 */

public class Tab1 extends Fragment {
    String URL ;
    String URL1 ;
    String URL2 ;
    String []Listazdjec=null;
    ProgressDialog mProgressDialog;
    private SharedPreferences preferencesID;
    private SharedPreferences preferencesDB;
    private DatabaseZG handlerZG;
    private DatabaseRO handlerRO;
    private DatabaseMG handlerMG;
    private DatabaseGramofon handlerGF;
    private DatabaseRM handlerRM;
    private DatabaseRD handlerRD;
    private DatabaseWZ handlerWZ;
    private DatabaseInne handlerInne;
    ViewPager mViewPager;
    TextView t1;
    TextView t2;
    Bitmap []sliderImagesId;// = new Bitmap[3];

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);

        t1 = (TextView)rootView.findViewById(R.id.texterror);
        t2=(TextView)rootView.findViewById(R.id.textNophoto);
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        preferencesDB = getActivity().getSharedPreferences("DB", Activity.MODE_PRIVATE);
        final String mes = preferencesDB.getString("DB", "");
        Log.i("Tab1",mes);
        preferencesID = getActivity().getSharedPreferences("ID", Activity.MODE_PRIVATE);
        final String id = preferencesID.getString("ID", "");
        Log.i("Tab1",id);
        if(mes.equals("zg")) {
            handlerZG = new DatabaseZG(getActivity());
            Listazdjec=handlerZG.getZdjecia(id);
            TextView t1 = (TextView) rootView.findViewById(R.id.nazwa);
            t1.setText(handlerZG.getNazwa(id));
        }
        else if(mes.equals("ro")){
            handlerRO = new DatabaseRO(getActivity());
            Listazdjec=handlerRO.getZdjecia(id);
            TextView t1 = (TextView) rootView.findViewById(R.id.nazwa);
            t1.setText(handlerRO.getNazwa(id));
        }
        else if(mes.equals("gf")){
            handlerGF = new DatabaseGramofon(getActivity());
            Listazdjec=handlerGF.getZdjecia(id);
            TextView t1 = (TextView) rootView.findViewById(R.id.nazwa);
            t1.setText(handlerGF.getNazwa(id));
        }
        else if(mes.equals("mg")){
            handlerMG = new DatabaseMG(getActivity());
            Listazdjec=handlerMG.getZdjecia(id);
            TextView t1 = (TextView) rootView.findViewById(R.id.nazwa);
            t1.setText(handlerMG.getNazwa(id));
        }
        else if(mes.equals("rm")){
            handlerRM = new DatabaseRM(getActivity());
            Listazdjec=handlerRM.getZdjecia(id);
            TextView t1 = (TextView) rootView.findViewById(R.id.nazwa);
            t1.setText(handlerRM.getNazwa(id));
        }
        else if(mes.equals("rd")){
            handlerRD = new DatabaseRD(getActivity());
            Listazdjec=handlerRD.getZdjecia(id);
            TextView t1 = (TextView) rootView.findViewById(R.id.nazwa);
            t1.setText(handlerRD.getNazwa(id));
        }
        else if(mes.equals("inne")){
            handlerInne = new DatabaseInne(getActivity());
            Listazdjec=handlerInne.getZdjecia(id);
            TextView t1 = (TextView) rootView.findViewById(R.id.nazwa);
            t1.setText(handlerInne.getNazwa(id));
        }
        else if(mes.equals("wz")){
            handlerWZ = new DatabaseWZ(getActivity());
            Listazdjec=handlerWZ.getZdjecia(id);
            TextView t1 = (TextView) rootView.findViewById(R.id.nazwa);
            t1.setText(handlerWZ.getNazwa(id));
        }




        ImageSliderAdapter adapterView = new ImageSliderAdapter(getActivity(),sliderImagesId);
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewPageAndroid);


        if ((info == null || !info.isConnected() || !info.isAvailable())) {
            t1.setVisibility(View.VISIBLE);
            Toast.makeText(getContext(), "Nie można pobrać galerii. Sprawdź połączenie z internetem.",Toast.LENGTH_SHORT).show();
            mViewPager.setVisibility(View.INVISIBLE);

        } else {
            Log.i("Listazdjec",Listazdjec[0]);
            if(Listazdjec[0].contains("brak")){
                Log.i("Listazdjec",Listazdjec[0]);
                t2.setVisibility(View.VISIBLE);
                return rootView;
            }
            else if(Listazdjec[1].contains("brak")){
                sliderImagesId=new Bitmap[1];
                URL=Listazdjec[0];
                new Tab1.DownloadImage().execute(URL);
                return rootView;
            }
            else if(Listazdjec[2].contains("brak")){
                sliderImagesId=new Bitmap[2];
                URL=Listazdjec[0];
                URL1=Listazdjec[1];
                new Tab1.DownloadImage().execute(URL);
                new Tab1.DownloadImage1().execute(URL1);
                return rootView;

            }
            else
            {
                sliderImagesId=new Bitmap[3];

            }//return rootView;


            t1.setVisibility(View.INVISIBLE);
            URL=Listazdjec[0];
            URL1=Listazdjec[1];
            URL2=Listazdjec[2];

            new Tab1.DownloadImage().execute(URL);
            new Tab1.DownloadImage1().execute(URL1);
            new Tab1.DownloadImage2().execute(URL2);
            mViewPager.setVisibility(View.VISIBLE);


        }

        return rootView;
    }


    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(getActivity());
            // Set progressdialog title
            mProgressDialog.setTitle("Pobieram zdjęcia");
            // Set progressdialog message
            mProgressDialog.setMessage("Pobieranie");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            //mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {

            String imageURL = URL[0];

            Bitmap bitmap = null;
            try {
                // Download Image from URL
                InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            sliderImagesId[0]=result;
            Log.i("Photo1", String.valueOf(sliderImagesId[0]));


            ImageSliderAdapter adapterView = new ImageSliderAdapter(getActivity(),sliderImagesId);
            mViewPager.setAdapter(adapterView);
            mProgressDialog.dismiss();
        }
    }

    public class DownloadImage1 extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(getActivity());
            // Set progressdialog title
            mProgressDialog.setTitle("Pobieram zdjęcia");
            // Set progressdialog message
            mProgressDialog.setMessage("Pobieranie");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
           // mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {

            String imageURL = URL[0];

            Bitmap bitmap = null;
            try {
                // Download Image from URL
                InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            Log.i("SetPhoto2","ustawione");
            sliderImagesId[1]=result;
            Log.i("Photo2", String.valueOf(sliderImagesId[1]));
            ImageSliderAdapter adapterView = new ImageSliderAdapter(getActivity(),sliderImagesId);
            mViewPager.setAdapter(adapterView);
           // mProgressDialog.dismiss();
        }
    }


    public class DownloadImage2 extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(getActivity());
            // Set progressdialog title
            mProgressDialog.setTitle("Pobieram zdjęcia");
            // Set progressdialog message
            mProgressDialog.setMessage("Pobieranie");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            //mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {

            String imageURL = URL[0];

            Bitmap bitmap = null;
            try {
                // Download Image from URL
                InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {

                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {

            //image1.setImageBitmap(result);
            //image2.setImageBitmap(result);
            //image3.setImageBitmap(result);
            sliderImagesId[2]=result;
            Log.i("Photo3", String.valueOf(sliderImagesId[2]));
            ImageSliderAdapter adapterView = new ImageSliderAdapter(getActivity(),sliderImagesId);
            mViewPager.setAdapter(adapterView);
            //sliderImagesId[1]=result;
            // sliderImagesId[2]=result;
            //mProgressDialog.dismiss();
        }
    }



}

