package be.vdab.taken.taak5Gemiddelde;

import java.math.BigDecimal;

public class Brouwers {
    private int id;
    private String naam;
    private String adres;
    private int postcode;
    private String gemeente;
    private int omzet;

    public Brouwers(int id, String naam, String adres, int postcode, String gemeente, int omzet) {
        this.id = id;
        this.naam = naam;
        this.adres = adres;
        this.postcode = postcode;
        this.gemeente = gemeente;
        this.omzet = omzet;
    }

    @Override
    public String toString() {
        return id + " : " + naam + " (" + gemeente + ")-> " + omzet;
    }
}
