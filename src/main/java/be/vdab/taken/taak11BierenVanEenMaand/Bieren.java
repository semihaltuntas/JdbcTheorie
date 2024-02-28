package be.vdab.taken.taak11BierenVanEenMaand;

public class Bieren {
    private int id;
    private String naam;

    public Bieren(int id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    @Override
    public String toString() {
        return id + " -> "+ naam;
    }
}
