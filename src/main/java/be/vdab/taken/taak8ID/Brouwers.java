package be.vdab.taken.taak8ID;

public class Brouwers {
    private int id;
    private String naam;
    private int postcode;
    private String adres;
    private String gemeente;
    private long omzet;

    public Brouwers(int id, String naam, int postcode, String adres, String gemeente, long omzet) {
        this.id = id;
        this.naam = naam;
        this.postcode = postcode;
        this.adres = adres;
        this.gemeente = gemeente;
        this.omzet = omzet;
    }

    @Override
    public String toString() {
        return id + " : " + naam + "-" + postcode + "-" + adres + "-" + gemeente + " ->" + omzet;
    }
}
