package com.example.pcelarksakuca_android10;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NarudzbineAdapter extends ArrayAdapter<Narudzbina> {
    private final Context context;
    private final ArrayList<Narudzbina> values;

    public NarudzbineAdapter(Context context, ArrayList<Narudzbina> narudzbine) {
        super(context, -1, narudzbine);
        this.context = context;
        this.values = narudzbine;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_narudzbine_rowlayout, parent, false);
        ////
        ArrayList<NarudzbinaPK> npk_list = new ArrayList<NarudzbinaPK>();
        for (int i=0; i<values.get(position).proizvod_ids.size(); i++)
        {
            for (Proizvod p : Podaci.proizvodi_list) {
                if(p.proizvod_id == values.get(position).proizvod_ids.get(i))
                    npk_list.add(new NarudzbinaPK(p.naziv, values.get(position).kolicine.get(i)));
            }
        }

        final NarudzbinaAdapter adapter = new NarudzbinaAdapter(context, npk_list);
        ListView listview = (ListView) rowView.findViewById(R.id.listview_narudzbina);
        listview.setAdapter(adapter);

        Drawable transparentDrawable = new ColorDrawable(Color.TRANSPARENT);
        listview.setDivider(transparentDrawable);
        //
        NarudzbinaAdapter listadp = (NarudzbinaAdapter) listview.getAdapter();
        if (listadp != null) {
            int totalHeight = 0;
            for (int i = 0; i < listadp.getCount(); i++) {
                View listItem = listadp.getView(i, null, listview);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = listview.getLayoutParams();
            params.height = totalHeight + (listview.getDividerHeight() * (listadp.getCount() - 1));
            listview.setLayoutParams(params);
            listview.requestLayout();
        }
        ////

        TextView tV_narudzbine_brDana = (TextView) rowView.findViewById(R.id.narudzbine_brDana);
        if(values.get(position).status == "POTVRĐENO"){
            tV_narudzbine_brDana.setText("BR. DANA: "+String.valueOf(values.get(position).br_dana));
        }
        else{
            tV_narudzbine_brDana.setVisibility(View.GONE);
        }
        TextView tV_narudzbine_status = (TextView) rowView.findViewById(R.id.narudzbine_status);
        TextView tV_narudzbine_iznos = (TextView) rowView.findViewById(R.id.narudzbine_iznos);
        tV_narudzbine_status.setText(values.get(position).status);
        tV_narudzbine_iznos.setText(String.valueOf(values.get(position).iznos));

        return rowView;
    }
}
