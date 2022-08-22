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

public class IzmenaSifreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izmena_sifre);

        Funkcije funkcijeInst = new Funkcije();

        ActionBar actionBar = getSupportActionBar();
        Window window = this.getWindow();
        Resources resursi = this.getResources();
        LinearLayout ll_izmenaSifre = (LinearLayout) findViewById(R.id.ll_izmenaSifre);

        funkcijeInst.izgled(actionBar, window, resursi, ll_izmenaSifre, this);

    }

    public void izmeniSifru(View view){
        EditText eT_sifra = findViewById(R.id.izmenaSifre_sifra);
        EditText eT_novaSifra1 = findViewById(R.id.izmenaSifre_novaSifra1);
        EditText eT_novaSifra2 = findViewById(R.id.izmenaSifre_novaSifra2);
        //
        String sifra = eT_sifra.getText().toString();
        String novaSifra1 = eT_novaSifra1.getText().toString();
        String novaSifra2 = eT_novaSifra2.getText().toString();

        if(sifra.isEmpty() || novaSifra1.isEmpty() || novaSifra2.isEmpty()){
            Toast.makeText(this,"Potrebno je popuniti sva polja!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!sifra.equals(Podaci.korisnici_list.get(Podaci.prijavljen_korisnik_id).sifra)){
            Toast.makeText(this,"Pogrešno uneta šifra!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!novaSifra1.equals(novaSifra2)){
            Toast.makeText(this,"Pogrešno ponovljena šifra!", Toast.LENGTH_SHORT).show();
            return;
        }

        for(int i=0; i<Podaci.korisnici_list.size(); i++){
            if(Podaci.korisnici_list.get(i).korisnik_id == Podaci.prijavljen_korisnik_id){
                Podaci.korisnici_list.get(i).sifra = novaSifra1;

                Intent intentIzmenaSifre = new Intent(view.getContext(), PodaciActivity.class);
                intentIzmenaSifre.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intentIzmenaSifre);
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

    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}


