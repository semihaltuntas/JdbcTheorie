package be.vdab.taken.taak6AlleSoorten;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        SoortenRepository repository = new SoortenRepository();
        try {
            repository.findAll().forEach(System.out::println);

        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
