package com.example.matje.unitraapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Matje on 31.08.2017.
 */

public class Tab2 extends Fragment {

    private SharedPreferences preferencesID;
    private SharedPreferences preferencesDB;
    private DatabaseZG handlerZG;
    private DatabaseRO handlerRO;
    private DatabaseGramofon handlerGF;
    private DatabaseMG handlerMG;
    private DatabaseRM handlerRM;
    private DatabaseRD handlerRD;
    private DatabaseInne handlerInne;
    private DatabaseWZ handlerWZ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);

        ArrayList<Specyfikacja2Parametry> searchResults = GetSearchResults();

        final ListView lv1 = (ListView)rootView.findViewById(R.id.specyfikacja);
        MyCustomBaseAdapter adapter = new MyCustomBaseAdapter(getContext(),R.layout.lw_row_test, searchResults);
        lv1.setAdapter(adapter);

        return rootView;
    }
    private ArrayList<Specyfikacja2Parametry> GetSearchResults() {
        preferencesDB = getActivity().getSharedPreferences("DB", Activity.MODE_PRIVATE);
        final String mes = preferencesDB.getString("DB", "");
        Log.i("Tab1", mes);
        if (mes.equals("zg")) {
            List<DataZG> dane = new LinkedList<DataZG>();
            handlerZG = new DatabaseZG(getActivity());
            preferencesID = getActivity().getSharedPreferences("ID", Activity.MODE_PRIVATE);
            final String id = preferencesID.getString("ID", "");
            Log.i("Tab1", id);
            dane = handlerZG.getDane(id);
            //Log.i("Nazwa Tab2",dane.get(0).getNazwa());


            ArrayList<Specyfikacja2Parametry> results = new ArrayList<Specyfikacja2Parametry>();


            Specyfikacja2Parametry sr1 = new Specyfikacja2Parametry();

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Nazwa");
            sr1.setModel(dane.get(0).getNazwa());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Model");
            sr1.setModel(dane.get(0).getModel());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Moc znamionowa (Watt)");
            sr1.setModel(dane.get(0).getMoc_znamionowa());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Moc maksymalna (Watt)");
            sr1.setModel(dane.get(0).getMoc_maksymalna());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Efektywność (db)");
            sr1.setModel(dane.get(0).getEfektywnosc());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Impedancja (Ohm)");
            sr1.setModel(dane.get(0).getImpedancja());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Pasmo przenoszenia(Hz)");
            sr1.setModel(dane.get(0).getPasmo_przenoszenia());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Konstrukcja");
            sr1.setModel(dane.get(0).getKonstrukcja());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Wymiary(cm)(wysokość X szerokość X głębokość)");
            sr1.setModel(dane.get(0).getWymiary());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Waga(kg)");
            sr1.setModel(dane.get(0).getWaga());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Głośnik niskotonowy");
            sr1.setModel(dane.get(0).getNiskotonowy());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Głośnik średniotonowy");
            sr1.setModel(dane.get(0).getSredniotonowy());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Głośnik wysokotnowy");
            sr1.setModel(dane.get(0).getWysokotonowy());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("");
            sr1.setModel("");
            results.add(sr1);

            return results;
        } else if (mes.equals("ro")) {
            handlerRO = new DatabaseRO(getActivity());

            List<DataRO> dane = new LinkedList<DataRO>();
            preferencesID = getActivity().getSharedPreferences("ID", Activity.MODE_PRIVATE);
            final String id = preferencesID.getString("ID", "");
            Log.i("Tab1", id);
            dane = handlerRO.getDane(id);
            ArrayList<Specyfikacja2Parametry> results = new ArrayList<Specyfikacja2Parametry>();


            Specyfikacja2Parametry sr1 = new Specyfikacja2Parametry();

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Nazwa");
            sr1.setModel(dane.get(0).getNazwa());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Producent");
            sr1.setModel(dane.get(0).getProducent());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Rodzaj");
            sr1.setModel(dane.get(0).getRodzaj());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Moc (Watt)");
            sr1.setModel(dane.get(0).getMoc());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Elementy aktywne");
            sr1.setModel(dane.get(0).getLampy());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Rok produkcji");
            sr1.setModel(dane.get(0).getRok_produkcji());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Zasilanie");
            sr1.setModel(dane.get(0).getZasilanie());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Wymiary(cm)(szerokość X wysokość X głębokość)");
            sr1.setModel(dane.get(0).getWymiary());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Waga (kg)");
            sr1.setModel(dane.get(0).getWaga());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("");
            sr1.setModel("");
            results.add(sr1);

            return results;

        }
        else if (mes.equals("gf")) {
            handlerGF = new DatabaseGramofon(getActivity());

            List<DataGramofon> dane = new LinkedList<DataGramofon>();
            preferencesID = getActivity().getSharedPreferences("ID", Activity.MODE_PRIVATE);
            final String id = preferencesID.getString("ID", "");
            Log.i("Tab1", id);
            dane = handlerGF.getDane(id);
            ArrayList<Specyfikacja2Parametry> results = new ArrayList<Specyfikacja2Parametry>();


            Specyfikacja2Parametry sr1 = new Specyfikacja2Parametry();

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Nazwa");
            sr1.setModel(dane.get(0).getNazwa());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Producent");
            sr1.setModel(dane.get(0).getProducent());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Typ");
            sr1.setModel(dane.get(0).getTyp());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Elementy aktywne");
            sr1.setModel(dane.get(0).getLampy());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Rok produkcji");
            sr1.setModel(dane.get(0).getRok_produkcji());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Zakres obrotów");
            sr1.setModel(dane.get(0).getZakres_obrotow());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Rodzaj");
            sr1.setModel(dane.get(0).getRodzaj());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Wymiary(cm)(szerokość X wysokość X głębokość)");
            sr1.setModel(dane.get(0).getWymiary());
            results.add(sr1);


            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("");
            sr1.setModel("");
            results.add(sr1);

            return results;

        }else if (mes.equals("mg")) {
            handlerMG = new DatabaseMG(getActivity());

            List<DataMG> dane = new LinkedList<DataMG>();
            preferencesID = getActivity().getSharedPreferences("ID", Activity.MODE_PRIVATE);
            final String id = preferencesID.getString("ID", "");
            Log.i("Tab1", id);
            dane = handlerMG.getDane(id);
            ArrayList<Specyfikacja2Parametry> results = new ArrayList<Specyfikacja2Parametry>();
            Specyfikacja2Parametry sr1 = new Specyfikacja2Parametry();

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Nazwa");
            sr1.setModel(dane.get(0).getNazwa());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Producent");
            sr1.setModel(dane.get(0).getProducent());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Typ");
            sr1.setModel(dane.get(0).getRodzaj());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Elementy aktywne");
            sr1.setModel(dane.get(0).getLampy());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Rok produkcji");
            sr1.setModel(dane.get(0).getRok_produkcji());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Wymiary(cm)");
            sr1.setModel(dane.get(0).getWymiary());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Waga (kg)");
            sr1.setModel(dane.get(0).getWaga());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Prędkość przesuwu taśmy cm/s");
            sr1.setModel(dane.get(0).getPrzesow_tasmy());
            results.add(sr1);


            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("");
            sr1.setModel("");
            results.add(sr1);
            return results;
        }
    else if (mes.equals("rm")) {
        handlerRM = new DatabaseRM(getActivity());

        List<DataRM> dane = new LinkedList<DataRM>();
        preferencesID = getActivity().getSharedPreferences("ID", Activity.MODE_PRIVATE);
        final String id = preferencesID.getString("ID", "");
        Log.i("Tab1", id);
        dane = handlerRM.getDane(id);
        ArrayList<Specyfikacja2Parametry> results = new ArrayList<Specyfikacja2Parametry>();
        Specyfikacja2Parametry sr1 = new Specyfikacja2Parametry();

        sr1 = new Specyfikacja2Parametry();
        sr1.setNazwa("Nazwa");
        sr1.setModel(dane.get(0).getNazwa());
        results.add(sr1);

        sr1 = new Specyfikacja2Parametry();
        sr1.setNazwa("Producent");
        sr1.setModel(dane.get(0).getProducent());
        results.add(sr1);

        sr1 = new Specyfikacja2Parametry();
        sr1.setNazwa("Typ");
        sr1.setModel(dane.get(0).getRodzaj());
        results.add(sr1);

        sr1 = new Specyfikacja2Parametry();
        sr1.setNazwa("Elementy aktywne");
        sr1.setModel(dane.get(0).getLampy());
        results.add(sr1);

        sr1 = new Specyfikacja2Parametry();
        sr1.setNazwa("Rok produkcji");
        sr1.setModel(dane.get(0).getRok_produkcji());
        results.add(sr1);

        sr1 = new Specyfikacja2Parametry();
        sr1.setNazwa("Wymiary(cm)");
        sr1.setModel(dane.get(0).getWymiary());
        results.add(sr1);

        sr1 = new Specyfikacja2Parametry();
        sr1.setNazwa("Waga (kg)");
        sr1.setModel(dane.get(0).getWaga());
        results.add(sr1);


        sr1 = new Specyfikacja2Parametry();
        sr1.setNazwa("");
        sr1.setModel("");
        results.add(sr1);
        return results;
    }
        else if (mes.equals("rd")) {
            handlerRD = new DatabaseRD(getActivity());

            List<DataRD> dane = new LinkedList<DataRD>();
            preferencesID = getActivity().getSharedPreferences("ID", Activity.MODE_PRIVATE);
            final String id = preferencesID.getString("ID", "");
            Log.i("Tab1", id);
            dane = handlerRD.getDane(id);
            ArrayList<Specyfikacja2Parametry> results = new ArrayList<Specyfikacja2Parametry>();
            Specyfikacja2Parametry sr1 = new Specyfikacja2Parametry();

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Nazwa");
            sr1.setModel(dane.get(0).getNazwa());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Producent");
            sr1.setModel(dane.get(0).getProducent());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Typ");
            sr1.setModel(dane.get(0).getRodzaj());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Elementy aktywne");
            sr1.setModel(dane.get(0).getLampy());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Rok produkcji");
            sr1.setModel(dane.get(0).getRok_produkcji());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Wymiary(cm)");
            sr1.setModel(dane.get(0).getWymiary());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Waga (kg)");
            sr1.setModel(dane.get(0).getWaga());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Dodatkowe informacje");
            sr1.setModel(dane.get(0).getInfo());
            results.add(sr1);


            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("");
            sr1.setModel("");
            results.add(sr1);
            return results;
        }
        else if (mes.equals("inne")) {
            handlerInne = new DatabaseInne(getActivity());

            List<DataInne> dane = new LinkedList<DataInne>();
            preferencesID = getActivity().getSharedPreferences("ID", Activity.MODE_PRIVATE);
            final String id = preferencesID.getString("ID", "");
            Log.i("Tab1", id);
            dane = handlerInne.getDane(id);
            ArrayList<Specyfikacja2Parametry> results = new ArrayList<Specyfikacja2Parametry>();
            Specyfikacja2Parametry sr1 = new Specyfikacja2Parametry();

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Nazwa");
            sr1.setModel(dane.get(0).getNazwa());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Producent");
            sr1.setModel(dane.get(0).getProducent());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Typ");
            sr1.setModel(dane.get(0).getRodzaj());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Elementy aktywne");
            sr1.setModel(dane.get(0).getLampy());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Rok produkcji");
            sr1.setModel(dane.get(0).getRok_produkcji());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Wymiary(cm)");
            sr1.setModel(dane.get(0).getWymiary());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Waga (kg)");
            sr1.setModel(dane.get(0).getWaga());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Dodatkowe informacje");
            sr1.setModel(dane.get(0).getInfo());
            results.add(sr1);


            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("");
            sr1.setModel("");
            results.add(sr1);
            return results;
        }
        else if (mes.equals("wz")) {
            handlerWZ = new DatabaseWZ(getActivity());

            List<DataWZ> dane = new LinkedList<DataWZ>();
            preferencesID = getActivity().getSharedPreferences("ID", Activity.MODE_PRIVATE);
            final String id = preferencesID.getString("ID", "");
            Log.i("Tab1", id);
            dane = handlerWZ.getDane(id);
            ArrayList<Specyfikacja2Parametry> results = new ArrayList<Specyfikacja2Parametry>();
            Specyfikacja2Parametry sr1 = new Specyfikacja2Parametry();

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Nazwa");
            sr1.setModel(dane.get(0).getNazwa());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Producent");
            sr1.setModel(dane.get(0).getProducent());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Typ");
            sr1.setModel(dane.get(0).getRodzaj());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Elementy aktywne");
            sr1.setModel(dane.get(0).getLampy());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Rok produkcji");
            sr1.setModel(dane.get(0).getRok_produkcji());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Wymiary(cm)");
            sr1.setModel(dane.get(0).getWymiary());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Waga (kg)");
            sr1.setModel(dane.get(0).getWaga());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Moc znamionowa");
            sr1.setModel(dane.get(0).getMoc());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Pasmo przenoszenia");
            sr1.setModel(dane.get(0).getPasmo_przenoszenia());
            results.add(sr1);

            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("Pobór mocy");
            sr1.setModel(dane.get(0).getPobor_mocy());
            results.add(sr1);


            sr1 = new Specyfikacja2Parametry();
            sr1.setNazwa("");
            sr1.setModel("");
            results.add(sr1);
            return results;
        }




        return null;

    }
}


