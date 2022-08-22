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

public class RegistracijaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);

        Funkcije funkcijeInst = new Funkcije();

        ActionBar actionBar = getSupportActionBar();
        Window window = this.getWindow();
        Resources resursi = this.getResources();
        LinearLayout ll_registracija = (LinearLayout) findViewById(R.id.ll_registracija);

        funkcijeInst.izgled(actionBar, window, resursi, ll_registracija, this);

    }

    public void registracija(View view){
        EditText eT_korisnicko_ime = findViewById(R.id.registracija_korisnicko_ime);
        EditText eT_sifra1 = findViewById(R.id.registracija_sifra1);
        EditText eT_sifra2 = findViewById(R.id.registracija_sifra2);
        EditText eT_ime = findViewById(R.id.registracija_ime);
        EditText eT_prezime = findViewById(R.id.registracija_prezime);
        EditText eT_adresa = findViewById(R.id.registracija_adresa);
        EditText eT_tel = findViewById(R.id.registracija_tel);
        EditText eT_mejl = findViewById(R.id.registracija_mejl);
        //
        String korisnicko_ime = eT_korisnicko_ime.getText().toString();
        String sifra1 = eT_sifra1.getText().toString();
        String sifra2 = eT_sifra2.getText().toString();
        String ime = eT_ime.getText().toString();
        String prezime = eT_prezime.getText().toString();
        String adresa = eT_adresa.getText().toString();
        String tel = eT_tel.getText().toString();
        String mejl = eT_mejl.getText().toString();

        if(korisnicko_ime.isEmpty() || sifra1.isEmpty() || sifra2.isEmpty() || ime.isEmpty() || prezime.isEmpty() || adresa.isEmpty() || tel.isEmpty() || mejl.isEmpty()){
            Toast.makeText(this,"Potrebno je popuniti sva polja!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!sifra1.equals(sifra2)){
            Toast.makeText(this,"Pogresno ponovljena sifra!", Toast.LENGTH_SHORT).show();
            return;
        }

        int noviID = Podaci.korisnici_list.get(Podaci.korisnici_list.size()-1).korisnik_id + 1;
        Podaci.korisnici_list.add(new Korisnik(noviID, ime, prezime, adresa, tel, mejl, korisnicko_ime, sifra1));
        //
        Intent intentRegistracija = new Intent(view.getContext(), PrijavaActivity.class);
        intentRegistracija.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intentRegistracija);
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

    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}


