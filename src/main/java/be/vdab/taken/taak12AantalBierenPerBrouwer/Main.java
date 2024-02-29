package be.vdab.taken.taak12AantalBierenPerBrouwer;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        BrouwerRepository repository = new BrouwerRepository();

        try {
            repository.findBrouwerNamenEnAantalBieren().forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
