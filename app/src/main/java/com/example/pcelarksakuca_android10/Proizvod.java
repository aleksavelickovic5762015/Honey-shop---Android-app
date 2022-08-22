package com.example.pcelarksakuca_android10;

public class Proizvod {
    int proizvod_id;
    String naziv;
    int cena;
    String kategorija;
    String opis;
    String nacinKoriscenja;

    Proizvod(int proizvod_id, String naziv, int cena, String kategorija, String opis, String nacinKoriscenja){
        this.proizvod_id = proizvod_id;
        this.naziv = naziv;
        this.cena = cena;
        this.kategorija = kategorija;
        this.opis = opis;
        this.nacinKoriscenja = nacinKoriscenja;
    }
}
