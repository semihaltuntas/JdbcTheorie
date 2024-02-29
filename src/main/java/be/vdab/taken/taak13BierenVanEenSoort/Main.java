package be.vdab.taken.taak13BierenVanEenSoort;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        BierenRepository repository = new BierenRepository();

        System.out.println("Type een soort : ");
        Scanner scanner = new Scanner(System.in);
        String soort = scanner.nextLine();

        try {
            List<String> namen = repository.findBySoortID(soort);
            if (namen.isEmpty()) {
                System.out.println("Geen bieren gevonden");
            } else {
                namen.forEach(System.out::println);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
