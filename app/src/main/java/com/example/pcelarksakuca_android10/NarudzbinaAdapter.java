package com.example.pcelarksakuca_android10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NarudzbinaAdapter extends ArrayAdapter<NarudzbinaPK> {
    private final Context context;
    private final ArrayList<NarudzbinaPK> values;

    public NarudzbinaAdapter(Context context, ArrayList<NarudzbinaPK> npk_list) {
        super(context, -1, npk_list);
        this.context = context;
        this.values = npk_list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_narudzbina_rowlayout, parent, false);

        TextView tV_narudzbina_naziv = (TextView) rowView.findViewById(R.id.narudzbina_naziv);
        TextView tV_narudzbina_kolicina = (TextView) rowView.findViewById(R.id.narudzbina_kolicina);

        tV_narudzbina_naziv.setText(values.get(position).naziv + ": ");
        tV_narudzbina_kolicina.setText(String.valueOf(values.get(position).kolicina) + " kom.");

        return rowView;
    }
}