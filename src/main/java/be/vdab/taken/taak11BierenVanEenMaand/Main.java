package be.vdab.taken.taak11BierenVanEenMaand;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        BierenRepository repository = new BierenRepository();
        System.out.println("Type een maann tussen 1-12 Aub: ");
        Scanner scanner = new Scanner(System.in);
        int month = scanner.nextInt();
        while (month < 1 || month > 12) {
            System.out.println("Verkeerd Maand!");
            month = scanner.nextInt();
        }

        try {
            repository.findByMaandNummerBieren(month).forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }

    }
}
