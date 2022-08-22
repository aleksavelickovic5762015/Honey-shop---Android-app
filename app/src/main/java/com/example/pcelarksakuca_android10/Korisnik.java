package com.example.pcelarksakuca_android10;

public class Korisnik {
    int korisnik_id;
    String ime;
    String prezime;
    String adresa;
    String tel;
    String mejl;
    String korisnicko_ime;
    String sifra;

    Korisnik(int korisnik_id, String ime, String prezime, String adresa, String tel, String mejl,
             String korisnicko_ime, String sifra){
        this.korisnik_id = korisnik_id;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.tel = tel;
        this.mejl = mejl;
        this.korisnicko_ime = korisnicko_ime;
        this.sifra = sifra;
    }
}

