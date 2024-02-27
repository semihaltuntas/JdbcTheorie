package be.vdab.taken.taak10Failliet;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        BierenRepository repository = new BierenRepository();

        try {
            repository.faillietBrouwer1();
            System.out.println("Transectie is gewerkt");
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
