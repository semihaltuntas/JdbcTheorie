package be.vdab.taken.taak6AlleSoorten;

public class Soort {
    private int id;
    private String naam;

    public Soort(int id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    @Override
    public String toString() {
        return id + " : " + naam;
    }
}
