package be.vdab.taken.taak18PapaEnMama;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PersoonRepository repository = new PersoonRepository();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Id: ");
        long id = scanner.nextLong();

        try {
            repository.findByIdMetPapaEnMama(id).ifPresentOrElse(
                    persoon -> toon(persoon), () -> System.out.println("niet gevonden"));
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }

    }

    private static void toon(PersoonOptionelePapaEnMama persoon) {
        System.out.println("KindNaam :"+ persoon.voornaam());
        persoon.voornaamPapa().ifPresent(System.out::println);
        persoon.voornaamMama().ifPresent(System.out::println);

    }
}
