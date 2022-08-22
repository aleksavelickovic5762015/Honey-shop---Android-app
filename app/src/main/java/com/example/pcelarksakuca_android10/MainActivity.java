package com.example.pcelarksakuca_android10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager mImageViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Funkcije funkcijeInst = new Funkcije();

        ActionBar actionBar = getSupportActionBar();
        Window window = this.getWindow();
        Resources resursi = this.getResources();
        LinearLayout ll_main = (LinearLayout) findViewById(R.id.ll_main);

        funkcijeInst.izgled(actionBar, window, resursi, ll_main, this);

        mImageViewPager = (ViewPager) findViewById(R.id.pager);
        //mImageViewPager = (ViewPager2) findViewById(R.id.pager);
        //
        WizardPagerAdapter adapter = new WizardPagerAdapter();
        mImageViewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(mImageViewPager, true);

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
        MenuItem prijava_item = menu.findItem(R.id.mainItem);
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

        //proizvodi_list.removeAll(proizvodi_list);
        /*proizvodi_list.clear();
        proizvodi_list.add(new Proizvod(0, "LIVADSKI MED", 800, "MED", "IZUZETAN MED", "ZAHVATITI KASIKOM"));
        proizvodi_list.add(new Proizvod(1, "BAGREMOV MED", 800, "MED", "IZUZETAN MED", "ZAHVATITI KASIKOM"));
        proizvodi_list.add(new Proizvod(2, "MATIČNI MLEČ", 900, "MED", "VEOMA DELOTVORAN", "ZAHVATITI KASIKOM"));
        proizvodi_list.add(new Proizvod(3, "PROPOLIS KREMA", 900, "PROPOLIS", "VEOMA DELOTVORAN", "NAMAZATI"));*/
    }
}