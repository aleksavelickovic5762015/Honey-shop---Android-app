package com.example.pcelarksakuca_android10;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class KorpaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korpa);

        Funkcije funkcijeInst = new Funkcije();

        ActionBar actionBar = getSupportActionBar();
        Window window = this.getWindow();
        Resources resursi = this.getResources();
        LinearLayout ll_korpa = (LinearLayout) findViewById(R.id.ll_korpa);

        funkcijeInst.izgled(actionBar, window, resursi, ll_korpa, this);

        //////////////////////////////////////////////////////////


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        //
        if(Podaci.prijavljen_korisnik_id != -1){
            MenuItem prijava_item = menu.findItem(R.id.prijavaItem);
            prijava_item.setVisible(false);
            prijava_item.setEnabled(false);
            //
            MenuItem podaci_item = menu.findItem(R.id.podaciItem);
            podaci_item.setVisible(true);
            podaci_item.setEnabled(true);
        }
        else{
            MenuItem prijava_item = menu.findItem(R.id.prijavaItem);
            prijava_item.setVisible(true);
            prijava_item.setEnabled(true);
            //
            MenuItem podaci_item = menu.findItem(R.id.podaciItem);
            podaci_item.setVisible(false);
            podaci_item.setEnabled(false);
        }
        //
        MenuItem prijava_item = menu.findItem(R.id.korpaItem);
        prijava_item.setVisible(false);
        prijava_item.setEnabled(false);
        //
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.mainItem:
                Intent intentMain = new Intent(this, MainActivity.class);
                intentMain.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intentMain);
                return true;
            case R.id.proizvodiItem:
                Intent intentProizvodi = new Intent(this, ProizvodiActivity.class);
                intentProizvodi.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intentProizvodi);
                return true;
            case R.id.prijavaItem:
                Intent intentPrijava = new Intent(this, PrijavaActivity.class);
                intentPrijava.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intentPrijava);
                return true;
            case R.id.podaciItem:
                Intent intentPodaci = new Intent(this, PodaciActivity.class);
                intentPodaci.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intentPodaci);
                return true;
            case R.id.korpaItem:
                Intent intentKorpa = new Intent(this, KorpaActivity.class);
                intentKorpa.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intentKorpa);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ukupanIznos();
        //
        final KorpaAdapter adapter = new KorpaAdapter(this, Podaci.korpa_list);
        //
        ListView listview = (ListView) findViewById(R.id.listview_korpa);
        listview.setAdapter(adapter);

        Drawable transparentDrawable = new ColorDrawable(Color.TRANSPARENT);
        listview.setDivider(transparentDrawable);
        listview.setDividerHeight(120);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                Intent intentProizvodi = new Intent(view.getContext(), ProizvodActivity.class);
                int proizvod_id = Podaci.korpa_list.get(position).proizvod_id;
                intentProizvodi.putExtra("proizvod_id", proizvod_id);
                intentProizvodi.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intentProizvodi);
            }

        });

    }

    public void isprazniKorpu(View view) {
        Podaci.korpa_list.clear();
        //
        final KorpaAdapter adapter = new KorpaAdapter(this, Podaci.korpa_list);
        //
        ListView listview = (ListView) findViewById(R.id.listview_korpa);
        listview.setAdapter(adapter);

        ukupanIznos();
    }

    public void obrisiKorpu(int position){
        Podaci.korpa_list.remove(position);
        //
        final KorpaAdapter adapter = new KorpaAdapter(this, Podaci.korpa_list);
        //
        ListView listview = (ListView) findViewById(R.id.listview_korpa);
        listview.setAdapter(adapter);

        ukupanIznos();
    }

    public void ukupanIznos(){
        TextView tV_korpa_ukupno = (TextView) findViewById(R.id.korpa_ukupno);

        int iznos = 0;
        for (Korpa k : Podaci.korpa_list) {
            for (Proizvod p : Podaci.proizvodi_list) {
                if(k.proizvod_id == p.proizvod_id){
                    iznos += p.cena * k.kolicina;
                }
            }
        }

        tV_korpa_ukupno.setText("UKUPNO: " + String.valueOf(iznos));
    }

    public void porucivanje(View view) {
        if(Podaci.korpa_list.size() == 0) return;
        //Toast.makeText(this, "Porucivanje..", Toast.LENGTH_SHORT).show();

        //nikoNijePrijavljen
        if(Podaci.prijavljen_korisnik_id == -1){
            Intent intentKorpaPrijava = new Intent(view.getContext(), PrijavaActivity.class);
            intentKorpaPrijava.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intentKorpaPrijava);
            return;
        }

        int n_id = Podaci.narudzbine_list.get(Podaci.narudzbine_list.size()-1).narudzbina_id + 1;
        Narudzbina n = new Narudzbina(n_id, new ArrayList<Integer>(), new ArrayList<Integer>(), Podaci.prijavljen_korisnik_id, "U TOKU", 0, 0);
        for (Korpa k : Podaci.korpa_list) {
            for (Proizvod p : Podaci.proizvodi_list) {
                if(k.proizvod_id == p.proizvod_id){
                    n.kolicine.add(k.kolicina);
                    n.proizvod_ids.add(k.proizvod_id);
                    n.iznos += p.cena * k.kolicina;
                }
            }
        }
        Podaci.narudzbine_list.add(n);

        isprazniKorpu(view);
        //
        Intent intentKorpa = new Intent(view.getContext(), NarudzbineActivity.class);
        intentKorpa.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intentKorpa);
    }

    public void decrement(View view) {
        LinearLayout vwParentRow = (LinearLayout) view.getParent();
        //
        Button decrementBtn = (Button) vwParentRow.getChildAt(3);
        EditText eT_korpa_kolicina = (EditText) vwParentRow.getChildAt(4);

        int kolicina = Integer.parseInt(eT_korpa_kolicina.getText().toString());
        kolicina--;
        if(kolicina == 0){
            return;
        }
        eT_korpa_kolicina.setText(String.valueOf(kolicina));
        //Podaci.korpa_list.get(position).kolicina = kolicina;
    }

    public void increment(View view) {
        EditText eT_korpa_kolicina = (EditText) view.findViewById(R.id.korpa_kolicina);
        int kolicina = Integer.parseInt(eT_korpa_kolicina.getText().toString());
        kolicina++;
        eT_korpa_kolicina.setText(String.valueOf(kolicina));
    }
}

