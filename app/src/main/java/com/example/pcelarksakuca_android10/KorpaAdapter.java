package com.example.pcelarksakuca_android10;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class KorpaAdapter extends ArrayAdapter<Korpa> {
    private final Context context;
    private final ArrayList<Korpa> values;

    public KorpaAdapter(Context context, ArrayList<Korpa> korpa) {
        super(context, -1, korpa);
        this.context = context;
        this.values = korpa;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_korpa_rowlayout, parent, false);
        //
        Proizvod korpa_proizvod = null;
        for (Proizvod p : Podaci.proizvodi_list)
        {
            if (values.get(position).proizvod_id == p.proizvod_id)
            {
                korpa_proizvod =  p;
                break;
            }
        }
        //
        TextView tV_korpa_naziv = (TextView) rowView.findViewById(R.id.korpa_naziv);
        TextView tV_korpa_cena = (TextView) rowView.findViewById(R.id.korpa_cena);
        TextView tV_korpa_kolicina = (TextView) rowView.findViewById(R.id.korpa_kolicina);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.korpa_slika);
        tV_korpa_naziv.setText(korpa_proizvod.naziv);
        tV_korpa_cena.setText("CENA: "+String.valueOf(korpa_proizvod.cena));
        tV_korpa_kolicina.setText("KOLIČINA: "+String.valueOf(values.get(position).kolicina));

        String uri = "@drawable/proizvod"+korpa_proizvod.proizvod_id;  // where myresource (without the extension) is the file
        int imageResource = this.context.getResources().getIdentifier(uri, null, this.context.getPackageName());
        Drawable res = this.context.getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);

        EditText eT_korpa_kolicina = (EditText) rowView.findViewById(R.id.korpa_kolicina);
        eT_korpa_kolicina.setGravity(Gravity.CENTER_HORIZONTAL);
        eT_korpa_kolicina.setText(String.valueOf(Podaci.korpa_list.get(position).kolicina));
        //
        Button decrementBtn = (Button) rowView.findViewById(R.id.decrementButton);
        decrementBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                int kolicina = Integer.parseInt(eT_korpa_kolicina.getText().toString());
                kolicina--;
                if(kolicina == 0){
                    return;
                }
                eT_korpa_kolicina.setText(String.valueOf(kolicina));
                Podaci.korpa_list.get(position).kolicina = kolicina;
                if (context instanceof KorpaActivity) {
                    ((KorpaActivity) context).ukupanIznos();
                }
            }
        });
        Button incrementBtn = (Button) rowView.findViewById(R.id.incrementButton);
        incrementBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                int kolicina = Integer.parseInt(eT_korpa_kolicina.getText().toString());
                kolicina++;
                eT_korpa_kolicina.setText(String.valueOf(kolicina));
                Podaci.korpa_list.get(position).kolicina = kolicina;
                if (context instanceof KorpaActivity) {
                    ((KorpaActivity) context).ukupanIznos();
                }
            }
        });
        Button obrisiBtn = (Button) rowView.findViewById(R.id.obrisiButton);
        obrisiBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (context instanceof KorpaActivity) {
                    ((KorpaActivity) context).obrisiKorpu(position);
                }
            }
        });

        return rowView;
    }
}

