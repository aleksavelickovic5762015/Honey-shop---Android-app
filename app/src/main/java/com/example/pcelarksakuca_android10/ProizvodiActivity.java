package com.example.pcelarksakuca_android10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

//import static com.example.pcelarksakuca_android10.MainActivity.proizvodi_array;

public class ProizvodiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proizvodi);

        Funkcije funkcijeInst = new Funkcije();

        ActionBar actionBar = getSupportActionBar();
        Window window = this.getWindow();
        Resources resursi = this.getResources();
        LinearLayout ll_proizvodi = (LinearLayout) findViewById(R.id.ll_proizvodi);

        funkcijeInst.izgled(actionBar, window, resursi, ll_proizvodi, this);

        ////
        ArrayList<String> kategorijeList = new ArrayList<String>();
        kategorijeList.add("SVI");
        for (Proizvod p: Podaci.proizvodi_list) {
            if(!kategorijeList.contains(p.kategorija))
                kategorijeList.add(p.kategorija);
        }
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        KategorijeAdapter kAdapter = new KategorijeAdapter(kategorijeList);
        LinearLayoutManager kLayoutManager = new LinearLayoutManager(getApplicationContext());
        kLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(kLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(kAdapter);

        ArrayList<Proizvod> p_list = new ArrayList<Proizvod>(Podaci.proizvodi_list);
        ////
        final MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, p_list);
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        Drawable transparentDrawable = new ColorDrawable(Color.TRANSPARENT);
        listview.setDivider(transparentDrawable);
        listview.setDividerHeight(120);
        //listview.setDivider(null);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                //Toast.makeText(getApplicationContext(), "kliilk", Toast.LENGTH_SHORT).show();
                Intent intentProizvodi = new Intent(view.getContext(), ProizvodActivity.class);
                //int proizvod_id = Podaci.proizvodi_list.get(position).proizvod_id;
                int proizvod_id = adapter.values.get(position).proizvod_id;
                //int proizvod_id = adapter.getFilter().
                intentProizvodi.putExtra("proizvod_id", proizvod_id);
                intentProizvodi.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intentProizvodi);
            }

        });
        /////////////
        final EditText editText = (EditText) findViewById(R.id.pretraga);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ////
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(ProizvodiActivity.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        //Toast.makeText(ProizvodiActivity.this, kategorijeList.get(position), Toast.LENGTH_SHORT).show();
                        p_list.clear();
                        for(Proizvod p : Podaci.proizvodi_list){
                            if(kategorijeList.get(position) == "SVI" || p.kategorija.equals(kategorijeList.get(position)))
                                p_list.add(p);
                        }
                        adapter.azurirajListu(p_list);
                        adapter.notifyDataSetChanged();
                        editText.setText("");
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
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
        MenuItem prijava_item = menu.findItem(R.id.proizvodiItem);
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
}
