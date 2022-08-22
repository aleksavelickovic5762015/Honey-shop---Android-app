package com.example.pcelarksakuca_android10;

import java.util.ArrayList;

public class Narudzbina {
    int narudzbina_id;
    ArrayList<Integer> kolicine;
    ArrayList<Integer> proizvod_ids;
    int korisnik_id;
    String status;
    int iznos;
    int br_dana;

    Narudzbina(int narudzbina_id, ArrayList<Integer> kolicine, ArrayList<Integer> proizvod_ids, int korisnik_id, String status, int iznos, int br_dana){
        this.narudzbina_id = narudzbina_id;
        this.kolicine = kolicine;
        this.proizvod_ids = proizvod_ids;
        this.korisnik_id = korisnik_id;
        this.status = status;
        this.iznos = iznos;
        this.br_dana = br_dana;
    }
}
