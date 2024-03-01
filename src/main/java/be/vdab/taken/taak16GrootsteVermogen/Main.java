package be.vdab.taken.taak16GrootsteVermogen;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        PersonenRepository repository = new PersonenRepository();

        try {
            repository.grootsteVermogen().forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
