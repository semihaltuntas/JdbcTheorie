package be.vdab.theorie;

import java.sql.SQLException;
import java.util.Scanner;

public class Main3ResultSet extends AbstractRepository {
    public static void main(String[] args) throws SQLException {
        Leverancier3Repository leverancierRepository = new Leverancier3Repository();
        System.out.println("-----Find All Names with ID's------");
        try {
            leverancierRepository.findAllNamen().forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        System.out.println("----------------");
        try {
            System.out.println("Aantal leveranciers");
            System.out.println(leverancierRepository.findAantal());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("--------Find All elements----------");

        leverancierRepository.findAll().forEach(System.out::println);

        System.out.println("------Parameters in SQL statements---------");

        System.out.println("Find By Woonplaats: ");
        Scanner scanner = new Scanner(System.in);
        String woonplaats = scanner.nextLine();

        try {
            leverancierRepository.findByWoonPlaats(woonplaats)
                    .forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
