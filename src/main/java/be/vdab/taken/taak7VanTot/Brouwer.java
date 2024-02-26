package be.vdab.taken.taak7VanTot;

public class Brouwer {
    private int id;
    private String naam;
    private int omzet;

    public Brouwer(int id, String naam, int omzet) {
        this.id = id;
        this.naam = naam;
        this.omzet = omzet;
    }

    @Override
    public String toString() {
        return id + " : " + naam + " -> " + omzet;
    }
}
