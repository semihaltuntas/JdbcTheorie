package be.vdab.taken.taak8ID;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        BrouwersRepository repository = new BrouwersRepository();

        System.out.println("Type een id:");
        Scanner scanner = new Scanner(System.in);
        long id = scanner.nextLong();

        try {
            repository.findByID(id)
                    .ifPresentOrElse(System.out::println,
                            () -> System.out.println("Niet Gevonden...!"));
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
