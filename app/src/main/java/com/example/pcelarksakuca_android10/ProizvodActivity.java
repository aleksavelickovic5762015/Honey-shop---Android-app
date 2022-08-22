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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ProizvodActivity extends AppCompatActivity {

    int proizvod_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proizvod);

        Funkcije funkcijeInst = new Funkcije();

        ActionBar actionBar = getSupportActionBar();
        Window window = this.getWindow();
        Resources resursi = this.getResources();
        LinearLayout ll_proizvod = (LinearLayout) findViewById(R.id.ll_proizvod);

        funkcijeInst.izgled(actionBar, window, resursi, ll_proizvod, this);

        /////////////////////////////////////////////////
        Intent intent = getIntent();
        proizvod_id = intent.getIntExtra("proizvod_id", 0); // here 0 is the default value
        Proizvod p = Podaci.proizvodi_list.get(proizvod_id);
        //
        TextView tV_proizvod_naziv = (TextView) findViewById(R.id.proizvod_naziv);
        TextView tV_proizvod_cena = (TextView) findViewById(R.id.proizvod_cena);
        TextView tV_proizvod_kategorija = (TextView) findViewById(R.id.proizvod_kategorija);
        TextView tV_proizvod_opis = (TextView) findViewById(R.id.proizvod_opis);
        TextView tV_proizvod_nacinKoriscenja = (TextView) findViewById(R.id.proizvod_nacinKoriscenja);
        ImageView imageView = (ImageView) findViewById(R.id.proizvod_slika);
        //
        tV_proizvod_naziv.setText(p.naziv);
        tV_proizvod_cena.setText("CENA: "+String.valueOf(p.cena));
        tV_proizvod_kategorija.setText("KATEGORIJA: "+String.valueOf(p.kategorija));
        tV_proizvod_opis.setText("OPIS: "+String.valueOf(p.opis));
        tV_proizvod_nacinKoriscenja.setText("NAČIN KORIŠĆENJA: "+String.valueOf(p.nacinKoriscenja));

        String uri = "@drawable/proizvod"+proizvod_id;  // where myresource (without the extension) is the file
        int imageResource = this.getResources().getIdentifier(uri, null, this.getPackageName());
        Drawable res = this.getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        //////////////////////////////////////////////////////////////
        //int korpa_position = Podaci.korpa_list.indexOf(p);
        //Korpa p_korpa = Podaci.korpa_list.get(korpa_position);
        //
        EditText eT_proizvod_kolicina = (EditText) findViewById(R.id.proizvod_kolicina);
        eT_proizvod_kolicina.setGravity(Gravity.CENTER_HORIZONTAL);

        eT_proizvod_kolicina.setText(String.valueOf(1));
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

    public void decrement(View view) {
        EditText eT_proizvod_kolicina = (EditText) findViewById(R.id.proizvod_kolicina);
        int kolicina = Integer.parseInt(eT_proizvod_kolicina.getText().toString());
        kolicina--;
        if(kolicina == 0){
            return;
        }
        eT_proizvod_kolicina.setText(String.valueOf(kolicina));
    }

    public void increment(View view) {
        EditText eT_proizvod_kolicina = (EditText) findViewById(R.id.proizvod_kolicina);
        int kolicina = Integer.parseInt(eT_proizvod_kolicina.getText().toString());
        kolicina++;
        eT_proizvod_kolicina.setText(String.valueOf(kolicina));
    }

    public void korpaDodaj(View view) {
        EditText eT_proizvod_kolicina = (EditText) findViewById(R.id.proizvod_kolicina);
        int kolicina = Integer.parseInt(eT_proizvod_kolicina.getText().toString());
        //
        for (int i=0; i<Podaci.korpa_list.size(); i++){
            if(Podaci.korpa_list.get(i).proizvod_id == proizvod_id){
                Podaci.korpa_list.get(i).kolicina += kolicina;

                Intent intentProizvod = new Intent(view.getContext(), ProizvodiActivity.class);
                intentProizvod.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intentProizvod);

                return;
            }
        }
        //
        Podaci.korpa_list.add(new Korpa(proizvod_id, kolicina));

        Intent intentProizvod = new Intent(view.getContext(), ProizvodiActivity.class);
        intentProizvod.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intentProizvod);
    }
}