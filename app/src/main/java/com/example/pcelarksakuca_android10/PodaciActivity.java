package com.example.pcelarksakuca_android10;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class PodaciActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podaci);

        Funkcije funkcijeInst = new Funkcije();

        ActionBar actionBar = getSupportActionBar();
        Window window = this.getWindow();
        Resources resursi = this.getResources();
        LinearLayout ll_podaci = (LinearLayout) findViewById(R.id.ll_podaci);

        funkcijeInst.izgled(actionBar, window, resursi, ll_podaci, this);

        /////////////////////////////////////////////////
        TextView tV_podaci_ime = (TextView) findViewById(R.id.podaci_ime);
        TextView tV_podaci_prezime = (TextView) findViewById(R.id.podaci_prezime);
        TextView tV_podaci_adresa = (TextView) findViewById(R.id.podaci_adresa);
        TextView tV_podaci_tel = (TextView) findViewById(R.id.podaci_tel);
        TextView tV_podaci_mejl = (TextView) findViewById(R.id.podaci_mejl);
        //
        tV_podaci_ime.setText("IME: " + Podaci.korisnici_list.get(Podaci.prijavljen_korisnik_id).ime);
        tV_podaci_prezime.setText("PREZIME: " + Podaci.korisnici_list.get(Podaci.prijavljen_korisnik_id).prezime);
        tV_podaci_adresa.setText("ADRESA: " + Podaci.korisnici_list.get(Podaci.prijavljen_korisnik_id).adresa);
        tV_podaci_tel.setText("BR.TEL: " + Podaci.korisnici_list.get(Podaci.prijavljen_korisnik_id).tel);
        tV_podaci_mejl.setText("E-MEJL: " + Podaci.korisnici_list.get(Podaci.prijavljen_korisnik_id).mejl);
        //////////////////////////////////////////////////////////////
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
        MenuItem prijava_item = menu.findItem(R.id.podaciItem);
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

    public void odjava(View view) {
        Intent intentPodaci = new Intent(view.getContext(), MainActivity.class);
        intentPodaci.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intentPodaci);
        //
        Podaci.prijavljen_korisnik_id = -1;
    }

    public void podaciNarudzbine(View view) {
        Intent intentPodaciNarudzbine = new Intent(view.getContext(), NarudzbineActivity.class);
        intentPodaciNarudzbine.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intentPodaciNarudzbine);
    }

    public void izmenaPodataka(View view) {
        Intent intentIzmena = new Intent(view.getContext(), IzmenaActivity.class);
        intentIzmena.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intentIzmena);
    }

    public void promenaSifre(View view) {
        Intent intentPromenaSifre = new Intent(view.getContext(), IzmenaSifreActivity.class);
        intentPromenaSifre.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intentPromenaSifre);
    }
}
