package be.vdab.theorie;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Leverancier3 {
    private final long id;
    private final String naam;
    private final String adres;
    private final int postcode;
    private final String woonplaats;
    private final LocalDate sinds;

    public Leverancier3(long id, String naam, String adres, int postcode, String woonplaats, LocalDate sinds) {
        this.id = id;
        this.naam = naam;
        this.adres = adres;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.sinds = sinds;
    }

    public long getAantalJaarLeverancier() {
        return ChronoUnit.YEARS.between(sinds, LocalDate.now());
    }

    @Override
    public String toString() {
        return id + "->" + naam + " (" + woonplaats + ") " + postcode + " " + sinds + ": " +
                getAantalJaarLeverancier() + " jaar";
    }
}
