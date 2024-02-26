package be.vdab.taken.taak7VanTot;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        BrouwersRepository repository = new BrouwersRepository();

        System.out.println("Type max.omzet: ");
        Scanner scanner = new Scanner(System.in);
        int maxOmzet = scanner.nextInt();
        System.out.println("Type min.omzet: ");
        Scanner scanner1 = new Scanner(System.in);
        int minOmzet = scanner1.nextInt();

        try {
            repository.findByOmzetTussen(maxOmzet, minOmzet)
                    .forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
