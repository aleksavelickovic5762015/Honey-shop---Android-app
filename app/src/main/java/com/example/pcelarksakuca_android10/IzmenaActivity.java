package com.example.pcelarksakuca_android10;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class IzmenaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izmena);

        Funkcije funkcijeInst = new Funkcije();

        ActionBar actionBar = getSupportActionBar();
        Window window = this.getWindow();
        Resources resursi = this.getResources();
        LinearLayout ll_izmena = (LinearLayout) findViewById(R.id.ll_izmena);

        funkcijeInst.izgled(actionBar, window, resursi, ll_izmena, this);

    }

    public void izmena(View view){
        EditText eT_ime = findViewById(R.id.izmena_ime);
        EditText eT_prezime = findViewById(R.id.izmena_prezime);
        EditText eT_adresa = findViewById(R.id.izmena_adresa);
        EditText eT_tel = findViewById(R.id.izmena_tel);
        EditText eT_mejl = findViewById(R.id.izmena_mejl);
        //
        String ime = eT_ime.getText().toString();
        String prezime = eT_prezime.getText().toString();
        String adresa = eT_adresa.getText().toString();
        String tel = eT_tel.getText().toString();
        String mejl = eT_mejl.getText().toString();

        if(ime.isEmpty() || prezime.isEmpty() || adresa.isEmpty() || tel.isEmpty() || mejl.isEmpty()){
            Toast.makeText(this,"Potrebno je popuniti sva polja!", Toast.LENGTH_SHORT).show();
            return;
        }

        for(int i=0; i<Podaci.korisnici_list.size(); i++){
            if(Podaci.korisnici_list.get(i).korisnik_id == Podaci.prijavljen_korisnik_id){
                Podaci.korisnici_list.get(i).ime = ime;
                Podaci.korisnici_list.get(i).prezime = prezime;
                Podaci.korisnici_list.get(i).adresa = adresa;
                Podaci.korisnici_list.get(i).tel = tel;
                Podaci.korisnici_list.get(i).mejl = mejl;

                Intent intentIzmena = new Intent(view.getContext(), PodaciActivity.class);
                intentIzmena.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intentIzmena);
                return;
            }
        }
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

    @Override
    protected void onResume() {
        super.onResume();
        EditText eT_ime = findViewById(R.id.izmena_ime);
        EditText eT_prezime = findViewById(R.id.izmena_prezime);
        EditText eT_adresa = findViewById(R.id.izmena_adresa);
        EditText eT_tel = findViewById(R.id.izmena_tel);
        EditText eT_mejl = findViewById(R.id.izmena_mejl);
        //
        /*
        eT_ime.setText(Podaci.korisnici_list.get(Podaci.prijavljen_korisnik_id).ime);
        eT_prezime.setText(Podaci.korisnici_list.get(Podaci.prijavljen_korisnik_id).prezime);
        eT_adresa.setText(Podaci.korisnici_list.get(Podaci.prijavljen_korisnik_id).adresa);
        eT_tel.setText(Podaci.korisnici_list.get(Podaci.prijavljen_korisnik_id).tel);
        eT_mejl.setText(Podaci.korisnici_list.get(Podaci.prijavljen_korisnik_id).mejl);
        */
        eT_ime.setText(Podaci.korisnici_list.get(Podaci.prijavljen_korisnik_id).ime);
        eT_prezime.setText(Podaci.korisnici_list.get(Podaci.prijavljen_korisnik_id).prezime);
        eT_adresa.setText(Podaci.korisnici_list.get(Podaci.prijavljen_korisnik_id).adresa);
        eT_tel.setText(Podaci.korisnici_list.get(Podaci.prijavljen_korisnik_id).tel);
        eT_mejl.setText(Podaci.korisnici_list.get(Podaci.prijavljen_korisnik_id).mejl);
    }

    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}


