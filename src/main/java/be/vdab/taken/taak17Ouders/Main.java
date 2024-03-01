package be.vdab.taken.taak17Ouders;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        PersoonRepository repository = new PersoonRepository();

        try {
            repository.findMetOuders().forEach(persoon -> {
                String toonInfo = persoon.voornaam() + " " +
                        persoon.papaVoornaam() + " " + persoon.mamaVoornaam();
                System.out.println(toonInfo);
            });
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
