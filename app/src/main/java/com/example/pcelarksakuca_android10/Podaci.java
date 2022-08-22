package com.example.pcelarksakuca_android10;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Podaci {
    public static ArrayList<Proizvod> proizvodi_list = new ArrayList<Proizvod>(){{
        add(new Proizvod(0, "LIVADSKI MED", 800, "MED", "IZUZETAN MED", "ZAHVATITI KASIKOM"));
        add(new Proizvod(1, "BAGREMOV MED", 800, "MED", "IZUZETAN MED", "ZAHVATITI KASIKOM"));
        add(new Proizvod(2, "MATIČNI MLEČ", 900, "MATIČNI MLEČ", "VEOMA DELOTVORAN", "ZAHVATITI KASIKOM"));
        add(new Proizvod(3, "PROPOLIS KREMA", 900, "PROPOLIS", "VEOMA DELOTVORAN", "NAMAZATI"));
    }};

    public static ArrayList<Korpa> korpa_list = new ArrayList<Korpa>(){{
        add(new Korpa(0, 2));
        add(new Korpa(2, 1));
    }};

    public static ArrayList<Korisnik> korisnici_list = new ArrayList<Korisnik>(){{
        add(new Korisnik(0, "Aleksa", "Veličković", "Kragujevac", "0641111222", "aleksa@gmail.com", "aleksa", "1234"));
        add(new Korisnik(1, "Marija", "Veličković", "Kragujevac", "0642222333", "marija@gmail.com", "marija", "0000"));
    }};

    public static int prijavljen_korisnik_id = -1;

    public static ArrayList<Narudzbina> narudzbine_list = new ArrayList<Narudzbina>(){{
        add(new Narudzbina(0, new ArrayList<Integer>(){{add(2); add(1);}}, new ArrayList<Integer>(){{add(1); add(3);}}, 1, "IZVRŠENO", 2500, 0));
        add(new Narudzbina(1, new ArrayList<Integer>(){{add(1); add(1);}}, new ArrayList<Integer>(){{add(2); add(3);}}, 1, "POTVRĐENO", 1800, 3));
    }};
}
