package be.vdab.theorie;

import java.sql.SQLException;
import java.util.Scanner;

public class Main2PreparedStatement extends AbstractRepository {

    public static void main(String[] args) {
        var repository = new Plant2Repository();

        System.out.println("Schrijf een naam van de planten :");
        Scanner scanner = new Scanner(System.in);
        String naam = scanner.nextLine();
        try {
            System.out.println(repository.verhoogPrijzenMet10Procent(naam));
            System.out.println(" planten aangepast.");

        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }

        System.out.println("------CallableStatement------");

        System.out.println(" Type een woord: ");
        Scanner scanner1 = new Scanner(System.in);
        String woord = scanner1.nextLine();

        try {
            repository.findNamenByWoord(woord).forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
