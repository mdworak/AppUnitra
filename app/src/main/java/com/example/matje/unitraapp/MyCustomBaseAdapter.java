package com.example.matje.unitraapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Matje on 01.09.2017.
 */

public class MyCustomBaseAdapter extends ArrayAdapter {
    private static ArrayList<Specyfikacja2Parametry> searchArrayList;

    private LayoutInflater mInflater;

    public MyCustomBaseAdapter(Context context, int resource,ArrayList<Specyfikacja2Parametry> results) {
        super(context, resource,results);
        searchArrayList = results;
        mInflater = LayoutInflater.from(context);
    }



    public int getCount() {
        return searchArrayList.size();
    }

    public Object getItem(int position) {
        return searchArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.lw_row_test, null);
            holder = new ViewHolder();
            holder.Nazwa = (TextView) convertView.findViewById(R.id.nazwa);
            holder.Wartosc = (TextView) convertView.findViewById(R.id.wartosc);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.Nazwa.setText(searchArrayList.get(position).getNazwa());
        holder.Wartosc.setText(searchArrayList.get(position).getModel());


        return convertView;
    }


    static class ViewHolder {
        TextView Nazwa;
        TextView Wartosc;

    }

}