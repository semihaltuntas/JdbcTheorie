package be.vdab.taken.taak4SoortNamen;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        SoortRepository repository = new SoortRepository();

        try {
            repository.SoortAllNamen()
                    .forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
